package com.zhy.cas.authentication;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apereo.cas.authentication.UsernamePasswordCredential;

import javax.validation.constraints.Size;

/**
 * @author: wjj
 * @date: 2022/3/28
 * @description: 验证码 Credential
 */
public class UsernamePasswordCaptchaCredential extends UsernamePasswordCredential {

    @Size(min = 5,max = 5, message = "require captcha")
    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(this.captcha)
                .toHashCode();
    }
}