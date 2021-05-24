package com.jd.socialmanager.controller;

import com.jd.socialmanager.entity.SecurityDO;
import com.jd.socialmanager.service.ISecurityService;
import com.jd.socialmanager.util.AjaxResult;
import com.jd.socialmanager.util.Data;
import com.jd.socialmanager.util.PageBean;
import com.jd.socialmanager.util.UidGenerateUtil;
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
@RequestMapping("/security")
public class SecurityController {
    @Autowired
    private ISecurityService securityService;

    /**
     * 跳转安保人员列表页面
     */
    @GetMapping("/securityList")
    public String securityList() {
        return "/security/securityList";
    }


    /**
     * 异步加载安保人员列表
     */
    @RequestMapping("/getSecurityList")
    @ResponseBody
    public Object getSecurityList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                   @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                                   @RequestParam(value = "securityName", defaultValue = "") String securityName,
                                   @RequestParam(value = "blockId", defaultValue = "0") String blockId,
                                   String from) {
        SecurityDO selectSecurity = new SecurityDO();
        selectSecurity.setSecurityName(securityName);
        if (!"0".equals(blockId)) {
            selectSecurity.setBlockId(Integer.valueOf(blockId));
        }
        PageBean<SecurityDO> pageBean = new PageBean<>(page, rows);
        List<SecurityDO> securitys = securityService.queryAll(selectSecurity, pageBean.getStartIndex(), rows);
        pageBean.setDatas(securitys);
        pageBean.setTotalsize(securitys.size());
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
     * 添加安保人员信息
     */
    @RequestMapping("/addSecurity")
    @ResponseBody
    public AjaxResult addSecurity(SecurityDO security) {
        AjaxResult ajaxResult = new AjaxResult();
        security.setUid(UidGenerateUtil.generateUid(security.getBlockId()));
        SecurityDO securityResult = securityService.insert(security);
        if (securityResult != null) {
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("添加安保人员成功");
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("添加安保人员失败");
        }
        return ajaxResult;
    }

    /**
     * 修改安保人员信息
     */
    @PostMapping("/editSecurity")
    @ResponseBody
    public AjaxResult editSecurity(SecurityDO security) {
        AjaxResult ajaxResult = new AjaxResult();
        SecurityDO securityResult = securityService.update(security);
        if (securityResult != null) {
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("修改成功");
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("修改失败");
        }
        return ajaxResult;
    }

    /**
     * 删除安保人员信息
     */
    @PostMapping("/deleteSecurity")
    @ResponseBody
    public AjaxResult deleteSecurity(Data data) {
        AjaxResult ajaxResult = new AjaxResult();
        List<Integer> ids = data.getIds();
        List<Integer> fails = new ArrayList<>();
        for (int id : ids) {
            boolean isDelete = securityService.deleteById(id);
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
