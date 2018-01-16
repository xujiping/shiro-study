package com.study.service;

import com.study.model.Role;

import java.util.List;

/**
 * @author xujiping
 * @version 2017年7月3日 下午4:14:19 类说明
 */
public interface RoleService {

    /**
     * 获取用户所有角色
     */
    public List<Role> getUserRoles(int userId);

}
