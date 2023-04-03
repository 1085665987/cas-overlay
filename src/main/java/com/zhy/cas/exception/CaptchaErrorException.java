package com.zhy.cas.exception;

import javax.security.auth.login.AccountException;

/**
 * @author: wjj
 * @date: 2022/3/30
 * @description: 验证码错误异常类
 */
public class CaptchaErrorException extends AccountException {

    public CaptchaErrorException() {
        super();
    }

    public CaptchaErrorException(String msg) {
        super(msg);
    }
}