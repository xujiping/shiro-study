package com.study.model;


/**
 * @author xujiping
 * @version 2017年4月26日 下午3:52:33 角色类
 */
public class Role {

	private int id;
	private String name;
	private String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", type=" + type + "]";
	}

}
