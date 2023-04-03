package com.zhy.cas.configurer;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import javax.servlet.annotation.WebFilter;

@Configuration("jdbcPropertiesConfigurer")
@ComponentScan(basePackages = {"com.zhy.cas"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {WebFilter.class})})
public class JdbcPropertiesConfigurer {


   /* @Bean(name = "jdbcProperties")
    public JdbcProperties getJdbcProperties(){
        return new JdbcProperties();
    }*/

}
