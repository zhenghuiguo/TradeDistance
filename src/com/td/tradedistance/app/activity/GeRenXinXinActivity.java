package com.td.tradedistance.app.activity;

import java.util.HashMap;
import java.util.Map;


import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.td.tradedistance.app.R;
import com.td.tradedistance.app.TDApp;
import com.td.tradedistance.app.bean.GeRenXinXin;
import com.td.tradedistance.app.bean.Login;
import com.td.tradedistance.app.global.Global;
import com.td.tradedistance.app.utils.DES;
import com.td.tradedistance.app.utils.Logger;
import com.td.tradedistance.app.utils.ProgressUtils;
import com.td.tradedistance.app.utils.ToastUtil;
import com.td.tradedistance.app.widget.TitleBar;

import down.GlobalDownLoading1;
import down.Globals;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import api.ApiAction;
import api.GsonRequest;
import api.VolleyController;

public class GeRenXinXinActivity extends BaseActivity{
	private static final String requestTag = "GeRenXinXinActivity";
	private Button outlogin_bnt;
	private TitleBar mTitleBar;
	private TextView name_tv,xuehao_tv,zhongxin_tv,pici_tv,cengci_tv,zhuangye_tv,xueji_tv;
	private GeRenXinXin xinxin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_gerenxinxin);
		super.onCreate(savedInstanceState);
		
	}

	@Override
	protected void initVariables() {
		Intent it = getIntent();
		xinxin =(GeRenXinXin)it.getSerializableExtra("xinXin");
	}

	@Override
	protected void initViews(Bundle savedInstanceState) {
		mTitleBar = (TitleBar) findViewById(R.id.titlebar);
		mTitleBar.setTitle(getResources().getString(R.string.gerenxinxin_str));
		outlogin_bnt = (Button) findViewById(R.id.outlogin_bnt);
		name_tv = (TextView) findViewById(R.id.name_tv);
		xuehao_tv = (TextView) findViewById(R.id.xuehao_tv);
		zhongxin_tv = (TextView) findViewById(R.id.zhongxin_tv);
		pici_tv = (TextView) findViewById(R.id.pici_tv);
		cengci_tv = (TextView) findViewById(R.id.cengci_tv);
		zhuangye_tv = (TextView) findViewById(R.id.zhuangye_tv);
		xueji_tv = (TextView) findViewById(R.id.xueji_tv);
	}

	@Override
	protected void loadData() {
		if(xinxin==null)
		     geRenXinXin();
		else{
			name_tv.setText(xinxin.getXingMing());
			xuehao_tv.setText(xinxin.getXueHao());
			zhongxin_tv.setText(xinxin.getZhongXin());
			pici_tv.setText(xinxin.getPiCi());
			cengci_tv.setText(xinxin.getCengCi());
			zhuangye_tv.setText(xinxin.getZhuanYe());
			xueji_tv.setText(xinxin.getXueJi());
		}
		outlogin_bnt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				zhuXiao();
			}
		});
	}
	private void geRenXinXin(){
		ProgressUtils.showDialog();
		Map<String, String> mParams = new HashMap<String, String>();
		try {
			mParams.put("xt", Global.XT_ID);
			mParams.put("zdsbm",
					DES.encryptDES(TDApp.deviceInfo.getDeviceId(), Global.KEY));
			mParams.put("wybs", TDApp.manager.getDESYongHuWeiYiBiaoShi());
			mParams.put("sqm", DES.encryptDES(TDApp.manager.getShouQuanMa(), Global.KEY));
		} catch (Exception e1) {
			 proDailog.dismiss();
		}
		GsonRequest<GeRenXinXin> zczdgsonRequest = new GsonRequest<GeRenXinXin>(
				ApiAction.getUrlParams(ApiAction.GeRenXinXi, mParams),
				GeRenXinXin.class, new Response.Listener<GeRenXinXin>() {

					@Override
					public void onResponse(GeRenXinXin response) {
                         proDailog.dismiss();
						int ret = response.getRet();
						Logger.d("注册终端", ret + "");
						if (ret >= Global.Success) {
							if(!TextUtils.isEmpty(response.getShouQuanMa()))
								TDApp.manager.setShouQuanMa(response.getShouQuanMa());
							name_tv.setText(response.getXingMing());
							xuehao_tv.setText(response.getXueHao());
							zhongxin_tv.setText(response.getZhongXin());
							pici_tv.setText(response.getPiCi());
							cengci_tv.setText(response.getCengCi());
							zhuangye_tv.setText(response.getZhuanYe());
							xueji_tv.setText(response.getXueJi());
						} else {// 不成功
							ToastUtil.TipToast(response.getErrInfo());
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						ApiAction.errorTip(getApplicationContext(), error);
					}
				});
		VolleyController.getInstance(getApplicationContext())
				.addToRequestQueueRepeat(zczdgsonRequest, requestTag);
	}
	private void zhuXiao() {
		ProgressUtils.showDialog();
		Map<String, String> mParams = new HashMap<String, String>();
		try {
			mParams.put("xt", Global.XT_ID);
			mParams.put("zdsbm",
					DES.encryptDES(TDApp.deviceInfo.getDeviceId(), Global.KEY));
			mParams.put("yhwybs", TDApp.manager.getDESYongHuWeiYiBiaoShi());
		} catch (Exception e1) {
			 proDailog.dismiss();
		}
		GsonRequest<Login> zczdgsonRequest = new GsonRequest<Login>(
				ApiAction.getUrlParams(ApiAction.ZhuXiao, mParams),
				Login.class, new Response.Listener<Login>() {

					@Override
					public void onResponse(Login response) {
                         proDailog.dismiss();
						int ret = response.getRet();
						Logger.d("注册终端", ret + "");
						if (ret >= Global.Success) {
							Globals.deleteAll();
							// 注销成功
							goLoginActivity();
						} else {// 不成功
							ToastUtil.TipToast(response.getErrInfo());
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						goLoginActivity();
						ApiAction.errorTip(getApplicationContext(), error);
					}
				});
		VolleyController.getInstance(getApplicationContext())
				.addToRequestQueueRepeat(zczdgsonRequest, requestTag);
	}
	
	public void goLoginActivity(){
		TDApp.manager.setPassWord("");
		GlobalDownLoading1.getInstance().stopLoadingDownloadTask();
		Globals.deleteAll();
		Intent it = new Intent(GeRenXinXinActivity.this,LoginActivity.class);
		it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(it);
		finish();
	}
}
