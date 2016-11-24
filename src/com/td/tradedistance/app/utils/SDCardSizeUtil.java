package com.td.tradedistance.app.utils;

import java.io.File;
import java.util.List;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;

import com.td.tradedistance.app.TDApp;
import com.td.tradedistance.app.bean.CourseDetailsShuju;
import com.td.tradedistance.app.localstorage.DownLoad_Info_Dao;
import com.td.tradedistance.app.localstorage.KeChengZhangJieDao;

import down.DownloadInfo;

public class SDCardSizeUtil {

	private static final String tag = SDCardSizeUtil.class.getSimpleName();
	/**
	 * 计算SDcard 的可用空间，SD卡的剩余空间小于某个值返回false，如果有足够的空间，则返回true
	 * @param sizeMb
	 * @return
	 */
	public static boolean isAvaiableSpace(int sizeMb) {
		boolean ishasSpace = false;
		if (externalMemoryAvailable()) {
			String sdcard = Environment.getExternalStorageDirectory().getPath();
			StatFs statFs = new StatFs(sdcard);
			long blockSize = statFs.getBlockSize();
			long blocks = statFs.getAvailableBlocks();
			long availableSpare = (blocks * blockSize) / (1024 * 1024);
			Logger.d(tag, "可用空间 = " + availableSpare);
			if (availableSpare > sizeMb) {
				ishasSpace = true;
			}
		}
		return ishasSpace;
	}
	public static long getAvailableInternalMemorySize(){  
        File path = Environment.getDataDirectory();  //获取数据目录  
        StatFs stat = new StatFs(path.getPath());  
        long blockSize = stat.getBlockSize();  
        long availableBlocks = stat.getAvailableBlocks();  
        return availableBlocks*blockSize;  
	}  
		      
	public static long getTotalInternalMemorySize(){  
	        File path = Environment.getDataDirectory();  
	        StatFs stat = new StatFs(path.getPath());  
	        long blockSize = stat.getBlockSize();  
	        long totalBlocks = stat.getBlockCount();  
	        return totalBlocks*blockSize;  
	}  
		      
	public static boolean externalMemoryAvailable(){  
	        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);  
	}  
		      
	
	
	
	
	
	public static boolean getAvailableExternalMemorySize(long size){ 
		    boolean ishasSpace = false;
	        if(externalMemoryAvailable()){  
	            File path = Environment.getExternalStorageDirectory();  
	            StatFs stat = new StatFs(path.getPath());  
	            long blockSize = stat.getBlockSize();  
	            long availableBlocks = stat.getAvailableBlocks();  
	            long availableSpare = (blockSize * availableBlocks);
	            Logger.d(tag, "可用空间 = " + availableSpare);
				if (availableSpare > size) {
					ishasSpace = true;
				}
	        }  
	       return ishasSpace;  
	}  
	
	
	
	/*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2)
	{
	blockSize = stat.getBlockSizeLong();
	totalBlocks = stat.getBlockCountLong();
	availableBlocks = stat.getAvailableBlocksLong();
	}
	else
	{
	blockSize = stat.getBlockSize();
	totalBlocks = stat.getBlockCount();
	availableBlocks = stat.getAvailableBlocks();
	}*/
	      /**
	       * 获取总大小
	       */
	public static long getTotalExternalMemorySize(){  
	        if(externalMemoryAvailable()){  
	            File path = Environment.getExternalStorageDirectory();  
	            StatFs stat = new StatFs(path.getPath());  
	            long blockSize = stat.getBlockSize();  
	            long totalBlocks = stat.getBlockCount();  
	            return totalBlocks*blockSize;  
	        }  
	        else{  
	            return -1;  
	        }  
	}  
	 /**
     * 获取可用空间大小
     */
	public static long getSDKeYongKongJian(){  
        File path = Environment.getDataDirectory();  //获取数据目录  
        StatFs stat = new StatFs(path.getPath());  
        long blockSize = stat.getBlockSize();  
        long availableBlocks = stat.getAvailableBlocks();  
        return availableBlocks*blockSize;  
	}  
	 /**
     * 获取占用空间大小
     */
	public static long getSDYiYongKongJian(){  
        
        return getSDTotleKongJian()-getSDKeYongKongJian();  
	}  
	public static long getSDTotleKongJian(){  
        File path = Environment.getDataDirectory();  //获取数据目录  
        StatFs stat = new StatFs(path.getPath());  
        long totalBlocks = stat.getBlockCount();  
        return totalBlocks;
	}  
	/**
	 * 判断SD卡是否可用
	 */
	public static boolean isSDCardEnable() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}

	/**
	 * 计算缓存文件总大小
	 * @param dirFile
	 * @param state
	 *            1为查询大小，2为删除文件
	 * @return
	 */
	public static long manageFile(int state) {
        String dirPath = FileOperationUtils.getMycoursePath()+TDApp.manager.getNoDESYongHuWeiYiBiaoShi();
		Logger.i(tag, "文件的路径是：" + dirPath);
		File dirFile = new File(dirPath);
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		File[] files = dirFile.listFiles();
		Logger.i(tag, "文件的长度为："+files.length);
		long size = 0;
		if (state == 1) {
			for (int i = 0; i < files.length; i++) {
				size += files[i].length();
			}
			Logger.i(tag, "缓存文件的大小是：" + size + " M");
			return size;
		} else if (state == 2) {
			if(files.length > 0){
				List<CourseDetailsShuju> list = new KeChengZhangJieDao().getUserZhangJieList();
				
					for (int i = 0; i < files.length; i++) {
					
						for(int k=0;k<list.size();k++){
							CourseDetailsShuju shuju = list.get(k);
							String name  = FileOperationUtils.getFileName(shuju.getShiPinDiZhi());
							if(!name.equals(files[i].getName())){
								files[i].delete();
								Logger.i(tag, name);
							}
						}
						
						
					}
				return 1;
			}else{
				Logger.i(tag, "没有文件");
				return 2;
			}
		}else{
			return -1;
		}
	}
   public static String getFileSize(int filesSize){
	   filesSize = filesSize /1024/1024;
	   return filesSize+"M";
   }
   
   public static String formatSize(Context c,long size) {
	   return Formatter.formatFileSize(c, size);
   }
   public static String getKongJianDaXiao(Context c,DownLoad_Info_Dao downDao,KeChengZhangJieDao keChengZhangJieDao){
	    long kykj = SDCardSizeUtil.getSDKeYongKongJian();
		//long totalkj = SDCardSizeUtil.getTotalExternalMemorySize();
		//long zykj = totalkj-kykj;
		//return "占用空间"+SDCardSizeUtil.formatSize(c,zykj)+"，可用空间"+SDCardSizeUtil.formatSize(c,kykj);
	    String zykj = null;
	 //   float size =  ((float)manageFile(1))/1000/1000/1000;
	    float wxzListSize =  downDao.getWeiXiaZaiListTotalSize();
	    float size = (keChengZhangJieDao.getUserFileTotalSize()+wxzListSize)/1024/1024/1024;
	    if(size == 0){
	    	zykj ="0.00";
	    }else{
	    	zykj =String.format("%.2f", size);
	    }
	    float kykjdx = ((float)kykj)/1000/1000/1000;
	    return "占用空间"+zykj+"G，可用空间"+String.format("%.2f",kykjdx)+"G";
		
   }
   
   
}