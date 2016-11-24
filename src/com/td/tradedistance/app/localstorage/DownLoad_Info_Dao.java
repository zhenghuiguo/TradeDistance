package com.td.tradedistance.app.localstorage;


import java.util.List;

import android.content.Context;

import com.td.tradedistance.app.bean.CourseDetailsShuju;
import com.td.tradedistance.app.bean.LoginRecord;

import down.DownloadInfo;

public class DownLoad_Info_Dao {
	public static final String TABLE_NAME = "DownLoadInfo";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_THREAD_ID = "Thread_Id";
	public static final String COLUMN_START_POS = "Start_Pos";
	public static final String COLUMN_END_POS = "End_Pos";
	public static final String COLUMN_COMPELETE_SIZE = "Compelete_Size";
    public static final String COLUMN_URL = "Url";
    public static final String COLUMN_LOCALURL = "LocalUrl";
    public static final String COLUMN_KE_CHENG_DAI_MA = "KeChengDaiMa";
    public static final String COLUMN_YHWYBS = "Yhwybs";
    public static final String COLUMN_FILESIZE = "FileSize";
    public static final String COLUMN_KECHENGMINGCHENG = "KeChengMingCheng";
    public static final String COLUMN_ZHAOPIAN = "ZhaoPian";
    public static final String COLUMN_ZHANG = "Zhang";
    public static final String COLUMN_JIE = "Jie";
    public static final String COLUMN_STATE = "State";
    public static final String COLUMN_BEIYONG1 = "Beiyong1";
	public static final String COLUMN_BEIYONG2 = "Beiyong2";
	public static final String COLUMN_BEIYONG3 = "Beiyong3";
	
	public DownLoad_Info_Dao() {
	}

	/**
	 * 查看数据库中是否有数据
	 */
	public boolean isHasInfors(String urlstr) {
		return DBManager.getInstance().isHasInfors(urlstr);
	}

	/**
	 * 保存 下载的具体信息
	 */
	public void saveInfos(List<DownloadInfo> infos) {
		DBManager.getInstance().saveInfos(infos);
	}

	/**
	 * 得到下载具体信息
	 */
	public  List<DownloadInfo> getInfos(String urlstr,String localUrl) {
		return DBManager.getInstance().getInfos(urlstr,localUrl);
	}
	
	/**
	 * 得到下载 中列表
	 */
	public  List<DownloadInfo> getList() {
		return DBManager.getInstance().getList();
	}
	public float getWeiXiaZaiListTotalSize(){
		List<DownloadInfo> list = getList();
		float size = 0.0f;
		if(list.size()>0){
		   for (int i = 0; i < list.size(); i++) {
			   DownloadInfo shuju =  list.get(i);
			   size+= shuju.getCompeleteSize();
		   }	
		   return size;
		}
		return 0.0f;
	}
	/**
	 * 得到下载 中列表
	 */
	public  List<DownloadInfo> getDownLoadIngList(String url) {
		return DBManager.getInstance().getDownLoadIngList(url);
	}
	/**
     * 更新数据库中的下载信息
     */
	public void updataInfos(int threadId, int compeleteSize, String urlstr,String localUrl) {
		DBManager.getInstance().updataInfos(threadId,compeleteSize,urlstr,localUrl);
	}
	
	/**
     * 载完成后删除数据库中的数据
     */
	public void deleteDownInfo(String url) {
		DBManager.getInstance().deleteDownInfo(url);
	}
	
}
