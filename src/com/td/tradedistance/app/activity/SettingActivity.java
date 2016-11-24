package com.td.tradedistance.app.activity;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.td.tradedistance.app.ApiGlobal;
import com.td.tradedistance.app.R;
import com.td.tradedistance.app.TDApp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import api.ApiAction;
import api.GsonRequest;
import api.VolleyController;

import com.td.tradedistance.app.TDApp;
import com.td.tradedistance.app.bean.LanMuCaiDanRoot;
import com.td.tradedistance.app.bean.Login;
import com.td.tradedistance.app.global.Global;
import com.td.tradedistance.app.utils.DES;
import com.td.tradedistance.app.utils.ProgressUtils;
import com.td.tradedistance.app.utils.ToastUtil;
public class SettingActivity extends BaseActivity {
	private ImageView tsgrxx_iv,wifi_iv;
	private RelativeLayout gwmpf_rl,gywm_rl;
	private String SHARED_KEY_TUI_SONG_GE_REN_XIAO_XIN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	setContentView(R.layout.avtivity_setting);
    	super.onCreate(savedInstanceState);
    }
	@Override
	protected void initVariables() {
		SHARED_KEY_TUI_SONG_GE_REN_XIAO_XIN = TDApp.manager.getCurrentUserName();
	}

	@Override
	protected void initViews(Bundle savedInstanceState) {
		tsgrxx_iv = (ImageView)findViewById(R.id.tsgrxx_iv);
		wifi_iv  = (ImageView)findViewById(R.id.wifi_iv);
		gywm_rl  = (RelativeLayout)findViewById(R.id.gywm_rl);
		gwmpf_rl  = (RelativeLayout)findViewById(R.id.gwmpf_rl);
		gywm_rl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent it = new Intent(SettingActivity.this,com.td.tradedistance.app.activity.WebActivity.class);
		         it.putExtra("title",getResources().getString(R.string.AboutMe_str));
		         it.putExtra("url", ApiGlobal.AboutMeUrl);
		         startActivity(it);
			}
		});
		gwmpf_rl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		tsgrxx_iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				 if(TDApp.manager.getTuiSongGeRenXiaoXin(SHARED_KEY_TUI_SONG_GE_REN_XIAO_XIN)){
					 tsgrxx_iv.setBackgroundResource(R.drawable.slideright);
		        	 TDApp.manager.setTuiSongGeRenXiaoXin(false,SHARED_KEY_TUI_SONG_GE_REN_XIAO_XIN);
		        	// JPushInterface.resumePush(getApplicationContext());
		        	 TuiSongSheZhi("0");
		         }else{
		        	 TDApp.manager.setTuiSongGeRenXiaoXin(true,SHARED_KEY_TUI_SONG_GE_REN_XIAO_XIN);
		        	 tsgrxx_iv.setBackgroundResource(R.drawable.slideleft);
		        	 //JPushInterface.stopPush(getApplicationContext());
		        	 TuiSongSheZhi("1");
		         }
			}
		});
		wifi_iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(TDApp.manager.getWifiHuanJingXiaZaiShiPing()){
					 wifi_iv.setBackgroundResource(R.drawable.slideright);
		        	 TDApp.manager.setWifiHuanJingXiaZaiShiPing(false);
		         }else{
		        	 TDApp.manager.setWifiHuanJingXiaZaiShiPing(true);
		        	 wifi_iv.setBackgroundResource(R.drawable.slideleft);
		        	 
		         }
			}
		});
	}

	@Override
	protected void loadData() {
         if(TDApp.manager.getTuiSongGeRenXiaoXin(SHARED_KEY_TUI_SONG_GE_REN_XIAO_XIN)){
        	 tsgrxx_iv.setBackgroundResource(R.drawable.slideleft);
         }else{
        	 tsgrxx_iv.setBackgroundResource(R.drawable.slideright);
         }
         if(TDApp.manager.getWifiHuanJingXiaZaiShiPing()){
        	 wifi_iv.setBackgroundResource(R.drawable.slideleft);
         }else{
        	 wifi_iv.setBackgroundResource(R.drawable.slideright);
         }
	}
	
	private void TuiSongSheZhi(String zt) {
		ProgressUtils.showDialog();
		Map<String, String> mParams = new HashMap<String, String>();
		try {
			mParams.put("xt", Global.XT_ID);
			mParams.put("zdsbm",
					DES.encryptDES(TDApp.deviceInfo.getDeviceId(), Global.KEY));
			mParams.put("wybs", TDApp.manager.getDESYongHuWeiYiBiaoShi());
			mParams.put("sqm", DES.encryptDES(TDApp.manager.getShouQuanMa(), Global.KEY));
			mParams.put("zt", DES.encryptDES(zt, Global.KEY));
		} catch (Exception e1) {
			 proDailog.dismiss();
		}
		GsonRequest<LanMuCaiDanRoot> TuiSongSheZhiRequest = new GsonRequest<LanMuCaiDanRoot>(
				ApiAction.getUrlParams(ApiAction.GeRenSheZhi, mParams),
				LanMuCaiDanRoot.class, new Response.Listener<LanMuCaiDanRoot>() {

					@Override
					public void onResponse(LanMuCaiDanRoot response) {
						proDailog.dismiss();
						int ret = response.getRet();
						if (ret >= Global.Success) {
							if(!TextUtils.isEmpty(response.getShouQuanMa()))
								TDApp.manager.setShouQuanMa(response.getShouQuanMa());
						} else {// 不成功
							ToastUtil.TipToast(response.getErrInfo());
						}
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						proDailog.dismiss();
					    ApiAction.errorTip(SettingActivity.this, error);
					}
				});
		VolleyController.getInstance(SettingActivity.this)
				.addToRequestQueue(TuiSongSheZhiRequest);

	}
}
