package com.td.tradedistance.app.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.td.tradedistance.app.R;
import com.td.tradedistance.app.bean.CourseImageInfo;
import com.td.tradedistance.app.widget.ViewPaperIndicator;

public class Course1Adapter extends BaseAdapter{
	private Context mContext;
	private List<CourseImageInfo> courseList;
	private Intent it;
    public Course1Adapter(Context context){
    	it = new Intent();
    	this.mContext = context;
    	courseList = new ArrayList<CourseImageInfo>();
    	CourseImageInfo b = new CourseImageInfo();
    	b.setType(0);
    	courseList.add(b);
    	/*CourseImageInfo bixiu = new CourseImageInfo();
    	bixiu.setCount(18);
    	bixiu.setFenlei("必修课程 ");
    	bixiu.setName1("投资银行学原理");
      	bixiu.setName2("投资银行学原理1");
      	bixiu.setType(1);
    	bixiu.setResId1(R.drawable.course_01);
    	bixiu.setResId2(R.drawable.course_introduce_01);
    	courseList.add(bixiu);
    	
    	CourseImageInfo xuanxiu = new CourseImageInfo();
    	xuanxiu.setCount(18);
    	xuanxiu.setFenlei("选修课程 ");
    	xuanxiu.setName1("哲学思想史论");
    	xuanxiu.setName2("哲学思想史论1");
    	xuanxiu.setResId1(R.drawable.course_01);
    	xuanxiu.setResId2(R.drawable.course_default);
    	xuanxiu.setType(1);
    	courseList.add(xuanxiu);*/
    	
    }
	@Override
	public int getCount() {
		return courseList != null ? courseList.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return courseList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
    @Override
    public int getItemViewType(int position) {
    	return courseList.get(position).getType();
    }
    @Override
    public int getViewTypeCount() {
    	// TODO Auto-generated method stub
    	return 1;
    }
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		ViewHolder1 viewHolder1;
		 int itemType = this.getItemViewType(position);
		    if(itemType == 0){
		    	if(convertView==null){
					viewHolder = new ViewHolder();
					convertView = LayoutInflater.from(mContext).inflate(R.layout.course_list_item, null);
					viewHolder.corusetype_tv = (TextView)convertView.findViewById(R.id.corusetype_tv);
					viewHolder.coursename1_tv = (TextView)convertView.findViewById(R.id.coursename1_tv);
					viewHolder.coursename2_tv = (TextView)convertView.findViewById(R.id.coursename2_tv);
					viewHolder.coursecount_tv = (TextView)convertView.findViewById(R.id.coursecount_tv);
					viewHolder.courseone_iv = (ImageView)convertView.findViewById(R.id.courseone_iv);
					viewHolder.coursetwo_iv = (ImageView)convertView.findViewById(R.id.coursetwo_iv);
					convertView.setTag(viewHolder);
				}else{
					viewHolder = (ViewHolder)convertView.getTag();
				}
		    	/*if(convertView == null){
		    		viewHolder1 = new ViewHolder1();
		    		convertView = LayoutInflater.from(mContext).inflate(R.layout.widget_title_bar, null,false);
		    		//viewHolder1.vp = (ViewPaperIndicator)convertView.findViewById(R.id.viewpaperindicator);
		    		convertView.setTag(viewHolder1);
		    	}else{
		    		viewHolder1 = (ViewHolder1)convertView.getTag();
		    	}*/
		    }/*else if(itemType == 1){
		    	if(convertView==null){
					viewHolder = new ViewHolder();
					convertView = LayoutInflater.from(mContext).inflate(R.layout.course_list_item, null);
					viewHolder.corusetype_tv = (TextView)convertView.findViewById(R.id.corusetype_tv);
					viewHolder.coursename1_tv = (TextView)convertView.findViewById(R.id.coursename1_tv);
					viewHolder.coursename2_tv = (TextView)convertView.findViewById(R.id.coursename2_tv);
					viewHolder.coursecount_tv = (TextView)convertView.findViewById(R.id.coursecount_tv);
					viewHolder.courseone_iv = (ImageView)convertView.findViewById(R.id.courseone_iv);
					viewHolder.coursetwo_iv = (ImageView)convertView.findViewById(R.id.coursetwo_iv);
					convertView.setTag(viewHolder);
				}else{
					viewHolder = (ViewHolder)convertView.getTag();
				}
				final CourseImageInfo info = courseList.get(position);	
				//viewHolder.bixiucoursename_tv.setText(info.getName());
				viewHolder.corusetype_tv.setText(info.getFenlei());
				viewHolder.coursename1_tv.setText(info.getName1());
				viewHolder.coursename2_tv.setText(info.getName1());
				viewHolder.coursecount_tv.setText("共"+info.getCount()+"门课程");
				viewHolder.courseone_iv.setBackgroundResource(info.getResId1());
				viewHolder.coursetwo_iv.setBackgroundResource(info.getResId2());
				
				viewHolder.courseone_iv.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						//Logger.d("onclick", "courseone_iv"+info.getName1());
						
						it.setClass(mContext, CourseDetailsActivity.class);
						it.putExtra("title", info.getName1());
						mContext.startActivity(it);
					}
				});
				viewHolder.coursetwo_iv.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						//Logger.d("onclick", "coursetwo_iv"+info.getName2());
						it.setClass(mContext, CourseDetailsActivity.class);
						it.putExtra("title", info.getName2());
						mContext.startActivity(it);
					}
				});
		    }*/
		
		return convertView;
	}
	
    static class ViewHolder{
    	ImageView coursetwo_iv,courseone_iv;
    	TextView coursename1_tv,coursename2_tv,corusetype_tv,coursecount_tv;
    }
    static class ViewHolder1{
    	ViewPaperIndicator vp;
    }
}
