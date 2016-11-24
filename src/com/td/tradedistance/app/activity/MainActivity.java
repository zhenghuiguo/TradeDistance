package com.td.tradedistance.app.activity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import api.ApiAction;
import api.GsonRequest;
import api.VolleyController;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.td.tradedistance.app.ApiGlobal;
import com.td.tradedistance.app.R;
import com.td.tradedistance.app.TDApp;
import com.td.tradedistance.app.bean.Login;
import com.td.tradedistance.app.fragment.CourseFragment;
import com.td.tradedistance.app.fragment.DownLoadFragment;
import com.td.tradedistance.app.fragment.MyFragment;
import com.td.tradedistance.app.fragment.StudyFragment1;
import com.td.tradedistance.app.global.Global;
import com.td.tradedistance.app.utils.DES;
import com.td.tradedistance.app.utils.Logger;
import com.td.tradedistance.app.utils.NetUtils;
import com.td.tradedistance.app.utils.ProgressUtils;
import com.td.tradedistance.app.utils.ToastUtil;
import com.td.tradedistance.app.widget.OnClickXiaoXiZhongXinListener;
import com.td.tradedistance.app.widget.TabItem;
import com.td.tradedistance.app.widget.TitleBar;

import down.GlobalDownLoading1;



public class MainActivity extends FragmentActivity {
	private static final String Tag = "MainActivity";
    @SuppressWarnings("unused")
	private TitleBar mTitleBar;
	private TabItem mCourse_layout;
	private TabItem mStudy_layout;
	private TabItem mDownLoad_layout;
	private TabItem mMy_layout;
	private ProgressUtils proDailog = null;
	private CourseFragment mCourse_Fragment;
	private StudyFragment1 mStudy_Fragment;
	private DownLoadFragment mDownLoad_Fragment;
	private MyFragment mMy_Fragment;
	private FragmentManager fragement_Manager;
	private FragmentTransaction transaction;
    private String userName;
    private String passWord;
    private Resources rs;
    private Intent it = null;
    
   // public static boolean isForeground = false;
    public  boolean isJPushTip = false;
  //for receive customer msg from jpush server
  	private MessageReceiver mMessageReceiver;
  	public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
  	public static final String KEY_TITLE = "title";
  	public static final String KEY_MESSAGE = "message";
  	public static final String KEY_EXTRAS = "extras";
  	
  	public void registerMessageReceiver() {
  		mMessageReceiver = new MessageReceiver();
  		IntentFilter filter = new IntentFilter();
  		filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
  		filter.addAction(MESSAGE_RECEIVED_ACTION);
  		registerReceiver(mMessageReceiver, filter);
  	}

  	public class MessageReceiver extends BroadcastReceiver {

