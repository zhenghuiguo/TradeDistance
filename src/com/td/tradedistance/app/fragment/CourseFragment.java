package com.td.tradedistance.app.fragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import api.ApiAction;
import api.GsonRequest;
import api.ImageCacheManager;
import api.VolleyController;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.td.tradedistance.app.R;
import com.td.tradedistance.app.TDApp;
import com.td.tradedistance.app.activity.XiaoXiZhongXiActivity;
import com.td.tradedistance.app.adapter.CourseAdapter;
import com.td.tradedistance.app.bean.GuangGaoWeiRoot;
import com.td.tradedistance.app.bean.GuangGaoWeiShuJu;
import com.td.tradedistance.app.bean.JiaoXueJiHua;
import com.td.tradedistance.app.bean.JiaoXueJiHuaRoot;
import com.td.tradedistance.app.bean.JiaoXueJiHuaShuJu;
import com.td.tradedistance.app.global.Global;
import com.td.tradedistance.app.utils.DES;
import com.td.tradedistance.app.utils.Logger;
import com.td.tradedistance.app.utils.ProgressUtils;
import com.td.tradedistance.app.utils.ToastUtil;
import com.td.tradedistance.app.widget.OnClickXiaoXiZhongXinListener;
import com.td.tradedistance.app.widget.TitleBar;
import com.td.tradedistance.app.widget.ViewPaperIndicator;

public class CourseFragment extends Fragment implements OnScrollListener{
	
	private static final String Tag = CourseFragment.class.getSimpleName();
	private ProgressUtils proDailog = null;
	//private TitleBar mTitleBar;
	private ViewPaperIndicator viewpaper;
	private  List<JiaoXueJiHuaShuJu> list;
	public ViewPaperIndicator getViewpaper() {
		return viewpaper;
	}
	private ListView mCourseList;
    private CourseAdapter adapter;
    private Handler mHandler;
    private ScrollView mSv;
    private List<GuangGaoWeiShuJu> ShuJu;
    
