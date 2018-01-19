package com.study.service;

import com.study.model.Oauth2Client;

import java.util.List;

/**
 * Oauth2ClientService
 *
 * @author xujiping 2018-01-18 15:53
 */
public interface Oauth2ClientService {

    public List<Oauth2Client> findAll();

    public Oauth2Client add(Oauth2Client client);

    public Oauth2Client update(Oauth2Client client);

    public void delete(Long id);

    Oauth2Client findOne(Long id);

    Oauth2Client findByClientId(String clientId);

    Oauth2Client findByClientSecret(String clientSecret);
}
