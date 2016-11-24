package com.td.tradedistance.app.activity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import api.ApiAction;
import api.GsonRequest;
import api.ImageCacheManager;
import api.VolleyController;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.td.tradedistance.app.R;
import com.td.tradedistance.app.TDApp;
import com.td.tradedistance.app.adapter.ZhiShiJieGouAdapter;
import com.td.tradedistance.app.bean.CourseDetailsRoot;
import com.td.tradedistance.app.bean.CourseDetailsShuju;
import com.td.tradedistance.app.bean.JiaoXueJiHua;
import com.td.tradedistance.app.global.Global;
import com.td.tradedistance.app.utils.DES;
import com.td.tradedistance.app.utils.ProgressUtils;
import com.td.tradedistance.app.utils.ToastUtil;
import com.td.tradedistance.app.widget.TitleBar;

public class CourseDetailsActivity extends BaseActivity{
	
	private static final String Tag = CourseDetailsActivity.class.getSimpleName();
	private LinearLayout seemore_lly;
	private ImageView courseimg_iv,head_iv;
	private TextView jianjiashenglie_tv,name_tv;
	private TextView coursecount_tv;
	private TitleBar mTitleBar;
	private Button gostudy_bnt;
	private ScrollView mSv;
	private boolean isShowJiaJia= false;
	private ZhiShiJieGouAdapter adapter;
	private Intent it;
	private ListView listView;
	private JiaoXueJiHua jxjh;
	private Handler mHandler;
	private List<CourseDetailsShuju> list;
	private String title= null;
	private String name = null;
	//private boolean isWanJieFlag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	setContentView(R.layout.activity_course_details);
    	super.onCreate(savedInstanceState);
    }
	@Override
	protected void initVariables() {
		it = getIntent();
		mHandler = new Handler();
		jxjh = (JiaoXueJiHua)it.getSerializableExtra("jxjh");
		title = jxjh.getKeChengMingCheng();
		//isWanJieFlag = (boolean)it.getBooleanExtra("isWanJieFlag",false);
	}

	@Override
	protected void initViews(Bundle savedInstanceState) {
		mSv = (ScrollView) findViewById(R.id.sv);
		mTitleBar = (TitleBar) findViewById(R.id.titlebar);
		seemore_lly = (LinearLayout) findViewById(R.id.seemore_lly);
		courseimg_iv = (ImageView) findViewById(R.id.courseimg_iv);
		head_iv = (ImageView) findViewById(R.id.head_iv);
		jianjiashenglie_tv = (TextView) findViewById(R.id.jianjiashenglie_tv);
		coursecount_tv = (TextView) findViewById(R.id.coursecount_tv);
		name_tv  = (TextView) findViewById(R.id.name_tv);
		gostudy_bnt = (Button)findViewById(R.id.gostudy_bnt);
		listView = (ListView) findViewById(R.id.zhishijiegou_lv);
		
		
		
		seemore_lly.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!isShowJiaJia){
					isShowJiaJia= true;
					jianjiashenglie_tv.setMaxLines(Integer.MAX_VALUE);
					coursecount_tv.setText(getResources().getString(R.string.hidemore_str));
				}else{
					isShowJiaJia = false;
					jianjiashenglie_tv.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
					jianjiashenglie_tv.setMaxLines(2);
					coursecount_tv.setText(getResources().getString(R.string.seemore_str));
				}
			}
		});
		
		mTitleBar.setTitle(jxjh.getKeChengMingCheng());
		ImageCacheManager.loadImage(CourseDetailsActivity.this, jxjh.getKeChengZhaoPian(), courseimg_iv,ImageCacheManager.setDefaultImage(CourseDetailsActivity.this, R.drawable.course_default), ImageCacheManager.setErrorImage(CourseDetailsActivity.this, R.drawable.course_default));
	}

	@Override
	protected void loadData() {
		ProgressUtils.showDialog();
		loadKeChengXinXi();
	}
	public void loadKeChengXinXi(){
		Map<String, String> mParams = new HashMap<String, String>();
		try {
			mParams.put("xt", Global.XT_ID);
			mParams.put("wybs", TDApp.manager.getDESYongHuWeiYiBiaoShi());
			mParams.put("sqm", DES.encryptDES(TDApp.manager.getShouQuanMa(), Global.KEY));
			mParams.put("zdsbm", DES.encryptDES(
					TDApp.deviceInfo.getDeviceId(), Global.KEY));
			mParams.put("kcdm", DES.encryptDES(jxjh.getKeChengDaiMa(), Global.KEY));
		} catch (Exception e1) {
			proDailog.dismiss();
			return;
		}
		GsonRequest<CourseDetailsRoot> gsonRequest = new GsonRequest<CourseDetailsRoot>(
				ApiAction.getUrlParams(ApiAction.KeChengXinXi, mParams),
				CourseDetailsRoot.class, new Response.Listener<CourseDetailsRoot>() {
					@SuppressWarnings("unused")
					@Override
					public void onResponse(CourseDetailsRoot jxjhRoot) {
						proDailog.dismiss();
						int ret = jxjhRoot.getRet();
						if (ret >= Global.Success) {
							if(jxjhRoot.getKeChengZhuangTai()!=null){
								if(!"n".equals(jxjhRoot.getKeChengZhuangTai().toLowerCase())){
									gostudy_bnt.setVisibility(View.GONE);
								}else{
									gostudy_bnt.setVisibility(View.VISIBLE);
								}
							}
							if(!TextUtils.isEmpty(jxjhRoot.getShouQuanMa()))
								TDApp.manager.setShouQuanMa(jxjhRoot.getShouQuanMa());
							name = jxjhRoot.getKeChengJiaoShi();
							name_tv.setText(jxjhRoot.getKeChengJiaoShi());
							ImageCacheManager.loadImage(CourseDetailsActivity.this, jxjhRoot.getJiaoShiZhaoPian(), head_iv,ImageCacheManager.setDefaultImage(CourseDetailsActivity.this, R.drawable.teacher_01), ImageCacheManager.setErrorImage(CourseDetailsActivity.this, R.drawable.teacher_01),300,300);
							jianjiashenglie_tv.setText(jxjhRoot.getKeChengJieShao());
							list =jxjhRoot.getShuJu();
							adapter = new ZhiShiJieGouAdapter(CourseDetailsActivity.this);
							adapter.setList(list);
							listView.setAdapter(adapter);
							gostudy_bnt.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									it.setClass(CourseDetailsActivity.this, GoStudyActivity.class);
									it.putExtra("title", title);
									it.putExtra("name", name);
									it.putExtra("jxjh", jxjh);
									it.putExtra("list",(Serializable)list);
									startActivity(it);
									
								}
							});
							scrollUp();
						} else {// 不成功
							ToastUtil.TipToast(jxjhRoot.getErrInfo());
						}
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						proDailog.dismiss();
						ApiAction.errorTip(CourseDetailsActivity.this, error);
					}
				}

		);

		VolleyController.getInstance(CourseDetailsActivity.this)
				.addToRequestQueueRepeat(gsonRequest, Tag);
	}
	 private void scrollUp(){
			
			mHandler.post(new Runnable() {
				
				@Override
				public void run() {
					mSv.smoothScrollTo(0, 0);
				}
			});
		}
}
