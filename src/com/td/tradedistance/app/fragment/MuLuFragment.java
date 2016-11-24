package com.td.tradedistance.app.fragment;

import java.util.List;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.td.tradedistance.app.R;
import com.td.tradedistance.app.activity.VideoPlayerActivity;
import com.td.tradedistance.app.adapter.MuLuXiaZaiAdapter;
import com.td.tradedistance.app.bean.CourseDetailsShuju;
import com.td.tradedistance.app.bean.JiaoXueJiHua;
import com.td.tradedistance.app.localstorage.KeChengZhangJieDao;
import com.td.tradedistance.app.utils.FileOperationUtils;
import com.td.tradedistance.app.utils.Logger;
import com.zhy.m.permission.MPermissions;


public class MuLuFragment extends Fragment{
	private static final String Tag ="MainActivity";// MyFragment.class.getSimpleName();
	private ListView zhishijiegou_lv;
	private MuLuXiaZaiAdapter adapter;
	private List<CourseDetailsShuju> list;
	private JiaoXueJiHua jxjh;
	private KeChengZhangJieDao dao;
	private static final int REQUECT_CODE_SDCARD = 2;
	/*@Override
	public void onAttach(Context context) {
		Logger.d(Tag,"onAttach MyFragment");
		super.onAttach(context);
	}*/
	public MuLuFragment(List<CourseDetailsShuju> list,JiaoXueJiHua jxjh) {
		this.list = list;
		this.jxjh = jxjh;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Logger.d(Tag,"onCreate MyFragment");
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		MPermissions.requestPermissions(getActivity(), REQUECT_CODE_SDCARD, Manifest.permission.WRITE_EXTERNAL_STORAGE);
		View view = inflater.inflate(R.layout.fragment_mulu, container, false);
		dao = new KeChengZhangJieDao();
		zhishijiegou_lv = (ListView)view.findViewById(R.id.zhishijiegou_lv);
		adapter = new MuLuXiaZaiAdapter(getActivity());
		adapter.setList(list,jxjh);
		zhishijiegou_lv.setAdapter(adapter);
		zhishijiegou_lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				CourseDetailsShuju shuju = list.get((int)id);
				if(shuju.getShiPinShiJian()!=0){
					shuju.setPlay(true);
					View  v =adapter.getView(position, view, parent);
					TextView tv =(TextView)v.findViewById(R.id.jie_tv);
					tv.setTextColor(getResources().getColor(R.color.tv_tab_selected));
					ImageView iv = (ImageView)v.findViewById(R.id.play_state);
					iv.setBackgroundResource(R.drawable.playred);
					
					Intent it = new Intent(getActivity(),VideoPlayerActivity.class);
					String zhangJieMingCheng = shuju.getZhangJieMingCheng();
					String path = shuju.getShiPinDiZhi();
					String  url = "";
					if(dao.getKeChengZhangJieItem(path)){
						  url = FileOperationUtils.getMycoursePathAddYhwybs()+"/"+FileOperationUtils.getFileName(path);
					}else{
						url = path;
					}
					it.putExtra("path", path);
					it.putExtra("url", url);
					it.putExtra("zhangjieming", zhangJieMingCheng);
					startActivity(it);
				}
			}
		});
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
    
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
