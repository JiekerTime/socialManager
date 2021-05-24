package com.jd.socialmanager.interceptors;

import com.jd.socialmanager.util.Const;
import com.jd.socialmanager.entity.Admin;
import com.jd.socialmanager.entity.HostDO;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        Admin user = (Admin)request.getSession().getAttribute(Const.ADMIN);
        HostDO host = (HostDO)request.getSession().getAttribute(Const.HOST);
        if(!StringUtils.isEmpty(user) || !StringUtils.isEmpty(host)){
            return true;
        }
        response.sendRedirect(request.getContextPath() + "/system/login");
        return false;
    }

}
