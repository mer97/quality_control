package com.ssoserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SsoServerApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
        System.err.println("qualityData：" + passwordEncoder.encode("qualityData"));
        System.err.println("qualityManage：" + passwordEncoder.encode("qualityManage"));
    }

}
