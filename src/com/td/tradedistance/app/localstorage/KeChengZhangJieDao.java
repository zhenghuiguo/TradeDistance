package com.td.tradedistance.app.localstorage;


import java.util.List;

import android.content.Context;

import com.td.tradedistance.app.bean.CourseDetailsShuju;
import com.td.tradedistance.app.bean.JiaoXueJiHua;
import com.td.tradedistance.app.bean.LoginRecord;

public class KeChengZhangJieDao {
	
	public static final String TABLE_NAME = "keChengZhangJie";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_KECHENGDAIMA = "KeChengDaiMa";
	public static final String COLUMN_ZHANG = "ZHANG";
	public static final String COLUMN_JIE = "jie";
	public static final String COLUMN_YHWYBS = "yhwybs";
	public static final String COLUMN_GUANGKANBAIFENBI = "GuangKanBaiFenBi";
	public static final String COLUMN_TOTAL_SIZE = "TotalSize";
	public static final String COLUMN_SHIPINGDIZHI = "ShiPingDiZhi";
	public static final String COLUMN_XUHAO = "XuHao";
	public static final String COLUMN_BEIYONG1 = "beiyong1";
	public static final String COLUMN_BEIYONG2 = "beiyong2";
	public static final String COLUMN_BEIYONG3 = "beiyong3";
	
	public KeChengZhangJieDao() {
	}

	/**
	 * 保存章节表记录
	 */
	public void saveKeChengZhangJie(CourseDetailsShuju shuju) {
		DBManager.getInstance().saveZhangJie(shuju);
	}

	/**
	 * 获取章节记录
	 */
	public boolean getKeChengZhangJieItem(String url) {
		if(DBManager.getInstance().getKeChengZhangJieItem(url)==null)
			return false;
		return true;
	}
	/**
	 * 更新章节
	 */
	public int updateZhangJie(String progress,String url ) {
		return DBManager.getInstance().updateZhangJie(progress,url);
	}
	
	/**
	 * 获取章节列表
	 */
	public List<CourseDetailsShuju> getKeChengZhangJieList(String KeChengDaiMa ) {
		return DBManager.getInstance().getKeChengZhangJieList(KeChengDaiMa);
	}
	/**
	 * 获取用户章节列表
	 */
	public List<CourseDetailsShuju> getUserZhangJieList() {
		return DBManager.getInstance().getUserZhangJieList();
	}
	public float getUserFileTotalSize(){
		List<CourseDetailsShuju> list = getUserZhangJieList();
		float size = 0.0f;
		if(list.size()>0){
		   for (int i = 0; i < list.size(); i++) {
			   CourseDetailsShuju shuju =  list.get(i);
			   if(shuju.getTotalSize()!=null&&shuju.getTotalSize().length()>0){
				   size+= Float.parseFloat(shuju.getTotalSize());
			   }
		   }	
		   return size;
		}
		return 0.0f;
	}
	/**
	 * 保存章节表记录
	 */
	public void SaveKeChengAndZhangJie(JiaoXueJiHua jxjh,CourseDetailsShuju zj){
		DBManager.getInstance().SaveKeChengAndZhangJie(jxjh,zj);
	}
	
	/**
	 * 删除章节表记录
	 */
	public int delKeChengZhangJie(String url){
		return DBManager.getInstance().delKeChengZhangJie(url);
	}
	/**
     * 删除用户登录出错信息表 
     *//*
	public void deleteKeCheng(String userName) {
		DBManager.getInstance().deleteLoginRecord(userName);
	}*/
	
}
