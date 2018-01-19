package com.study.service;

/**
 * OAuthService
 *
 * @author xujiping 2018-01-18 16:49
 */
public interface OAuthService {

    public void addAuthCode(String authCode, String username);

    public void addAccessToken(String accessToken, String username);

    /**
     * 验证auth code是否有效
     * @param authCode
     * @return
     */
    boolean checkAuthCode(String authCode);

    /**
     * 验证access token是否有效
     * @param accessToken
     * @return
     */
    boolean checkAccessToken(String accessToken);

    String getUsernameByAuthCode(String authCode);

    String getUsernameByAccessToken(String accessToken);

    /**
     * 获取过期时间
     * @return
     */
    long getExpireIn();

    /**
     * 检查客户端id是否存在
     * @param clientId
     * @return
     */
    public boolean checkClientId(String clientId);

    /**
     * 检查客户端安全KEY是否存在
     * @param clientSecret
     * @return
     */
    public boolean checkClientSecret(String clientSecret);
}
