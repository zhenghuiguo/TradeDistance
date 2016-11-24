package com.td.tradedistance.app.activity;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import api.ApiAction;
import api.GsonRequest;
import api.VolleyController;


import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.td.tradedistance.app.R;
import com.td.tradedistance.app.TDApp;
import com.td.tradedistance.app.bean.Login;
import com.td.tradedistance.app.bean.LoginRecord;
import com.td.tradedistance.app.bean.ZhuCeZhongDuan;
import com.td.tradedistance.app.global.Global;
import com.td.tradedistance.app.localstorage.LoginRecordDao;
import com.td.tradedistance.app.utils.CommonUtil;
import com.td.tradedistance.app.utils.DES;
import com.td.tradedistance.app.utils.Logger;
import com.td.tradedistance.app.utils.ProgressUtils;
import com.td.tradedistance.app.utils.ToastUtil;

public class LoginActivity extends BaseActivity {
	private static final String requestTag = "Login";
	private EditText mUserNameEt;
	private EditText mPasswrodEt;
	private Button mLoginBnt;
	private String mUserName;
	private String mPassword;
	private Intent it;
	//private ProgressUtils proDailog = null;
	private LoginRecordDao loginRecordDao;
	private boolean isLock = false;// false 解锁 true 枷锁 4个小时内解锁
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_login);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initVariables() {
		it = new Intent(LoginActivity.this, MainActivity.class);
		loginRecordDao = new LoginRecordDao();
	}

	@Override
	protected void initViews(Bundle savedInstanceState) {
		mUserNameEt = (EditText) findViewById(R.id.username_et);
		mPasswrodEt = (EditText) findViewById(R.id.password_et);
		mLoginBnt = (Button) findViewById(R.id.login_bnt);
		if(TDApp.manager.getUserName()!=null){
			mUserNameEt.setText(TDApp.manager.getUserName());
		}
	}

	public void login() {
		if (validate()) {
			DengLu();
		}
	}

	@Override
	protected void loadData() {
		mLoginBnt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//startActivity(new Intent(LoginActivity.this,TestListActivity.class));
				if (Global.MeiBangDing == TDApp.manager.getZhongDuanBangDing()) {
					BangDing();
				} else {
					login();
				}
			}
		});
	}

	private boolean validate() {
		setmUserName(mUserNameEt.getText().toString().trim());
		setmPassword(mPasswrodEt.getText().toString().trim());

		// TODO TEST MODE
		//mUserName = ("".equals(mUserName)) ? Global.USERNAME : mUserName;
		// mPassword = ("".equals(mPassword)) ? Global.PASSWORD : mPassword;
		if (getmUserName().equals("")) {
			ToastUtil.TipToast(Global.UserNameTip);
			mUserNameEt.requestFocus();
			return false;
		}
		if (getmPassword().equals("")) {
			ToastUtil.TipToast(Global.PasswordTip);
			mPasswrodEt.requestFocus();
			return false;
		}
		return true;
	}

	private void DengLu() {
		LoginRecord lr = getLoginRecord();
		if (lr != null) {
			if (lr.getLock_flag() == 1) {
				// ToastUtil.TipToast("同一天内密码连续输错5次，锁定该帐号4小时");
				try {
					if (CommonUtil.localdateLtDate(lr.getLogin_date())) {
						isLock = false;
						loginRecordDao.deleteLoginRecord(getmUserName());
					} else {
						isLock = true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else{
			isLock = false;
		}
		if (!isLock) {
			ProgressUtils.showDialog();
			Map<String, String> mParams = new HashMap<String, String>();
			try {
				mParams.put("xt", Global.XT_ID);
				mParams.put("yhlx", DES.encryptDES(Global.YHLX, Global.KEY));
				mParams.put("yhm", DES.encryptDES(getmUserName(), Global.KEY));
				mParams.put("mm", DES.encryptDES(getmPassword(), Global.KEY));
				mParams.put("zdsbm", DES.encryptDES(
						TDApp.deviceInfo.getDeviceId(), Global.KEY));
			} catch (Exception e1) {
				proDailog.dismiss();
				return;
			}
			GsonRequest<Login> gsonRequest = new GsonRequest<Login>(
					ApiAction.getUrlParams(ApiAction.DengLu, mParams),
					Login.class, new Response.Listener<Login>() {
						@SuppressWarnings("unused")
						@Override
						public void onResponse(Login login) {
							proDailog.dismiss();
							int ret = login.getRet();
							if (ret >= Global.Success) {
								saveUserName();
								savePassWord();
								TDApp.manager.setShouQuanMa(login
										.getShouQuanMa());
								TDApp.manager.setNoDESYongHuWeiYiBiaoShi(login
										.getYongHuBiaoShi());
								try {
									TDApp.manager.setDESYongHuWeiYiBiaoShi(DES
											.encryptDES(
													login.getYongHuBiaoShi(),
													Global.KEY));
								} catch (Exception e) {
									e.printStackTrace();
								}
								goMain(true);
							} else {// 不成功
								if (ret == -205) {
									LoginRecord();// 记录登录密码出错次数
								}
								ToastUtil.TipToast(login.getErrInfo());
							}
						}
					}, new Response.ErrorListener() {

						@Override
						public void onErrorResponse(VolleyError error) {
							proDailog.dismiss();
							ApiAction.errorTip(getApplicationContext(), error);
						}
					}

			);

			VolleyController.getInstance(getApplicationContext())
					.addToRequestQueueRepeat(gsonRequest, requestTag);

		}else{
			ToastUtil.TipToast(getResources().getString(R.string.zhanghaobeisuoding_str));
		}
	}

	private void BangDing() {
		Map<String, String> mParams = new HashMap<String, String>();
		try {
			mParams.put("xt", Global.XT_ID);
			mParams.put("zdsbm",
					DES.encryptDES(TDApp.deviceInfo.getDeviceId(), Global.KEY));
			mParams.put("zdxtlx", Global.ZDXTLX);
			mParams.put("zdtsm", TDApp.deviceInfo.getDeviceId());
			mParams.put("zdpmc",
					String.valueOf(TDApp.deviceInfo.getHeightPixels()));
			mParams.put("zdpmk",
					String.valueOf(TDApp.deviceInfo.getWidthPixels()));
			mParams.put("zdxx", TDApp.deviceInfo.getZDXX(
					TDApp.deviceInfo.getMobilModel(),
					TDApp.deviceInfo.getMobilVersion()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		GsonRequest<ZhuCeZhongDuan> zczdgsonRequest = new GsonRequest<ZhuCeZhongDuan>(
				ApiAction.getUrlParams(ApiAction.BangDingZhongDuan, mParams),
				ZhuCeZhongDuan.class, new Response.Listener<ZhuCeZhongDuan>() {

					@Override
					public void onResponse(ZhuCeZhongDuan response) {

						int ret = response.getRet();
						Logger.d("注册终端", ret + "");
						if (ret >= Global.Success) {// 绑定成功
							TDApp.manager.setZhongDuanBangDing(true);// true
							login();
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

	private void goMain(boolean flag) {
		it.putExtra("flag", flag);
		startActivity(it);
	}

	public void saveUserName() {
		TDApp.manager.setUserName(getmUserName());
	}

	public void savePassWord() {
		TDApp.manager.setPassWord(getmPassword());
	}

	public String getmUserName() {
		return mUserName;
	}

	public void setmUserName(String mUserName) {
		this.mUserName = mUserName;
	}

	public String getmPassword() {
		return mPassword;
	}

	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
	}

	public LoginRecord getLoginRecord() {
		return loginRecordDao.getLoginRecord(getmUserName());
	}

	// 记录同一个账户登录密码出错次数
	@SuppressWarnings("unused")
	public void LoginRecord() {
		LoginRecord lr = getLoginRecord();
		
		if (lr == null) {
			
			loginRecord();
		} else {
			Logger.d(
					"loginRecordDao",
					lr.getFailure_num() + "|" + lr.getLock_flag() + "|"
							+ lr.getLogin_date() + "|" + lr.getUserName());
			if (lr.getFailure_num() < 5) {
				LoginRecord loginRecord = new LoginRecord();
				loginRecord.setUserName(getmUserName());
				try {
					if (!CommonUtil.localdateLtDate1(String.valueOf(lr
							.getLogin_date()))) {
						loginRecord.setLogin_date(System.currentTimeMillis()+"");
						if ((lr.getFailure_num() + 1) == 5)
							loginRecord.setLock_flag(1);
						else
							loginRecord.setLock_flag(0);
						loginRecord.setFailure_num(lr.getFailure_num() + 1);
						loginRecordDao.updateLoginReCord(getmUserName(),
								loginRecord);
					} else {
						loginRecordDao.deleteLoginRecord(getmUserName());
						loginRecord();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
	}

	public void loginRecord() {
		LoginRecord loginRecord = new LoginRecord();
		loginRecord.setUserName(getmUserName());
		loginRecord.setFailure_num(1);
		loginRecord.setLock_flag(0);
		loginRecord.setLogin_date(String.valueOf(System.currentTimeMillis()));
		loginRecordDao.saveContactList(loginRecord);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
}
