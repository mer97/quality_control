package com.qualitymanage.setting_test.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author lihai
 * Create Date: 2019-10-23
 */
@Api(value = "KPI配置模块", description = "基础模块的接口信息")
@RestController
@RequestMapping("/api/v1/kpi_setting")
public class KpiSettingRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(KpiSettingRestController.class);

    @ApiOperation(value = "修改kpi基准值", notes = "根据kpi的id修改某个kpi基准值")
    @PreAuthorize("hasAuthority('QUALITY_MANAGE_KPI_REFERENCE_VALUE_UPDATE')")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "id", value = "kpi的id", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "reference_value", value = "新的kpi基准值", required = true)
    })
    @PatchMapping("/reference_value")
    public String referenceValueById(@RequestBody Map map){
        // TODO 调用业务逻辑代码

        LOGGER.info("new kpiReferenceValue：{}", map);
        return "SUCCESS";
    }

}
