package com.zhy.cas.authentication;

import com.zhy.cas.authentication.handler.support.UsernamePasswordCaptchaAuthenticationHandler;
import com.zhy.cas.service.UserService;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlan;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlanConfigurer;
import org.apereo.cas.authentication.AuthenticationHandler;
import org.apereo.cas.authentication.principal.DefaultPrincipalFactory;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("captchaAuthenticationEventExecutionPlanConfigurer")
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class CaptchaAuthenticationEventExecutionPlanConfigurer implements AuthenticationEventExecutionPlanConfigurer {

    @Autowired
    @Qualifier("servicesManager")
    private ServicesManager servicesManager;

    @Autowired
    private CasConfigurationProperties casProperties;

    /*@Autowired
    private UserService userService;*/
    /**
     * 放到 shiro(order=10) 验证器的前面 先验证验证码
     * @return
     */
    @Bean
    public AuthenticationHandler rememberMeUsernamePasswordCaptchaAuthenticationHandler() {
        UsernamePasswordCaptchaAuthenticationHandler handler = new UsernamePasswordCaptchaAuthenticationHandler(
                UsernamePasswordCaptchaAuthenticationHandler.class.getSimpleName(),
                servicesManager,
                new DefaultPrincipalFactory(),
                9);
        handler.setUserService(new UserService());
        return handler;
    }

    @Override
    public void configureAuthenticationExecutionPlan(AuthenticationEventExecutionPlan plan) {
        plan.registerAuthenticationHandler(rememberMeUsernamePasswordCaptchaAuthenticationHandler());
    }
}