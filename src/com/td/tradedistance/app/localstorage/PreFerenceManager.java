package com.td.tradedistance.app.localstorage;

import android.content.Context;
import android.content.SharedPreferences;

public class PreFerenceManager {
	private static final String UserName = "userinfo";
	private static SharedPreferences sharedPreferences;
	@SuppressWarnings("unused")
	private static SharedPreferences.Editor editor;
	private static PreFerenceManager manager;
	private String SHARED_KEY_ZhongDuanBangDing ="shared_key_Zhong_Duan_Bang_Ding";
	private String SHARED_KEY_DESYongHuWeiYiBiaoShi = "shared_key_DES_Yong_hu_wei_yi_Biao_shi";
	private String SHARED_KEY_NODESYongHuWeiYiBiaoShi = "shared_key_No_DESYong_hu_wei_yi_Biao_shi";
	private String SHARED_KEY_SHOUQUANMA = "shared_key_Shou_Quan_Ma";
	private String SHARED_KEY_UUID = "shared_key_uuid";
	private String SHARED_KEY_USER_NAME = "shared_key_user_name";
	private String SHARED_KEY_PASSWORD = "shared_key_password";
	private String SHARED_KEY_Xue_Hao = "shared_key_xue_hao";
	private String SHARED_KEY_tou_xiang = "shared_key_tou_xiang";
	//private String SHARED_KEY_TUI_SONG_GE_REN_XIAO_XIN = "shared_key_tui_song_ge_ren_xiao_xin";
	private String SHARED_KEY_WIFI_HUANJING_XIA_ZAI_SHI_PING ="shared_key_wifi_huanjing_xia_zai_shi_ping";
    private String SHARED_KEY_DENG_LU_Chu_Cuo_CI_SHU ="shared_key_Deng_lu_chu_cuo_ci_shu";
    private String SHARED_KEY_DI_YI_CI_DENG_LU_CHU_CUO_SHI_JIAN ="shared_key_di_yi_ci_deng_lu_chu_cuo_shi_jian";
	private String SHARED_KEY_PLAY_Progress = "share_key_play_Progress";
	private String SHARED_KEY_IS_LOGIN = "share_key_is_login";
	public PreFerenceManager(Context ctx){
		sharedPreferences = ctx.getSharedPreferences(UserName, Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
	}
	/*public synchronized static void init(Context cxt){
		manager = new PreFerenceManager(cxt);
	}*/
	public static PreFerenceManager getInstance(Context cxt){
		if(manager == null)
			manager = new PreFerenceManager(cxt);
		return manager;
	}
	public void setUserName(String username){
		editor.putString(SHARED_KEY_USER_NAME, username);
		editor.commit();
	}
	public String getUserName(){
		return sharedPreferences.getString(SHARED_KEY_USER_NAME, null);
	}
	public String getCurrentUserName(){
		return sharedPreferences.getString(SHARED_KEY_USER_NAME, null);
	}
	
	public void setPassWord(String password){
		editor.putString(SHARED_KEY_PASSWORD, password);
		editor.commit();
	}
	public String getPassWord(){
		return sharedPreferences.getString(SHARED_KEY_PASSWORD, null);
	}
	
	public void setXueHao(String xueHao){
		editor.putString(SHARED_KEY_Xue_Hao, xueHao);
		editor.commit();
	}
	public String getXueHao(){
		return sharedPreferences.getString(SHARED_KEY_Xue_Hao, null);
	}
	
	public void setTouXiang(String touXiang){
		editor.putString(SHARED_KEY_Xue_Hao, touXiang);
		editor.commit();
	}
	public String getTouXiang(){
		return sharedPreferences.getString(SHARED_KEY_tou_xiang, null);
	}
	
	public void setTuiSongGeRenXiaoXin(boolean tuiSongGeRenXiaoXin,String SHARED_KEY_TUI_SONG_GE_REN_XIAO_XIN){
		editor.putBoolean(SHARED_KEY_TUI_SONG_GE_REN_XIAO_XIN, tuiSongGeRenXiaoXin);
		editor.commit();
	}
	public boolean getTuiSongGeRenXiaoXin(String SHARED_KEY_TUI_SONG_GE_REN_XIAO_XIN){
		return sharedPreferences.getBoolean(SHARED_KEY_TUI_SONG_GE_REN_XIAO_XIN, false);
	}
	
	public void setWifiHuanJingXiaZaiShiPing(boolean wifiHuanJingXiaZaiShiPing){
		editor.putBoolean(SHARED_KEY_WIFI_HUANJING_XIA_ZAI_SHI_PING, wifiHuanJingXiaZaiShiPing);
		editor.commit();
	}
	public boolean getWifiHuanJingXiaZaiShiPing(){
		return sharedPreferences.getBoolean(SHARED_KEY_WIFI_HUANJING_XIA_ZAI_SHI_PING, false);
	}
	
	//设备唯一标识
	public void setUUID(String uuid){
		editor.putString(SHARED_KEY_UUID, uuid);
		editor.commit();
	}
	public  String getUUID() {
		return sharedPreferences.getString(SHARED_KEY_UUID, null);
	}
	//
	public void setShouQuanMa(String shouQuanMa){
		editor.putString(SHARED_KEY_SHOUQUANMA, shouQuanMa);
		editor.commit();
	}
	public  String getShouQuanMa() {
		return sharedPreferences.getString(SHARED_KEY_SHOUQUANMA, null);
	}
	//没有加密的用户唯一标识
	public void setNoDESYongHuWeiYiBiaoShi(String yongHuWeiYiBiaoShi){
		editor.putString(SHARED_KEY_NODESYongHuWeiYiBiaoShi, yongHuWeiYiBiaoShi);
		editor.commit();
	}
	public  String getNoDESYongHuWeiYiBiaoShi() {
		return sharedPreferences.getString(SHARED_KEY_NODESYongHuWeiYiBiaoShi, null);
	}
	//加密的用户唯一标识
	public void setDESYongHuWeiYiBiaoShi(String yongHuWeiYiBiaoShi){
		editor.putString(SHARED_KEY_DESYongHuWeiYiBiaoShi, yongHuWeiYiBiaoShi);
		editor.commit();
	}
	public  String getDESYongHuWeiYiBiaoShi() {
		return sharedPreferences.getString(SHARED_KEY_DESYongHuWeiYiBiaoShi, null);
	}
	
	public void setZhongDuanBangDing(boolean zhongDuanBangDing){
		editor.putBoolean(SHARED_KEY_ZhongDuanBangDing, zhongDuanBangDing);
		editor.commit();
	}
	public  boolean getZhongDuanBangDing() {
		return sharedPreferences.getBoolean(SHARED_KEY_ZhongDuanBangDing, false);
	}
	
	public void setDengLuChuCuoCiShu(String dengLuChuCuoCiShu){
		editor.putString(SHARED_KEY_DENG_LU_Chu_Cuo_CI_SHU, dengLuChuCuoCiShu);
		editor.commit();
	}
	public  String getDengLuChuCuoCiShu() {
		return sharedPreferences.getString(SHARED_KEY_DENG_LU_Chu_Cuo_CI_SHU, null);
	}
	
	
	public void setDiYiChiDengLuChuCuoShiJian(String diYiChiDengLuChuCuoShiJian){
		editor.putString(SHARED_KEY_DI_YI_CI_DENG_LU_CHU_CUO_SHI_JIAN, diYiChiDengLuChuCuoShiJian);
		editor.commit();
	}
	public  String getDiYiChiDengLuChuCuoShiJian() {
		return sharedPreferences.getString(SHARED_KEY_DI_YI_CI_DENG_LU_CHU_CUO_SHI_JIAN, null);
	}
	
	public void setPlayProgress(String url,int progress){
		editor.putInt(url, progress);
		editor.commit();
	}
	public  int getPlayProgress(String url) {
		return sharedPreferences.getInt(url, 0);
	}
	
	public  void delPlayProgress(String url) {
		editor.remove(url).commit();
	}
	
	public void setIsLogin(boolean IsLogin){
		editor.putBoolean(SHARED_KEY_IS_LOGIN, IsLogin);
		editor.commit();
	}
	public  boolean getIsLogin() {
		return sharedPreferences.getBoolean(SHARED_KEY_IS_LOGIN, false);
	}
}
