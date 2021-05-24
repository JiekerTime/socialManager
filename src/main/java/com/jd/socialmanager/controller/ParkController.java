package com.jd.socialmanager.controller;

import com.jd.socialmanager.entity.BaseDO;
import com.jd.socialmanager.entity.HostDO;
import com.jd.socialmanager.entity.ParkDO;
import com.jd.socialmanager.service.IHostService;
import com.jd.socialmanager.service.IParkService;
import com.jd.socialmanager.util.AjaxResult;
import com.jd.socialmanager.util.Const;
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

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/park")
public class ParkController {
    @Autowired
    private IParkService parkService;
    @Autowired
    private IHostService hostService;

    /**
     * 跳转停车位信息列表页面
     */
    @GetMapping("/parkList")
    public String parkList() {
        return "/park/parkList";
    }


    /**
     * 异步加载停车位信息列表
     */
    @RequestMapping("/getParkList")
    @ResponseBody
    public Object getParkList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                              @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                              @RequestParam(value = "parkId", defaultValue = "0") String parkId,
                              @RequestParam(value = "blockId", defaultValue = "0") String blockId,
                              String from, HttpSession session) {

        HostDO host = (HostDO) session.getAttribute(Const.HOST);
        ParkDO selectPark = new ParkDO();
        if (host != null) {
            parkId = String.valueOf(host.getParkId());
        }
        PageBean<BaseDO> pageBean = new PageBean<>(page, rows);
        if (!"0".equals(blockId)) {
            selectPark.setBlockId(Integer.valueOf(blockId));
            List<ParkDO> parks = parkService.queryAll(selectPark, pageBean.getStartIndex(), rows);
            pageBean.setDatas(parks);
            pageBean.setTotalsize(parks.size());
        } else if ("0".equals(parkId)) {
            List<ParkDO> parks = parkService.queryAll(null, pageBean.getStartIndex(), rows);
            pageBean.setDatas(parks);
            pageBean.setTotalsize(parks.size());
        } else {
            ParkDO park = parkService.queryById(Integer.valueOf(parkId));
            pageBean.setDatas(Collections.singletonList(park));
            pageBean.setTotalsize(1);
        }
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
     * 添加停车位信息信息
     */
    @RequestMapping("/addPark")
    @ResponseBody
    public AjaxResult addPark(ParkDO park) {
        AjaxResult ajaxResult = new AjaxResult();
        ParkDO parkResult = parkService.insert(park);
        if (parkResult != null) {
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("添加成功");
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("添加失败");
        }
        return ajaxResult;
    }

    /**
     * 修改停车位信息信息
     */
    @PostMapping("/editPark")
    @ResponseBody
    public AjaxResult editPark(ParkDO park) {
        AjaxResult ajaxResult = new AjaxResult();
        ParkDO parkResult = parkService.update(park);
        if (parkResult != null) {
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("修改成功");
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("修改失败");
        }
        return ajaxResult;
    }

    /**
     * 删除停车位信息信息
     */
    @PostMapping("/deletePark")
    @ResponseBody
    public AjaxResult deletePark(Data data) {
        AjaxResult ajaxResult = new AjaxResult();
        List<Integer> ids = data.getIds();
        List<Integer> fails = new ArrayList<>();
        for (int id : ids) {
            boolean isDelete = parkService.deleteById(id);
            HostDO host = new HostDO();
            host.setParkId(id);
            List<HostDO> hosts = hostService.queryAll(host, 0, 10);
            for (HostDO temp : hosts) {
                temp.setParkId(0);
                hostService.update(temp);
            }
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
