package com.td.tradedistance.app.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.td.tradedistance.app.TDApp;
import com.td.tradedistance.app.global.Global;

/**
 * <p>
 * 功能描述:缓存一些服务端信息和用户基本信息
 * </p>
 * 
 * @author <a href="mailto:zhenghuiguo@yahoo.com.cn">郑惠国</a>
 * @version $Revision: 1.1 $
 * @Date[日期 2013-5-14]
 */
public class UserInfo {
	/**
	 * Userinfo.xml 存储信息
	 */
	private static final String UserInfo = "Userinfo";

	/**
	 * <p>
	 * 功能描述:创建Userinfo.xml
	 * </p>
	 * 
	 * @param context
	 * @param fileName
	 * @author:郑惠国
	 * @update:[日期2013-5-14] [更改人姓名][变更描述]
	 */
	public static void CreateUserInfoFile(Context context) {
		@SuppressWarnings("unused")
		SharedPreferences userInfo = context.getSharedPreferences(UserInfo,
				Activity.MODE_PRIVATE);
	}

	/**
	 * <p>
	 * 功能描述:获取用户标识
	 * </p>
	 * 
	 * @param context
	 * @return String
	 * @author:郑惠国
	 * @update:[日期2013-5-14] [更改人姓名][变更描述]
	 */
	public static String getYongHuBiaoShi(Context context) {
		return context.getSharedPreferences(UserInfo, Activity.MODE_PRIVATE)
				.getString(Global.YongHuBiaoShi, null);
	}

	public static String getNoDecYongHuBiaoShi(Context context) {
		return context.getSharedPreferences(UserInfo, Activity.MODE_PRIVATE)
				.getString(Global.NODecYongHuBiaoShi, null);
	}

	/**
	 * <p>
	 * 功能描述:获取授权码
	 * </p>
	 * 
	 * @param context
	 * @return String
	 * @author:郑惠国
	 * @update:[日期2013-5-14] [更改人姓名][变更描述]
	 */
	public static String getShouQuanMa(Context context) {
		return context.getSharedPreferences(UserInfo, Activity.MODE_PRIVATE)
				.getString(Global.ShouQuanMa, null);
	}

	/**
	 * <p>
	 * 功能描述:获取系统id
	 * </p>
	 * 
	 * @param context
	 * @return String
	 * @author:郑惠国
	 * @update:[日期2013-5-14] [更改人姓名][变更描述]
	 */
	public static String getXT_ID(Context context) {
		return context.getSharedPreferences(UserInfo, Activity.MODE_PRIVATE)
				.getString(Global.XT_ID, null);
	}

	/**
	 * <p>
	 * 功能描述:向Userinfo.xml以key-value形式存储
	 * </p>
	 * 
	 * @param context
	 * @param key
	 * @param value
	 * @author:郑惠国
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public static void put(Context context, String key, String value) {
		SharedPreferences userInfo = context.getSharedPreferences(UserInfo,
				Activity.MODE_PRIVATE);
		userInfo.edit().putString(key, value).commit();
	}
	public static void put(Context context, String key, int value) {
		SharedPreferences userInfo = context.getSharedPreferences(UserInfo,
				Activity.MODE_PRIVATE);
		userInfo.edit().putInt(key, value).commit();
	}

	/**
	 * <p>
	 * 功能描述:清除缓存数据
	 * </p>
	 * 
	 * @param context
	 * @author:郑惠国
	 * @update:[日期2013-5-14] [更改人姓名][变更描述]
	 */
	public static void clear(Context context) {
		SharedPreferences userInfo = context.getSharedPreferences(UserInfo,
				Activity.MODE_PRIVATE);
		userInfo.edit().clear().commit();
	}

	public static String getIsBangDingZhongDuan(Context context) {
		return context.getSharedPreferences(UserInfo, Activity.MODE_PRIVATE)
				.getString(Global.ZhongDuanBangDingKey, null);
	}

	public static boolean getIsLogin(Context context) {
		return Boolean.valueOf(context.getSharedPreferences(UserInfo,
				Activity.MODE_PRIVATE).getString(Global.LoginKey, null));
	}

	public static boolean getIsPass(Context context) {
		return Boolean.valueOf(context.getSharedPreferences(UserInfo,
				Activity.MODE_PRIVATE).getString(Global.passKey, null));
	}

	public static String getIsSavePassWord(Context context) {
		return context.getSharedPreferences(UserInfo, Activity.MODE_PRIVATE)
				.getString(Global.PassWordKey, null);
	}

	public static String getIsSavePassWord1(Context context) {
		return context.getSharedPreferences(UserInfo, Activity.MODE_PRIVATE)
				.getString(Global.PassWordKey1, null);
	}

	public static String getIsSaveUserName(Context context) {
		return context.getSharedPreferences(UserInfo, Activity.MODE_PRIVATE)
				.getString(Global.UserNameKey, null);
	}

	public static void delIsLogin(Context context) {
		SharedPreferences sp = context.getSharedPreferences(UserInfo,
				Activity.MODE_PRIVATE);
		sp.edit().remove(Global.LoginKey).commit();
	}

	public static void delIsPass(Context context) {
		SharedPreferences sp = context.getSharedPreferences(UserInfo,
				Activity.MODE_PRIVATE);
		sp.edit().remove(Global.passKey).commit();
	}

	public static void delPassWord(Context context) {
		SharedPreferences sp = context.getSharedPreferences(UserInfo,
				Activity.MODE_PRIVATE);
		sp.edit().remove(Global.PassWordKey).commit();
	}

