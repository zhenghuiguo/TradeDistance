package com.td.tradedistance.app;

import java.net.URLEncoder;


import com.td.tradedistance.app.bean.CourseDetailsShuju;
import com.td.tradedistance.app.global.Global;
import com.td.tradedistance.app.utils.DES;

//15600693086   abc123
public class ApiGlobal {
	// TODO 基地址 ceshi
	
	public static String xiaZaiUrl = "http://m.chinahcm.com/XiaZai/";// 外部发版正式地址
	//public static String baseUrl = "http://192.168.100.88:8010/Interface/YiDongApp/";// "http://221.123.188.94/JieKou/";
   // public static String XiaoXiZhongXiaoUrl = "http://192.168.100.88:8010/Interface/YiDongApp/tongzhigonggao.html";
	//public static String AboutMeUrl = "http://192.168.100.88:8010/Interface/YiDongApp/guanyuwomen.html";
	// TODO 基地址 Release
	public static String baseUrl = "http://lms.euibe.com/euibestudent/Interface/YiDongApp/";// "http://221.123.188.94/JieKou/";
    public static String XiaoXiZhongXiaoUrl = "http://lms.euibe.com/euibestudent/Interface/YiDongApp/tongzhigonggao.html";
	public static String AboutMeUrl = "http://lms.euibe.com/euibestudent/Interface/YiDongApp/guanyuwomen.html";

	public final static String RequestEncoding = "UTF-8";

	public final static void setBaseUrl(final String url) {
		baseUrl = url != null ? (url.endsWith("/") ? url : url + "/") : null;
	}

	public final static String getBaseUrl() {
		return baseUrl != null ? baseUrl : "";
	}

	public final static String getUrl(final String action) {

		/*
		 * if("DengLu".equals(action)){ return
		 * getBaseUrl()+"ChinaHCM/".concat(action).concat(".ashx"); }
		 */
		return getBaseUrl().concat(action).concat(".ashx");
	}

	public static final int REQUEST_TIMEOUT = 30 * 1000;// 设置请求超时5秒钟
	public static final int SO_TIMEOUT = 30 * 1000; // //设置等待数据超时时间5秒钟
	public static final boolean DEBUG = false;// 日志标记 //日志标记 true:开日志 false：关日志
	public static final String threadCount = "1";
	/**
	 * sd卡低30M
	 */
	public static final int isAvaiableSpace = 30;
	public static final int BufferSize =  100920;//100920;
	public static final int RollBuffer = 8096;
	/**
	 * 3M
	 */
	public static long max = 3000000;

	public static String getXiaZaiUrl(CourseDetailsShuju shuJu) {
		
		StringBuffer b = null;
		try {
			b = new StringBuffer();
			b.append(baseUrl).append("xiazai.ashx?")
			.append(URLEncoder.encode("xt",ApiGlobal.RequestEncoding)).append("=").append(URLEncoder.encode(Global.XT_ID,ApiGlobal.RequestEncoding))
			.append("&")
			.append(URLEncoder.encode("sqm",ApiGlobal.RequestEncoding)).append("=").append(URLEncoder.encode(DES.encryptDES(TDApp.manager.getShouQuanMa(),Global.KEY),ApiGlobal.RequestEncoding))
			.append("&")
			.append(URLEncoder.encode("zdsbm",ApiGlobal.RequestEncoding)).append("=").append(URLEncoder.encode(DES.encryptDES(TDApp.deviceInfo.getDeviceId(), Global.KEY),ApiGlobal.RequestEncoding))
		    .append("&")
		    .append(URLEncoder.encode("wybs",ApiGlobal.RequestEncoding)).append("=").append(URLEncoder.encode(TDApp.manager.getDESYongHuWeiYiBiaoShi(),ApiGlobal.RequestEncoding))
		    .append("&")
		    .append(URLEncoder.encode("kcdm",ApiGlobal.RequestEncoding)).append("=").append(URLEncoder.encode(DES.encryptDES(shuJu.getKeChengDaiMa(), Global.KEY),ApiGlobal.RequestEncoding))
		    .append("&")
		    .append(URLEncoder.encode("zjbs",ApiGlobal.RequestEncoding)).append("=").append(URLEncoder.encode(DES.encryptDES(shuJu.getXuHao(), Global.KEY),ApiGlobal.RequestEncoding));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b.toString();
	}

	public static String getHtml(String baseUrl) {
		StringBuffer b = null;
		try {
			b = new StringBuffer();
			//b.append(baseUrl).append(name + "html?")
			b.append(baseUrl).append("?")
			.append(URLEncoder.encode("sqm",ApiGlobal.RequestEncoding)).append("=").append(URLEncoder.encode(DES.encryptDES(TDApp.manager.getShouQuanMa(),Global.KEY),ApiGlobal.RequestEncoding))
			.append("&")
			.append(URLEncoder.encode("xitongID",ApiGlobal.RequestEncoding)).append("=").append(URLEncoder.encode(Global.XT_ID,ApiGlobal.RequestEncoding))
			.append("&")
			.append(URLEncoder.encode("zdsbm",ApiGlobal.RequestEncoding)).append("=").append(URLEncoder.encode(DES.encryptDES(TDApp.deviceInfo.getDeviceId(), Global.KEY),ApiGlobal.RequestEncoding))
		    .append("&")
		    .append(URLEncoder.encode("wybs",ApiGlobal.RequestEncoding)).append("=").append(URLEncoder.encode(TDApp.manager.getDESYongHuWeiYiBiaoShi(),ApiGlobal.RequestEncoding));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b.toString();
	}
	
	public static String getKaoShiHtml(String name,String kcdm,String xh,String ksid) {
		StringBuffer b = null;
		try {
			b = new StringBuffer();
			b.append(baseUrl).append(name + ".aspx?")
			.append(URLEncoder.encode("sqm",ApiGlobal.RequestEncoding)).append("=").append(URLEncoder.encode(DES.encryptDES(TDApp.manager.getShouQuanMa(),Global.KEY),ApiGlobal.RequestEncoding))
			.append("&")
			.append(URLEncoder.encode("xitongID",ApiGlobal.RequestEncoding)).append("=").append(URLEncoder.encode(Global.XT_ID,ApiGlobal.RequestEncoding))
			.append("&")
			.append(URLEncoder.encode("zdsbm",ApiGlobal.RequestEncoding)).append("=").append(URLEncoder.encode(DES.encryptDES(TDApp.deviceInfo.getDeviceId(), Global.KEY),ApiGlobal.RequestEncoding))
		    .append("&")
		    .append(URLEncoder.encode("wybs",ApiGlobal.RequestEncoding)).append("=").append(URLEncoder.encode(TDApp.manager.getDESYongHuWeiYiBiaoShi(),ApiGlobal.RequestEncoding))
			.append("&")
			.append(URLEncoder.encode("kcdm",ApiGlobal.RequestEncoding)).append("=").append(URLEncoder.encode(DES.encryptDES(kcdm, Global.KEY),ApiGlobal.RequestEncoding))
			.append("&")
			.append(URLEncoder.encode("xh",ApiGlobal.RequestEncoding)).append("=").append(URLEncoder.encode(DES.encryptDES(xh, Global.KEY),ApiGlobal.RequestEncoding))
			.append("&")
			.append(URLEncoder.encode("ksid",ApiGlobal.RequestEncoding)).append("=").append(URLEncoder.encode(DES.encryptDES(ksid, Global.KEY),ApiGlobal.RequestEncoding));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b.toString();
	}
	
	public static boolean isChangedVideo = false;
}
