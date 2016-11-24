package com.td.tradedistance.app.fragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import api.ApiAction;
import api.GsonRequest;
import api.VolleyController;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.td.tradedistance.app.R;
import com.td.tradedistance.app.TDApp;
import com.td.tradedistance.app.adapter.StudyAdapter;
import com.td.tradedistance.app.bean.JiaoXueJiHuaRoot;
import com.td.tradedistance.app.bean.JiaoXueJiHuaShuJu;
import com.td.tradedistance.app.global.Global;
import com.td.tradedistance.app.utils.DES;
import com.td.tradedistance.app.utils.Logger;
import com.td.tradedistance.app.utils.ToastUtil;

public class ZaiXueCourseFragment1 extends Fragment{
	private static final String Tag ="MainActivity";// MyFragment.class.getSimpleName();
	private ListView mCourseList;
    private StudyAdapter adapter;
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
		View view = inflater.inflate(R.layout.fragment_wanjiecourse,container, false);
		Logger.d(Tag,"onCreateView MyFragment");
		mCourseList= (ListView) view.findViewById(R.id.wanjiecourselist_lv);
		loadZaiXueKeCheng();
		
		mCourseList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			     Logger.d("onclick", "onItemClick");
			}
		});
		return view;
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
							List<JiaoXueJiHuaShuJu> list = jxjh.getShuJu();
							adapter = new StudyAdapter(getActivity());
							adapter.setList(list);
							mCourseList.setAdapter(adapter);
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
