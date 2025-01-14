package com.catering.service.helper;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public final class CurrentUser {

    private CurrentUser() {}

    /*Get the username from the authentication object*/
    public static final String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return username;
    }

    /*Get the user ip address from the http servlet request*/
    public static final String getUserIPAddress() {
        HttpServletRequest request = (
                (ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes())
                .getRequest();

        String ipAddress = Optional.ofNullable(request.getHeader("X-FORWARDED-FOR"))
                .orElse(request.getRemoteAddr());

        if (ipAddress.equals("0:0:0:0:0:0:0:1")) {
            ipAddress = "127.0.0.1";
        }
        return ipAddress;
    }
}
