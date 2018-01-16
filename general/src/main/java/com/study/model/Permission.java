package com.study.model;


/**
 * @author xujiping
 * @version 2017年4月26日 下午3:50:22 权限类
 */
public class Permission {

	private int id;
	private String url;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", url=" + url + ", name=" + name + "]";
	}

}
