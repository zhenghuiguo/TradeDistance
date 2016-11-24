package com.td.tradedistance.app.adapter;



import java.util.List;

import com.td.tradedistance.app.R;
import com.td.tradedistance.app.activity.CourseDetailsActivity;
import com.td.tradedistance.app.bean.JiaoXueJiHua;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import api.ImageCacheManager;

public class TypeTotalCourseAdapter extends BaseAdapter {
	private Context mContext;
	private int yushu;
	private Intent it;
	private boolean mBusy;
	public boolean ismBusy() {
		return mBusy;
	}

	public void setmBusy(boolean mBusy) {
		this.mBusy = mBusy;
	}
	List<JiaoXueJiHua> listJXJH;
	public TypeTotalCourseAdapter(Context context) {
		it = new Intent();
		this.mContext = context;
	}

	@Override
	public int getCount() {
		return listJXJH == null ? 0 : getHanshu();
	}
	 public int getHanshu(){
		   int count = listJXJH.size();
	    	if(count%2 == 0){
	    		return count/2;
	    	}
	    	yushu = count%2;
	    	return count/2+1;
	    }
	@Override
	public Object getItem(int position) {
		return listJXJH.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void setList(List<JiaoXueJiHua> listType) {
      this.listJXJH = listType;
	}
	 public void setCourse(final int size,final int position,final ViewHolder  viewHolder){
	    	for(int i=0;i<size;i++){
	    		 int pos = position*2+i;
				 final JiaoXueJiHua jxjh = listJXJH.get(pos);
				 int xueWeiKe = jxjh.getXueWeiKe();
				 
				if(i==0){
					viewHolder.coursename1_tv.setText(jxjh.getKeChengMingCheng());
					viewHolder.courseone_iv.setTag(jxjh.getKeChengZhaoPian());
					if(!ismBusy())
						ImageCacheManager.loadImage(mContext, jxjh.getKeChengZhaoPian(), viewHolder.courseone_iv,ImageCacheManager.setDefaultImage(mContext, R.drawable.course_default), ImageCacheManager.setErrorImage(mContext, R.drawable.course_default));
					
					if(xueWeiKe==1){
						viewHolder.isxuewei1_iv.setVisibility(View.VISIBLE);
					}else{
						viewHolder.isxuewei1_iv.setVisibility(View.GONE);
					}
					
					viewHolder.courseone_iv
					.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// Logger.d("onclick",
							// "courseone_iv"+info.getName1());

							it.setClass(mContext,
									CourseDetailsActivity.class);
							it.putExtra("jxjh", jxjh);
							mContext.startActivity(it);
						}
					});
			
					
					
				}
				if(i==1){
					viewHolder.coursetwo_iv.setTag(jxjh.getKeChengZhaoPian());
					if(xueWeiKe==1){
						viewHolder.isxuewei1_iv.setVisibility(View.VISIBLE);
					}else{
						viewHolder.isxuewei1_iv.setVisibility(View.GONE);
					}
					viewHolder.coursename2_tv.setText(jxjh.getKeChengMingCheng());
					
					if(!ismBusy())
					   ImageCacheManager.loadImage(mContext, jxjh.getKeChengZhaoPian(), viewHolder.coursetwo_iv,ImageCacheManager.setDefaultImage(mContext, R.drawable.course_default), ImageCacheManager.setErrorImage(mContext, R.drawable.course_default));
					
			viewHolder.coursetwo_iv
					.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// Logger.d("onclick",
							// "coursetwo_iv"+info.getName2());
							it.setClass(mContext,
									CourseDetailsActivity.class);
							it.putExtra("jxjh", jxjh);
							mContext.startActivity(it);
						}
					});
				
				
				}
				
	    }
	 }
	@SuppressWarnings("null")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.typetotalcourse_item, null);
			viewHolder.coursename1_tv = (TextView) convertView
					.findViewById(R.id.coursename1_tv);
			viewHolder.coursename2_tv = (TextView) convertView
					.findViewById(R.id.coursename2_tv);
			viewHolder.courseone_iv = (ImageView) convertView
					.findViewById(R.id.courseone_iv);
			viewHolder.coursetwo_iv = (ImageView) convertView
					.findViewById(R.id.coursetwo_iv);
			viewHolder.isxuewei1_iv= (ImageView) convertView
					.findViewById(R.id.isxuewei1_iv);
			viewHolder.isxuewei2_iv= (ImageView) convertView
					.findViewById(R.id.isxuewei2_iv);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.isxuewei1_iv.setVisibility(View.GONE);
		viewHolder.isxuewei2_iv.setVisibility(View.GONE);
		viewHolder.coursetwo_iv.setVisibility(View.VISIBLE);
		viewHolder.coursename2_tv.setVisibility(View.VISIBLE);
		  if(getCount()-1 == position){
		    	if(yushu!=0){
		    		setCourse(yushu,position,viewHolder);
		    		if(yushu == 1){
		    			viewHolder.coursetwo_iv.setVisibility(View.INVISIBLE);
		    			viewHolder.coursename2_tv.setVisibility(View.INVISIBLE);
		    		}
		    	}else{
		    		setCourse(2,position,viewHolder);
		    	}
		    	
		    }else{
		    	setCourse(2,position,viewHolder);
			}
		
		
		return convertView;
	}
   
	static class ViewHolder {
		ImageView coursetwo_iv, courseone_iv,isxuewei1_iv,isxuewei2_iv;
		TextView coursename1_tv, coursename2_tv;
	}
	public enum KeChengShuXing {

		BiXiuKe(1,"必修课"), XuanXiuKe(2,"绿色"), KaoChaKe(3,"考查课");
		private String name;
		private int index;

		private KeChengShuXing(int index,String name){
			this.index = index;
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

	}

}
