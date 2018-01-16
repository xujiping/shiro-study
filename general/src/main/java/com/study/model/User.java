package com.study.model;

import java.util.Date;

/**
* @author xujiping
* @version 2017年4月26日 下午3:53:49
* 用户类
*/

public class User {

	private int id;
	private String nickname;
	private String email;
	private String pswd;
	private Date createTime;
	private Date lastLoginTime;
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nickname=" + nickname + ", email=" + email + ", pswd=" + pswd + ", createTime="
				+ createTime + ", lastLoginTime=" + lastLoginTime + ", status=" + status + "]";
	}

}
