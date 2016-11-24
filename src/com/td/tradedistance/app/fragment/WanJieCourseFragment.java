package com.td.tradedistance.app.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import api.ApiAction;
import api.GsonRequest;
import api.VolleyController;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.td.tradedistance.app.R;
import com.td.tradedistance.app.TDApp;
import com.td.tradedistance.app.adapter.StudyElAdapter;
import com.td.tradedistance.app.bean.JiaoXueJiHua;
import com.td.tradedistance.app.bean.JiaoXueJiHuaRoot;
import com.td.tradedistance.app.bean.JiaoXueJiHuaShuJu;
import com.td.tradedistance.app.global.Global;
import com.td.tradedistance.app.utils.DES;
import com.td.tradedistance.app.utils.ProgressUtils;
import com.td.tradedistance.app.utils.ToastUtil;
import com.td.tradedistance.app.widget.ProgressBarView;

public class WanJieCourseFragment extends Fragment{
	private static final String Tag ="MainActivity";// MyFragment.class.getSimpleName();
	private ProgressUtils proDailog = null;
	ExpandableListView el_study;
	List<JiaoXueJiHuaShuJu> groupList;
	List<List<JiaoXueJiHua>> childList;
	private ProgressBarView pb_Loading_List;
	public ProgressBarView getPb_Loading_List() {
		return pb_Loading_List;
	}
	public void setPb_Loading_List(ProgressBarView pb_Loading_List) {
		this.pb_Loading_List = pb_Loading_List;
	}
    
	private StudyElAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LinearLayout root = (LinearLayout)inflater.inflate(R.layout.fragment_zaixuecourse, null);
		el_study = (ExpandableListView)root.findViewById(R.id.el_study);
		pb_Loading_List = (ProgressBarView) root.findViewById(R.id.pb_loading_list);
		pb_Loading_List.getHide();
		proDailog = ProgressUtils.createDialog(getActivity());
		ProgressUtils.showDialog();
		loadZaiXueKeCheng();
		//获取所属类别内容 
		el_study.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			/*	Intent it = new Intent(getActivity(),FengHouLangYanXiangQingActivity.class);
				getActivity().startActivity(it);*/
			}
		});
		return root;	
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
			proDailog.dismiss();
			return;
		}
		GsonRequest<JiaoXueJiHuaRoot> gsonRequest = new GsonRequest<JiaoXueJiHuaRoot>(
				ApiAction.getUrlParams(ApiAction.ZaiXueKeCheng_YWJ, mParams),
				JiaoXueJiHuaRoot.class, new Response.Listener<JiaoXueJiHuaRoot>() {
					@SuppressWarnings("unused")
					@Override
					public void onResponse(JiaoXueJiHuaRoot jxjh) {
						proDailog.dismiss();
						int ret = jxjh.getRet();
						if (ret >= Global.Success) {
							if(!TextUtils.isEmpty(jxjh.getShouQuanMa()))
								TDApp.manager.setShouQuanMa(jxjh.getShouQuanMa());
							showInfo(jxjh);
						} else {// 不成功
							pb_Loading_List.getHide();
							ToastUtil.TipToast(jxjh.getErrInfo());
						}
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						proDailog.dismiss();
						pb_Loading_List.getHide();
						ApiAction.errorTip(getActivity(), error);
					}
				}

		);

		VolleyController.getInstance(getActivity())
				.addToRequestQueue(gsonRequest);
	}
	private void showInfo(JiaoXueJiHuaRoot jxjh){
		List<JiaoXueJiHuaShuJu> shuju = jxjh.getShuJu();
		
		if(shuju!=null){
			
			groupList = shuju;
				if(groupList!=null && groupList.size()>0){
					childList = new ArrayList<List<JiaoXueJiHua>>();
					pb_Loading_List.getHide();
					if(groupList!= null && groupList.size()>0){
						for(int i = 0;i<groupList.size();i++){
							JiaoXueJiHuaShuJu g = (JiaoXueJiHuaShuJu)groupList.get(i);
							if(g.getZShuJu().size()%2 !=0) {
								List<JiaoXueJiHua> list = g.getZShuJu();
								list.add(new JiaoXueJiHua());
								childList.add(list);
							}else{
							   childList.add(g.getZShuJu());
							}
						}
					}
					
					//Toast.makeText(PropertypassApp.getContext(), groupList.size()+"", Toast.LENGTH_SHORT).show();
					adapter = new StudyElAdapter(getActivity(),groupList,childList);
					adapter.setWanJieFlag(true);
					el_study.setAdapter(adapter);
					//默认展开所有分组
					for(int i = 0; i < adapter.getGroupCount(); i++){
						el_study.expandGroup(i);
					}
					el_study.setOnGroupClickListener(new OnGroupClickListener() {
						
						@Override
						public boolean onGroupClick(ExpandableListView parent, View v,
								int groupPosition, long id) {
							return true;
						}
					});
				}
		}
		
	}
}
