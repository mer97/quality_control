package com.ssoserver.common.security.config;

import com.ssoserver.common.security.enumeration.Authority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author lihai
 * Create Date: 2019-10-21
 */
@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    private static Map<String, String> USER_MAP = new HashMap<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(UsernamePasswordAuthenticationProvider.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
     * 模拟数据库用户名和密码
     */
    static {
        USER_MAP.put("zhangsan", "123456");
        USER_MAP.put("lisi", "123456");
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
        String password = (String) authentication.getCredentials();

        LOGGER.info("用户名：{}-密码：{} 正在尝试登陆", username, password);

        if (USER_MAP.get(username) == null) {
            throw new UsernameNotFoundException("User Not Found: " + username);
        }

        if (password != null && !password.equals(USER_MAP.get(username))){
            throw new BadCredentialsException("密码错误");
        }

        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
                username,
                passwordEncoder.encode(password),
                Authority.getAuthorityAllToSimpleGrantedAuthoritys()
        );
        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
