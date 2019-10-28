package com.ssoserver.common.security.handler;

import com.ssoserver.common.enumeration.ErrorCode;
import com.ssoserver.common.util.JsonUtil;
import com.ssoserver.common.util.ResultUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author lihai
 * Create Date: 2019-10-20
 */
@Component
public class AuthFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        ResultUtil result = ResultUtil.fail(ErrorCode.BAD_REQUEST, ErrorCode.BAD_REQUEST.getMessage());
        response.getOutputStream().write(JsonUtil.OBJECT_MAPPER.writeValueAsBytes(result));
    }
}
