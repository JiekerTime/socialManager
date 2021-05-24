package com.jd.socialmanager.controller;

import com.jd.socialmanager.entity.BaseDO;
import com.jd.socialmanager.service.IBaseService;
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
@RequestMapping("/base")
public class BaseController {
    @Autowired
    private IBaseService baseService;

    /**
     * 跳转基础设施列表页面
     */
    @GetMapping("/baseList")
    public String baseList() {
        return "/base/baseList";
    }


    /**
     * 异步加载基础设施列表
     */
    @RequestMapping("/getBaseList")
    @ResponseBody
    public Object getBaseList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                   @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                                   @RequestParam(value = "baseName", defaultValue = "") String baseName,
                                   @RequestParam(value = "blockId", defaultValue = "0") String blockId,
                                   String from) {
        BaseDO selectBase = new BaseDO();
        selectBase.setBaseName(baseName);
        if (!"0".equals(blockId)) {
            selectBase.setBlockId(Integer.valueOf(blockId));
        }
        PageBean<BaseDO> pageBean = new PageBean<>(page, rows);
        List<BaseDO> bases = baseService.queryAll(selectBase, pageBean.getStartIndex(), rows);
        pageBean.setDatas(bases);
        pageBean.setTotalsize(bases.size());
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
     * 添加基础设施信息
     */
    @RequestMapping("/addBase")
    @ResponseBody
    public AjaxResult addBase(BaseDO base) {
        AjaxResult ajaxResult = new AjaxResult();
        BaseDO baseResult = baseService.insert(base);
        if (baseResult != null) {
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("添加成功");
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("添加失败");
        }
        return ajaxResult;
    }

    /**
     * 修改基础设施信息
     */
    @PostMapping("/editBase")
    @ResponseBody
    public AjaxResult editBase(BaseDO base) {
        AjaxResult ajaxResult = new AjaxResult();
        BaseDO baseResult = baseService.update(base);
        if (baseResult != null) {
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("修改成功");
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("修改失败");
        }
        return ajaxResult;
    }

    /**
     * 删除基础设施信息
     */
    @PostMapping("/deleteBase")
    @ResponseBody
    public AjaxResult deleteBase(Data data) {
        AjaxResult ajaxResult = new AjaxResult();
        List<Integer> ids = data.getIds();
        List<Integer> fails = new ArrayList<>();
        for (int id : ids) {
            boolean isDelete = baseService.deleteById(id);
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
