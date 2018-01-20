package com.study.model;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * OAuth2Token
 *
 * @author xujiping 2018-01-19 14:43
 */

public class OAuth2Token implements AuthenticationToken{

    private String authCode;
    private String principal;

    public OAuth2Token(String authCode) {
        this.authCode = authCode;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getPrincipal() {
        return principal;
    }

    @Override
    public Object getCredentials() {
        return authCode;
    }

    @Override
    public String toString() {
        return "OAuth2Token{" +
            "authCode='" + authCode + '\'' +
            ", principal='" + principal + '\'' +
            '}';
    }
}