	public static void delPassWord1(Context context) {
		SharedPreferences sp = context.getSharedPreferences(UserInfo,
				Activity.MODE_PRIVATE);
		sp.edit().remove(Global.PassWordKey1).commit();
	}

	public static void delUserName(Context context) {
		SharedPreferences sp = context.getSharedPreferences(UserInfo,
				Activity.MODE_PRIVATE);
		sp.edit().remove(Global.USERNAME).commit();
	}

	/**
	 * 退出登陆时显示自动保存用户名和密码
	 */
	public static boolean getShowNameAndPass(Context context) {
		return Boolean.valueOf(context.getSharedPreferences(UserInfo,
				Activity.MODE_PRIVATE).getString(Global.ShowNameAndPass, null));
	}

	public static void putShowNameAndPassFlag(Context context, String key,
			String value) {
		SharedPreferences userInfo = context.getSharedPreferences(UserInfo,
				Activity.MODE_PRIVATE);
		userInfo.edit().putString(key, value).commit();
	}

	/**
	 * 保存客户端版本号
	 * 
	 * @param context
	 * @param userName
	 * @param versioncode
	 */
	public static void saveVersion(Context context, String version,
			String versioncode) {
		SharedPreferences sp = context.getSharedPreferences(UserInfo,
				Activity.MODE_PRIVATE);
		sp.edit().putString(version, versioncode).commit();
	}

	public static String getVersion(Context con, String keyString) {
		SharedPreferences sp = con.getSharedPreferences(UserInfo,
				Activity.MODE_PRIVATE);
		return sp.getString(keyString, null);
	}

	// 获取我的提问个数
	public static String getWdtwNum(Context context) {
		return context.getSharedPreferences(UserInfo, Activity.MODE_PRIVATE)
				.getString(Global.WdtwKey, null);
	}

	// 获取我的回答个数
	public static String getWdhdNum(Context context) {
		return context.getSharedPreferences(UserInfo, Activity.MODE_PRIVATE)
				.getString(Global.WdhdKey, null);
	}

	// 获取我的收藏个数
	public static String getWdscNum(Context context) {
		return context.getSharedPreferences(UserInfo, Activity.MODE_PRIVATE)
				.getString(Global.WdscKey, null);
	}

	// 保存文件加密串
	public static String getjWJJMC(Context context) {
		return context.getSharedPreferences(UserInfo, Activity.MODE_PRIVATE)
				.getString(Global.WJJMKey, null);
	}

	// 保存统一系统的版本
	public static String getTYXTBBH(Context context) {
		return context.getSharedPreferences(UserInfo, Activity.MODE_PRIVATE)
				.getString(Global.TYXTBBHKey, null);
	}

	// 获取视频播放进度
	public static String getProgress(Context context, String key) {
		return context.getSharedPreferences(UserInfo, Activity.MODE_PRIVATE)
				.getString(key, "0");
	}

	// 删除播放完视频的进度
	public static void delProgress(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences(UserInfo,
				Activity.MODE_PRIVATE);
		sp.edit().remove(key).commit();
	}

	// 保存授权码
	public static void saveShouQuanMa1(String sqm) {
		if (sqm != null && sqm.length() > 0) {
			put(TDApp.getContext(), Global.ShouQuanMa, sqm);
		}
	}

	// 获取是否要更新课程中心分类
	public static String getFenLei(Context context) {
		return context.getSharedPreferences(UserInfo, Activity.MODE_PRIVATE)
				.getString(Global.FlKey, null);
	}

	// 获取listview刷新时间
	public static String getRefreshTime(Context context) {
		return context.getSharedPreferences(UserInfo, Activity.MODE_PRIVATE)
				.getString(Global.listviewTimeKey, null);
	}
	//设备唯一标识
	public static String getUUID(Context context) {
		return context.getSharedPreferences(UserInfo, Activity.MODE_PRIVATE)
				.getString(Global.uuid, null);
	}
	
	//注册标识 1代表 第一次注册 。0没有注册
	public static int getRegis(Context context) {
		return context.getSharedPreferences(UserInfo, Activity.MODE_PRIVATE)
				.getInt(Global.reg, 0);
	}
	//广告
	public static int getGuanGao(Context context) {
		return context.getSharedPreferences(UserInfo, Activity.MODE_PRIVATE)
				.getInt(Global.guanggao, 0);
	}
	//广告图片
	public static String getGuanGaoTuPian(Context context) {
		return context.getSharedPreferences(UserInfo, Activity.MODE_PRIVATE)
				.getString(Global.guanggaoTuPian, null);
	}
	//广告链接
	public static String getGuanGaoLianJie(Context context) {
		return context.getSharedPreferences(UserInfo, Activity.MODE_PRIVATE)
				.getString(Global.guanggaolianjie, null);
	}
	//姓名
	public static String getXingMing(Context context) {
		return context.getSharedPreferences(UserInfo, Activity.MODE_PRIVATE)
				.getString(Global.XingMing, null);
	}
	//身份证
	public static String getShenFenZheng(Context context) {
		return context.getSharedPreferences(UserInfo, Activity.MODE_PRIVATE)
				.getString(Global.ShenFenZheng, null);
	}
	//积分
	public static int getJiFen(Context context) {
		return context.getSharedPreferences(UserInfo, Activity.MODE_PRIVATE)
				.getInt(Global.JiFen, 0);
	}
	//帮助页
	public static int getHelpGuide(Context context) {
		return context.getSharedPreferences(UserInfo, Activity.MODE_PRIVATE)
				.getInt(Global.Guide, 0);
	}
}
