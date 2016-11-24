package com.td.tradedistance.app.utils;



import java.util.UUID;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.td.tradedistance.app.TDApp;
import com.td.tradedistance.app.bean.DeviceInfo;
import com.td.tradedistance.app.global.Global;
import com.td.tradedistance.app.localstorage.*;

/**
 * <p>功能描述: 手机基本信息</p>
 * @author  <a href="mailto:zhenghuiguo@yahoo.com.cn">郑惠国</a>
 * @version $Revision: 1.1 $
 */
public class Device {
	/**
	 * <p>功能描述:获取手机基本信息</p>
	 * @param context
	 * @return DeviceInfo
	 * @author:郑惠国
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public static DeviceInfo getDeviceInfo(Context context){
		  DeviceInfo  devInfo = new DeviceInfo();
		  /*TelephonyManager telephonyManager = (TelephonyManager) context .getSystemService(Context.TELEPHONY_SERVICE);
	      
	      String imei = telephonyManager.getDeviceId();
	      if(imei == null) imei = getLocalMacAddress(context);*/
		  String uuid = TDApp.manager.getUUID();
		  if(uuid!=null && uuid.length()>0){
		       devInfo.setDeviceId(uuid);
		  }else{
			  uuid =  UUID.randomUUID().toString();
			  if(uuid!=null && uuid.length()>0){
			       uuid = uuid.replaceAll("-", "");
			       TDApp.manager.setUUID(uuid);
			       devInfo.setDeviceId(uuid);
			  }
		  }
		 // String imei =  UUID.randomUUID().toString();//getLocalMacAddress(context);
		  //if(imei==null) imei = "null";577e31d7-7e06-4b9f-a4fd-11efe053da60
		  Logger.d("uuid", uuid);
	     // devInfo.setSimSerialNumber(telephonyManager.getSimSerialNumber());// iccid
	     // devInfo.setSubscriberId(telephonyManager.getSubscriberId());// imsi
	      //devInfo.setType(isDeviceType(telephonyManager));
	      DisplayMetrics dm = new DisplayMetrics();
	      WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		  windowManager.getDefaultDisplay().getMetrics(dm);
		  devInfo.setWidthPixels(dm.widthPixels);
		  Logger.d("dm.widthPixels", dm.widthPixels+"");
		  devInfo.setHeightPixels(dm.heightPixels);
		  Logger.d("dm.heightPixels", dm.heightPixels+"");
		  devInfo.setMobilModel(android.os.Build.MODEL);
		  devInfo.setMobilVersion(android.os.Build.VERSION.RELEASE);
	      return devInfo;
	}
	public static String getLocalMacAddress(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }
	public static boolean isDeviceType(TelephonyManager telephony) {
        //TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        int type = telephony.getPhoneType();
        if (type == TelephonyManager.PHONE_TYPE_NONE) {
           // Logger.d("is Tablet!");
        	return true;
        } else {
        	return false;
            //Logger.d("is phone!");
        }
    }
}
