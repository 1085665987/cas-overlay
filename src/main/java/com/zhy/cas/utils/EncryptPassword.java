package com.zhy.cas.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 安全服务工具类
 * 
 * @author ruoyi
 */
public class EncryptPassword implements PasswordEncoder  {

    private static final Logger log = LoggerFactory.getLogger(EncryptPassword.class);

    @Override
    public String encode(CharSequence charSequence) {
        //charSequence 为用户输入的密码
        log.info("encode---------------用户输入的密码:{}",charSequence);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(charSequence);
    }

    /**
     *
     * @param charSequence 用户输入的密码
     * @param str 数据库密码
     * @return
     */
    @Override
    public boolean matches(CharSequence charSequence, String str) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(charSequence, str);
    }
}
