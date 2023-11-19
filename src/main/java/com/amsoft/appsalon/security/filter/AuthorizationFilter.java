package com.amsoft.appsalon.security.filter;

import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

/**
 * AuthorizationFilter
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml", "*.jsp" })
public class AuthorizationFilter extends HttpFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            System.out.println("Pasamdo por filter");
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession ses = req.getSession(false);

            String reqURI = req.getRequestURI();
            if (reqURI.indexOf("/login.xhtml") >= 0
                    || reqURI.indexOf("/signup.xhtml") >= 0
                    || (ses != null && ses.getAttribute("username") != null)
                    || reqURI.indexOf("/public/") >= 0
                    || reqURI.contains("javax.faces.resource"))
                chain.doFilter(request, response);
            else
                res.sendRedirect(req.getContextPath() + "/views/auth/login.xhtml");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}