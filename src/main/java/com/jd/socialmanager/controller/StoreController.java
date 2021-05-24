package com.jd.socialmanager.controller;

import com.jd.socialmanager.entity.HostDO;
import com.jd.socialmanager.entity.StoreDO;
import com.jd.socialmanager.service.IHostService;
import com.jd.socialmanager.service.IStoreService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private IStoreService storeService;
    @Autowired
    private IHostService hostService;

    /**
     * 跳转底商列表页面
     */
    @GetMapping("/storeList")
    public String storeList() {
        return "/store/storeList";
    }


    /**
     * 异步加载底商列表
     */
    @RequestMapping("/getStoreList")
    @ResponseBody
    public Object getStoreList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                               @RequestParam(value = "storeName", defaultValue = "") String storeName,
                               @RequestParam(value = "storeType", defaultValue = "") String storeType,
                               @RequestParam(value = "hostName", defaultValue = "") String hostName,
                               @RequestParam(value = "blockId", defaultValue = "0") String blockId,
                               String from, HttpSession session) {
        HostDO host = (HostDO) session.getAttribute(Const.HOST);
        StoreDO selectStore = new StoreDO();
        selectStore.setStoreName(storeName);
        selectStore.setStoreType(storeType);
        HostDO selectHost = new HostDO();
        selectHost.setHostName(hostName);
        if (!"0".equals(blockId)) {
            selectHost.setBlockId(Integer.valueOf(blockId));
        }
        PageBean<StoreDO> pageBean = new PageBean<>(page, rows);
        boolean flag = false;
        if (!StringUtils.isEmpty(host)) {
            selectStore.setHostId(host.getHostId());
        } else {
            if (!"".equals(hostName)) {
                List<HostDO> hosts = hostService.queryAll(selectHost, 0, 1);
                if (!hosts.isEmpty()) {
                    selectStore.setHostId(hosts.get(0).getHostId());
                } else {
                    flag = true;
                    pageBean.setDatas(hosts);
                    pageBean.setTotalsize(hosts.size());
                }
            }
        }
        if (!flag) {
            List<StoreDO> stores = storeService.queryAll(selectStore, pageBean.getStartIndex(), rows);
            pageBean.setDatas(stores);
            pageBean.setTotalsize(stores.size());
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
     * 添加底商信息
     */
    @RequestMapping("/addStore")
    @ResponseBody
    public AjaxResult addStore(StoreDO store, @RequestParam("uid") String uid) {
        AjaxResult ajaxResult = new AjaxResult();
        HostDO host = new HostDO();
        host.setUid(uid);
        List<HostDO> hosts = hostService.queryAll(host, 0, 1);
        if (hosts.isEmpty()) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("相关负责人不存在，请核实身份证号");
            return ajaxResult;
        }
        HostDO hostSelect = hosts.get(0);
        store.setHostId(hostSelect.getHostId());
        StoreDO storeResult = storeService.insert(store);
        if (storeResult != null) {
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("添加成功");
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("添加失败");
        }
        return ajaxResult;
    }

    /**
     * 修改底商信息
     */
    @PostMapping("/editStore")
    @ResponseBody
    public AjaxResult editStore(StoreDO store) {
        AjaxResult ajaxResult = new AjaxResult();
        StoreDO storeResult = storeService.update(store);
        if (storeResult != null) {
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("修改成功");
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("修改失败");
        }
        return ajaxResult;
    }

    /**
     * 删除底商信息
     */
    @PostMapping("/deleteStore")
    @ResponseBody
    public AjaxResult deleteStore(Data data) {
        AjaxResult ajaxResult = new AjaxResult();
        List<Integer> ids = data.getIds();
        List<Integer> fails = new ArrayList<>();
        for (int id : ids) {
            boolean isDelete = storeService.deleteById(id);
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
