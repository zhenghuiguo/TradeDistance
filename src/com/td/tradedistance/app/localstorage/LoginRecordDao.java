package com.td.tradedistance.app.localstorage;


import android.content.Context;

import com.td.tradedistance.app.bean.LoginRecord;

public class LoginRecordDao {
	public static final String TABLE_NAME = "LoginRecord";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NAME_ID = "username";
	public static final String COLUMN_LOCK_FLAG = "lock_flag";
	public static final String COLUMN_FAILURE_NUM = "failure_num";
	public static final String COLUMN_LOGIN_DATE = "login_date";

	public LoginRecordDao() {
	}

	/**
	 * 保存登录出错记录
	 */
	public void saveContactList(LoginRecord loginRecord) {
		DBManager.getInstance().savaLoginRecord(loginRecord);
	}

	/**
	 * 更新登录出错记录
	 */
	public void updateLoginReCord(String UserName, LoginRecord loginRecord) {
		DBManager.getInstance().updateLoginReCord(UserName, loginRecord);
	}

	/**
	 * 获取登录出错记录
	 */
	public LoginRecord getLoginRecord(String userName) {
		return DBManager.getInstance().getLoginRecord(userName);
	}
	/**
     * 删除用户登录出错信息表 
     */
	public void deleteLoginRecord(String userName) {
		DBManager.getInstance().deleteLoginRecord(userName);
	}
	
}
