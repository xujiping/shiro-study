package com.study.realm;

import com.study.model.Role;
import com.study.model.User;
import com.study.service.RoleService;
import com.study.service.UserService;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xujiping
 * @version 2017年5月2日 上午11:28:27 自定义权限认证
 */
public class PermissionRealm extends AuthorizingRealm {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private UserService userService;


    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private RoleService roleService;

    /**
     * 权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限认证：" + principals.toString());
        Set<String> roles = new HashSet<>();
        List<Role> userRoles = roleService.getUserRoles(2);
        System.out.println("当前用户所属角色列表：" + userRoles);
        for (Role role : userRoles) {
            roles.add(role.getName());
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //		info.setStringPermissions(permissions);  //添加权限集合
        info.addRoles(roles);
        return info;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws
        AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        System.out.println("PermissionReam--获取认证的用户名：" + username + "  密码：" + password);
        User user = userService.getUserByName(username);
        if (user == null) {
            throw new AccountException("账号或密码不正确！");
        } else if (user.getLocked() == 1) {
            throw new DisabledAccountException("账号已经被禁止登录！");
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo
            (user.getUsername(), user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), getName());
        return simpleAuthenticationInfo;
    }

    @Override
    public String getName() {
        return "permissionRealm";
    }

    /**
     * 判断此Realm是否支持此Token
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持UsernamePasswordToken类型的Token
        return token instanceof UsernamePasswordToken;
    }

}
