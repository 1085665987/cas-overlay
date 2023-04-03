package com.zhy.cas.service;

import com.zhy.cas.configurer.JdbcPropertiesConfigurer;
import com.zhy.cas.utils.JdbcProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.security.auth.login.FailedLoginException;
import java.util.Map;
import java.util.Properties;

/**
 * 安全服务工具类
 *
 * @author ruoyi
 */
@Component
public class UserService {

    @Autowired
    private JdbcProperties jdbcProperties;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);


    public Map<String, Object> findByUserName(String username) {

        //log.info("================findByUserName=================={}",jdbcProperties.toString());

        /*DriverManagerDataSource d = new DriverManagerDataSource();
        d.setDriverClassName(jdbcProperties.getQueryDriverClass());
        d.setUrl(jdbcProperties.getQueryUrl());
        d.setUsername(jdbcProperties.getQueryUser());
        d.setPassword(jdbcProperties.getQueryPassword());
        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(d);*/

        // 通过ClassPathResource 工具读取 properties
        Properties properties = getJdbcProperties();
        DriverManagerDataSource d = new DriverManagerDataSource();
        d.setDriverClassName(properties.getProperty("zhy.cas.jdbc.query.driverClass"));
        d.setUrl(properties.getProperty("zhy.cas.jdbc.query.url"));
        d.setUsername(properties.getProperty("zhy.cas.jdbc.query.user"));
        d.setPassword(properties.getProperty("zhy.cas.jdbc.query.password"));
        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(d);

        /*DriverManagerDataSource d = new DriverManagerDataSource();
        d.setDriverClassName("com.mysql.cj.jdbc.Driver");
        d.setUrl("jdbc:mysql://127.0.0.1:3306/cas?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai");
        d.setUsername("root");
        d.setPassword("root");
        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(d);*/

        //查询数据库加密的的密码
        Map<String, Object> user = template.queryForMap(properties.getProperty("zhy.cas.jdbc.query.sql"), username);
        return user;
    }

    /**
     * 通过配置文件名读取内容
     * @return
     */
    public Properties getJdbcProperties() {
        try {
            Resource resource = new ClassPathResource("application.properties");
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            return properties;
        } catch (Exception e) {
            log.error("————读取配置文件出现异常————");
            e.printStackTrace();
        }
        return null;
    }
}
