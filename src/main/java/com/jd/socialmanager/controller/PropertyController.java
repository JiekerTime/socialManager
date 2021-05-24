package com.jd.socialmanager.controller;

import com.jd.socialmanager.entity.PropertyDO;
import com.jd.socialmanager.service.IPropertyService;
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
@RequestMapping("/property")
public class PropertyController {
    @Autowired
    private IPropertyService propertyService;

    /**
     * 跳转物业人员列表页面
     */
    @GetMapping("/propertyList")
    public String propertyList() {
        return "/property/propertyList";
    }


    /**
     * 异步加载物业人员列表
     */
    @RequestMapping("/getPropertyList")
    @ResponseBody
    public Object getPropertyList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                   @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                                   @RequestParam(value = "propertyName", defaultValue = "") String propertyName,
                                   @RequestParam(value = "blockId", defaultValue = "0") String blockId,
                                   String from) {
        PropertyDO selectProperty = new PropertyDO();
        selectProperty.setPropertyName(propertyName);
        if (!"0".equals(blockId)) {
            selectProperty.setBlockId(Integer.valueOf(blockId));
        }
        PageBean<PropertyDO> pageBean = new PageBean<>(page, rows);
        List<PropertyDO> propertys = propertyService.queryAll(selectProperty, pageBean.getStartIndex(), rows);
        pageBean.setDatas(propertys);
        pageBean.setTotalsize(propertys.size());
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
     * 添加物业人员信息
     */
    @RequestMapping("/addProperty")
    @ResponseBody
    public AjaxResult addProperty(PropertyDO property) {
        AjaxResult ajaxResult = new AjaxResult();
        property.setUid(UidGenerateUtil.generateUid(property.getBlockId()));
        PropertyDO propertyResult = propertyService.insert(property);
        if (propertyResult != null) {
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("添加安保人员成功");
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("添加安保人员失败");
        }
        return ajaxResult;
    }

    /**
     * 修改物业人员信息
     */
    @PostMapping("/editProperty")
    @ResponseBody
    public AjaxResult editProperty(PropertyDO property) {
        AjaxResult ajaxResult = new AjaxResult();
        PropertyDO propertyResult = propertyService.update(property);
        if (propertyResult != null) {
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("修改成功");
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("修改失败");
        }
        return ajaxResult;
    }

    /**
     * 删除物业人员信息
     */
    @PostMapping("/deleteProperty")
    @ResponseBody
    public AjaxResult deleteProperty(Data data) {
        AjaxResult ajaxResult = new AjaxResult();
        List<Integer> ids = data.getIds();
        List<Integer> fails = new ArrayList<>();
        for (int id : ids) {
            boolean isDelete = propertyService.deleteById(id);
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
