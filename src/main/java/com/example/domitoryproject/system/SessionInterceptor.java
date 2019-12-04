package com.example.domitoryproject.system;

import com.example.domitoryproject.login.Login;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle (HttpServletRequest request,
                              HttpServletResponse response,
                              Object handler) throws Exception {
        HttpSession session = request.getSession(true);
        if(session.getAttribute("login") == null) {
            session.setAttribute("login", new Login());
        }

        return true;
    }
}