    /**
	 * 记录是否刚打开程序，用于解决进入程序不滚动屏幕，不会下载图片的问题。
	 */
	private boolean isInit = false;
	/**
	 * 第一张可见图片的下标
	 */
	int _start_index;
	int _end_index;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View courseLayout = inflater.inflate(R.layout.fragment_course, container, false);
		mSv = (ScrollView)courseLayout.findViewById(R.id.sv);
		//mTitleBar = (TitleBar) courseLayout.findViewById(R.id.titleBar);
		viewpaper = (ViewPaperIndicator) courseLayout.findViewById(R.id.viewpaperindicator);
		mCourseList= (ListView) courseLayout.findViewById(R.id.courselist_lv);
		initVariables();
		loadData();
		return courseLayout;
	}
	public void initVariables(){
		mHandler = new Handler();
		
		mCourseList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			     Logger.d("onclick", "onItemClick");
			}
		});
		/*mTitleBar.setXxzxListener(new OnClickXiaoXiZhongXinListener() {
			
			@Override
			public void onXiaoXiZhongXin() {
				Intent it = new Intent(getActivity(),XiaoXiZhongXiActivity.class);
				getActivity().startActivity(it);
				
			}
		});*/
		
	}
	public void loadData(){
		proDailog = ProgressUtils.createDialog(getActivity());
		ProgressUtils.showDialog();
		GuangGaoWei();//广告位
		loadJiaoXueJiHua();//教学计划课程接口 
		
		//loadKeChengXinXi();//课程信息接口
		//loadZaiXueKeCheng();//在学课程接口 
		//loadFuXiDaGang();//复习大纲接口  404
		//loadPingShiZuoYeChengJi();//平时作业成绩接口 404
		//loadLanMuCaiDan();//我的栏目菜单
	}
	public void GuangGaoWei(){
		Map<String, String> mParams = new HashMap<String, String>();
		try {
			mParams.put("xt", Global.XT_ID);
			mParams.put("wybs", TDApp.manager.getDESYongHuWeiYiBiaoShi());
			mParams.put("sqm", DES.encryptDES(TDApp.manager.getShouQuanMa(), Global.KEY));
			mParams.put("zdsbm", DES.encryptDES(
					TDApp.deviceInfo.getDeviceId(), Global.KEY));
		} catch (Exception e1) {
			viewpaper.getPb_Loading().getHide();
			return;
		}
		GsonRequest<GuangGaoWeiRoot> gsonRequest = new GsonRequest<GuangGaoWeiRoot>(
				ApiAction.getUrlParams(ApiAction.GuangGaoWei, mParams),
				GuangGaoWeiRoot.class, new Response.Listener<GuangGaoWeiRoot>() {
					@SuppressWarnings("unused")
					@Override
					public void onResponse(GuangGaoWeiRoot ggw) {
						viewpaper.getPb_Loading().getHide();
						int ret = ggw.getRet();
						if (ret >= Global.Success) {
							if(!TextUtils.isEmpty(ggw.getShouQuanMa()))
								TDApp.manager.setShouQuanMa(ggw.getShouQuanMa());
							ShuJu = ggw.getShuJu();
							viewpaper.initViewPager(ShuJu);
						} else {// 不成功
							ToastUtil.TipToast(ggw.getErrInfo());
						}
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						viewpaper.getPb_Loading().getHide();
						ApiAction.errorTip(getActivity(), error);
					}
				}

		);

		VolleyController.getInstance(getActivity())
				.addToRequestQueue(gsonRequest);
	}
	public void loadLanMuCaiDan() {
		Map<String, String> mParams = new HashMap<String, String>();
		try {
			mParams.put("xt", Global.XT_ID);
			mParams.put("wybs", TDApp.manager.getDESYongHuWeiYiBiaoShi());
			mParams.put("sqm", DES.encryptDES(TDApp.manager.getShouQuanMa(), Global.KEY));
			mParams.put("zdsbm", DES.encryptDES(
					TDApp.deviceInfo.getDeviceId(), Global.KEY));
		} catch (Exception e1) {
			//proDailog.dismiss();
			return;
		}
		GsonRequest<JiaoXueJiHuaRoot> gsonRequest = new GsonRequest<JiaoXueJiHuaRoot>(
				ApiAction.getUrlParams(ApiAction.LanMuCaiDan, mParams),
				JiaoXueJiHuaRoot.class, new Response.Listener<JiaoXueJiHuaRoot>() {
					@SuppressWarnings("unused")
					@Override
					public void onResponse(JiaoXueJiHuaRoot jxjh) {
						//proDailog.dismiss();
						int ret = jxjh.getRet();
						if (ret >= Global.Success) {
							if(!TextUtils.isEmpty(jxjh.getShouQuanMa()))
								TDApp.manager.setShouQuanMa(jxjh.getShouQuanMa());
							jxjh.getShuJu();
						} else {// 不成功
							ToastUtil.TipToast(jxjh.getErrInfo());
						}
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						//proDailog.dismiss();
						ApiAction.errorTip(getActivity(), error);
					}
				}

		);

		VolleyController.getInstance(getActivity())
				.addToRequestQueueRepeat(gsonRequest, Tag);
	}
	public void loadPingShiZuoYeChengJi() {
		Map<String, String> mParams = new HashMap<String, String>();
		try {
			mParams.put("xt", Global.XT_ID);
			mParams.put("wybs", TDApp.manager.getDESYongHuWeiYiBiaoShi());
			mParams.put("sqm", DES.encryptDES(TDApp.manager.getShouQuanMa(), Global.KEY));
			mParams.put("zdsbm", DES.encryptDES(
					TDApp.deviceInfo.getDeviceId(), Global.KEY));
			mParams.put("kcdm", DES.encryptDES("acc201a", Global.KEY));
		} catch (Exception e1) {
			//proDailog.dismiss();
			return;
		}
		GsonRequest<JiaoXueJiHuaRoot> gsonRequest = new GsonRequest<JiaoXueJiHuaRoot>(
				ApiAction.getUrlParams(ApiAction.PingShiZuoYeChengJi, mParams),
				JiaoXueJiHuaRoot.class, new Response.Listener<JiaoXueJiHuaRoot>() {
					@SuppressWarnings("unused")
					@Override
					public void onResponse(JiaoXueJiHuaRoot jxjh) {
						//proDailog.dismiss();
						int ret = jxjh.getRet();
						if (ret >= Global.Success) {
							if(!TextUtils.isEmpty(jxjh.getShouQuanMa()))
								TDApp.manager.setShouQuanMa(jxjh.getShouQuanMa());
							jxjh.getShuJu();
						} else {// 不成功
							ToastUtil.TipToast(jxjh.getErrInfo());
						}
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						//proDailog.dismiss();
						ApiAction.errorTip(getActivity(), error);
					}
				}

		);

		VolleyController.getInstance(getActivity())
				.addToRequestQueueRepeat(gsonRequest, Tag);
	}
	public void loadFuXiDaGang(){
		Map<String, String> mParams = new HashMap<String, String>();
		try {
			mParams.put("xt", Global.XT_ID);
			mParams.put("wybs", TDApp.manager.getDESYongHuWeiYiBiaoShi());
			mParams.put("sqm", DES.encryptDES(TDApp.manager.getShouQuanMa(), Global.KEY));
			mParams.put("zdsbm", DES.encryptDES(
					TDApp.deviceInfo.getDeviceId(), Global.KEY));
			mParams.put("kcdm", DES.encryptDES("acc201a", Global.KEY));
		} catch (Exception e1) {
			//proDailog.dismiss();
			return;
		}
		GsonRequest<JiaoXueJiHuaRoot> gsonRequest = new GsonRequest<JiaoXueJiHuaRoot>(
				ApiAction.getUrlParams(ApiAction.FuXiDaGang, mParams),
				JiaoXueJiHuaRoot.class, new Response.Listener<JiaoXueJiHuaRoot>() {
					@SuppressWarnings("unused")
					@Override
					public void onResponse(JiaoXueJiHuaRoot jxjh) {
						//proDailog.dismiss();
						int ret = jxjh.getRet();
						if (ret >= Global.Success) {
							if(!TextUtils.isEmpty(jxjh.getShouQuanMa()))
								TDApp.manager.setShouQuanMa(jxjh.getShouQuanMa());
							jxjh.getShuJu();
						} else {// 不成功
							ToastUtil.TipToast(jxjh.getErrInfo());
						}
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						//proDailog.dismiss();
						ApiAction.errorTip(getActivity(), error);
					}
				}

		);

		VolleyController.getInstance(getActivity())
				.addToRequestQueueRepeat(gsonRequest, Tag);
	}
	public void loadJiaoXueJiHua(){
		Map<String, String> mParams = new HashMap<String, String>();
		try {
			mParams.put("xt", Global.XT_ID);
			mParams.put("wybs", TDApp.manager.getDESYongHuWeiYiBiaoShi());
			mParams.put("sqm", DES.encryptDES(TDApp.manager.getShouQuanMa(), Global.KEY));
			mParams.put("zdsbm", DES.encryptDES(
					TDApp.deviceInfo.getDeviceId(), Global.KEY));
		} catch (Exception e1) {
			proDailog.dismiss();
			return;
		}
		GsonRequest<JiaoXueJiHuaRoot> gsonRequest = new GsonRequest<JiaoXueJiHuaRoot>(
				ApiAction.getUrlParams(ApiAction.JiaoXueJiHua, mParams),
				JiaoXueJiHuaRoot.class, new Response.Listener<JiaoXueJiHuaRoot>() {
					@SuppressWarnings("unused")
					@Override
					public void onResponse(JiaoXueJiHuaRoot jxjh) {
						proDailog.dismiss();
						int ret = jxjh.getRet();
						if (ret >= Global.Success) {
							if(!TextUtils.isEmpty(jxjh.getShouQuanMa()))
								TDApp.manager.setShouQuanMa(jxjh.getShouQuanMa());
							  list = jxjh.getShuJu();
							 if(list!=null){
								    adapter = new CourseAdapter(getActivity());
								    adapter.setList(list);
								    adapter.setListView(mCourseList);
									mCourseList.setAdapter(adapter);
									scrollUp();
							 }
						} else {// 不成功
							ToastUtil.TipToast(jxjh.getErrInfo());
						}
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						proDailog.dismiss();
						ApiAction.errorTip(getActivity(), error);
					}
				}

		);

		VolleyController.getInstance(getActivity())
				.addToRequestQueueRepeat(gsonRequest, Tag);
	}
	
	public void loadKeChengXinXi(){
		Map<String, String> mParams = new HashMap<String, String>();
		try {
			mParams.put("xt", Global.XT_ID);
			mParams.put("wybs", TDApp.manager.getDESYongHuWeiYiBiaoShi());
			mParams.put("sqm", DES.encryptDES(TDApp.manager.getShouQuanMa(), Global.KEY));
			mParams.put("zdsbm", DES.encryptDES(
					TDApp.deviceInfo.getDeviceId(), Global.KEY));
			mParams.put("kcdm", DES.encryptDES("acc201a", Global.KEY));
		} catch (Exception e1) {
			//proDailog.dismiss();
			return;
		}
		GsonRequest<JiaoXueJiHuaRoot> gsonRequest = new GsonRequest<JiaoXueJiHuaRoot>(
				ApiAction.getUrlParams(ApiAction.KeChengXinXi, mParams),
				JiaoXueJiHuaRoot.class, new Response.Listener<JiaoXueJiHuaRoot>() {
					@SuppressWarnings("unused")
					@Override
					public void onResponse(JiaoXueJiHuaRoot jxjh) {
						//proDailog.dismiss();
						int ret = jxjh.getRet();
						if (ret >= Global.Success) {
							if(!TextUtils.isEmpty(jxjh.getShouQuanMa()))
								TDApp.manager.setShouQuanMa(jxjh.getShouQuanMa());
							jxjh.getShuJu();
						} else {// 不成功
							ToastUtil.TipToast(jxjh.getErrInfo());
						}
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						//proDailog.dismiss();
						ApiAction.errorTip(getActivity(), error);
					}
				}

		);

		VolleyController.getInstance(getActivity())
				.addToRequestQueueRepeat(gsonRequest, Tag);
	}
	
	public void loadZaiXueKeCheng(){
		Map<String, String> mParams = new HashMap<String, String>();
		try {
			mParams.put("xt", Global.XT_ID);
			mParams.put("wybs", TDApp.manager.getDESYongHuWeiYiBiaoShi());
			mParams.put("sqm", DES.encryptDES(TDApp.manager.getShouQuanMa(), Global.KEY));
			mParams.put("zdsbm", DES.encryptDES(
					TDApp.deviceInfo.getDeviceId(), Global.KEY));
		} catch (Exception e1) {
			//proDailog.dismiss();
			return;
		}
		GsonRequest<JiaoXueJiHuaRoot> gsonRequest = new GsonRequest<JiaoXueJiHuaRoot>(
				ApiAction.getUrlParams(ApiAction.ZaiXueKeCheng, mParams),
				JiaoXueJiHuaRoot.class, new Response.Listener<JiaoXueJiHuaRoot>() {
					@SuppressWarnings("unused")
					@Override
					public void onResponse(JiaoXueJiHuaRoot jxjh) {
						//proDailog.dismiss();
						int ret = jxjh.getRet();
						if (ret >= Global.Success) {
							if(!TextUtils.isEmpty(jxjh.getShouQuanMa()))
								TDApp.manager.setShouQuanMa(jxjh.getShouQuanMa());
							jxjh.getShuJu();
						} else {// 不成功
							ToastUtil.TipToast(jxjh.getErrInfo());
						}
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						//proDailog.dismiss();
						ApiAction.errorTip(getActivity(), error);
					}
				}

		);

		VolleyController.getInstance(getActivity())
				.addToRequestQueueRepeat(gsonRequest, Tag);
	}
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	Logger.d(Tag,"onActivityCreated CourseFragment");
    	super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onStart() {
    	Logger.d(Tag,"onStart CourseFragment");
    	super.onStart();
    }
    @Override
    public void onResume() {
    	Logger.d(Tag,"onResume CourseFragment");
    	scrollUp();
    	if(!viewpaper.isContinue()){
	    	if(viewpaper!=null){ 
	    		if(ShuJu!=null&&ShuJu.size()>1){
	    			viewpaper.startThread();
	    		}
	    	}
    	}
    	super.onResume();
    }
    @Override
    public void onPause() {
    	Logger.d(Tag,"onPause CourseFragment");
    	super.onPause();
    }
    @Override
    public void onStop() {
    	Logger.d(Tag,"onStop CourseFragment");
    	viewpaper.setContinue(false);
    	super.onStop();
    }
    @Override
    public void onDestroyView() {
    	Logger.d(Tag,"onDestroyView CourseFragment");
    	super.onDestroyView();
    }
    @Override
    public void onDestroy() {
    	Logger.d(Tag,"onDestroy CourseFragment");
    	super.onDestroy();
    }
    @Override
    public void onDetach() {
    	Logger.d(Tag,"onDetach CourseFragment");
    	super.onDetach();
    }
    
    private void scrollUp(){
		
		mHandler.post(new Runnable() {
			
			@Override
			public void run() {
				mSv.smoothScrollTo(0, 0);
			}
		});
	}
	private void pageImgLoad(int start_index, int end_index, AbsListView view) {
		if (list != null) {
			for (; start_index < end_index; start_index++) {
				final JiaoXueJiHuaShuJu type = list.get(start_index);
				List<JiaoXueJiHua> typeList = type.getZShuJu();
				if (typeList != null) {
					int size = typeList.size();
						if (size == 1) {
							final JiaoXueJiHua jxjh1 = typeList.get(0);
							ImageView iv = (ImageView)view.findViewWithTag(jxjh1.getKeChengZhaoPian());
						    if(iv!=null)
						    	ImageCacheManager.loadImage(getActivity(), jxjh1.getKeChengZhaoPian(), iv,ImageCacheManager.setDefaultImage(getActivity(), R.drawable.course_default), ImageCacheManager.setErrorImage(getActivity(), R.drawable.course_default));
						} else if (size >= 2) {
							final JiaoXueJiHua jxjh1 = typeList.get(0);
							ImageView one_iv = (ImageView)view.findViewWithTag(jxjh1.getKeChengZhaoPian());
							final JiaoXueJiHua jxjh2 = typeList.get(1);
							ImageView two_iv = (ImageView)view.findViewWithTag(jxjh1.getKeChengZhaoPian());
							if(one_iv!=null)
								ImageCacheManager.loadImage(getActivity(), jxjh1.getKeChengZhaoPian(), one_iv,ImageCacheManager.setDefaultImage(getActivity(), R.drawable.course_default), ImageCacheManager.setErrorImage(getActivity(), R.drawable.course_default));
							if(two_iv!=null)
								ImageCacheManager.loadImage(getActivity(), jxjh2.getKeChengZhaoPian(), two_iv,ImageCacheManager.setDefaultImage(getActivity(), R.drawable.course_default), ImageCacheManager.setErrorImage(getActivity(), R.drawable.course_default));
						}
			}
			}
		}
	}
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		isInit = true;
		adapter.setmBusy(isInit);
		switch (scrollState) {
		case OnScrollListener.SCROLL_STATE_IDLE:// list停止滚动时加载图片
			//pageImgLoad(_start_index, _end_index, view);
			Logger.d("OnScrollListener", "OnScrollListener");
			break;
		case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
			Logger.d("SCROLL_STATE_TOUCH_SCROLL", "SCROLL_STATE_TOUCH_SCROLL");
			break;
		case OnScrollListener.SCROLL_STATE_FLING:
			Logger.d("SCROLL_STATE_FLING", "SCROLL_STATE_FLING");
			break;
		}
	}
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		Logger.d("onScroll", "onScroll firstVisibleItem=" + firstVisibleItem
				+ "visibleItemCount=" + visibleItemCount + "totalItemCount="
				+ totalItemCount);
		     // 设置当前屏幕显示的起始index和结束index
				if (firstVisibleItem == 0)
					_start_index = firstVisibleItem;
				else
					_start_index = firstVisibleItem - 1;
				Logger.d("_start_index", _start_index + "");
				_end_index = firstVisibleItem + visibleItemCount;
				if (_end_index >= totalItemCount) {
					_end_index = totalItemCount;
				}
	}
}
