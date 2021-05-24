package com.jd.socialmanager.controller;

import com.jd.socialmanager.entity.PoliticalDO;
import com.jd.socialmanager.service.IPoliticalService;
import com.jd.socialmanager.util.AjaxResult;
import com.jd.socialmanager.util.Data;
import com.jd.socialmanager.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/political")
public class PoliticalController {
    @Autowired
    private IPoliticalService politicalService;

    /**
     * 跳转政治面貌列表页面
     */
    @GetMapping("/politicalList")
    public String politicalList() {
        return "/political/politicalList";
    }


    /**
     * 异步加载政治面貌列表
     */
    @RequestMapping("/getPoliticalList")
    @ResponseBody
    public Object getPoliticalList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                               @RequestParam(value = "politicalType", defaultValue = "") String politicalType,
                               String from) {
        PoliticalDO selectPolitical = new PoliticalDO();
        selectPolitical.setPoliticalType(politicalType);
        PageBean<PoliticalDO> pageBean = new PageBean<>(page, rows);
        List<PoliticalDO> politicals = politicalService.queryAll(selectPolitical, pageBean.getStartIndex(), rows);
        pageBean.setDatas(politicals);
        pageBean.setTotalsize(politicals.size());
        if (!StringUtils.isEmpty(from) && "combox".equals(from)) {
            return pageBean.getDatas();
        } else {
            Map<String, Object> result = new HashMap();
            result.put("total", pageBean.getTotalsize());
            result.put("rows", pageBean.getDatas());
            return result;
        }
    }

    /**
     * 添加政治面貌信息
     */
    @RequestMapping("/addPolitical")
    @ResponseBody
    public AjaxResult addPolitical(PoliticalDO political) {
        AjaxResult ajaxResult = new AjaxResult();
        PoliticalDO politicalResult = politicalService.insert(political);
        if (politicalResult != null) {
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("添加成功");
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("添加失败");
        }
        return ajaxResult;
    }

    /**
     * 修改政治面貌信息
     */
    @PostMapping("/editPolitical")
    @ResponseBody
    public AjaxResult editPolitical(PoliticalDO political) {
        AjaxResult ajaxResult = new AjaxResult();
        PoliticalDO politicalResult = politicalService.update(political);
        if (politicalResult != null) {
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("修改成功");
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("修改失败");
        }
        return ajaxResult;
    }

    /**
     * 删除政治面貌信息
     */
    @PostMapping("/deletePolitical")
    @ResponseBody
    public AjaxResult deletePolitical(Data data) {
        AjaxResult ajaxResult = new AjaxResult();
        List<Integer> ids = data.getIds();
        List<Integer> fails = new ArrayList<>();
        for (int id : ids) {
            boolean isDelete = politicalService.deleteById(id);
            if (!isDelete) {
                fails.add(id);
            }
        }
        if (fails.size() == 0) {
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("删除成功");

        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("删除失败");
        }
        return ajaxResult;
    }
}
