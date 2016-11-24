package com.td.tradedistance.app.utils;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.td.tradedistance.app.ApiGlobal;
import com.td.tradedistance.app.TDApp;
import com.td.tradedistance.app.bean1.DownloadFile;
import com.td.tradedistance.app.bean1.KeCheng;
import com.td.tradedistance.app.bean1.XiaZai;
import com.td.tradedistance.app.bean1.ZhangJie;
import com.td.tradedistance.app.global.Global;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.Toast;


public class DownLoadUtil {
	public static  int downLoad(String serverpath, String savedfilepath,
			ProgressBar pb,final Map<Integer, DownloadFile> map, final int position,final Handler handler) {
		FileOutputStream fos = null;
		InputStream is = null;
		final DownloadFile downloadFile = new DownloadFile();
		int code = 0;
		try {
			URL url = new URL(serverpath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			 code = conn.getResponseCode();
			
			if (code == 200) {
				int max = conn.getContentLength();
				pb.setMax(max);
				is = conn.getInputStream();
				File file = new File(savedfilepath);
				fos = new FileOutputStream(file);
				int len = 0;
				byte[] buffer = new byte[300];
				int total=0;
				while ((len = is.read(buffer)) != -1) {
					//if(!Global.pause){
						fos.write(buffer, 0, len);
						total+=len;
						pb.setProgress(total);
						downloadFile.setTotal(buffer.length);
						downloadFile.setDownloadSize(total);
						downloadFile.setPause(true);
						map.put(position, downloadFile);
				        Message message = new Message();
				        handler.sendMessage(message);
					//}
				}
				fos.flush();
				fos.close();
				is.close();
				return code;
			} else {
				return code;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return code;
		}finally{
			try {
				if(fos!=null)
					 fos.close();
			    if(is != null)
					 is.close();
			    return code;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

	}
	
	public static  int downLoadCourse(XiaZai xz, String savedfilepath,
			ProgressBar pb,final Context ctx,final Handler handler,final Map<Integer, DownloadFile> map, final int position,final KeCheng kc,final ZhangJie zj,final ProgressUtils proDailog,final Map<String, Boolean> isDownMap) {
		FileOutputStream fos = null;
		InputStream is = null;
		int code = 0;
		try {
			URL url = new URL(ApiGlobal.xiaZaiUrl+"XiaZai.ashx?sqm="+xz.getSqm()+"&xt="+xz.getXt()+"&zdsbm="+xz.getZdsbm()+"&lmdm="+xz.getLmdm()+"&xxbs="+xz.getXxbs());
			Logger.d("downLoadCourse", ApiGlobal.xiaZaiUrl+"XiaZai.ashx?sqm="+xz.getSqm()+"&xt="+xz.getXt()+"&zdsbm="+xz.getZdsbm()+"&lmdm="+xz.getLmdm()+"&xxbs="+xz.getXxbs());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			 code = conn.getResponseCode();
			 DownloadFile downloadFile = new DownloadFile();
			if (code == 200) {
				isDownMap.put(zj.getXiaZaiDiZhi(), true);
				if(proDailog != null)
					proDailog.destry();
				int max = conn.getContentLength();
				pb.setMax(max);
				is = conn.getInputStream();
				
				if(is == null){
					//Toast.makeText(HcmApp.getContext(), Global.noFile, Toast.LENGTH_SHORT);
					return 404;
				}
				
				File file = new File(savedfilepath);
				fos = new FileOutputStream(file);
				int len = 0;
				byte[] buffer = new byte[1024];
				int total=0;
				while ((len = is.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
					total+=len;
					downloadFile.setTotal(max);
					downloadFile.setDownloadSize(total);
					map.put(position, downloadFile);
					 Message message = new Message();
			        handler.sendMessage(message);
				}
				 /*setDB(kc,ctx,zj);
				 download_pic_ib.setBackgroundResource(R.drawable.downloaded);
				 Looper.prepare();  
				 GoToMyCourse(ctx);  
				 if(zj.getType().equals("mp4"))
					 play.setVisibility(View.VISIBLE);
                 Looper.loop(); */
                 fos.flush();
                 fos.close();
 				is.close();
 				return code;
			} else {
				return code;
				//Toast.makeText(HcmApp.getContext(), Global.Downfailed, Toast.LENGTH_SHORT).show();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return code;
		}finally{
			try {
				if(fos!=null)
					 fos.close();
			    if(is != null)
					 is.close();
			    return code;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	public static void GoToMyCourse(Context ctx){
		Toast.makeText(ctx, "课程已下载，请到“我的课程”进行学习。", Toast.LENGTH_LONG).show();
		//ComDialog.GoToMyCourse(ctx);
	}
	/*public static void setDB(KeCheng kc,Context mcontext,ZhangJie zj){
		 ContentValues mycourse = new ContentValues();
        mycourse.put(DBHelper1.KECHENDDAIMA, kc.getKeChengDaiMa());
        mycourse.put(DBHelper1.KECHENGMING, kc.getKeChengMing());
        mycourse.put(DBHelper1.TUPIAN, kc.getTuPian());
        mycourse.put(DBHelper1.ZHANGJISHU,kc.getZhangJieShu());
        mycourse.put(DBHelper1.JIESHAO, kc.getJieShao());
        mycourse.put(DBHelper1.YHWYBS, TDApp.manager.getNoDESYongHuWeiYiBiaoShi());
        mycourse.put(DBHelper1.ZILANMUID, kc.getZiLanMuID());
        if(!HcmLDao1.getDao(mcontext).getItem(DBHelper1.Sql5, kc.getKeChengDaiMa())){
        	HcmLDao1.getDao(mcontext).insert(DBHelper1.TABLE_MY_COURSE, mycourse);
        }else{
        	HcmLDao1.getDao(mcontext).UpdateMyCourse(kc.getKeChengDaiMa(),kc.getZhangJieShu(),kc.getKeChengMing(),Integer.parseInt(kc.getZiLanMuID()));
        }
        ContentValues zhangjie = new ContentValues();
		     zhangjie.put(DBHelper1.ZHANGJIEID, zj.getZhangJieID());
		     zhangjie.put(DBHelper1.ZHANGJIEMING, zj.getZhangJieMing());
		     zhangjie.put(DBHelper1.XIAZAIDIZHI, zj.getXiaZaiDiZhi());
		     zhangjie.put(DBHelper1.KECHENDDAIMA, kc.getKeChengDaiMa());
		     zhangjie.put(DBHelper1.LMDM, kc.getLmdm());
		     zhangjie.put(DBHelper1.TYPE, zj.getType());
		     zhangjie.put(DBHelper1.ZHANGJIEXUHAO, zj.getZhangJieXuHao());
		     zhangjie.put(DBHelper1.YHWYBS, TDApp.manager.getNoDESYongHuWeiYiBiaoShi());
		     zhangjie.put(DBHelper1.JiaMiBanBenBianHao, zj.getJiaMiBanBenBianHao());
		     if(!HcmLDao1.getDao(mcontext).getItem(DBHelper1.Sql6, zj.getZhangJieID())){
		    	 HcmLDao1.getDao(mcontext).insert(DBHelper1.TABLE_ZHANG_JIE, zhangjie);
		     }
	}*/
	/*public static boolean decompress(String srcfilepath, String savedfilepath ) throws IOException {
		boolean b  = false;
		boolean c = false;
		boolean d = false;
		ZipInputStream in = new ZipInputStream(new FileInputStream(new File(
				srcfilepath)));
		ZipEntry entry;
		try {
			 while ((entry = in.getNextEntry()) != null) {
				     //Logger.d("entry.getName()", entry.getName());
					 if(Global.CourseIndex.equals(entry.getName())){
				    	 b = true;
				    	 downFile(savedfilepath,in,Global.CourseIndex);
				    	 c = true;
				     }
				      //Logger.d("entry.getName()", entry.getName());
				     if(Global.imgName.equals(entry.getName().split("\\.")[0])){
						  b = true;
						  d = true;
						 if(entry.getName().indexOf("\\.") == -1){
							 downFile(savedfilepath,in,Global.imgName);
						 }
				     }
				    if(c&&d) break;
			}
			return b;
		} finally { 
			in.close();
		}
	}*/
	
	public static void downFile(String savedfilepath,ZipInputStream in,String name){
		byte[] buffer = new byte[1024];
		int len;
		 FileOutputStream out;
		try {
			out = new FileOutputStream(new File(savedfilepath,name));
				 try {
					while ((len = in.read(buffer)) > 0) {
						 try {
							out.write(buffer, 0, len);
						} catch (IOException e) {
							e.printStackTrace();
						}
					 }
				} catch (IOException e) {
					e.printStackTrace();
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
					
	}
	
	public static File downLoadVersion(String serverpath, String savedfilepath,
			ProgressDialog pb) {
		FileOutputStream fos = null;
		InputStream is = null;
		try {
			URL url = new URL(serverpath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			
			if (conn.getResponseCode() == 200) {
				int max = conn.getContentLength();
				pb.setMax(max);
				is = conn.getInputStream();
				File file = new File(savedfilepath);
				fos = new FileOutputStream(file);
				int len = 0;
				byte[] buffer = new byte[1024];
				int total=0;
				while ((len = is.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
					total+=len;
					pb.setProgress(total);
				}
				fos.flush();
				fos.close();
				is.close();
		    	
				return file;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				if(fos!=null)
				   fos.close();
				if(is != null)
				   is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

	}
}



