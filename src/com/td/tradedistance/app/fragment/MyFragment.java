package com.td.tradedistance.app.fragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import api.ApiAction;
import api.GsonRequest;
import api.ImageCacheManager;
import api.VolleyController;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.td.tradedistance.app.ApiGlobal;
import com.td.tradedistance.app.R;
import com.td.tradedistance.app.TDApp;
import com.td.tradedistance.app.activity.GeRenXinXinActivity;
import com.td.tradedistance.app.activity.SettingActivity;
import com.td.tradedistance.app.bean.GeRenXinXin;
import com.td.tradedistance.app.bean.LanMuCaiDanRoot;
import com.td.tradedistance.app.bean.LanMuCaiDanShuJu;
import com.td.tradedistance.app.global.Global;
import com.td.tradedistance.app.utils.DES;
import com.td.tradedistance.app.utils.Logger;
import com.td.tradedistance.app.utils.ProgressUtils;
import com.td.tradedistance.app.utils.ToastUtil;
import com.td.tradedistance.app.widget.MyTabView;

public class MyFragment extends Fragment{
	private static final String Tag ="MyFragment";// MyFragment.class.getSimpleName();
	private ImageView setting_iv;
	private ImageView head_iv;
	private TextView name_tv,bianhao_tv;
	private List<LanMuCaiDanShuJu> list;
	private LanMuCaiDanRoot response;
	private GeRenXinXin xinxin;
	private MyTabView tab1;
	private MyTabView tab2;
	private MyTabView tab3;
	private MyTabView tab4;
	private MyTabView tab5;
	private MyTabView tab6;
	public ProgressUtils proDailog = null;
	/*@Override
	public void onAttach(Context context) {
		Logger.d(Tag,"onAttach MyFragment");
		super.onAttach(context);
	}*/
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Logger.d(Tag,"onCreate MyFragment");
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_my, container, false);
		setting_iv = (ImageView)view.findViewById(R.id.setting_iv);
		head_iv  = (ImageView)view.findViewById(R.id.head_iv);
		name_tv = (TextView)view.findViewById(R.id.name_tv);
		bianhao_tv = (TextView)view.findViewById(R.id.bianhao_tv);
		tab1 = (MyTabView)view.findViewById(R.id.tab1);
	    tab2 = (MyTabView)view.findViewById(R.id.tab2);
	    tab3 = (MyTabView)view.findViewById(R.id.tab3);
	    tab4 = (MyTabView)view.findViewById(R.id.tab4);
	    tab5 = (MyTabView)view.findViewById(R.id.tab5);
	    tab6 = (MyTabView)view.findViewById(R.id.tab6);
		Logger.d(Tag,"onCreateView MyFragment");
		proDailog = ProgressUtils.createDialog(getActivity());
		
		setting_iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent it = new Intent(getActivity(),SettingActivity.class);
				it.putExtra("xinXin",  xinxin);
				startActivity(it);
			}
		});
		head_iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent it = new Intent(getActivity(),GeRenXinXinActivity.class);
				startActivity(it);
			}
		});
		return view;
	}
	private void geRenXinXin(){
		//ProgressUtils.showDialog();
		Map<String, String> mParams = new HashMap<String, String>();
		try {
			mParams.put("xt", Global.XT_ID);
			mParams.put("zdsbm",
					DES.encryptDES(TDApp.deviceInfo.getDeviceId(), Global.KEY));
			mParams.put("wybs", TDApp.manager.getDESYongHuWeiYiBiaoShi());
			mParams.put("sqm", DES.encryptDES(TDApp.manager.getShouQuanMa(), Global.KEY));
		} catch (Exception e1) {
			// proDailog.dismiss();
		}
		GsonRequest<GeRenXinXin> zczdgsonRequest = new GsonRequest<GeRenXinXin>(
				ApiAction.getUrlParams(ApiAction.GeRenXinXi, mParams),
				GeRenXinXin.class, new Response.Listener<GeRenXinXin>() {

					@Override
					public void onResponse(GeRenXinXin response) {
						setXinXin(response);

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						ApiAction.errorTip(getActivity(), error);
					}
				});
		VolleyController.getInstance(getActivity())
				.addToRequestQueue(zczdgsonRequest);
	}
	
	public void setXinXin(GeRenXinXin response){
		xinxin = response;
        //proDailog.dismiss();
		int ret = response.getRet();
		if (ret >= Global.Success) {
			if(!TextUtils.isEmpty(response.getShouQuanMa()))
				TDApp.manager.setShouQuanMa(response.getShouQuanMa());
			name_tv.setText(response.getXingMing());
			bianhao_tv.setText(response.getXueHao());
			ImageCacheManager.loadImage(getActivity(), response.getXueJiZhaoPian(), head_iv,ImageCacheManager.setDefaultImage(getActivity(), R.drawable.course_default), ImageCacheManager.setErrorImage(getActivity(), R.drawable.course_default));
		} else {// 不成功
			ToastUtil.TipToast(response.getErrInfo());
		}
	}
	private void LanMuCaiDan(){
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
		GsonRequest<LanMuCaiDanRoot> zczdgsonRequest = new GsonRequest<LanMuCaiDanRoot>(
				ApiAction.getUrlParams(ApiAction.LanMuCaiDan, mParams),
				LanMuCaiDanRoot.class, new Response.Listener<LanMuCaiDanRoot>() {

					@Override
					public void onResponse(LanMuCaiDanRoot response) {
                        
						setCaiDan(response);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						proDailog.dismiss();
						if(getActivity()!=null)
							ApiAction.errorTip(getActivity(), error);
					}
				});
		VolleyController.getInstance(getActivity())
				.addToRequestQueue(zczdgsonRequest);
	}
	public void setCaiDan(LanMuCaiDanRoot response){
		 this.response = response;
		 proDailog.dismiss();
			int ret = response.getRet();
			if (ret >= Global.Success) {
				if(!TextUtils.isEmpty(response.getShouQuanMa()))
					TDApp.manager.setShouQuanMa(response.getShouQuanMa());
				list = response.getShuJu();
				int size = list.size();
				for (int i = 0; i < size; i++) {
					final LanMuCaiDanShuJu shuju=(LanMuCaiDanShuJu)list.get(i);
					switch (i) {
					case 0:
						tab1.setVisibility(View.VISIBLE);
						if(getActivity()!=null)
							ImageCacheManager.loadImage(getActivity(), shuju.getCaiDanTuBiaoDiZhi(), tab1.getIv(),ImageCacheManager.setDefaultImage(getActivity(), R.drawable.course_default), ImageCacheManager.setErrorImage(getActivity(), R.drawable.course_default));
						tab1.setTv(shuju.getCaiDanMingCheng());
						tab1.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								 gotoWeb(shuju);
							}
						});
						break;
					case 1:
						tab2.setVisibility(View.VISIBLE);
						tab2.setTv(shuju.getCaiDanMingCheng());
						if(getActivity()!=null)
							ImageCacheManager.loadImage(getActivity(), shuju.getCaiDanTuBiaoDiZhi(), tab2.getIv(),ImageCacheManager.setDefaultImage(getActivity(), R.drawable.course_default), ImageCacheManager.setErrorImage(getActivity(), R.drawable.course_default));
						tab2.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								gotoWeb(shuju);
							}
						});
						break;

					case 2:
						tab3.setVisibility(View.VISIBLE);
						tab3.setTv(shuju.getCaiDanMingCheng());
						if(getActivity()!=null)
							ImageCacheManager.loadImage(getActivity(), shuju.getCaiDanTuBiaoDiZhi(), tab3.getIv(),ImageCacheManager.setDefaultImage(getActivity(), R.drawable.course_default), ImageCacheManager.setErrorImage(getActivity(), R.drawable.course_default));
						tab3.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								gotoWeb(shuju);
							}
						});
						break;

					case 3:
						tab4.setVisibility(View.VISIBLE);
						tab4.setTv(shuju.getCaiDanMingCheng());
						if(getActivity()!=null)
							ImageCacheManager.loadImage(getActivity(), shuju.getCaiDanTuBiaoDiZhi(), tab4.getIv(),ImageCacheManager.setDefaultImage(getActivity(), R.drawable.course_default), ImageCacheManager.setErrorImage(getActivity(), R.drawable.course_default));
						tab4.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								gotoWeb(shuju);
							}
						});
						break;

					case 4:
						tab5.setVisibility(View.VISIBLE);
						tab5.setTv(shuju.getCaiDanMingCheng());
						if(getActivity()!=null)
							ImageCacheManager.loadImage(getActivity(), shuju.getCaiDanTuBiaoDiZhi(), tab5.getIv(),ImageCacheManager.setDefaultImage(getActivity(), R.drawable.course_default), ImageCacheManager.setErrorImage(getActivity(), R.drawable.course_default));
						tab5.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								gotoWeb(shuju);
							}
						});
						break;

					case 5:
						tab6.setVisibility(View.VISIBLE);
						tab6.setTv(shuju.getCaiDanMingCheng());
						if(getActivity()!=null)
							ImageCacheManager.loadImage(getActivity(), shuju.getCaiDanTuBiaoDiZhi(), tab6.getIv(),ImageCacheManager.setDefaultImage(getActivity(), R.drawable.course_default), ImageCacheManager.setErrorImage(getActivity(), R.drawable.course_default));
						tab6.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								gotoWeb(shuju);
							}
						});
						
						break;
					}
				}
				
			} else {// 不成功
				ToastUtil.TipToast(response.getErrInfo());
			}
	}
	public void gotoWeb(LanMuCaiDanShuJu shuju){
		 Intent it = new Intent(getActivity(),com.td.tradedistance.app.activity.WebActivity.class);
         it.putExtra("title", shuju.getCaiDanMingCheng());
         it.putExtra("url", ApiGlobal.getHtml(shuju.getCaiDanDiZhi()));
         
         getActivity().startActivity(it);
	}
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	Logger.d(Tag,"onActivityCreated MyFragment");
    	super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onStart() {
    	Logger.d(Tag,"onStart MyFragment");
    	super.onStart();
    }
    @Override
    public void onResume() {
    	Logger.d(Tag,"onResume MyFragment");
    	
    	if((list!=null &&list.size()>0)&&(xinxin!=null)){
    		setCaiDan(response);
    		setXinXin(xinxin);
    	}else{
    		geRenXinXin();
    		LanMuCaiDan();
    	}
    	super.onResume();
    }
    @Override
    public void onPause() {
    	Logger.d(Tag,"onPause MyFragment");
    	super.onPause();
    }
    @Override
    public void onStop() {
    	Logger.d(Tag,"onStop MyFragment");
    	super.onStop();
    }
    @Override
    public void onDestroyView() {
    	Logger.d(Tag,"onDestroyView MyFragment");
    	super.onDestroyView();
    }
    @Override
    public void onDestroy() {
    	Logger.d(Tag,"onDestroy MyFragment");
    	super.onDestroy();
    }
    @Override
    public void onDetach() {
    	Logger.d(Tag,"onDetach MyFragment");
    	super.onDetach();
    }
}
