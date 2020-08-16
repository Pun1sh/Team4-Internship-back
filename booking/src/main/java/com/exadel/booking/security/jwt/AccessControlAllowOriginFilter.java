package com.exadel.booking.security.jwt;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessControlAllowOriginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Access-Control-Allow-Headers", "Content-Type");
        res.addHeader("Access-Control-Allow-Headers", "Authorization");
        res.addHeader(" Access-Control-Allow-Methods", "POST");
        res.addHeader(" Access-Control-Allow-Methods", "GET");
        res.addHeader(" Access-Control-Allow-Methods", "PUT");
        res.addHeader(" Access-Control-Allow-Methods", "DELETE");
        res.addHeader(" Access-Control-Allow-Methods", "OPTIONS");
        chain.doFilter(req, res);
    }
}