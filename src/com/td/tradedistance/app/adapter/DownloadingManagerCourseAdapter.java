package com.td.tradedistance.app.adapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.td.tradedistance.app.R;
import com.td.tradedistance.app.bean.DownLoading;
import com.td.tradedistance.app.global.Global;
import com.td.tradedistance.app.utils.Logger;
import com.td.tradedistance.app.utils.SDCardSizeUtil;

import down.Downloader;
import down.Globals;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import api.ImageCacheManager;

 public class DownloadingManagerCourseAdapter extends BaseAdapter{
	private Context mContext;
	private ListView listView;
	private List<DownLoading> list ;
    Resources rs;
	/**
     * 利用消息处理机制适时更新进度条
     */
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
           DownLoading loader =	(DownLoading)msg.obj;
			if(loader!=null){
				updateView(loader,loader.getPos());
     /*     ProgressBar bar= loader.getpBar();
          Logger.d("mHandler",loader.getLoader().getCompelete_Size());
          loader.getTv().setText(loader.getDaxiao());
         // loader.getTv().setText(SDCardSizeUtil.getFileSize(loader.getLoader().getCompelete_Size())+"/"+SDCardSizeUtil.getFileSize(loader.getLoader().getFileSize()));
          if(bar !=null){
        	  bar.setProgress(loader.getJdt());
        	  //bar.setProgress(loader.getLoader().getCompelete_Size());
        	  if(bar.getProgress()== bar.getMax()){
            	  loader.setFlagDowning(false);
            	  //getDownLoaderList();
           	      //notifyDataSetChanged();
              }*/
          }
        }
    };
    
   
    public DownloadingManagerCourseAdapter(Context context){
    	this.mContext = context;
    	rs = context.getResources();
    	getDownLoaderList();
    }
	@Override
	public int getCount() {
		return list != null ? list.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
    
    public void getDownLoaderList(){
    	if(Globals.downloaders.size()>0){
			@SuppressWarnings("rawtypes")
			Set  set=Globals.downloaders.entrySet();  
	        @SuppressWarnings("rawtypes")
			Iterator   iterator=set.iterator();  
	        list = new ArrayList<DownLoading>();
	        while (iterator.hasNext()){  
	           @SuppressWarnings("rawtypes")
			    Map.Entry  mapentry = (Map.Entry) iterator.next();  
	           DownLoading  loading = new DownLoading();
	           Downloader loader = (Downloader)mapentry.getValue();
	           loading.setUrl(loader.getUrlstr());
	           loading.setLoader(loader);
	            
	             list.add(loading);
	        }
		}else{
			list = null;
		}
    }
    public void setListView(ListView view){
		this.listView = view;
	}
    
	 @SuppressWarnings("null")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;
		if(convertView==null){
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.downloading_item, null);
			viewHolder.cource_iv =(ImageView)  convertView.findViewById(R.id.cource_iv);
			viewHolder.kecheng_tv = (TextView) convertView.findViewById(R.id.kecheng_tv);
			viewHolder.zhang_tv = (TextView) convertView.findViewById(R.id.zhang_tv);
			viewHolder.jie_tv = (TextView) convertView.findViewById(R.id.jie_tv);
			viewHolder.state_tv = (TextView) convertView.findViewById(R.id.state_tv);
			viewHolder.daxiao_tv = (TextView) convertView.findViewById(R.id.daxiao1_tv);
			viewHolder.xiazai_iv = (TextView) convertView.findViewById(R.id.xiazai_iv);
			viewHolder.progress_bar = (ProgressBar) convertView.findViewById(R.id.progress_bar1);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder)convertView.getTag();
		}
		 final DownLoading loader = list.get(position);
		 if(loader != null){
			 viewHolder.progress_bar.setTag(loader.getLoader().getUrlstr()); 
			 ImageCacheManager.loadImage(mContext, loader.getLoader().getZhaoPain(), viewHolder.cource_iv,ImageCacheManager.setDefaultImage(mContext, R.drawable.course_default), ImageCacheManager.setErrorImage(mContext, R.drawable.course_default));
			 viewHolder.kecheng_tv.setText(loader.getLoader().getKeChengMingCheng());
			 viewHolder.zhang_tv.setText(loader.getLoader().getZhang());
			 viewHolder.jie_tv.setText(loader.getLoader().getJie());
			 switch (loader.getLoader().getState()) {
			case 2:
				viewHolder.state_tv.setText("下载中");
				break;
			case 3:
				viewHolder.state_tv.setText("下载暂停");
				Globals.setBackGroudxxzx(viewHolder.xiazai_iv,Global.stop);
				break;
			}
			 down(loader,viewHolder.daxiao_tv,viewHolder.progress_bar,position);
			 viewHolder.daxiao_tv.setText(SDCardSizeUtil.getFileSize(loader.getLoader().getCompelete_Size())+"/"+SDCardSizeUtil.getFileSize(loader.getLoader().getFileSize()));
			
			    if (loader.getLoader().isdownloading()){
			    	 Globals.setBackGroudxxzx(viewHolder.xiazai_iv,Global.downing);
			 	 }else if(loader.getLoader().isdownPause()){
			 		Globals.setBackGroudxxzx(viewHolder.xiazai_iv,Global.stop);
			 	 }
			    viewHolder.xiazai_iv.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						if (loader.getLoader().isdownloading()){
					    	 Globals.setBackGroudxxzx(viewHolder.xiazai_iv,Global.stop);
					    	 loader.getLoader().setState(Global.stop);
					    	// GlobalsListDownLoading1.stopTask(loader.getUrl());
					    	//loader.setFlagDowning(false);
					 	 }else if(loader.getLoader().isdownPause()){
					 		Globals.setBackGroudxxzx(viewHolder.xiazai_iv,Global.downing);
					 		//loader.setFlagDowning(true);
					 		//GlobalsListDownLoading1.getTask(loader.getUrl());
					 		    loader.getLoader().download();
					 		
					 	 }
					}
				});
		 }
		 return convertView;
	}
	 
	// 更新指定item的数据
	  	public void updateView(DownLoading loader,int index){
	  		int visiblePos = listView.getFirstVisiblePosition();
	  		int offset = index - visiblePos;
	  		//Log.e("", "index="+index+"visiblePos="+visiblePos+"offset="+offset);
	  		// 只有在可见区域才更新
	  		if(offset < 0) return;
	  		
	  		View view = listView.getChildAt(offset);
	  		TextView tv = (TextView)view.findViewById(R.id.daxiao1_tv);
	  		//final DownLoading app = dataList.get(index);
	  		if(loader==null) return;
	  			tv.setText(SDCardSizeUtil.getFileSize(loader.getLoader().getCompelete_Size())+"/"+SDCardSizeUtil.getFileSize(loader.getLoader().getFileSize()));
	  		
	  		ProgressBar v = (ProgressBar) listView
	    		      .findViewWithTag(loader.getLoader().getUrlstr());
	  		if(v!=null) {
	  			v.setProgress(loader.getLoader().getCompelete_Size());
		  		if(v.getProgress()== v.getMax()){
		  			//loader.setFlagDowning(false);
		  			//GlobalsListDownLoading1.stopTask(loader.getUrl());
		      	    loader.setState(1);
		      	    loader = null;
		      	 
		        }
	  		}
	  	}
	public void down(final DownLoading loader,final TextView v,final ProgressBar pBar,int pos){
		loader.setH(mHandler);
		loader.setPos(pos);
		//GlobalsListDownLoading1.addTask(loader);
	}
    static class ViewHolder{
    	ProgressBar progress_bar;
    	ImageView cource_iv;
    	TextView kecheng_tv,zhang_tv,jie_tv,state_tv,daxiao_tv,xiazai_iv;
    }

}
