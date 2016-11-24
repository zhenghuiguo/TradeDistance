package com.td.tradedistance.app.utils;



import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import com.td.tradedistance.app.TDApp;

import android.content.Context;
import android.os.Environment;
/**
 * I/O 操作类
 */
public class FileOperationUtils {
    private String SDPATH; // 用于存sd card的文件的路径

    /**
     * 构造方法 获取SD卡路径
     *
     * @return
     */
    public FileOperationUtils() {
        // 获得当前外部存储设备的目录
        SDPATH = Environment.getExternalStorageDirectory() + "/";
    }

    /**
     * 在SD卡上创建文件
     *
     * @throws IOException
     */
    public File createSDFile(String fileName) throws IOException {
       // Logger.d("创建文件路径", SDPATH + fileName);
        File file = new File(SDPATH + fileName);
        file.createNewFile();
        return file;
    }

    /**
     * 在SD卡上创建目录
     */
    public boolean createSDDir(String dirName) {
        File dir = new File(SDPATH + dirName);
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            if (!dir.isFile()) {
                dir.mkdir();
            }
            return true;
        }
        return false;
    }

    /**
     * 判断SD卡上的文件夹是否存在
     */
    public boolean isFileExist(String fileName) {
        File file = new File(SDPATH + fileName);
        return file.exists();
    }
    public static boolean isSD() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
    public static boolean delFile(String url){
    	
    	File file = new File(url);
    	if(file.exists()){
    		file.delete();
    		return true;
    	}
    	return false;
    }
    public static byte[] readFile(File file) throws IOException {
        long len = file.length();
        byte[] bytes = new byte[(int) len];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        int r = bufferedInputStream.read(bytes);
        if (r != len)
            throw new IOException("");
        bufferedInputStream.close();
        return bytes;
    }
    public static void WriteFile(String json,String path){
    	fileIsMkdirs(path);
    	 File dest = new File(path);  
    	 try {  
    	     BufferedWriter writer  = new BufferedWriter(new FileWriter(dest));  
    	     String line = json;  
    	     writer.write(line);
    	     writer.flush();  
    	     writer.close();  
    	 } catch (FileNotFoundException e) {  
    	     e.printStackTrace();  
    	 } catch (IOException e) {  
    	     e.printStackTrace();  
    	 }  

    }
    @SuppressWarnings("unused")
	public static String readFileStr(String path){ 
    	fileIsMkdirs(path);
    	File file = new File(path); 
    	BufferedReader reader = null; 
    	String tempString =""; 
    	try { 
	    	reader = new BufferedReader(new FileReader(file)); 
	    	String str=reader.readLine();
	    	while (str != null){ 
	    		tempString += str;
	    		str = reader.readLine();
	    	} 
	    	reader.close(); 
	    } catch (IOException e) { 
	    		e.printStackTrace(); 
	    } finally { 
		    	if (reader != null){ 
			    	try { 
			    	reader.close(); 
			    	} catch (IOException e1) { 
			    	} 
		    	} 
	     }
		return tempString; 
    }  
    public static boolean isFile(String filePath) {
        boolean result = false;
        File file = new File(filePath);
        if (file.exists()) {
            result = true;
        }
        return result;
    }
    public static String getFileName(String serverurl){
    	if(serverurl!=null && serverurl.length()>0)
    			return serverurl.substring(serverurl.lastIndexOf("/")+1);
    	return ""; 
    	
	}
    public static String getFileNamePath12(String serverurl){
		return serverurl.substring(0,serverurl.lastIndexOf(".")+1);
	}
    public static String getFileType(String serverurl){
    	     if(serverurl.lastIndexOf(".") == -1) return null;
		return serverurl.substring(serverurl.lastIndexOf(".")+1);
	}
    public static String getBasePath(){
    	return Environment.getExternalStorageDirectory().getPath() + "/TD/";
    }
    public static String getCourseCenterPath(){
    	return getBasePath()+"CourseCenter/";
    }
    public static String getTuPianPath(){
    	return getCourseCenterPath()+"cache/";
    }
    public static String getMycoursePath(){
    	return getCourseCenterPath()+"mycourse/";
    }
   /* public static String getMycoursePathAddYhwybs(Context mcontext){
    	return getMycoursePath()+UserInfo.getNoDecYongHuBiaoShi(mcontext)+"/";
    }*/
    public static String getMycoursePathAddYhwybs(){
    	return getMycoursePath()+TDApp.manager.getNoDESYongHuWeiYiBiaoShi()+"/";
    }
    public static String getJPYDPath(){
    	return getBasePath()+"jpyd/";
    }
    public static String getMoueeCourseDome(){
    	return getBasePath() + "mouee/coursedome/";
    }
    public static String getCachePath(){
    	return getBasePath()+".cache/";
    }
    public static String getCachePath1(){
    	return getBasePath()+"cache/";
    }
    public static String getHeadPath(){
    	return getBasePath()+"head/";
    }
    public static String getJsonPath(){
    	return getCachePath()+TDApp.manager.getNoDESYongHuWeiYiBiaoShi()+"/.bat/";
    }
    public static String getMoueePath(){
    	return Environment.getExternalStorageDirectory().getPath()+"/mouee/";
    }
    public static String getLearnResPath(){
    	return getBasePath()+"learnresouce/";
    }
    public static void  MkDirs(String path){
    	File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
    public static String getName(String name){
    	String str = name;
        if(str != null)
        	return str = str.split(".zip")[0];
    	return null;
    }
    public static String getDZZlPath(){
    	return getBasePath()+"dzzl/";
    }
    public static String getDZZlPathAddYhwybs(Context mcontext){
    	return getDZZlPath()+TDApp.manager.getNoDESYongHuWeiYiBiaoShi()+"/";
    }
    public static String getTempPath(){
    	return getLogPath()+".temp/";
    }
    public static String getLogPath(){
    	return getBasePath()+".log/";
    }
    public static void fileIsMkdirs(String path){
    	if(!FileOperationUtils.isFile(path)){
			 File dir = new File(path.substring(0, path.lastIndexOf("/")));
		        if (Environment.getExternalStorageState().equals(
		                Environment.MEDIA_MOUNTED)) {
		            if (!dir.isFile()) {
		                dir.mkdirs();
		            }
		        }
		}
    }
    
    public static boolean rename(String path,String oldName, String newName) {
    	try  {   
	    		File source = new File(path+oldName);  
	    		File target = new File(source.getParent()+"/"+newName);   
	    		return source.renameTo(target);
    		}  catch (Exception e)  {
    			e.printStackTrace();
    		}
			return false; 
    }
    
    public static String getNames(String name){
    	String str = name;
        if(str != null){
        	if(str.indexOf(".")== -1) return str;
        	       str = str.substring(0,str.indexOf("."));
        	return str;
        }
    	return null;
    }
    public static boolean isAvaiableSpace(String path){
    		return SDCardSizeUtil.getAvailableExternalMemorySize(new File(path).length()+2*1024);
    }
    public static boolean avaiableSpaceTip(String path,Context context){
    	if(isAvaiableSpace(path)){
    		return true;
    	}
    	return false;
    }
 
    public  static boolean copyFile(String oldPath, String newPath) {
   	 try { 
   		 int byteread = 0;
   		 File oldfile = new File(oldPath);  
   		 if (oldfile.exists()) { 
   			 //文件存在时   
   			 InputStream inStream = new FileInputStream(oldPath);
   			 //读入原文件              
   			 FileOutputStream fs = new FileOutputStream(newPath); 
   			 byte[] buffer = new byte[1024]; 
   			 while ( (byteread = inStream.read(buffer)) != -1) {  
   				 fs.write(buffer, 0, byteread); 
   		     }               
   			 	inStream.close();  
   			 	fs.close();
   			    delFile(oldPath);
   			 	 return true;
   			 }      
   		 }catch (Exception e) {  
   			 return false;
   		 }   
   	 return false;
   	 } 
   
    public static void deleteCache1(){
    	File file = new File(getCachePath1());
    	if(file.exists()){
    		deleteAllWjjAndwj(file);
    	}
    }
    
    
    public static void deleteAllWjjAndwj(File file){
 	   File[] files = file.listFiles();
 	   if (files == null || files.length == 0) {
 			return;
 	   }
 	   for(File f : files){
 		   if(f.isFile())
 			   f.delete();
 		   else 
 			  deleteAllWjjAndwj(f);//递归删除每一个文件
 	   }
    }
    public static void deleteAll1(String file){
   	 File wjj= new File(file);
   	 if(wjj.exists()){
   		 File[] f = wjj.listFiles();
   		 for(int i=0;i<f.length;i++){
   			 f[i].delete();
   		 }
   		 wjj.delete();
   	 }
   }
    public static void deleteAll(String file){
    	 File wjj= new File(file);
    	 if(wjj.exists()){
    		 File[] f = wjj.listFiles();
    		 for(int i=0;i<f.length;i++){
    			 f[i].delete();
    		 }
    	 }
    }
}

