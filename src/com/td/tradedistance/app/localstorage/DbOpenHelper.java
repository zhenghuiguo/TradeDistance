package com.td.tradedistance.app.localstorage;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper{
    private static int DATABASE_VERSION = 1;
    private static DbOpenHelper instance;
    
    
    private static final String LOGIN_RECORD_TABLE_CREATE = "CREATE TABLE "
			+ LoginRecordDao.TABLE_NAME + " ("
			+ LoginRecordDao.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ LoginRecordDao.COLUMN_NAME_ID + " TEXT, "
			+ LoginRecordDao.COLUMN_LOCK_FLAG + " INTEGER, "
			+ LoginRecordDao.COLUMN_FAILURE_NUM + " INTEGER, "
	        + LoginRecordDao.COLUMN_LOGIN_DATE + " TEXT); ";
    
    
    private static final String KE_CHENG_TABLE_CREATE = "CREATE TABLE "
			+ KeChengDao.TABLE_NAME + " ("
			+ KeChengDao.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ KeChengDao.COLUMN_KECHENGDAIMA + " TEXT, "
			+ KeChengDao.COLUMN_KECHENGMINGCHENG + " TEXT, "
			+ KeChengDao.COLUMN_KECHENGZHAOPIAN + " TEXT, "
			+ KeChengDao.COLUMN_YHWYBS + " TEXT, "
		    + KeChengDao.COLUMN_COUNT + " INTEGER, "
			+ KeChengDao.COLUMN_XUEWWEIKE + " INTEGER, "
		    + KeChengDao.COLUMN_BEIYONG1 + " TEXT, "
		    + KeChengDao.COLUMN_BEIYONG2 + " TEXT, "
	        + KeChengDao.COLUMN_BEIYONG3 + " TEXT); ";
    
    
    private static final String KE_CHENG_ZHANGJIE_TABLE_CREATE = "CREATE TABLE "
			+ KeChengZhangJieDao.TABLE_NAME + " ("
			+ KeChengZhangJieDao.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ KeChengZhangJieDao.COLUMN_KECHENGDAIMA + " TEXT, "
			+ KeChengZhangJieDao.COLUMN_SHIPINGDIZHI + " TEXT, "
			+ KeChengZhangJieDao.COLUMN_ZHANG + " TEXT, "
			+ KeChengZhangJieDao.COLUMN_JIE + " TEXT, "
			+ KeChengZhangJieDao.COLUMN_YHWYBS + " TEXT, "
			+ KeChengZhangJieDao.COLUMN_GUANGKANBAIFENBI + " TEXT, "
			+ KeChengZhangJieDao.COLUMN_TOTAL_SIZE + " TEXT, "
			+ KeChengZhangJieDao.COLUMN_XUHAO + " INTEGER, "
		    + KeChengZhangJieDao.COLUMN_BEIYONG1 + " TEXT, "
		    + KeChengZhangJieDao.COLUMN_BEIYONG2 + " TEXT, "
	        + KeChengZhangJieDao.COLUMN_BEIYONG3 + " TEXT); ";
    
    @SuppressWarnings("unused")
	private static final String DOWNLOAD_INFO_TABLE_CREATE = "CREATE TABLE "
 			+ DownLoad_Info_Dao.TABLE_NAME + " ("
 			+ DownLoad_Info_Dao.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
 			+ DownLoad_Info_Dao.COLUMN_KE_CHENG_DAI_MA + " TEXT, "
 			+ DownLoad_Info_Dao.COLUMN_START_POS + " INTEGER, "
 			+ DownLoad_Info_Dao.COLUMN_END_POS + " INTEGER, "
 			+ DownLoad_Info_Dao.COLUMN_YHWYBS + " TEXT, "
 			+ DownLoad_Info_Dao.COLUMN_THREAD_ID + " INTEGER, "
 			+ DownLoad_Info_Dao.COLUMN_FILESIZE + " INTEGER, "
 			+ DownLoad_Info_Dao.COLUMN_STATE + " INTEGER, "
 			+ DownLoad_Info_Dao.COLUMN_ZHAOPIAN + " TEXT, "
 			+ DownLoad_Info_Dao.COLUMN_KECHENGMINGCHENG + " TEXT, "
 			+ DownLoad_Info_Dao.COLUMN_ZHANG + " TEXT, "
 			+ DownLoad_Info_Dao.COLUMN_JIE + " TEXT, "
 			+ DownLoad_Info_Dao.COLUMN_COMPELETE_SIZE + " INTEGER, "
 			+ DownLoad_Info_Dao.COLUMN_URL + " TEXT, "
 			+ DownLoad_Info_Dao.COLUMN_LOCALURL + " TEXT, "
 		    + DownLoad_Info_Dao.COLUMN_BEIYONG1 + " TEXT, "
 		    + DownLoad_Info_Dao.COLUMN_BEIYONG2 + " TEXT, "
 	        + DownLoad_Info_Dao.COLUMN_BEIYONG3 + " TEXT); ";

	private DbOpenHelper(Context mCtx) {
		super(mCtx, getName(mCtx), null, DATABASE_VERSION);
	}
    public static DbOpenHelper getInstance(Context mCtx){
    	if(instance == null) instance = new DbOpenHelper(mCtx);
    	return instance;
    }
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(LOGIN_RECORD_TABLE_CREATE);
		db.execSQL(KE_CHENG_TABLE_CREATE);
		db.execSQL(KE_CHENG_ZHANGJIE_TABLE_CREATE);
		db.execSQL(DOWNLOAD_INFO_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	public static String getName(Context mCtx){
		return "td.db";
	}

	public static void closeDB(){
		if(instance!=null){
			try {
				SQLiteDatabase db = instance.getWritableDatabase();
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			instance = null;
		}
	}

}
