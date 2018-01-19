package com.study.conf;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * spring相关配置
 *
 * @author xujiping 2018-01-19 9:43
 */
@Configuration
public class SpringConfiguration {

    @Bean
    public EhCacheCacheManager springCacheManager(){
        EhCacheCacheManager cacheManager = new EhCacheCacheManager();
        cacheManager.setCacheManager(ehcacheManager().getObject());
        return cacheManager;
    }

    @Bean
    public EhCacheManagerFactoryBean ehcacheManager(){
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
        factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        factoryBean.setShared(true);
        return factoryBean;
    }

}
