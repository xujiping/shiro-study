package com.study.realm;

import com.study.exception.OAuth2AuthenticationException;
import com.study.model.OAuth2Token;
import com.study.model.Role;
import com.study.model.User;
import com.study.service.RoleService;
import com.study.service.UserService;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.message.types.GrantType;
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

    private String clientId;
    private String clientSecret;
    private String accessTokenUrl;
    private String userInfoUrl;
    private String redirectUrl;

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setAccessTokenUrl(String accessTokenUrl) {
        this.accessTokenUrl = accessTokenUrl;
    }

    public void setUserInfoUrl(String userInfoUrl) {
        this.userInfoUrl = userInfoUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

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
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws
        AuthenticationException {
        OAuth2Token oAuth2Token = (OAuth2Token) token;
        String code = oAuth2Token.getAuthCode();
        String username = extractUsername(code);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, code, getName());
        return  authenticationInfo;
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
        //仅支持OAuth2Token类型的Token
        return token instanceof OAuth2Token;
    }

    private String extractUsername(String code) {

        try {
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

            OAuthClientRequest accessTokenRequest = OAuthClientRequest
                .tokenLocation(accessTokenUrl)
                .setGrantType(GrantType.AUTHORIZATION_CODE)
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .setCode(code)
                .setRedirectURI(redirectUrl)
                .buildQueryMessage();

            OAuthAccessTokenResponse oAuthResponse = oAuthClient.accessToken(accessTokenRequest, OAuth.HttpMethod.POST);

            String accessToken = oAuthResponse.getAccessToken();
            Long expiresIn = oAuthResponse.getExpiresIn();

            OAuthClientRequest userInfoRequest = new OAuthBearerClientRequest(userInfoUrl)
                .setAccessToken(accessToken).buildQueryMessage();

            OAuthResourceResponse resourceResponse = oAuthClient.resource(userInfoRequest, OAuth.HttpMethod.GET, OAuthResourceResponse.class);
            String username = resourceResponse.getBody();
            return username;
        } catch (Exception e) {
            e.printStackTrace();
            throw new OAuth2AuthenticationException(e);
        }
    }

}
