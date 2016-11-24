package com.td.tradedistance.app.bean;

public class LoginRecord {
	// 登录记录ID
	private int id;
	// 用户名
	private String userName;
	// 锁定标志，'1'代表锁定状态 '0'未锁定状态
	private int lock_flag;
	// 登录失败次数
	private int failure_num;
	// 登录时间，默认为当前时间
	private String login_date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getLock_flag() {
		return lock_flag;
	}

	public void setLock_flag(int lock_flag) {
		this.lock_flag = lock_flag;
	}

	public int getFailure_num() {
		return failure_num;
	}

	public void setFailure_num(int failure_num) {
		this.failure_num = failure_num;
	}

	public String getLogin_date() {
		return login_date;
	}

	public void setLogin_date(String login_date) {
		this.login_date = login_date;
	}
}
