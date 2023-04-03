package com.zhy.cas.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
public class JdbcProperties {

    /**
     * sql
     */
    @Value("${zhy.cas.jdbc.query.sql}")
    private String querySql;

    /**
     * fieldPassword
     */
    @Value("${zhy.cas.jdbc.query.fieldPassword}")
    private String queryFieldPassword;

    /**
     * url
     */
    @Value("${zhy.cas.jdbc.query.url}")
    private String queryUrl;

    /**
     * user
     */
    @Value("${zhy.cas.jdbc.query.user}")
    private String queryUser;

    /**
     * password
     */
    @Value("${zhy.cas.jdbc.query.password}")
    private String queryPassword;

    /**
     * driverClass
     */
    @Value("${zhy.cas.jdbc.query.driverClass}")
    private String queryDriverClass;


    public String getQuerySql() {
        return querySql;
    }

    public void setQuerySql(String querySql) {
        this.querySql = querySql;
    }

    public String getQueryFieldPassword() {
        return queryFieldPassword;
    }

    public void setQueryFieldPassword(String queryFieldPassword) {
        this.queryFieldPassword = queryFieldPassword;
    }

    public String getQueryUrl() {
        return queryUrl;
    }

    public void setQueryUrl(String queryUrl) {
        this.queryUrl = queryUrl;
    }

    public String getQueryUser() {
        return queryUser;
    }

    public void setQueryUser(String queryUser) {
        this.queryUser = queryUser;
    }

    public String getQueryPassword() {
        return queryPassword;
    }

    public void setQueryPassword(String queryPassword) {
        this.queryPassword = queryPassword;
    }

    public String getQueryDriverClass() {
        return queryDriverClass;
    }

    public void setQueryDriverClass(String queryDriverClass) {
        this.queryDriverClass = queryDriverClass;
    }

    @Override
    public String toString() {
        return "JdbcProperties{" +
                "querySql='" + querySql + '\'' +
                ", queryFieldPassword='" + queryFieldPassword + '\'' +
                ", queryUrl='" + queryUrl + '\'' +
                ", queryUser='" + queryUser + '\'' +
                ", queryPassword='" + queryPassword + '\'' +
                ", queryDriverClass='" + queryDriverClass + '\'' +
                '}';
    }

    public JdbcProperties(){
        System.out.println("JdbcProperties=========初始化。。。。。。。。。");
    }
}
