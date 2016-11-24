package com.td.tradedistance.app.localstorage;

import java.util.ArrayList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.td.tradedistance.app.TDApp;
import com.td.tradedistance.app.bean.CourseDetailsShuju;
import com.td.tradedistance.app.bean.JiaoXueJiHua;
import com.td.tradedistance.app.bean.LoginRecord;
import com.td.tradedistance.app.bean1.KeCheng;
import com.td.tradedistance.app.bean1.ZhangJie;

import down.DownloadInfo;

public class DBManager {
    static private DBManager dbMgr = new DBManager();
    private DbOpenHelper dbHelper;
    
    private DBManager(){
        dbHelper = DbOpenHelper.getInstance(TDApp.getContext());
    }
    
    public static synchronized DBManager getInstance(){
        if(dbMgr == null){
            dbMgr = new DBManager();
        }
        return dbMgr;
    }
    synchronized public void closeDB(){
        if(dbHelper != null){
            dbHelper.closeDB();
        }
        dbMgr = null;
    }
    /**
     * 保存登录出错记录 
     */
    public synchronized void savaLoginRecord(LoginRecord loginRecord){
    		 SQLiteDatabase db = dbHelper.getWritableDatabase();
    	        ContentValues values = new ContentValues();
    	        values.put(LoginRecordDao.COLUMN_NAME_ID, loginRecord.getUserName());
    	        values.put(LoginRecordDao.COLUMN_LOGIN_DATE, loginRecord.getLogin_date());
    	        values.put(LoginRecordDao.COLUMN_FAILURE_NUM, loginRecord.getFailure_num());
    	        values.put(LoginRecordDao.COLUMN_LOCK_FLAG, loginRecord.getLock_flag());
    	        if(db.isOpen()){
    	            db.replace(LoginRecordDao.TABLE_NAME, null, values);
    	        }
    }
    /**
     * 更新登录出错记录 
     */
    synchronized public void updateLoginReCord(String UserName,LoginRecord loginRecord){
    	    ContentValues values = new ContentValues();
	        values.put(LoginRecordDao.COLUMN_NAME_ID, loginRecord.getUserName());
	        values.put(LoginRecordDao.COLUMN_LOGIN_DATE, loginRecord.getLogin_date());
	        values.put(LoginRecordDao.COLUMN_FAILURE_NUM, loginRecord.getFailure_num());
	        values.put(LoginRecordDao.COLUMN_LOCK_FLAG, loginRecord.getLock_flag());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db.isOpen()){
            db.update(LoginRecordDao.TABLE_NAME, values, LoginRecordDao.COLUMN_NAME_ID + " = ?", new String[]{UserName});
        }
    }
    
    /**
     * 获取登录出错记录  
     */
     public synchronized LoginRecord getLoginRecord(String userName) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        LoginRecord loginRecord = null;
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from " + LoginRecordDao.TABLE_NAME +" where "+LoginRecordDao.COLUMN_NAME_ID+" ='"+userName+"'" , null);
            while (cursor.moveToNext()) {
            	
                int lock_flag = cursor.getInt(cursor.getColumnIndex(LoginRecordDao.COLUMN_LOCK_FLAG));
                int failure_num = cursor.getInt(cursor.getColumnIndex(LoginRecordDao.COLUMN_FAILURE_NUM));
                String login_date = cursor.getString(cursor.getColumnIndex(LoginRecordDao.COLUMN_LOGIN_DATE));
                loginRecord = new LoginRecord();
                loginRecord.setUserName(userName);
                loginRecord.setLock_flag(lock_flag);
                loginRecord.setFailure_num(failure_num);
                loginRecord.setLogin_date(login_date);
            }
            cursor.close();
        }
        return loginRecord;
    }
    
    /**
     * 删除用户登录出错信息表 
     * @param username
     */
    synchronized public void deleteLoginRecord(String username){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db.isOpen()){
            db.delete(LoginRecordDao.TABLE_NAME, LoginRecordDao.COLUMN_NAME_ID + " = ?", new String[]{username});
        }
    }
    
    
    /**
     * 保存课程记录
     */
    synchronized public void saveKeCheng(JiaoXueJiHua jxjh){
    	 SQLiteDatabase db = dbHelper.getWritableDatabase();
	        ContentValues values = new ContentValues();
	        values.put(KeChengDao.COLUMN_KECHENGDAIMA, jxjh.getKeChengDaiMa());
	        values.put(KeChengDao.COLUMN_KECHENGMINGCHENG, jxjh.getKeChengMingCheng());
	        values.put(KeChengDao.COLUMN_KECHENGZHAOPIAN, jxjh.getKeChengZhaoPian());
	        values.put(KeChengDao.COLUMN_YHWYBS, TDApp.manager.getNoDESYongHuWeiYiBiaoShi());
	        values.put(KeChengDao.COLUMN_COUNT, 0);
	        values.put(KeChengDao.COLUMN_XUEWWEIKE,jxjh.getXueWeiKe());
	        if(db.isOpen()){
	            db.replace(KeChengDao.TABLE_NAME, null, values);
	        }
    }
    
    /**
     * 更新课程记录 
     */
    synchronized public void updateKeCheng(JiaoXueJiHua jxjh){
    	    ContentValues values = new ContentValues();
	        values.put(KeChengDao.COLUMN_KECHENGMINGCHENG, jxjh.getKeChengMingCheng());
	        values.put(KeChengDao.COLUMN_KECHENGZHAOPIAN, jxjh.getKeChengZhaoPian());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db.isOpen()){
            db.update(KeChengDao.TABLE_NAME, values, KeChengDao.COLUMN_KECHENGDAIMA + " = ? and "+KeChengDao.COLUMN_YHWYBS +" = ?" , new String[]{jxjh.getKeChengDaiMa(),TDApp.manager.getNoDESYongHuWeiYiBiaoShi()});
        }
    }
    /**
     * 获取章节记录
     */
    synchronized public JiaoXueJiHua getKeChengItem(String keChengDaiMa) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        JiaoXueJiHua jxjh = null;
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from " + KeChengDao.TABLE_NAME  + " where "+KeChengDao.COLUMN_YHWYBS+"='"+TDApp.manager.getNoDESYongHuWeiYiBiaoShi()+"' and "+KeChengDao.COLUMN_KECHENGDAIMA+" = '"+keChengDaiMa+"' order by "+KeChengDao.COLUMN_ID+ " desc" , null);
            while (cursor.moveToNext()) {
            	 jxjh = new JiaoXueJiHua();
                 jxjh.setKeChengDaiMa(cursor.getString(cursor.getColumnIndex(KeChengDao.COLUMN_KECHENGDAIMA)));
                 jxjh.setKeChengMingCheng(cursor.getString(cursor.getColumnIndex(KeChengDao.COLUMN_KECHENGMINGCHENG)));
                 jxjh.setKeChengZhaoPian(cursor.getString(cursor.getColumnIndex(KeChengDao.COLUMN_KECHENGZHAOPIAN)));
                 jxjh.setXueWeiKe(cursor.getInt(cursor.getColumnIndex(KeChengDao.COLUMN_XUEWWEIKE)));
            }
            cursor.close();
        }
        return jxjh;
    }
    /**
     * 获取课程列表
     */
    synchronized public List<JiaoXueJiHua> getKeChengList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<JiaoXueJiHua> list = new ArrayList<JiaoXueJiHua>();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from " + KeChengDao.TABLE_NAME  + " where "+KeChengDao.COLUMN_YHWYBS+"='"+TDApp.manager.getNoDESYongHuWeiYiBiaoShi()+"' order by "+KeChengDao.COLUMN_ID+ " desc " , null);
            while (cursor.moveToNext()) {
                JiaoXueJiHua jxjh = new JiaoXueJiHua();
                jxjh.setKeChengDaiMa(cursor.getString(cursor.getColumnIndex(KeChengDao.COLUMN_KECHENGDAIMA)));
                jxjh.setKeChengMingCheng(cursor.getString(cursor.getColumnIndex(KeChengDao.COLUMN_KECHENGMINGCHENG)));
                jxjh.setKeChengZhaoPian(cursor.getString(cursor.getColumnIndex(KeChengDao.COLUMN_KECHENGZHAOPIAN)));
                jxjh.setXueWeiKe(cursor.getInt(cursor.getColumnIndex(KeChengDao.COLUMN_XUEWWEIKE)));
               
                list.add(jxjh);
            }
            cursor.close();
        }
        return list;
    }
    /**
     * 删除课程记录
     * @param username
     */
    synchronized public int deleteKeChengItem(String KeChengDaiMa){
       /* SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db.isOpen()){
            db.delete(KeChengDao.TABLE_NAME, KeChengDao.COLUMN_KECHENGDAIMA + " = ? and "+KeChengDao.COLUMN_YHWYBS +" = ?" , new String[]{KeChengDaiMa,TDApp.manager.getNoDESYongHuWeiYiBiaoShi()});
        }*/
        
        
        SQLiteDatabase db = dbHelper.getReadableDatabase();
  		try {
  			return db.delete(KeChengDao.TABLE_NAME, KeChengDao.COLUMN_KECHENGDAIMA+"=? and "+KeChengDao.COLUMN_YHWYBS+"=?", new String[] { KeChengDaiMa,TDApp.manager.getNoDESYongHuWeiYiBiaoShi() });
  		} catch (Exception e) {
  			e.printStackTrace();
  		} finally {
  		}
  		return 0;
    }
    
    /**
     * 更新章节记录
     */
    public int updateZhangJie(String progress,String url) throws SQLException {
    	SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();        
        values.put(KeChengZhangJieDao.COLUMN_GUANGKANBAIFENBI, progress);     
        return  db.update(KeChengZhangJieDao.TABLE_NAME, values, KeChengZhangJieDao.COLUMN_SHIPINGDIZHI+"=? and "+KeChengZhangJieDao.COLUMN_YHWYBS+"=?", new String[]{url,TDApp.manager.getNoDESYongHuWeiYiBiaoShi()});     
    }
    /**
     * 删除章节记录
     */
    public synchronized int delKeChengZhangJie(String url) {
    	SQLiteDatabase db = dbHelper.getReadableDatabase();
  		try {
  			return db.delete(KeChengZhangJieDao.TABLE_NAME, KeChengZhangJieDao.COLUMN_SHIPINGDIZHI+"=? and "+KeChengZhangJieDao.COLUMN_YHWYBS+"=?", new String[] { url,TDApp.manager.getNoDESYongHuWeiYiBiaoShi() });
  		} catch (Exception e) {
  			e.printStackTrace();
  		} finally {
  		}
  		return 0;
  	}
    /**
     * 保存章节记录
     */
    synchronized public void saveZhangJie(CourseDetailsShuju shuju){
    	 SQLiteDatabase db = dbHelper.getWritableDatabase();
	        ContentValues values = new ContentValues();
	        values.put(KeChengZhangJieDao.COLUMN_KECHENGDAIMA, shuju.getKeChengDaiMa());
	        values.put(KeChengZhangJieDao.COLUMN_ZHANG, shuju.getZhang());
	        values.put(KeChengZhangJieDao.COLUMN_JIE, shuju.getJie());
	        values.put(KeChengZhangJieDao.COLUMN_SHIPINGDIZHI, shuju.getShiPinDiZhi());
	        values.put(KeChengZhangJieDao.COLUMN_YHWYBS, TDApp.manager.getNoDESYongHuWeiYiBiaoShi());
	        values.put(KeChengZhangJieDao.COLUMN_GUANGKANBAIFENBI, shuju.getGuangKanBaiFenBi());
	        values.put(KeChengZhangJieDao.COLUMN_TOTAL_SIZE,shuju.getTotalSize());
	        values.put(KeChengZhangJieDao.COLUMN_XUHAO,0);
	        if(db.isOpen()){
	            db.replace(KeChengZhangJieDao.TABLE_NAME, null, values);
	        }
    }
    
    /**
     * 获取章节记录
     *///select * from keChengZhangJie where yhwybs='84700' and ShiPingDiZhi = 'http://m.euibe.com/EUibeCourses/acc001a/acc001a02.mp4' order by id DESC 
    synchronized public CourseDetailsShuju getKeChengZhangJieItem(String url) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        CourseDetailsShuju shuju = null;
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from " + KeChengZhangJieDao.TABLE_NAME  + " where "+KeChengZhangJieDao.COLUMN_YHWYBS+"='"+TDApp.manager.getNoDESYongHuWeiYiBiaoShi()+"' and "+KeChengZhangJieDao.COLUMN_SHIPINGDIZHI+" = '"+url+"' order by "+KeChengZhangJieDao.COLUMN_ID+" DESC " , null);
            while (cursor.moveToNext()) {
            	shuju = new CourseDetailsShuju();
                shuju.setKeChengDaiMa(cursor.getString(cursor.getColumnIndex(KeChengZhangJieDao.COLUMN_KECHENGDAIMA)));
                shuju.setZhang(cursor.getString(cursor.getColumnIndex(KeChengZhangJieDao.COLUMN_ZHANG)));
                shuju.setJie(cursor.getString(cursor.getColumnIndex(KeChengZhangJieDao.COLUMN_JIE)));
                shuju.setGuangKanBaiFenBi(cursor.getString(cursor.getColumnIndex(KeChengZhangJieDao.COLUMN_GUANGKANBAIFENBI)));
                shuju.setTotalSize(cursor.getString(cursor.getColumnIndex(KeChengZhangJieDao.COLUMN_TOTAL_SIZE)));
                shuju.setShiPinDiZhi(cursor.getString(cursor.getColumnIndex(KeChengZhangJieDao.COLUMN_SHIPINGDIZHI)));
            }
            cursor.close();
        }
        return shuju;
    }
    /**
     * 获取章节列表
     *///select * from keChengZhangJie where yhwybs='84700' and KeChengDaiMa = 'ACC201A' order by id desc
    synchronized public List<CourseDetailsShuju> getKeChengZhangJieList(String KeChengDaiMa) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<CourseDetailsShuju> list = new ArrayList<CourseDetailsShuju>();
        if (db.isOpen()) {
        	 // Cursor cursor = db.rawQuery("select * from " + KeChengZhangJieDao.TABLE_NAME  + " where "+KeChengZhangJieDao.COLUMN_YHWYBS+"='"+TDApp.manager.getNoDESYongHuWeiYiBiaoShi()+"' and "+KeChengZhangJieDao.COLUMN_SHIPINGDIZHI+" = '"+url+"' order by "+KeChengZhangJieDao.COLUMN_ID+" DESC " , null);
            Cursor cursor = db.rawQuery("select * from " + KeChengZhangJieDao.TABLE_NAME  + " where "+KeChengZhangJieDao.COLUMN_YHWYBS+"='"+TDApp.manager.getNoDESYongHuWeiYiBiaoShi()+"' and "+KeChengZhangJieDao.COLUMN_KECHENGDAIMA+" = '"+KeChengDaiMa+"' order by "+KeChengZhangJieDao.COLUMN_ID+ " DESC" , null);
            while (cursor.moveToNext()) {
            	CourseDetailsShuju shuju = new CourseDetailsShuju();
                shuju.setKeChengDaiMa(cursor.getString(cursor.getColumnIndex(KeChengZhangJieDao.COLUMN_KECHENGDAIMA)));
                shuju.setZhang(cursor.getString(cursor.getColumnIndex(KeChengZhangJieDao.COLUMN_ZHANG)));
                shuju.setJie(cursor.getString(cursor.getColumnIndex(KeChengZhangJieDao.COLUMN_JIE)));
                shuju.setGuangKanBaiFenBi(cursor.getString(cursor.getColumnIndex(KeChengZhangJieDao.COLUMN_GUANGKANBAIFENBI)));
                shuju.setTotalSize(cursor.getString(cursor.getColumnIndex(KeChengZhangJieDao.COLUMN_TOTAL_SIZE)));
                shuju.setShiPinDiZhi(cursor.getString(cursor.getColumnIndex(KeChengZhangJieDao.COLUMN_SHIPINGDIZHI)));
               
                list.add(shuju);
            }
            cursor.close();
        }
        return list;
    }
  /**
   * 获取用户已下载的文件总大小
   * @return
   */
    synchronized public List<CourseDetailsShuju> getUserZhangJieList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<CourseDetailsShuju> list = new ArrayList<CourseDetailsShuju>();
        if (db.isOpen()) {
        	 // Cursor cursor = db.rawQuery("select * from " + KeChengZhangJieDao.TABLE_NAME  + " where "+KeChengZhangJieDao.COLUMN_YHWYBS+"='"+TDApp.manager.getNoDESYongHuWeiYiBiaoShi()+"' and "+KeChengZhangJieDao.COLUMN_SHIPINGDIZHI+" = '"+url+"' order by "+KeChengZhangJieDao.COLUMN_ID+" DESC " , null);
            Cursor cursor = db.rawQuery("select * from " + KeChengZhangJieDao.TABLE_NAME  + " where "+KeChengZhangJieDao.COLUMN_YHWYBS+"='"+TDApp.manager.getNoDESYongHuWeiYiBiaoShi()+"'" , null);
            while (cursor.moveToNext()) {
            	CourseDetailsShuju shuju = new CourseDetailsShuju();
                shuju.setTotalSize(cursor.getString(cursor.getColumnIndex(KeChengZhangJieDao.COLUMN_TOTAL_SIZE)));
                shuju.setShiPinDiZhi(cursor.getString(cursor.getColumnIndex(KeChengZhangJieDao.COLUMN_SHIPINGDIZHI)));
                list.add(shuju);
            }
            cursor.close();
        }
        return list;
    }
    /**
  	 * 查看数据库中是否有数据
  	 */
      public synchronized boolean isHasInfors(String urlstr) {
    	  SQLiteDatabase db = dbHelper.getReadableDatabase();
  		int count = -1;
  		Cursor cursor = null;
  		try {
  			String sql = "select count(*)  from "+DownLoad_Info_Dao.TABLE_NAME+" where "+DownLoad_Info_Dao.COLUMN_LOCALURL+"=? and "+DownLoad_Info_Dao.COLUMN_YHWYBS+"=?";
  			cursor = db.rawQuery(sql, new String[] { urlstr,TDApp.manager.getNoDESYongHuWeiYiBiaoShi() });
  			if (cursor.moveToFirst()) {
  				count = cursor.getInt(0);
  			} 
  		} catch (Exception e) {
  			e.printStackTrace();
  		} finally {
  			if (null != cursor) {
  				cursor.close();
  			}
  		}
  		return count == 0;
  	}
  	/**
  	 * 保存 下载的具体信息
  	 */
      public synchronized void saveInfos(List<DownloadInfo> infos) {
    	SQLiteDatabase db = dbHelper.getReadableDatabase();
  		try {
  			for (DownloadInfo info : infos) {
  				String sql = "insert into "+DownLoad_Info_Dao.TABLE_NAME+"("+DownLoad_Info_Dao.COLUMN_THREAD_ID+","+DownLoad_Info_Dao.COLUMN_START_POS+","+DownLoad_Info_Dao.COLUMN_END_POS+","+DownLoad_Info_Dao.COLUMN_COMPELETE_SIZE+","+DownLoad_Info_Dao.COLUMN_URL+","+DownLoad_Info_Dao.COLUMN_LOCALURL+","+DownLoad_Info_Dao.COLUMN_YHWYBS+","+DownLoad_Info_Dao.COLUMN_FILESIZE+","+DownLoad_Info_Dao.COLUMN_KECHENGMINGCHENG+","+DownLoad_Info_Dao.COLUMN_ZHANG+","+DownLoad_Info_Dao.COLUMN_JIE+","+DownLoad_Info_Dao.COLUMN_ZHAOPIAN+") values (?,?,?,?,?,?,?,?,?,?,?,?)";
  				
  				Object[] bindArgs = { info.getThreadId(), info.getStartPos(),
  						info.getEndPos(), info.getCompeleteSize(),
  						info.getUrl(),info.getLocalUrl(),TDApp.manager.getNoDESYongHuWeiYiBiaoShi(),info.getMaxSize(),info.getKeChengMingCheng(),info.getZhang(),info.getJie(),info.getZhaoPain() };
  				db.execSQL(sql, bindArgs);
  			}
  		} catch (Exception e) {
  			e.printStackTrace();
  		} finally {
  		}
  	}

  	/**
  	 * 得到下载具体信息
  	 */
      public synchronized List<DownloadInfo> getInfos(String urlstr,String localUrl) {
    	SQLiteDatabase db = dbHelper.getReadableDatabase();
  		List<DownloadInfo> list = new ArrayList<DownloadInfo>();
  		Cursor cursor = null;
  		try {
  			//String select_download_info = "select "+ DownLoad_Info_Dao.COLUMN_THREAD_ID +"," +DownLoad_Info_Dao.COLUMN_START_POS+"," +DownLoad_Info_Dao.COLUMN_END_POS+"," +DownLoad_Info_Dao.COLUMN_COMPELETE_SIZE+"," +DownLoad_Info_Dao.COLUMN_URL +" from "+DownLoad_Info_Dao.TABLE_NAME+" where "+DownLoad_Info_Dao.COLUMN_URL+"=? and "+DownLoad_Info_Dao.COLUMN_YHWYBS+"=?";
  			
  			String select_download_info = "select * from "+DownLoad_Info_Dao.TABLE_NAME+" where "+DownLoad_Info_Dao.COLUMN_LOCALURL+"='"+localUrl+"' and "+DownLoad_Info_Dao.COLUMN_YHWYBS+"='"+TDApp.manager.getNoDESYongHuWeiYiBiaoShi()+"'";
  			
  			//String[] bindArgs = { urlstr, TDApp.manager.getNoDESYongHuWeiYiBiaoShi()};
  			cursor = db.rawQuery(select_download_info,null);
  			while (cursor.moveToNext()) {
  				
  				DownloadInfo info = new DownloadInfo(
  						cursor.getInt(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_THREAD_ID)),
  						cursor.getInt(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_START_POS)),
  						cursor.getInt(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_END_POS)),
  						cursor.getInt(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_COMPELETE_SIZE)),
  						urlstr,
  						cursor.getString(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_LOCALURL)),
  						cursor.getString(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_KECHENGMINGCHENG)),
  						cursor.getString(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_ZHANG)),
  						cursor.getString(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_JIE)),
  						cursor.getString(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_ZHAOPIAN)),
  						cursor.getInt(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_FILESIZE))
  						);
  				list.add(info);//cursor.getInt(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_STATE))
  			}
  		} catch (Exception e) {
  			e.printStackTrace();
  		} finally {
  			if (null != cursor) {
  				cursor.close();
  			}
  		}
  		return list;
  	}
      /**
       * 获取列表 
       * @param urlstr
       * @return
       */
      public synchronized List<DownloadInfo> getList() {
      	SQLiteDatabase db = dbHelper.getReadableDatabase();
    		List<DownloadInfo> list = new ArrayList<DownloadInfo>();
    		Cursor cursor = null;
    		try {
    			String select_download_info = "select * from "+DownLoad_Info_Dao.TABLE_NAME+" where "+DownLoad_Info_Dao.COLUMN_YHWYBS+"=?";
      			cursor = db.rawQuery(select_download_info, new String[] {TDApp.manager.getNoDESYongHuWeiYiBiaoShi()});
      			while (cursor.moveToNext()) {
      				
      				DownloadInfo info = new DownloadInfo(
      						cursor.getInt(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_THREAD_ID)),
      						cursor.getInt(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_START_POS)),
      						cursor.getInt(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_END_POS)),
      						cursor.getInt(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_COMPELETE_SIZE)),
      						cursor.getString(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_URL)),
      						cursor.getString(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_LOCALURL)),
      						cursor.getString(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_KECHENGMINGCHENG)),
      						cursor.getString(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_ZHANG)),
      						cursor.getString(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_JIE)),
      						cursor.getString(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_ZHAOPIAN)),
      						cursor.getInt(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_FILESIZE))
      						);
      				list.add(info);
      			}
    		} catch (Exception e) {
    			e.printStackTrace();
    		} finally {
    			if (null != cursor) {
    				cursor.close();
    			}
    		}
    		return list;
    	}
      
      
      /**
       * 获取正在下载列表 
       * @param urlstr
       * @return
       */
      public synchronized List<DownloadInfo> getDownLoadIngList(String url) {
      	SQLiteDatabase db = dbHelper.getReadableDatabase();
    		List<DownloadInfo> list = new ArrayList<DownloadInfo>();
    		Cursor cursor = null;
    		try {
    			String select_download_info = "select * from "+DownLoad_Info_Dao.TABLE_NAME+" where "+DownLoad_Info_Dao.COLUMN_LOCALURL+"=? and "+DownLoad_Info_Dao.COLUMN_YHWYBS+"=?";
      			cursor = db.rawQuery(select_download_info, new String[] {String.valueOf(url),TDApp.manager.getNoDESYongHuWeiYiBiaoShi()});
      			while (cursor.moveToNext()) {
      				
      				DownloadInfo info = new DownloadInfo(
      						cursor.getInt(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_THREAD_ID)),
      						cursor.getInt(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_START_POS)),
      						cursor.getInt(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_END_POS)),
      						cursor.getInt(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_COMPELETE_SIZE)),
      						cursor.getString(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_URL)),
      						cursor.getString(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_LOCALURL)),
      						cursor.getString(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_KECHENGMINGCHENG)),
      						cursor.getString(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_ZHANG)),
      						cursor.getString(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_JIE)),
      						cursor.getString(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_ZHAOPIAN)),
      						cursor.getInt(cursor.getColumnIndex(DownLoad_Info_Dao.COLUMN_FILESIZE))
      						);
      				list.add(info);
      			}
    		} catch (Exception e) {
    			e.printStackTrace();
    		} finally {
    			if (null != cursor) {
    				cursor.close();
    			}
    		}
    		return list;
    	}
  	/**
  	 * 更新数据库中的下载信息
  	 */
      public synchronized void updataInfos(int threadId, int compeleteSize, String urlstr,String loaclUrl) {
    	  SQLiteDatabase db = dbHelper.getReadableDatabase();
  		try {
  			String sql = "update "+DownLoad_Info_Dao.TABLE_NAME+" set "+DownLoad_Info_Dao.COLUMN_COMPELETE_SIZE+"=? where "+DownLoad_Info_Dao.COLUMN_THREAD_ID+"=? and "+DownLoad_Info_Dao.COLUMN_URL+"=? and "+DownLoad_Info_Dao.COLUMN_LOCALURL+"=? and "+DownLoad_Info_Dao.COLUMN_YHWYBS+"=?";
  			Object[] bindArgs = { compeleteSize, threadId, urlstr,loaclUrl, TDApp.manager.getNoDESYongHuWeiYiBiaoShi() };
  			db.execSQL(sql, bindArgs);
  		} catch (Exception e) {
  			e.printStackTrace();
  		} finally {
  		}
  	}


  	/**
  	 * 下载完成后删除数据库中的数据
  	 */
      public synchronized void deleteDownInfo(String url) {
    	SQLiteDatabase db = dbHelper.getReadableDatabase();
  		try {
  			db.delete(DownLoad_Info_Dao.TABLE_NAME, DownLoad_Info_Dao.COLUMN_LOCALURL+"=? and "+DownLoad_Info_Dao.COLUMN_YHWYBS+"=?", new String[] { url,TDApp.manager.getNoDESYongHuWeiYiBiaoShi() });
  		} catch (Exception e) {
  			e.printStackTrace();
  		} finally {
  		}
  	}
      
      
      /**
       * 下载完成 后 保存 课程和 章节 数据 
       */
      public  synchronized void SaveKeChengAndZhangJie(JiaoXueJiHua jxjh,CourseDetailsShuju zj){
		if(getKeChengItem(jxjh.getKeChengDaiMa())==null){
			saveKeCheng(jxjh);
		}else{
			//updateKeCheng(jxjh);
		}
		if(getKeChengZhangJieItem(zj.getUrl())==null){
	    	saveZhangJie(zj);
	     }
      }
   /* *//**
     * 保存好友list
     * 
     * @param contactList
     *//*
    synchronized public void saveContactList(List<EaseUser> contactList) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.delete(UserDao.TABLE_NAME, null, null);
            for (EaseUser user : contactList) {
                ContentValues values = new ContentValues();
                values.put(UserDao.COLUMN_NAME_ID, user.getUsername());
                if(user.getNick() != null)
                    values.put(UserDao.COLUMN_NAME_NICK, user.getNick());
                if(user.getAvatar() != null)
                    values.put(UserDao.COLUMN_NAME_AVATAR, user.getAvatar());
                db.replace(UserDao.TABLE_NAME, null, values);
            }
        }
    }

    *//**
     * 获取好友list
     * 
     * @return
     *//*
    synchronized public Map<String, EaseUser> getContactList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Map<String, EaseUser> users = new Hashtable<String, EaseUser>();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from " + UserDao.TABLE_NAME  + " desc" , null);
            while (cursor.moveToNext()) {
                String username = cursor.getString(cursor.getColumnIndex(UserDao.COLUMN_NAME_ID));
                String nick = cursor.getString(cursor.getColumnIndex(UserDao.COLUMN_NAME_NICK));
                String avatar = cursor.getString(cursor.getColumnIndex(UserDao.COLUMN_NAME_AVATAR));
                EaseUser user = new EaseUser(username);
                user.setNick(nick);
                user.setAvatar(avatar);
                if (username.equals(Constant.NEW_FRIENDS_USERNAME) || username.equals(Constant.GROUP_USERNAME)
                        || username.equals(Constant.CHAT_ROOM)|| username.equals(Constant.CHAT_ROBOT)) {
                        user.setInitialLetter("");
                } else {
                    EaseCommonUtils.setUserInitialLetter(user);
                }
                users.put(username, user);
            }
            cursor.close();
        }
        return users;
    }
    
    *//**
     * 删除一个联系人
     * @param username
     *//*
    synchronized public void deleteContact(String username){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db.isOpen()){
            db.delete(UserDao.TABLE_NAME, UserDao.COLUMN_NAME_ID + " = ?", new String[]{username});
        }
    }
    
    *//**
     * 保存一个联系人
     * @param user
     *//*
    synchronized public void saveContact(EaseUser user){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserDao.COLUMN_NAME_ID, user.getUsername());
        if(user.getNick() != null)
            values.put(UserDao.COLUMN_NAME_NICK, user.getNick());
        if(user.getAvatar() != null)
            values.put(UserDao.COLUMN_NAME_AVATAR, user.getAvatar());
        if(db.isOpen()){
            db.replace(UserDao.TABLE_NAME, null, values);
        }
    }
    
    public void setDisabledGroups(List<String> groups){
        setList(UserDao.COLUMN_NAME_DISABLED_GROUPS, groups);
    }
    
    public List<String>  getDisabledGroups(){       
        return getList(UserDao.COLUMN_NAME_DISABLED_GROUPS);
    }
    
    public void setDisabledIds(List<String> ids){
        setList(UserDao.COLUMN_NAME_DISABLED_IDS, ids);
    }
    
    public List<String> getDisabledIds(){
        return getList(UserDao.COLUMN_NAME_DISABLED_IDS);
    }
    
    synchronized private void setList(String column, List<String> strList){
        StringBuilder strBuilder = new StringBuilder();
        
        for(String hxid:strList){
            strBuilder.append(hxid).append("$");
        }
        
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(column, strBuilder.toString());

            db.update(UserDao.PREF_TABLE_NAME, values, null,null);
        }
    }
    
    synchronized private List<String> getList(String column){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select " + column + " from " + UserDao.PREF_TABLE_NAME,null);
        if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }

        String strVal = cursor.getString(0);
        if (strVal == null || strVal.equals("")) {
            return null;
        }
        
        cursor.close();
        
        String[] array = strVal.split("$");
        
        if(array != null && array.length > 0){
            List<String> list = new ArrayList<String>();
            for(String str:array){
                list.add(str);
            }
            
            return list;
        }
        
        return null;
    }
    
    *//**
     * 保存message
     * @param message
     * @return  返回这条messaged在db中的id
     *//*
    public synchronized Integer saveMessage(InviteMessage message){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int id = -1;
        if(db.isOpen()){
            ContentValues values = new ContentValues();
            values.put(InviteMessgeDao.COLUMN_NAME_FROM, message.getFrom());
            values.put(InviteMessgeDao.COLUMN_NAME_GROUP_ID, message.getGroupId());
            values.put(InviteMessgeDao.COLUMN_NAME_GROUP_Name, message.getGroupName());
            values.put(InviteMessgeDao.COLUMN_NAME_REASON, message.getReason());
            values.put(InviteMessgeDao.COLUMN_NAME_TIME, message.getTime());
            values.put(InviteMessgeDao.COLUMN_NAME_STATUS, message.getStatus().ordinal());
            values.put(InviteMessgeDao.COLUMN_NAME_GROUPINVITER, message.getGroupInviter());
            db.insert(InviteMessgeDao.TABLE_NAME, null, values);
            
            Cursor cursor = db.rawQuery("select last_insert_rowid() from " + InviteMessgeDao.TABLE_NAME,null); 
            if(cursor.moveToFirst()){
                id = cursor.getInt(0);
            }
            
            cursor.close();
        }
        return id;
    }
    
    *//**
     * 更新message
     * @param msgId
     * @param values
     *//*
    synchronized public void updateMessage(int msgId,ContentValues values){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db.isOpen()){
            db.update(InviteMessgeDao.TABLE_NAME, values, InviteMessgeDao.COLUMN_NAME_ID + " = ?", new String[]{String.valueOf(msgId)});
        }
    }
    
    *//**
     * 获取messges
     * @return
     *//*
    synchronized public List<InviteMessage> getMessagesList(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<InviteMessage> msgs = new ArrayList<InviteMessage>();
        if(db.isOpen()){
            Cursor cursor = db.rawQuery("select * from " + InviteMessgeDao.TABLE_NAME + " desc",null);
            while(cursor.moveToNext()){
                InviteMessage msg = new InviteMessage();
                int id = cursor.getInt(cursor.getColumnIndex(InviteMessgeDao.COLUMN_NAME_ID));
                String from = cursor.getString(cursor.getColumnIndex(InviteMessgeDao.COLUMN_NAME_FROM));
                String groupid = cursor.getString(cursor.getColumnIndex(InviteMessgeDao.COLUMN_NAME_GROUP_ID));
                String groupname = cursor.getString(cursor.getColumnIndex(InviteMessgeDao.COLUMN_NAME_GROUP_Name));
                String reason = cursor.getString(cursor.getColumnIndex(InviteMessgeDao.COLUMN_NAME_REASON));
                long time = cursor.getLong(cursor.getColumnIndex(InviteMessgeDao.COLUMN_NAME_TIME));
                int status = cursor.getInt(cursor.getColumnIndex(InviteMessgeDao.COLUMN_NAME_STATUS));
                String groupInviter = cursor.getString(cursor.getColumnIndex(InviteMessgeDao.COLUMN_NAME_GROUPINVITER));
                
                msg.setId(id);
                msg.setFrom(from);
                msg.setGroupId(groupid);
                msg.setGroupName(groupname);
                msg.setReason(reason);
                msg.setTime(time);
                msg.setGroupInviter(groupInviter);
                
                if(status == InviteMesageStatus.BEINVITEED.ordinal())
                    msg.setStatus(InviteMesageStatus.BEINVITEED);
                else if(status == InviteMesageStatus.BEAGREED.ordinal())
                    msg.setStatus(InviteMesageStatus.BEAGREED);
                else if(status == InviteMesageStatus.BEREFUSED.ordinal())
                    msg.setStatus(InviteMesageStatus.BEREFUSED);
                else if(status == InviteMesageStatus.AGREED.ordinal())
                    msg.setStatus(InviteMesageStatus.AGREED);
                else if(status == InviteMesageStatus.REFUSED.ordinal())
                    msg.setStatus(InviteMesageStatus.REFUSED);
                else if(status == InviteMesageStatus.BEAPPLYED.ordinal())
                    msg.setStatus(InviteMesageStatus.BEAPPLYED);
                else if(status == InviteMesageStatus.GROUPINVITATION.ordinal())
                    msg.setStatus(InviteMesageStatus.GROUPINVITATION);
                else if(status == InviteMesageStatus.GROUPINVITATION_ACCEPTED.ordinal())
                    msg.setStatus(InviteMesageStatus.GROUPINVITATION_ACCEPTED);
                else if(status == InviteMesageStatus.GROUPINVITATION_DECLINED.ordinal())
                    msg.setStatus(InviteMesageStatus.GROUPINVITATION_DECLINED);
                
                msgs.add(msg);
            }
            cursor.close();
        }
        return msgs;
    }
    
    *//**
     * 删除要求消息
     * @param from
     *//*
    synchronized public void deleteMessage(String from){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db.isOpen()){
            db.delete(InviteMessgeDao.TABLE_NAME, InviteMessgeDao.COLUMN_NAME_FROM + " = ?", new String[]{from});
        }
    }
    
    synchronized int getUnreadNotifyCount(){
        int count = 0;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        if(db.isOpen()){
            Cursor cursor = db.rawQuery("select " + InviteMessgeDao.COLUMN_NAME_UNREAD_MSG_COUNT + " from " + InviteMessgeDao.TABLE_NAME, null);
            if(cursor.moveToFirst()){
                count = cursor.getInt(0);
            }
            cursor.close();
        }
         return count;
    }
    
    synchronized void setUnreadNotifyCount(int count){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db.isOpen()){
            ContentValues values = new ContentValues();
            values.put(InviteMessgeDao.COLUMN_NAME_UNREAD_MSG_COUNT, count);

            db.update(InviteMessgeDao.TABLE_NAME, values, null,null);
        }
    }
    
    synchronized public void closeDB(){
        if(dbHelper != null){
            dbHelper.closeDB();
        }
        dbMgr = null;
    }
    
    
    *//**
     * Save Robot list
     *//*
	synchronized public void saveRobotList(List<RobotUser> robotList) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		if (db.isOpen()) {
			db.delete(UserDao.ROBOT_TABLE_NAME, null, null);
			for (RobotUser item : robotList) {
				ContentValues values = new ContentValues();
				values.put(UserDao.ROBOT_COLUMN_NAME_ID, item.getUsername());
				if (item.getNick() != null)
					values.put(UserDao.ROBOT_COLUMN_NAME_NICK, item.getNick());
				if (item.getAvatar() != null)
					values.put(UserDao.ROBOT_COLUMN_NAME_AVATAR, item.getAvatar());
				db.replace(UserDao.ROBOT_TABLE_NAME, null, values);
			}
		}
	}
    
    *//**
     * load robot list
     *//*
	synchronized public Map<String, RobotUser> getRobotList() {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Map<String, RobotUser> users = null;
		if (db.isOpen()) {
			Cursor cursor = db.rawQuery("select * from " + UserDao.ROBOT_TABLE_NAME, null);
			if(cursor.getCount()>0){
				users = new Hashtable<String, RobotUser>();
			};
			while (cursor.moveToNext()) {
				String username = cursor.getString(cursor.getColumnIndex(UserDao.ROBOT_COLUMN_NAME_ID));
				String nick = cursor.getString(cursor.getColumnIndex(UserDao.ROBOT_COLUMN_NAME_NICK));
				String avatar = cursor.getString(cursor.getColumnIndex(UserDao.ROBOT_COLUMN_NAME_AVATAR));
				RobotUser user = new RobotUser(username);
				user.setNick(nick);
				user.setAvatar(avatar);
				String headerName = null;
				if (!TextUtils.isEmpty(user.getNick())) {
					headerName = user.getNick();
				} else {
					headerName = user.getUsername();
				}
				if(Character.isDigit(headerName.charAt(0))){
					user.setInitialLetter("#");
				}else{
					user.setInitialLetter(HanziToPinyin.getInstance().get(headerName.substring(0, 1)).get(0).target
							.substring(0, 1).toUpperCase());
					char header = user.getInitialLetter().toLowerCase().charAt(0);
					if (header < 'a' || header > 'z') {
						user.setInitialLetter("#");
					}
				}
				
				users.put(username.toLowerCase(), user);
			}
			cursor.close();
		}
		return users;
	}*/
    
    
    
}
