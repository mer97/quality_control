package com.ssoserver.common.security.config;

import com.ssoserver.common.security.enumeration.Authority;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * AD域用户授权
 *
 * @author lihai
 * Create Date: 2019-10-20
 */
@Component
public class ADLdapUserDetailsMapper extends LdapUserDetailsMapper {

    @Override
    public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {
        // 给用户自动创建角色并赋予权限
        return super.mapUserFromContext(
                ctx,
                username,
                Authority.getAuthorityAllToSimpleGrantedAuthoritys()
        );
    }
}
