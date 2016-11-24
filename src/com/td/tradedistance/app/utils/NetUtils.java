package com.td.tradedistance.app.utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * <p>功能描述: 网络类</p>
 * @author  <a href="mailto:zhenghuiguo@yahoo.com.cn">郑惠国</a>
 * @version $Revision: 1.1 $
 */
public class NetUtils {
	/**
	 * <p>功能描述:手机是否联网</p>
	 * @param context
	 * @return boolean
	 * @author:郑惠国
	 * @update:[日期2013-5-13] [更改人姓名][变更描述]
	 */
    public static boolean checkNet(Context context) {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                // 获取网络连接管理的对象
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    // 判断当前网络是否已经连接
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        boolean available = info.isAvailable();
                        if (available) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return false;
    }
    
   /* public static void checkNet1(Context context) {
    	ConnectivityManager manager = (ConnectivityManager) context 
				.getSystemService(Context.CONNECTIVITY_SERVICE); 
    	if(manager != null){
    		if(!isWifiConnected(context,manager)){
        		Toast.makeText(context, Global.no_WIFI, Toast.LENGTH_SHORT).show();
        		return;
    		}
        	else if(!isMobileConnected(context,manager)){
        		Toast.makeText(context, Global.no_3G, Toast.LENGTH_SHORT).show();
        		return;
        	}
    	}else{
    		Toast.makeText(context, Global.networdtip, Toast.LENGTH_SHORT).show();
    		return;
    	}
    }
    */
    //判断wifi是否可用
	public static boolean isWifiConnected(Context context) { 
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE); 
		if (context != null) { 
			NetworkInfo mWiFiNetworkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI); 
			if (mWiFiNetworkInfo != null) { 
				return mWiFiNetworkInfo.isAvailable(); 
			}
		} 
		return false; 
	}
	
	//判断手机3G信号是否可用
	public static boolean isMobileConnected(Context context, ConnectivityManager manager) { 
		if (context != null) { 
			NetworkInfo mMobileNetworkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE); 
			if (mMobileNetworkInfo != null) { 
				return mMobileNetworkInfo.isAvailable(); 
			}
		}	 
		return false; 
	}

}
