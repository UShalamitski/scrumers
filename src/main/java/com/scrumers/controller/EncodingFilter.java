package com.scrumers.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncodingFilter implements Filter {

    public static String DEF_ENCODING = "UTF-8";

    public static String PARAM_NAME = "encoding";

    private String encoding = DEF_ENCODING;

    @Override
    public void init(final FilterConfig cfg) throws ServletException {
        String str = cfg.getInitParameter(PARAM_NAME);
        if (str != null && !str.isEmpty()) {
            encoding = str;
        }
    }

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res,
            final FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        httpServletRequest.setCharacterEncoding(encoding);
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }

}
