package com.study.conf;

import com.study.credentials.RetryLimitHashedCredentialsMatcher;
import com.study.realm.PermissionRealm;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;

/**
* @author xujiping
* @version 2017年4月26日 下午4:42:42
* 权限配置
*/
@Configuration
public class ShiroConfiguration {

	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		//必须设置SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//如果不设置默认会自动寻找根目录/login.jsp页面
		shiroFilterFactoryBean.setLoginUrl("/login");
		//登录成功后跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index");
		//未授权界面
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		//拦截器
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		//配置不会被拦截的链接，顺序判断
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/ajaxLogin", "anon");
		filterChainDefinitionMap.put("/signUp/**", "anon");
		//配置退出过滤器,具体代码Shiro已经实现
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/add", "perms[权限添加]");
		// <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		System.out.println("Shiro拦截器工厂类注入成功");
		return shiroFilterFactoryBean;
	}
	
	@Bean
	public SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		//设置realm，可以指定多个realm实现
		securityManager.setRealm(permissionRealm(credentialsMatcher()));
		return securityManager;
	}

    /**
     * 配置自定义权限登录器
     * @param matcher
     * @return
     */
	@Bean
	public PermissionRealm permissionRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher){

        PermissionRealm permissionRealm = new PermissionRealm();
        permissionRealm.setCredentialsMatcher(matcher);
        return permissionRealm;
    }

    /**
     * 缓存管理器
     * @return
     */
	@Bean
	public EhCacheManager cacheManager(){
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return ehCacheManager;
    }

	/**
	 * 凭证匹配器
	 * @return
	 */
	@Bean
	public CredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher matcher = new
            RetryLimitHashedCredentialsMatcher(cacheManager());
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(2);
        matcher.setStoredCredentialsHexEncoded(true);
        return matcher;
    }

}
