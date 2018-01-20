package com.study.service.impl;

import com.study.common.dao.UserDao;
import com.study.model.User;
import com.study.service.UserService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* @author xujiping
* @version 2017年4月26日 下午5:11:31
* 类说明
*/
@Service
public class UserServiceImpl implements UserService {
	
	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	private UserDao userDao;

	@Override
	public int add(User user) {
		return userDao.insertSelective(user);
	}

	@Override
	public User getUser(Map<String, Object> map) {
		List<User> list = userDao.selectByExample(map);
		return list.get(0);
	}

	@Override
	public User getUserByName(String username) {
		return userDao.selectUserByName(username);
	}

}
