package com.study.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * Mybatis配置类
 *
 * @author xujiping 2018-01-16 17:34
 */
@Configuration
public class MyBatisConfiguration {

    /**
     * MyBatis扫描mapper
     * @return
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        //获取注入的sqlSessionFactory对象
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        //指定xml配置文件路径
        configurer.setBasePackage("com.study.*.dao");
        Properties properties = new Properties();
        properties.setProperty("mappers", "com.study.common.BaseDao");
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        configurer.setProperties(properties);
        return configurer;
    }
}
