package com.qualitydata.common.base.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author lihai
 * Create Date: 2019-10-21
 */
@Api(value = "基础模块", description = "基础模块的接口信息")
@Controller
@RequestMapping("/api/v1")
public class BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @ApiOperation(value = "获取登录用户", notes = "获取当前登录的用户信息")
    @PreAuthorize("hasAuthority('QUALITY_DATA_USER')")
    @GetMapping("/principal")
    @ResponseBody
    public Object getPrincipal(Principal principal){
        LOGGER.info("当前登录的用户：{}", principal);
        return principal;
    }

}
