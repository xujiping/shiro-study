package com.study.service.impl;

import com.study.common.dao.RoleDao;
import com.study.model.Role;
import com.study.service.RoleService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author xujiping
* @version 2017年7月3日 下午4:14:51
* 类说明
*/
@Service
public class RoleServiceImpl implements RoleService {
	
	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	private RoleDao roleDao;

	@Override
	public List<Role> getUserRoles(int userId) {
		return roleDao.queryUserRoles(userId);
	}

}