  		@Override
  		public void onReceive(Context context, Intent intent) {
  			if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
  				isJPushTip = true;
               /* String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                if (!ExampleUtil.isEmpty(extras)) {
              	  showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                }*/
                setRightImage(R.drawable.news_top_new);
  			}
  		}
  	}
  	
  	private void setRightImage(int resid){
  			mTitleBar.setRightImageBg(resid);
  	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		rs = getResources();
		registerMessageReceiver();  // used for receive msg
		Logger.d(Tag, "onCreate");
		mTitleBar = (TitleBar) findViewById(R.id.titleBar);
		mCourse_layout = (TabItem) findViewById(R.id.course_layout);
		mStudy_layout = (TabItem) findViewById(R.id.study_layout);
		mDownLoad_layout = (TabItem) findViewById(R.id.download_layout);
		mMy_layout = (TabItem) findViewById(R.id.my_layout);
		boolean isLoginflag = getIntent().getBooleanExtra("flag", false);//是否需要调用登录接口  true不需要 ，false需要 
		if(!isLogin()){
			 it = new Intent(MainActivity.this,LoginActivity.class);
			 startActivity(it);
			 finish();
			 return;
		}
		if(!isLoginflag){
			DengLu();
		}else{
			setTabSelection(0);
		}
	
        
		mTitleBar.setXxzxListener(new OnClickXiaoXiZhongXinListener() {
			
			@Override
			public void onXiaoXiZhongXin() {
				if(isJPushTip){
					isJPushTip = false;
				}
				setRightImage(R.drawable.news_top);
				Intent it = new Intent(MainActivity.this,com.td.tradedistance.app.activity.WebActivity.class);
		         it.putExtra("title",rs.getString(R.string.xiaoxizhongxin_str));
		         it.putExtra("url", ApiGlobal.getHtml(ApiGlobal.XiaoXiZhongXiaoUrl));
		         startActivity(it);
			}
		});

		mCourse_layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setTabSelection(0);
			}
		});

		mStudy_layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setTabSelection(1);
			}
		});
		mDownLoad_layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setTabSelection(2);
			}
		});

		mMy_layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setTabSelection(3);
			}
		});

	}
    public boolean isLogin(){
    	 userName = TDApp.manager.getUserName();
    	 passWord = TDApp.manager.getPassWord();
    	if((userName!=null&&userName.length()>0) && (passWord!=null&&passWord.length()>0)){
    		return true;
    	}
    	return false;
    }
	@Override
	protected void onStart() {
		Logger.d(Tag, "onStart");
		super.onStart();
	}

	@Override
	protected void onResume() {
		Logger.d(Tag, "onResume");
		//isForeground = true;
		super.onResume();
	}

	@Override
	protected void onPause() {
		Logger.d(Tag, "onPause");
		//isForeground = false;
		super.onPause();
	}

	@Override
	protected void onStop() {
		Logger.d(Tag, "onStop");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		Logger.d(Tag, "onDestroy");
		unregisterReceiver(mMessageReceiver);
		super.onDestroy();
	}

	private void setTabSelection(int index) {
		GlobalDownLoading1.getInstance().stopAllDownloadTask();
		clearSelection();
		fragement_Manager = this.getSupportFragmentManager();
		Logger.d("MainActivity", fragement_Manager + " fragement_Manager");
		transaction = fragement_Manager.beginTransaction();
		Logger.d("MainActivity", transaction + " transaction");
		hideFragments(transaction);
		switch (index) {
		case 0:
			titleBarShow( rs.getString(R.string.course_str) , R.drawable.news_top, R.drawable.news_top_new);
			mCourse_layout.setTabImage(R.drawable.bottom_curriculum_red);
			mCourse_layout.setNameTextColor(rs.getColor(
					R.color.tv_tab_selected));
			if (mCourse_Fragment == null) {
				mCourse_Fragment = new CourseFragment();
				transaction.add(R.id.content, mCourse_Fragment);
			} else {
				/*transaction.remove(mCourse_Fragment);
				mCourse_Fragment = new CourseFragment();
				transaction.add(R.id.content, mCourse_Fragment);*/
				transaction.show(mCourse_Fragment);
			}
			break;
		case 1:
			titleBarShow( rs.getString(R.string.study_str) , R.drawable.news_top, R.drawable.news_top_new);
			mStudy_layout.setTabImage(R.drawable.bottom_study_red);
			mStudy_layout.setNameTextColor(rs.getColor(
					R.color.tv_tab_selected));
			if (mStudy_Fragment == null) {
				mStudy_Fragment = new StudyFragment1(fragement_Manager);
				transaction.add(R.id.content, mStudy_Fragment);
			} else {
				/*transaction.remove(mStudy_Fragment);
				mStudy_Fragment = new StudyFragment1(fragement_Manager);
				transaction.add(R.id.content, mStudy_Fragment);*/
				transaction.show(mStudy_Fragment);
			}
			
			break;
		case 2:
			titleBarShow( rs.getString(R.string.download_str) , R.drawable.news_top, R.drawable.news_top_new);
			mDownLoad_layout.setTabImage(R.drawable.bottom_download_red);
			mDownLoad_layout.setNameTextColor(rs.getColor(
					R.color.tv_tab_selected));
			
			
			if (mDownLoad_Fragment == null) {
				mDownLoad_Fragment = new DownLoadFragment();
				transaction.add(R.id.content, mDownLoad_Fragment);
			} else {
				transaction.remove(mDownLoad_Fragment);
				mDownLoad_Fragment = new DownLoadFragment();
				transaction.add(R.id.content, mDownLoad_Fragment);
				//transaction.show(mDownLoad_Fragment);
			}
			break;

		case 3:
			mTitleBar.setVisibility(View.GONE);
			mMy_layout.setTabImage(R.drawable.bottom_my_red);
			mMy_layout.setNameTextColor(rs.getColor(
					R.color.tv_tab_selected));
			if (mMy_Fragment == null) {
				mMy_Fragment = new MyFragment();
				transaction.add(R.id.content, mMy_Fragment);
			} else {
				/*transaction.remove(mMy_Fragment);
				mMy_Fragment = new MyFragment();
				transaction.add(R.id.content, mMy_Fragment);*/
				transaction.show(mMy_Fragment);
			}
			break;

		default:
			break;
		}
		transaction.commit();
	}

	/**
	 * 清除掉所有的选中状态。
	 */
	private void clearSelection() {
		mCourse_layout.setTabImage(R.drawable.bottom_curriculum_gray);
		mCourse_layout.setNameTextColor(rs.getColor(
				R.color.tv_tab_normal));
		mStudy_layout.setTabImage(R.drawable.bottom_study_gray);
		mStudy_layout.setNameTextColor(rs.getColor(
				R.color.tv_tab_normal));
		mDownLoad_layout.setTabImage(R.drawable.bottom_download_gray);
		mDownLoad_layout.setNameTextColor(rs.getColor(
				R.color.tv_tab_normal));
		mMy_layout.setTabImage(R.drawable.bottom_my_gray);
		mMy_layout.setNameTextColor(rs.getColor(
				R.color.tv_tab_normal));
	}

	/**
	 * 将所有的Fragment都置为隐藏状态。
	 * 
	 * @param transaction
	 *            用于对Fragment执行操作的事务
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (mCourse_Fragment != null) {
			transaction.hide(mCourse_Fragment);
		}
		if (mDownLoad_Fragment != null) {
			transaction.hide(mDownLoad_Fragment);
		}
		if (mStudy_Fragment != null) {
			transaction.hide(mStudy_Fragment);
		}
		if (mMy_Fragment != null) {
			transaction.hide(mMy_Fragment);
		}
	}
	
	
private void DengLu() {
	    proDailog = ProgressUtils.createDialog(MainActivity.this);
	    ProgressUtils.showDialog();
		Map<String, String> mParams = new HashMap<String, String>();
		try {
			mParams.put("xt", Global.XT_ID);
			mParams.put("yhlx", DES.encryptDES(Global.YHLX, Global.KEY));
			mParams.put("yhm", DES.encryptDES(userName, Global.KEY));
			mParams.put("mm", DES.encryptDES(passWord, Global.KEY));
			mParams.put("zdsbm", DES.encryptDES(TDApp.deviceInfo.getDeviceId(),Global.KEY));
		} catch (Exception e1) {
			proDailog.dismiss();
			return;
		}
		GsonRequest<Login> gsonRequest = new GsonRequest<Login>(
				ApiAction.getUrlParams(ApiAction.DengLu, mParams), Login.class,
				new Response.Listener<Login>() {
					@Override
					public void onResponse(Login login) {
						proDailog.dismiss();
						int ret = login.getRet();
						if (ret >= Global.Success) {
							TDApp.manager.setShouQuanMa(login.getShouQuanMa());
							TDApp.manager.setNoDESYongHuWeiYiBiaoShi(login.getYongHuBiaoShi());
							try {
								TDApp.manager.setDESYongHuWeiYiBiaoShi(DES.encryptDES(login.getYongHuBiaoShi(),Global.KEY));
							} catch (Exception e) {
								e.printStackTrace();
							}
							setTabSelection(0);
						} else {// 不成功
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
				.addToRequestQueueRepeat(gsonRequest, Tag);

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			onBackPressed();
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public void onBackPressed() {
		Intent i = new Intent(Intent.ACTION_MAIN);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.addCategory(Intent.CATEGORY_HOME);
		startActivity(i);
	}
	private void titleBarShow(String strTitle ,int resid,int residTip){
		mTitleBar.setVisibility(View.VISIBLE);
		mTitleBar.setTitle(strTitle);
		if(isJPushTip) mTitleBar.setRightImageBg(residTip);
		else{
			mTitleBar.setRightImageBg(resid);
		}
	}
	
}
