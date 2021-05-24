package com.jd.socialmanager.controller;

import com.jd.socialmanager.entity.HostDO;
import com.jd.socialmanager.entity.RentDO;
import com.jd.socialmanager.entity.StoreDO;
import com.jd.socialmanager.service.IHostService;
import com.jd.socialmanager.service.IRentService;
import com.jd.socialmanager.util.AjaxResult;
import com.jd.socialmanager.util.Const;
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

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rent")
public class RentController {
    @Autowired
    private IRentService rentService;
    @Autowired
    private IHostService hostService;

    /**
     * 跳转租户列表页面
     */
    @GetMapping("/rentList")
    public String rentList() {
        return "/rent/rentList";
    }


    /**
     * 异步加载租户列表
     */
    @RequestMapping("/getRentList")
    @ResponseBody
    public Object getRentList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                              @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                              @RequestParam(value = "rentName", defaultValue = "") String rentName,
                              @RequestParam(value = "hostName", defaultValue = "") String hostName,
                              String from, HttpSession session) {
        HostDO host = (HostDO) session.getAttribute(Const.HOST);
        RentDO selectRent = new RentDO();
        selectRent.setRentName(rentName);
        HostDO selectHost = new HostDO();
        selectHost.setHostName(hostName);
        PageBean<StoreDO> pageBean = new PageBean<>(page, rows);
        boolean flag = false;
        if (!StringUtils.isEmpty(host)) {
            selectRent.setHostId(host.getHostId());
        } else {
            if (!"".equals(hostName)) {
                List<HostDO> hosts = hostService.queryAll(selectHost, 0, 1);
                if (!hosts.isEmpty()) {
                    selectRent.setHostId(hosts.get(0).getHostId());
                } else {
                    flag = true;
                    pageBean.setDatas(hosts);
                    pageBean.setTotalsize(hosts.size());
                }
            }
        }
        if (!flag) {
            List<RentDO> rents = rentService.queryAll(selectRent, pageBean.getStartIndex(), rows);
            pageBean.setDatas(rents);
            pageBean.setTotalsize(rents.size());
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
     * 添加租户信息
     */
    @RequestMapping("/addRent")
    @ResponseBody
    public AjaxResult addRent(RentDO rent, @RequestParam("uid") String uid) {
        AjaxResult ajaxResult = new AjaxResult();
        HostDO host = new HostDO();
        host.setUid(uid);
        List<HostDO> hosts = hostService.queryAll(host, 0, 1);
        if (hosts.isEmpty()) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("房主不存在，请核实身份证号");
            return ajaxResult;
        }
        HostDO hostSelect = hosts.get(0);
        if (hostSelect.getRentId() != null) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("房主房间已租出，请核实身份证号");
            return ajaxResult;
        }
        rent.setHostId(hostSelect.getHostId());
        rent.setRentUid(UidGenerateUtil.generateUid(hostSelect.getBlockId()));
        RentDO rentResult = rentService.insert(rent);
        hostSelect.setRentId(rentResult.getRentId());
        HostDO hostResult = hostService.update(hostSelect);
        if (hostResult != null) {
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("添加成功");
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("添加失败");
        }
        return ajaxResult;
    }

    /**
     * 修改租户信息
     */
    @PostMapping("/editRent")
    @ResponseBody
    public AjaxResult editRent(RentDO rent) {
        AjaxResult ajaxResult = new AjaxResult();
        RentDO rentResult = rentService.update(rent);
        if (rentResult != null) {
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("修改成功");
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("修改失败");
        }
        return ajaxResult;
    }

    /**
     * 删除租户信息
     */
    @PostMapping("/deleteRent")
    @ResponseBody
    public AjaxResult deleteRent(Data data) {
        AjaxResult ajaxResult = new AjaxResult();
        List<Integer> ids = data.getIds();
        List<Integer> fails = new ArrayList<>();
        for (int id : ids) {
            RentDO rent = rentService.queryById(id);
            boolean isDelete = rentService.deleteById(id);
            HostDO host = hostService.queryById(rent.getHostId());
            host.setRentId(0);
            hostService.update(host);
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
