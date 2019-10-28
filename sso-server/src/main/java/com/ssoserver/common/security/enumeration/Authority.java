package com.ssoserver.common.security.enumeration;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lihai
 * Create Date: 2019-10-23
 */
public enum Authority {

    QUALITY_DATA_USER("质量数据-普通用户"),
    QUALITY_DATA_KPI_READ("质量数据-质量kpi-kpi查看"),

    QUALITY_MANAGE_USER("质量管理-普通用户"),
    QUALITY_MANAGE_KPI_REFERENCE_VALUE_UPDATE("质量管理-kpi基准值-kpi基准值修改");

    private String description;

    Authority(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static List<SimpleGrantedAuthority> getAuthorityAllToSimpleGrantedAuthoritys(){
        List<SimpleGrantedAuthority> simpleGrantedAuthoritys = new ArrayList<>();
        Arrays.asList(Authority.values()).forEach(authority ->
                simpleGrantedAuthoritys.add(new SimpleGrantedAuthority(authority.toString()))
        );
        return simpleGrantedAuthoritys;
    }
}
