package com.study.conf;

import com.study.credentials.RetryLimitHashedCredentialsMatcher;
import com.study.filter.OAuth2AuthenticationFilter;
import com.study.realm.PermissionRealm;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;

import javax.servlet.Filter;

/**
 * @author xujiping
 * @version 2017年4月26日 下午4:42:42 权限配置
 */
@Configuration
public class ShiroConfiguration {

    @Autowired
    private SessionListener mySessionListener;

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //必须设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //如果不设置默认会自动寻找根目录/login.jsp页面
        shiroFilterFactoryBean.setLoginUrl
            ("http://localhost:8110/authorize?client_id=c1ebe466-1cdc-4bd3-ab69-77c3561b9dee&response_type=code&redirect_uri=http://localhost:8111/oauth2-login");
        //登录成功后跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        Map<String, Filter> filters = new HashMap<>();
        filters.put("oauth2Authc", oAuth2AuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filters);
        //拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //配置不会被拦截的链接，顺序判断
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        //导向oauth2服务端登陆
        filterChainDefinitionMap.put("/oauth2-login", "oauth2Authc");
        //配置退出过滤器,具体代码Shiro已经实现
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/add", "perms[权限添加]");
        // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm，可以指定多个realm实现
        securityManager.setRealm(permissionRealm(credentialsMatcher()));
        securityManager.setSessionManager(sessionManager());
        securityManager.setRememberMeManager(rememberMeManager());  //设置rememberMe
        return securityManager;
    }

    /**
     * 配置自定义权限登录器
     */
    @Bean
    public PermissionRealm permissionRealm(@Qualifier("credentialsMatcher") CredentialsMatcher
                                               matcher) {
        PermissionRealm permissionRealm = new PermissionRealm();
        permissionRealm.setCachingEnabled(true);
        permissionRealm.setAuthenticationCachingEnabled(true);
        permissionRealm.setAuthenticationCacheName("authenticationCache");
        permissionRealm.setAuthorizationCachingEnabled(true);
        permissionRealm.setAuthorizationCacheName("authorizationCache");
        permissionRealm.setClientId("c1ebe466-1cdc-4bd3-ab69-77c3561b9dee");
        permissionRealm.setClientSecret("d8346ea2-6017-43ed-ad68-19c0f971738b");
        permissionRealm.setAccessTokenUrl("http://localhost:8110/accessToken");
        permissionRealm.setUserInfoUrl("http://localhost:8110/userInfo");
        permissionRealm.setRedirectUrl("http://localhost:8110/login");
        permissionRealm.setCredentialsMatcher(matcher);
        return permissionRealm;
    }

    /**
     * 缓存管理器
     */
    @Bean
    public EhCacheManager cacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return ehCacheManager;
    }

    /**
     * 凭证匹配器
     */
    @Bean
    public CredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher matcher = new
            RetryLimitHashedCredentialsMatcher(cacheManager());
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(2);
        matcher.setStoredCredentialsHexEncoded(true);
        return matcher;
    }

    /**
     * 会话管理器
     */
    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //设置监听器
        List<SessionListener> list = new ArrayList<>();
        list.add(mySessionListener);
        sessionManager.setSessionListeners(list);
        //设置会话Cookie
        Cookie sessionIdCookie = new SimpleCookie();
        sessionIdCookie.setName("sid");
        sessionIdCookie.setMaxAge(-1);  //设置Cookie过期时间，单位秒
        sessionIdCookie.setHttpOnly(true);  //如果设置为true，则客户端不会暴露给客户端脚本代码
        sessionManager.setSessionIdCookie(sessionIdCookie);
        //其它设置
        return sessionManager;
    }

    /**
     * RememberMe管理器
     */
    @Bean
    public RememberMeManager rememberMeManager() {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        Cookie cookie = new SimpleCookie();
        cookie.setName("rememberMe");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(2592000);  //30天
        rememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        rememberMeManager.setCookie(cookie);
        return rememberMeManager;
    }

    @Bean
    public Filter oAuth2AuthenticationFilter() {
        OAuth2AuthenticationFilter filter = new OAuth2AuthenticationFilter();
        filter.setAuthcCodeParam("code");
        filter.setFailureUrl("oauth2Failure");
        return filter;
    }

}
