package com.jd.socialmanager.controller;

import com.jd.socialmanager.util.AjaxResult;
import com.jd.socialmanager.util.Const;
import com.jd.socialmanager.util.Data;
import com.jd.socialmanager.util.PageBean;
import com.jd.socialmanager.util.UidGenerateUtil;
import com.jd.socialmanager.entity.GuestDO;
import com.jd.socialmanager.entity.HostDO;
import com.jd.socialmanager.service.IGuestService;
import com.jd.socialmanager.service.IHostService;
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
@RequestMapping("/guest")
public class GuestController {
    @Autowired
    private IGuestService guestService;
    @Autowired
    private IHostService hostService;

    /**
     * 跳转访客列表页面
     */
    @GetMapping("/guestList")
    public String guestList() {
        return "/guest/guestList";
    }


    /**
     * 异步加载访客列表
     */
    @RequestMapping("/getGuestList")
    @ResponseBody
    public Object getGuestList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                               @RequestParam(value = "guestName", defaultValue = "") String guestName,
                               String from, HttpSession session) {
        // 判断权限
        HostDO host = (HostDO) session.getAttribute(Const.HOST);
        GuestDO selectGuest = new GuestDO();
        if (!StringUtils.isEmpty(host)) {
            selectGuest.setHostId(host.getHostId());
        }
        selectGuest.setGuestName(guestName);
        PageBean<GuestDO> pageBean = new PageBean<>(page, rows);
        List<GuestDO> guests = guestService.queryAll(selectGuest, pageBean.getStartIndex(), rows);
        pageBean.setDatas(guests);
        pageBean.setTotalsize(guests.size());
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
     * 添加访客信息
     */
    @RequestMapping("/addGuest")
    @ResponseBody
    public AjaxResult addGuest(GuestDO guest, @RequestParam(value = "hostUID", defaultValue = "") String hostUID) {
        AjaxResult ajaxResult = new AjaxResult();
        HostDO host = new HostDO();
        host.setUid(hostUID);
        List<HostDO> hosts = hostService.queryAll(host, 0, 1);
        if (hosts.isEmpty()) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("业主不存在，添加访客失败");
            return ajaxResult;
        }
        guest.setHostId(hosts.get(0).getHostId());
        guest.setGuestUID(UidGenerateUtil.generateUid(guest.getHostId()));
        GuestDO guestResult = guestService.insert(guest);
        if (guestResult != null) {
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("添加访客成功");
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("添加访客失败");
        }
        return ajaxResult;
    }

    /**
     * 修改访客信息
     */
    @PostMapping("/editGuest")
    @ResponseBody
    public AjaxResult editGuest(GuestDO guest) {
        AjaxResult ajaxResult = new AjaxResult();
        GuestDO guestResult = guestService.update(guest);
        if (guestResult != null) {
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("修改成功");
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("修改失败");
        }
        return ajaxResult;
    }

    /**
     * 删除访客
     */
    @PostMapping("/deleteGuest")
    @ResponseBody
    public AjaxResult deleteGuest(Data data) {
        AjaxResult ajaxResult = new AjaxResult();
        List<Integer> ids = data.getIds();
        List<Integer> fails = new ArrayList<>();
        for (int id : ids) {
            boolean isDelete = guestService.deleteById(id);
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
