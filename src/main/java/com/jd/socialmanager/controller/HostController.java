package com.jd.socialmanager.controller;

import com.jd.socialmanager.entity.ParkDO;
import com.jd.socialmanager.service.IHostService;
import com.jd.socialmanager.service.IParkService;
import com.jd.socialmanager.util.AjaxResult;
import com.jd.socialmanager.util.Const;
import com.jd.socialmanager.util.Data;
import com.jd.socialmanager.util.PageBean;
import com.jd.socialmanager.util.UidGenerateUtil;
import com.jd.socialmanager.entity.HostDO;
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
@RequestMapping("/host")
public class HostController {
    @Autowired
    private IHostService hostService;
    @Autowired
    private IParkService parkService;

    /**
     * 跳转用户列表页面
     */
    @GetMapping("/hostList")
    public String hostList() {
        return "/host/hostList";
    }


    /**
     * 异步加载业主列表
     */
    @RequestMapping("/getHostList")
    @ResponseBody
    public Object getHostList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                              @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                              @RequestParam(value = "hostName", defaultValue = "") String hostName,
                              @RequestParam(value = "blockId", defaultValue = "0") String blockId,
                              @RequestParam(value = "politicalId", defaultValue = "0") String politicalId,
                              String from, HttpSession session) {
        // 判断权限
        HostDO host = (HostDO) session.getAttribute(Const.HOST);
        HostDO selectHost = new HostDO();
        selectHost.setHostName(hostName);
        if (!"0".equals(blockId)) {
            selectHost.setBlockId(Integer.valueOf(blockId));
        }
        if (!"0".equals(politicalId)) {
            selectHost.setPoliticalId(Integer.valueOf(politicalId));
        }
        PageBean<HostDO> pageBean = new PageBean<>(page, rows);
        if (!StringUtils.isEmpty(host)) {
            selectHost = hostService.queryById(host.getHostId());
            pageBean.setDatas(Collections.singletonList(selectHost));
            pageBean.setTotalsize(1);
        } else {
            List<HostDO> hosts = hostService.queryAll(selectHost, pageBean.getStartIndex(), rows);
            pageBean.setDatas(hosts);
            pageBean.setTotalsize(hosts.size());
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
     * 添加业主信息
     */
    @RequestMapping("/addHost")
    @ResponseBody
    public AjaxResult addHost(HostDO host) {
        AjaxResult ajaxResult = new AjaxResult();
        if (host.getPetId() != null) {
            ParkDO park = parkService.queryById(host.getParkId());
            if (park == null) {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("停车位不存在，添加业主失败");
                return ajaxResult;
            }
        }
        host.setUid(UidGenerateUtil.generateUid(host.getBlockId()));
        HostDO hostResult = hostService.insert(host);
        if (hostResult != null) {
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("添加业主成功");
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("添加业主失败");
        }
        return ajaxResult;
    }

    /**
     * 修改业主信息
     */
    @PostMapping("/editHost")
    @ResponseBody
    public AjaxResult editHost(HostDO host) {
        AjaxResult ajaxResult = new AjaxResult();
        HostDO hostResult = hostService.update(host);
        if (hostResult != null) {
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("修改成功");
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("修改失败");
        }
        return ajaxResult;
    }

    /**
     * 删除业主
     */
    @PostMapping("/deleteHost")
    @ResponseBody
    public AjaxResult deleteHost(Data data) {
        AjaxResult ajaxResult = new AjaxResult();
        List<Integer> ids = data.getIds();
        for (int id : ids) {
            HostDO host = hostService.queryById(id);
            if (host.getRentId() != null) {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage(host.getHostName() + "无法删除,存在相关租客" + host.getRent().getRentName());
                return ajaxResult;
            }
        }
        List<Integer> fails = new ArrayList<>();
        for (int id : ids) {
            boolean isDelete = hostService.deleteById(id);
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
