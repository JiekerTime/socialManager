package com.jd.socialmanager.controller;

import com.jd.socialmanager.entity.GuestDO;
import com.jd.socialmanager.entity.HostDO;
import com.jd.socialmanager.entity.PetDO;
import com.jd.socialmanager.service.IHostService;
import com.jd.socialmanager.service.IPetService;
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
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private IPetService petService;
    @Autowired
    private IHostService hostService;

    /**
     * 跳转宠物列表页面
     */
    @GetMapping("/petList")
    public String petList() {
        return "/pet/petList";
    }


    /**
     * 异步加载宠物列表
     */
    @RequestMapping("/getPetList")
    @ResponseBody
    public Object getPetList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                             @RequestParam(value = "petName", defaultValue = "") String petName,
                             @RequestParam(value = "petType", defaultValue = "") String petType,
                             @RequestParam(value = "hostName", defaultValue = "") String hostName,
                             String from, HttpSession session) {
        // 判断权限
        HostDO host = (HostDO) session.getAttribute(Const.HOST);
        PetDO selectPet = new PetDO();
        boolean flag = false;
        PageBean<GuestDO> pageBean = new PageBean<>(page, rows);
        if (!StringUtils.isEmpty(host)) {
            selectPet.setHostId(host.getHostId());
        } else {
            HostDO selectHost = new HostDO();
            if (!"".equals(hostName)) {
                List<HostDO> hosts = hostService.queryAll(selectHost, 0, 1);
                selectHost.setHostName(hostName);
                if (hosts.isEmpty()) {
                    flag = true;
                    pageBean.setDatas(hosts);
                    pageBean.setTotalsize(hosts.size());
                } else {
                    selectPet.setHostId(hosts.get(0).getHostId());
                }
            }
        }
        if (!flag) {
            selectPet.setPetName(petName);
            selectPet.setPetType(petType);
            List<PetDO> pets = petService.queryAll(selectPet, pageBean.getStartIndex(), rows);
            pageBean.setDatas(pets);
            pageBean.setTotalsize(pets.size());
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
     * 添加宠物信息
     */
    @RequestMapping("/addPet")
    @ResponseBody
    public AjaxResult addPet(PetDO pet, @RequestParam(value = "uid", defaultValue = "") String uid) {
        AjaxResult ajaxResult = new AjaxResult();
        HostDO host = new HostDO();
        host.setUid(uid);
        List<HostDO> hosts = hostService.queryAll(host, 0, 1);
        if (hosts.isEmpty()) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("业主不存在，添加宠物信息失败");
            return ajaxResult;
        }
        host = hosts.get(0);
        if (host.getPetId() != null) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage(host.getHostName() + "超出宠物上线，添加宠物信息失败");
            return ajaxResult;
        }
        pet.setHostId(host.getHostId());
        PetDO petResult = petService.insert(pet);
        host.setPetId(pet.getPetId());
        hostService.update(host);
        if (petResult != null) {
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("添加宠物信息成功");
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("添加宠物信息失败");
        }
        return ajaxResult;
    }

    /**
     * 修改宠物信息
     */
    @PostMapping("/editPet")
    @ResponseBody
    public AjaxResult editPet(PetDO pet, @RequestParam(value = "uid", defaultValue = "") String uid) {
        AjaxResult ajaxResult = new AjaxResult();
        HostDO host = new HostDO();
        host.setUid(uid);
        boolean flag = false;
        List<HostDO> hosts = hostService.queryAll(host, 0, 1);
        if (hosts.isEmpty()) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("业主不存在，修改宠物信息失败");
            return ajaxResult;
        }
        host = hosts.get(0);
        PetDO petSelect = petService.queryById(pet.getPetId());
        HostDO temp = hostService.queryById(petSelect.getHostId());
        if (!host.getUid().equals(temp.getUid())) {
            flag = true;
        }
        if (host.getPetId() != null) {
            if (host.getPetId().equals(pet.getPetId())) {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage(host.getHostName() + "不能重复修改身份证号码");
                return ajaxResult;
            }
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage(host.getHostName() + "超出宠物上线，添加宠物信息失败");
            return ajaxResult;
        }
        if (flag) {
            temp.setPetId(0);
            hostService.update(temp);
        }
        pet.setHostId(host.getHostId());
        PetDO petResult = petService.update(pet);
        host.setPetId(pet.getPetId());
        hostService.update(host);
        if (petResult != null) {
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("修改成功");
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("修改失败");
        }
        return ajaxResult;
    }

    /**
     * 删除宠物
     */
    @PostMapping("/deletePet")
    @ResponseBody
    public AjaxResult deletePet(Data data) {
        AjaxResult ajaxResult = new AjaxResult();
        List<Integer> ids = data.getIds();
        List<Integer> fails = new ArrayList<>();
        for (int id : ids) {
            PetDO pet = petService.queryById(id);
            HostDO host = hostService.queryById(pet.getHostId());
            host.setPetId(0);
            hostService.update(host);
            boolean isDelete = petService.deleteById(id);
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
