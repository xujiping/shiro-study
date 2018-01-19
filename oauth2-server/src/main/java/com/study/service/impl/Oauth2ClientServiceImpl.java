package com.study.service.impl;

import com.study.common.dao.Oauth2ClientDao;
import com.study.model.Oauth2Client;
import com.study.service.Oauth2ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Oauth2ClientServiceImpl
 *
 * @author xujiping 2018-01-18 15:54
 */
@Service
public class Oauth2ClientServiceImpl implements Oauth2ClientService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private Oauth2ClientDao oauth2ClientDao;

    @Override
    public List<Oauth2Client> findAll() {
        return oauth2ClientDao.selectAll();
    }

    @Override
    public Oauth2Client add(Oauth2Client client) {
        oauth2ClientDao.insertSelective(client);
        return client;
    }

    @Override
    public Oauth2Client update(Oauth2Client client) {
        oauth2ClientDao.updateByPrimaryKeySelective(client);
        return client;
    }

    @Override
    public void delete(Long id) {
        oauth2ClientDao.deleteByPrimaryKey(id);
    }

    @Override
    public Oauth2Client findOne(Long id) {
        return oauth2ClientDao.selectByPrimaryKey(id);
    }

    @Override
    public Oauth2Client findByClientId(String clientId) {
        Oauth2Client client = new Oauth2Client();
        client.setClientId(clientId);
        return oauth2ClientDao.selectOne(client);
    }

    @Override
    public Oauth2Client findByClientSecret(String clientSecret) {
        Oauth2Client client = new Oauth2Client();
        client.setClientSecret(clientSecret);
        return oauth2ClientDao.selectOne(client);
    }
}
