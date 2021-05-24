package com.jd.socialmanager.controller;

import com.jd.socialmanager.util.AjaxResult;
import com.jd.socialmanager.util.Const;
import com.jd.socialmanager.util.CpachaUtil;
import com.jd.socialmanager.entity.Admin;
import com.jd.socialmanager.entity.HostDO;
import com.jd.socialmanager.service.IHostService;
import com.jd.socialmanager.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private IAdminService adminService;
    @Autowired
    private IHostService hostService;

    /**
     * 跳转登录界面
     */
    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    /**
     * 登录表单提交 校验
     */
    @PostMapping("/login")
    @ResponseBody
    public AjaxResult submitLogin(String username, String password, String code, String type,
                                  HttpSession session) {
        AjaxResult ajaxResult = new AjaxResult();
        if (StringUtils.isEmpty(username)) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("请填写用户名");
            return ajaxResult;
        }
        if (StringUtils.isEmpty(password)) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("请填写密码");
            return ajaxResult;
        }
        if (StringUtils.isEmpty(code)) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("请填验证码");
            return ajaxResult;
        }
        if (StringUtils.isEmpty(session.getAttribute(Const.CODE))) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("会话时间过长，请刷新");
            return ajaxResult;
        } else {
            if (!code.equalsIgnoreCase((String) session.getAttribute(Const.CODE))) {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("验证码错误");
                return ajaxResult;
            }
        }
        //数据库校验
        switch (type) {
            //管理员
            case "1": {
                Admin admin = new Admin();
                admin.setPassword(password);
                admin.setUsername(username);
                Admin ad = adminService.findAdmin(admin);
                if (StringUtils.isEmpty(ad)) {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("用户名或密码错误");
                    return ajaxResult;
                }
                ajaxResult.setSuccess(true);
                session.setAttribute(Const.ADMIN, ad);
                session.setAttribute(Const.USERTYPE, "1");
                break;
            }
            case "2": {
                HostDO host = hostService.findHost(username, password);
                if (StringUtils.isEmpty(host)) {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("用户名或密码错误");
                    return ajaxResult;
                }
                ajaxResult.setSuccess(true);
                session.setAttribute(Const.HOST, host);
                session.setAttribute(Const.USERTYPE, "2");
                break;
            }
        }
        return ajaxResult;
    }

    /**
     * 显示 验证码
     */
    @GetMapping("/checkCode")
    public void generateCpacha(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value = "vl", defaultValue = "4", required = false) Integer vl,
                               @RequestParam(value = "w", defaultValue = "110", required = false) Integer w,
                               @RequestParam(value = "h", defaultValue = "39", required = false) Integer h) {
        CpachaUtil cpachaUtil = new CpachaUtil(vl, w, h);
        String generatorVCode = cpachaUtil.generatorVCode();
        request.getSession().setAttribute(Const.CODE, generatorVCode);
        BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
        try {
            ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转后台主页
     */
    @GetMapping("/index")
    public String index() {
        return "/system/index";
    }


    /**
     * 登出
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "/login";
    }


    @GetMapping("/personalView")
    public String personalView() {
        return "/system/personalView";
    }


    /**
     * 修改密码
     */
    @PostMapping("/editPassword")
    @ResponseBody
    public AjaxResult editPassword(String password, String newPassword, HttpSession session) {
        AjaxResult ajaxResult = new AjaxResult();
        String usertype = (String) session.getAttribute(Const.USERTYPE);
        if ("1".equals(usertype)) {
            Admin admin = (Admin) session.getAttribute(Const.ADMIN);
            if (!password.equals(admin.getPassword())) {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("原密码错误");
                return ajaxResult;
            } else if (password.equals(newPassword)) {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("两次密码一致");
                return ajaxResult;
            }
            admin.setPassword(newPassword);
            Admin resultAdmin = adminService.changePassword(admin);
            if (resultAdmin != null) {
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("修改成功,请重新登录");
            } else {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("修改失败");
            }
        }
        if ("2".equals(usertype)) {
            HostDO host = (HostDO) session.getAttribute(Const.HOST);
            if (!password.equals(host.getPassword())) {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("原密码错误");
                return ajaxResult;
            } else if (password.equals(newPassword)) {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("两次密码一致");
                return ajaxResult;
            }
            host.setPassword(newPassword);
            HostDO hostResult = hostService.update(host);
            if (hostResult != null) {
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("修改成功,请重新登录");
            } else {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("修改失败");
            }
        }
        return ajaxResult;
    }

}
