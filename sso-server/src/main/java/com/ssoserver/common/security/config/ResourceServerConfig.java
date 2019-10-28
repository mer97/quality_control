package com.ssoserver.common.security.config;

import com.ssoserver.common.enumeration.ErrorCode;
import com.ssoserver.common.security.handler.AuthFailureHandler;
import com.ssoserver.common.security.handler.AuthSuccessHandler;
import com.ssoserver.common.util.JsonUtil;
import com.ssoserver.common.util.ResultUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

/**
 * @author lihai
 * Create Date: 2019-10-20
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private AuthFailureHandler authFailureHandler;
    private AuthSuccessHandler authSuccessHandler;

    public ResourceServerConfig(AuthFailureHandler authFailureHandler,
                                AuthSuccessHandler authSuccessHandler) {
        this.authFailureHandler = authFailureHandler;
        this.authSuccessHandler = authSuccessHandler;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/api/v1/login")
                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandler);

        http.exceptionHandling()
                .authenticationEntryPoint(unauthorizedEntryPoint())
                .accessDeniedHandler(handleAccessDeniedForUser())
                .and()
             .authorizeRequests()
                .antMatchers("/api/v1/login")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
             .csrf()
                .disable()
                .cors();

    }

    /**
     * 自定义 未登入系统，直接请求资源 的处理。
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
                response.sendRedirect("/api/v1/login");
            }
        };
    }

    /**
     * 自定义 无权请求的资源 的处理。
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
                response.sendRedirect("/api/v1/login");
            }
        };
    }

}
