package com.td.tradedistance.app.fragment;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.td.tradedistance.app.R;
import com.td.tradedistance.app.activity.DownLoadingManagerActivity;
import com.td.tradedistance.app.adapter.YiXiaZaiCourseAdapter;
import com.td.tradedistance.app.bean.DownLoading;
import com.td.tradedistance.app.bean.JiaoXueJiHua;
import com.td.tradedistance.app.localstorage.DownLoad_Info_Dao;
import com.td.tradedistance.app.localstorage.KeChengDao;
import com.td.tradedistance.app.localstorage.KeChengZhangJieDao;
import com.td.tradedistance.app.utils.CommonUtil;
import com.td.tradedistance.app.utils.Logger;
import com.td.tradedistance.app.utils.SDCardSizeUtil;

import down.DownloadInfo;
import down.Downloader;
import down.GlobalDownLoading1;
import down.Globals;
import down.GlobalsDownLoading;

public class DownLoadFragment extends Fragment{
	private static final String Tag ="DownLoadFragment";// DownLoadFragment.class.getSimpleName();
	private ListView mYiXiaZaiCourseList;
	private TextView kecheng_tv;//zhang_tv,jie_tv;
	private YiXiaZaiCourseAdapter adapter;
	private TextView zhujiajiaoshi_tv,downing_tv,Kongjiandaxiao_tv;
	private ImageView go_iv;
	private View include_downloading;
	private ProgressBar progress_bar;
	private  KeChengDao keChengDao;
	private GlobalDownLoading1 manager;
	private DownLoad_Info_Dao downDao;
	private KeChengZhangJieDao keChengZhangJieDao;
	private Resources rs;
	  private Handler mHandler = new Handler() {
	        public void handleMessage(Message msg) {
	           Downloader d= (Downloader)msg.obj;
	           if(kecheng_tv!=null)
	        	   kecheng_tv.setText(CommonUtil.getKeChengText(d.getKeChengMingCheng(),d.getZhang(),d.getJie()));
	          
	           if(downing_tv!=null) 
	        	   if(rs!=null)
	        	      downing_tv.setText(rs.getString(R.string.downloading_str)+"（"+Globals.downloaders.size()+"）");
	           // 设置进度条按读取的length长度更新
	           if(progress_bar!=null) {
	        	   progress_bar.setMax(d.getFileSize());
		           progress_bar.setProgress(d.getCompelete_Size());
		           if(progress_bar.getProgress()== progress_bar.getMax()){
		        	   manager.stopLoaderTask(d.getUrlstr());
		        	   int size =Globals.downloaders.size();
		       		   if(size<=0){ 
		       			   include_downloading.setVisibility(View.GONE);//隐藏
		       			   manager.stopAllDownloadTask();
		       			   nDataSetChanged();
		       		   }else{
		       			if(kecheng_tv!=null)
		 	        	   kecheng_tv.setText(CommonUtil.getKeChengText(d.getKeChengMingCheng(),d.getZhang(),d.getJie()));
		       			   progress_bar.setMax(d.getFileSize());
				           progress_bar.setProgress(d.getCompelete_Size());
		       			   nDataSetChanged();
		       		   }
					}
	           }
	        }
	    };
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Logger.d(Tag,"onCreate DownLoadFragment");
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_download, container, false);
		Logger.d(Tag,"onCreateView DownLoadFragment");
		include_downloading = (View)view.findViewById(R.id.include_downloading);
		 downDao = new DownLoad_Info_Dao();
		 keChengZhangJieDao =new KeChengZhangJieDao() ;
		rs = getActivity().getResources();
		manager = GlobalDownLoading1.getInstance();
		manager.setH(mHandler);
		manager.stopAllDownloadTask();
		keChengDao = new KeChengDao();
		kecheng_tv = (TextView) view.findViewById(R.id.kecheng_tv);
		Kongjiandaxiao_tv = (TextView) view.findViewById(R.id.Kongjiandaxiao_tv);//占用空间7.7G，可用空间37.7G
		Kongjiandaxiao_tv.setText(SDCardSizeUtil.getKongJianDaXiao(getActivity(),downDao,keChengZhangJieDao));
		go_iv = (ImageView) view.findViewById(R.id.go_iv);
		zhujiajiaoshi_tv = (TextView) view.findViewById(R.id.zhujiajiaoshi_tv);
		mYiXiaZaiCourseList= (ListView) view.findViewById(R.id.yixiazaicourselist_lv);
		adapter = new YiXiaZaiCourseAdapter(getActivity());
		List<JiaoXueJiHua> list =keChengDao.getKeChengList();
		zhujiajiaoshi_tv.setText(rs.getString(R.string.yixiazai_str )+"（"+list.size()+"）");
		adapter.setList(list);
		mYiXiaZaiCourseList.setAdapter(adapter);
		mYiXiaZaiCourseList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			     Logger.d("onclick", "onItemClick");
			}
		});
		
		int size  = Globals.downloaders.size();
		if(size<=0){
			include_downloading.setVisibility(View.GONE);//隐藏
		}else{
			include_downloading.setVisibility(View.VISIBLE);
			downing_tv = (TextView)include_downloading.findViewById(R.id.downing_tv);
			downing_tv.setText(rs.getString(R.string.downloading_str)+"（"+size+"）");
			progress_bar = (ProgressBar)include_downloading.findViewById(R.id.progress_bar);
			@SuppressWarnings("rawtypes")
			Set  set=Globals.downloaders.entrySet();  
            @SuppressWarnings("rawtypes")
			Iterator   iterator=set.iterator();  
            while (iterator.hasNext()){  
            	@SuppressWarnings("rawtypes")
            	Map.Entry  mapentry = (Map.Entry) iterator.next();  
                Downloader d = (Downloader)mapentry.getValue();
                manager.addTask(d);
            }
            
           
		}
		 include_downloading.setOnClickListener(new OnClickListener() {
 			
 			@Override
 			public void onClick(View v) {
 				getActivity().startActivity(new Intent(getActivity(),DownLoadingManagerActivity.class));
 			}
 		});
		
	    
		return view;
	}
	public void nDataSetChanged(){
		int size  = Globals.downloaders.size();
		if(size<=0){
			include_downloading.setVisibility(View.GONE);//隐藏
			manager.stopAllDownloadTask();
		}
		List<JiaoXueJiHua> list =keChengDao.getKeChengList();
		Kongjiandaxiao_tv.setText(SDCardSizeUtil.getKongJianDaXiao(getActivity(),downDao,keChengZhangJieDao));
		if(zhujiajiaoshi_tv!=null)
			if(rs!=null)
				zhujiajiaoshi_tv.setText(rs.getString(R.string.yixiazai_str )+"（"+list.size()+"）");
		if(adapter!=null){
			adapter.setList(list);
			adapter.notifyDataSetChanged();
		}
	}
	
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	Logger.d(Tag,"onActivityCreated DownLoadFragment");
    	super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onStart() {
    	Logger.d(Tag,"onStart DownLoadFragment");
    	super.onStart();
    }
    @Override
    public void onResume() {
    	Logger.d(Tag,"onResume DownLoadFragment");
    	if(adapter!=null){
    		 nDataSetChanged();
    	}
    	super.onResume();
    }
    @Override
    public void onPause() {
    	Logger.d(Tag,"onPause DownLoadFragment");
    	super.onPause();
    }
    @Override
    public void onStop() {
    	Logger.d(Tag,"onStop DownLoadFragment");
    	//GlobalsDownLoading.deleteAll();
    	super.onStop();
    }
    @Override
    public void onDestroyView() {
    	Logger.d(Tag,"onDestroyView DownLoadFragment");
    	super.onDestroyView();
    }
    @Override
    public void onDestroy() {
    	Logger.d(Tag,"onDestroy DownLoadFragment");
    	super.onDestroy();
    }
    @Override
    public void onDetach() {
    	Logger.d(Tag,"onDetach DownLoadFragment");
    	super.onDetach();
    }
}
