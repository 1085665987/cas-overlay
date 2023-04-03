package com.zhy.cas.authentication.handler.support;

import com.zhy.cas.authentication.UsernamePasswordCaptchaCredential;
import com.zhy.cas.exception.CaptchaErrorException;
import com.zhy.cas.service.UserService;
import com.zhy.cas.utils.EncryptPassword;
import org.apache.commons.lang3.StringUtils;
import org.apereo.cas.authentication.AuthenticationHandlerExecutionResult;
import org.apereo.cas.authentication.Credential;
import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: wjj
 * @date: 2022/3/28
 * @description:
 */
public class UsernamePasswordCaptchaAuthenticationHandler extends AbstractPreAndPostProcessingAuthenticationHandler {


    //@Autowired
    private UserService userService = new UserService();

    private static final Logger log = LoggerFactory.getLogger(UsernamePasswordCaptchaAuthenticationHandler.class);

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UsernamePasswordCaptchaAuthenticationHandler(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {
        super(name, servicesManager, principalFactory, order);
    }

    @Override
    protected AuthenticationHandlerExecutionResult doAuthentication(Credential credential)
            throws FailedLoginException, CaptchaErrorException, AccountNotFoundException, AccountLockedException {
        UsernamePasswordCaptchaCredential myCredential = (UsernamePasswordCaptchaCredential) credential;
        String requestCaptcha = myCredential.getCaptcha();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Object attribute = attributes.getRequest().getSession().getAttribute("captcha");
        String realCaptcha = attribute == null ? null : attribute.toString().toUpperCase();
        if(StringUtils.isBlank(requestCaptcha) || !requestCaptcha.toUpperCase().equals(realCaptcha)){
            throw new CaptchaErrorException("验证码错误");
        }

        String username = myCredential.getUsername();
        Map<String, Object> user = userService.findByUserName(username);

        if (user == null) {
            throw new AccountNotFoundException("用户名或密码错误");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(!passwordEncoder.matches(myCredential.getPassword(), user.get("password").toString())){
            throw new FailedLoginException("用户名或密码错误");
        }

        if ("1".equals(user.get("state"))) {
            throw new AccountLockedException("账号已被锁定,请联系管理员！");
        }
        return createHandlerResult(credential, this.principalFactory.createPrincipal(username));
    }

    @Override
    public boolean supports(Credential credential) {
        return credential instanceof UsernamePasswordCaptchaCredential;
    }
}