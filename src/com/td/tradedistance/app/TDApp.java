package com.td.tradedistance.app;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Application;
import android.content.Context;

import cn.jpush.android.api.JPushInterface;

import com.td.tradedistance.app.bean.DeviceInfo;
import com.td.tradedistance.app.localstorage.PreFerenceManager;
import com.td.tradedistance.app.utils.Device;
import com.td.tradedistance.app.utils.Logger;

public class TDApp extends Application {
	private static final String TAG = "TDApp";
	private static TDApp mCApp = null;
	public static PreFerenceManager manager;
	public static  DeviceInfo deviceInfo = null;

	public DeviceInfo getDeviceInfo() {
		return deviceInfo;
	}

	

	public static TDApp getContext() {
		return mCApp;
	}

	@Override
	public void onCreate() {
		Logger.d(TAG, "onCreate");
		super.onCreate();
		init();
	}
    public static void getCurrentUserName(){
    	PreFerenceManager.getInstance(getContext()).getUserName();
    }
	@Override
	public void onLowMemory() {
		super.onLowMemory();
		System.gc();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}

	public void init() {
		mCApp = this;
		manager = PreFerenceManager.getInstance(this);
		deviceInfo = Device.getDeviceInfo(this);
		JPushInterface.init(getApplicationContext());
		JPushInterface.setDebugMode(ApiGlobal.DEBUG); 	// 设置开启日志,发布时请关闭日志
	}

	/**
	 * 检测某ActivityUpdate是否在当前Task的栈顶
	 */
	public static boolean isTopActivy(Context context, String cmdName) {
		ActivityManager manager = (ActivityManager) context
				.getSystemService(ACTIVITY_SERVICE);
		List<RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
		String cmpNameTemp = null;
		if (null != runningTaskInfos) {
			cmpNameTemp = (runningTaskInfos.get(0).topActivity).getClassName();
			Logger.e("cmpname", "cmpname:" + cmdName);
		}
		if (null == cmpNameTemp)
			return false;
		return cmpNameTemp.equals(cmdName);
	}

	public void displayBriefMemory() {
		Runtime rt = Runtime.getRuntime();
		Logger.d(
				"memory",
				"总内存=" + rt.totalMemory() + ", 已用="
						+ (rt.totalMemory() - rt.freeMemory()) + ", 可用="
						+ rt.freeMemory() + ", 最大=" + rt.maxMemory());
		final ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

		ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();

		activityManager.getMemoryInfo(info);

		Logger.i(TAG, "系统剩余内存:" + (info.availMem >> 10) + "k");

		Logger.i(TAG, "系统是否处于低内存运行：" + info.lowMemory);

		Logger.i(TAG, "当系统剩余内存低于" + info.threshold + "时就看成低内存运行");

	}
}
