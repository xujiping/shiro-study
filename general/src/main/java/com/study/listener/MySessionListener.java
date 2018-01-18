package com.study.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 会话监听器
 *
 * @author xujiping 2018-01-18 14:18
 */
@Component
public class MySessionListener implements SessionListener {

    private Logger _LOGGER = LoggerFactory.getLogger(MySessionListener.class);

    @Override
    public void onStart(Session session) {
        _LOGGER.info("会话创建：" + session.getId());
    }

    @Override
    public void onStop(Session session) {
        _LOGGER.info("会话停止：" + session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        _LOGGER.info("会话过期：" + session.getId());
    }
}
