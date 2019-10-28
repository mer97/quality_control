package com.qualitymanage.common.security.config;

import com.qualitymanage.common.enumeration.ErrorCode;
import com.qualitymanage.common.util.JsonUtil;
import com.qualitymanage.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

/**
 * @author lihai
 * Create Date: 2019-10-21
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${app.sso.login.url}")
    private String ssoLoginUrl;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.exceptionHandling()
                .authenticationEntryPoint(unauthorizedEntryPoint())
                .accessDeniedHandler(handleAccessDeniedForUser())
                .and()
             .headers()
                .frameOptions()
                .disable()
                .and()
             .authorizeRequests()
                .antMatchers(
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/v2/**",
                        "/swagger-resources/**"
                )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
             .csrf()
                .disable()
                .cors();

    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(unauthorizedEntryPoint());
    }

    /**
     * 自定义 未登入系统，直接请求资源 处理。
     * @return
     */
    private AuthenticationEntryPoint unauthorizedEntryPoint() {
        return (HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) -> {
            String requestedWithHeader = request.getHeader("X-Requested-With");
            if ("XMLHttpRequest".equals(requestedWithHeader)) {
                response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                ResultUtil result = ResultUtil.fail(ErrorCode.UNAUTHORIZED, ErrorCode.UNAUTHORIZED.getMessage());
                response.getOutputStream().write(JsonUtil.OBJECT_MAPPER.writeValueAsBytes(result));
            } else {
                response.sendRedirect(ssoLoginUrl);
            }
        };
    }

    /**
     * 自定义 无权请求的资源 AccessDeniedHandler来处理。
     * @return
     */
    private AccessDeniedHandler handleAccessDeniedForUser() {
        return (HttpServletRequest request,
                HttpServletResponse response,
                AccessDeniedException accessDeniedException) -> {
            String requestedWithHeader = request.getHeader("X-Requested-With");
            if ("XMLHttpRequest".equals(requestedWithHeader)) {
                response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                ResultUtil result = ResultUtil.fail(ErrorCode.FORBIDDEN, ErrorCode.FORBIDDEN.getMessage());
                response.getOutputStream().write(JsonUtil.OBJECT_MAPPER.writeValueAsBytes(result));
            } else {
                response.sendRedirect(ssoLoginUrl);
            }
        };
    }

}
