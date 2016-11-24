package com.td.tradedistance.app.adapter;



import java.util.List;

import com.td.tradedistance.app.R;
import com.td.tradedistance.app.activity.CourseDetailsActivity;
import com.td.tradedistance.app.bean.JiaoXueJiHua;
import com.td.tradedistance.app.bean.JiaoXueJiHuaShuJu;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import api.ImageCacheManager;


public class StudyElAdapter extends BaseExpandableListAdapter {
	private Context mcontext;
	private List<JiaoXueJiHuaShuJu> groupList;
	private List<List<JiaoXueJiHua>> childList;
	private Intent it;
	private int yushu;
	private int item = 2;
	private boolean mBusy;
	private boolean isWanJieFlag;
	public boolean ismBusy() {
		return mBusy;
	}

	public void setWanJieFlag(boolean isWanJieFlag) {
		this.isWanJieFlag = isWanJieFlag;
	}
	public boolean getWanJieFlag(boolean isWanJieFlag) {
		return isWanJieFlag;
	}
	public StudyElAdapter(Context context,List<JiaoXueJiHuaShuJu> groupList,List<List<JiaoXueJiHua>> childList) {
		this.mcontext = context;
		this.groupList = groupList;
		this.childList = childList;
		it = new Intent();
	}
	
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return childList.get(groupPosition).get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		 ChildHolder childHolder;
		if(convertView == null){
			childHolder = new ChildHolder();
			convertView = View.inflate(mcontext, R.layout.el_content, null);
			childHolder.coursename1_tv = (TextView) convertView
					.findViewById(R.id.coursename1_tv);
			childHolder.coursename2_tv = (TextView) convertView
					.findViewById(R.id.coursename2_tv);
			childHolder.courseone_iv = (ImageView) convertView
					.findViewById(R.id.courseone_iv);
			childHolder.coursetwo_iv = (ImageView) convertView
					.findViewById(R.id.coursetwo_iv);
			childHolder.isxuewei1_iv= (ImageView) convertView
					.findViewById(R.id.isxuewei1_iv);
			childHolder.isxuewei2_iv= (ImageView) convertView
					.findViewById(R.id.isxuewei2_iv);
			convertView.setTag(childHolder);
		}else {
			childHolder = (ChildHolder) convertView.getTag();
		}
		
		childHolder.isxuewei1_iv.setVisibility(View.GONE);
		childHolder.isxuewei2_iv.setVisibility(View.GONE);
		childHolder.coursetwo_iv.setVisibility(View.VISIBLE);
		childHolder.coursename2_tv.setVisibility(View.VISIBLE);
		  if(getChildrenCount(groupPosition)-1 == childPosition){
		    	if(yushu!=0){
		    		setCourse(yushu,groupPosition,childPosition,childHolder);
		    		if(yushu == 1){
		    			childHolder.coursetwo_iv.setVisibility(View.INVISIBLE);
		    			childHolder.coursename2_tv.setVisibility(View.INVISIBLE);
		    			childHolder.isxuewei2_iv.setVisibility(View.GONE);
		    		}
		    	}else{
		    		setCourse(item,groupPosition,childPosition,childHolder);
		    	}
		    	
		    }else{
		    	setCourse(item,groupPosition,childPosition,childHolder);
			}
		
		return convertView;
	}
	 public void setCourse(final int size,final int groupPosition,final int position, ChildHolder  viewHolder){
	    	for(int i=0;i<size;i++){
	    		 int pos = position*2+i;
	    		
	    		// List<JiaoXueJiHua> list = childList.get(position);
	    		 final JiaoXueJiHua jxjh = (JiaoXueJiHua)getChild(groupPosition, pos);
				 int xueWeiKe = jxjh.getXueWeiKe();
				if(i==0){
					
					viewHolder.coursename1_tv.setText(jxjh.getKeChengMingCheng());
					
					ImageCacheManager.loadImage(mcontext, jxjh.getKeChengZhaoPian(), viewHolder.courseone_iv,ImageCacheManager.setDefaultImage(mcontext, R.drawable.course_default), ImageCacheManager.setErrorImage(mcontext, R.drawable.course_default));
					
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
							// "coursetwo_iv"+info.getName2());
							it.setClass(mcontext,
									CourseDetailsActivity.class);
							it.putExtra("jxjh", jxjh);
							it.putExtra("isWanJieFlag", isWanJieFlag);
							mcontext.startActivity(it);
						}
					});
				}
				if(i==1){
					if(jxjh.getKeChengDaiMa() == null){
						viewHolder.coursetwo_iv.setVisibility(View.INVISIBLE);
						viewHolder.coursename2_tv.setVisibility(View.INVISIBLE);
						viewHolder.isxuewei2_iv.setVisibility(View.GONE);
						return;
					}
					viewHolder.coursetwo_iv.setVisibility(View.VISIBLE);
					viewHolder.coursename2_tv.setVisibility(View.VISIBLE);
					if(xueWeiKe==1){
						viewHolder.isxuewei2_iv.setVisibility(View.VISIBLE);
					}else{
						viewHolder.isxuewei2_iv.setVisibility(View.GONE);
					}
					viewHolder.coursename2_tv.setText(jxjh.getKeChengMingCheng());
					ImageCacheManager.loadImage(mcontext, jxjh.getKeChengZhaoPian(), viewHolder.coursetwo_iv,ImageCacheManager.setDefaultImage(mcontext, R.drawable.course_default), ImageCacheManager.setErrorImage(mcontext, R.drawable.course_default));
				
					viewHolder.coursetwo_iv
					.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// Logger.d("onclick",
							// "coursetwo_iv"+info.getName2());
							it.setClass(mcontext,
									CourseDetailsActivity.class);
							it.putExtra("jxjh", jxjh);
							it.putExtra("isWanJieFlag", isWanJieFlag);
							mcontext.startActivity(it);
						}
					});
				}
				
	    }
	 }
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		GroupHolder groupHolder = null;
		if (convertView == null) {
			groupHolder = new GroupHolder();
			convertView = View.inflate(mcontext, R.layout.el_type, null);
			
			
			groupHolder.corusetype_tv = (TextView) convertView
					.findViewById(R.id.corusetype_tv);
			convertView.setTag(groupHolder);
		} else {
			groupHolder = (GroupHolder) convertView.getTag();
		}
        final JiaoXueJiHuaShuJu jpld = (JiaoXueJiHuaShuJu)getGroup(groupPosition);
        groupHolder.corusetype_tv.setText(jpld.getType());
		return convertView;
	}
   
	@Override
	public int getChildrenCount(int groupPosition) {
		return childList.get(groupPosition) == null ? 0 :getHanshu(groupPosition);
	}
	 public int getHanshu(int groupPosition){
		   int count = childList.get(groupPosition).size();
	    	if(count%2 == 0){
	    		return count/2;
	    	}
	    	yushu = count%2;
	    	return count/2+1;
	    }
	@Override
	public Object getGroup(int groupPosition) {
		return groupList.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return groupList == null ? 0 : groupList.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	
	class GroupHolder {
		private TextView corusetype_tv;
		
	}
	
	public class ChildHolder{
		ImageView coursetwo_iv, courseone_iv,isxuewei1_iv,isxuewei2_iv;
		TextView coursename1_tv, coursename2_tv;
	}
}
