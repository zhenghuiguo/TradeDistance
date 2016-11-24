package com.td.tradedistance.app.fragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import api.ApiAction;
import api.GsonRequest;
import api.VolleyController;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.td.tradedistance.app.ApiGlobal;
import com.td.tradedistance.app.R;
import com.td.tradedistance.app.TDApp;
import com.td.tradedistance.app.activity.KaoShiWebActivity;
import com.td.tradedistance.app.adapter.ZuoYeAdapter;
import com.td.tradedistance.app.bean.ZuoYeRoot;
import com.td.tradedistance.app.bean.ZuoYeShuju;
import com.td.tradedistance.app.global.Global;
import com.td.tradedistance.app.utils.DES;
import com.td.tradedistance.app.utils.Logger;
import com.td.tradedistance.app.utils.ToastUtil;

public class ZuoYeFragment extends Fragment{
	private static final String Tag ="ZuoYeFragment";// MyFragment.class.getSimpleName();
	private ListView zuoye_lv;
	private ZuoYeAdapter adapter;
	private String kcdm;
	private String title;
	public ZuoYeFragment(String kcdm,String title){
		this.kcdm = kcdm;
		this.title = title;
	}
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
		View view = inflater.inflate(R.layout.fragment_zuoye, container, false);
		zuoye_lv= (ListView) view.findViewById(R.id.zuoye_lv);
		zuoye_lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ZuoYeShuju zy = (ZuoYeShuju)adapter.getItem((int)id);
				Logger.d("onItemClick", "onItemClick");
				if(zy.getWanChengZhuangTai()==0&&zy.getShiFouKeYiZuoZuoYe()==1){
				    Intent it = new Intent(getActivity(),KaoShiWebActivity.class);
				    it.putExtra("title", title);
				    it.putExtra("url", ApiGlobal.getKaoShiHtml(ApiAction.kaoshi, kcdm, zy.getXuHao(), zy.getZaiXianKaoShiID()));
				    getActivity().startActivity(it);
				}
			}
		});
		loadPingShiZuoYeChengJi();
		Logger.d(Tag,"onCreateView MyFragment");
		return view;
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
    
    public void loadPingShiZuoYeChengJi() {
		Map<String, String> mParams = new HashMap<String, String>();
		try {
			mParams.put("xt", Global.XT_ID);
			mParams.put("wybs", TDApp.manager.getDESYongHuWeiYiBiaoShi());
			mParams.put("sqm", DES.encryptDES(TDApp.manager.getShouQuanMa(), Global.KEY));
			mParams.put("zdsbm", DES.encryptDES(
					TDApp.deviceInfo.getDeviceId(), Global.KEY));
			mParams.put("kcdm", DES.encryptDES(kcdm, Global.KEY));
		} catch (Exception e1) {
			//proDailog.dismiss();
			return;
		}
		GsonRequest<ZuoYeRoot> gsonRequest = new GsonRequest<ZuoYeRoot>(
				ApiAction.getUrlParams(ApiAction.PingShiZuoYeChengJi, mParams),
				ZuoYeRoot.class, new Response.Listener<ZuoYeRoot>() {
					@SuppressWarnings("unused")
					@Override
					public void onResponse(ZuoYeRoot jxjh) {
						//proDailog.dismiss();
						int ret = jxjh.getRet();
						if (ret >= Global.Success) {
							if(!TextUtils.isEmpty(jxjh.getShouQuanMa()))
								TDApp.manager.setShouQuanMa(jxjh.getShouQuanMa());
							List<ZuoYeShuju> list =jxjh.getShuJu();
							adapter = new ZuoYeAdapter(getActivity());
							adapter.setList(list);
							zuoye_lv.setAdapter(adapter);
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
}
