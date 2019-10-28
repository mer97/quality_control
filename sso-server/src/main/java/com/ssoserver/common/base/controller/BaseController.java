package com.ssoserver.common.base.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 * @author lihai
 * Create Date: 2019-10-21
 */
@Controller
@RequestMapping("/api/v1")
public class BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @Value("${app.web.login.url}")
    private String webLoginUrl;

    /**
     * 获取当前登录用户。
    */
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/principal")
    @ResponseBody
    public Principal getUserRidAuthority(Principal principal){
        LOGGER.info("当前登录的用户信息：{}", principal);
        return principal;
    }

    @GetMapping("/login")
    public void toWebLogin(HttpServletResponse response) throws IOException {
        response.sendRedirect(webLoginUrl);
    }

}
