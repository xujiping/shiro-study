package com.study.common.dao;

import com.study.model.Role;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author xujiping
* @version 2017年4月26日 下午3:57:43
* xml方式完成映射
*/
@Mapper
public interface RoleDao{
	
	public List<Role> queryAll();
	
	@Select("select id, name, type from role, user_role where role.id=user_role.rid and user_role.uid=#{userId}")
	public List<Role> queryUserRoles(int userId);

}
