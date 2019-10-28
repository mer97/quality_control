package com.qualitymanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class QualityManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(QualityManageApplication.class, args);
    }

}
