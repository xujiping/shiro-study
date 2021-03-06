package com.study.model;

/**
 * 客户端
 *
 * @author xujiping 2018-01-18 15:49
 */

public class Oauth2Client {

    private Long id;
    private String clientName;
    private String clientId;
    private String clientSecret;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @Override
    public String toString() {
        return "Oauth2Client{" +
            "id=" + id +
            ", clientName='" + clientName + '\'' +
            ", clientId='" + clientId + '\'' +
            ", clientSecret='" + clientSecret + '\'' +
            '}';
    }
}
