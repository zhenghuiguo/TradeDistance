package com.td.tradedistance.app.adapter;

import java.io.Serializable;
import java.util.List;

import com.td.tradedistance.app.R;
import com.td.tradedistance.app.activity.DownedManagerXQActivity;
import com.td.tradedistance.app.bean.CourseDetailsShuju;
import com.td.tradedistance.app.bean.JiaoXueJiHua;
import com.td.tradedistance.app.localstorage.KeChengZhangJieDao;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import api.ImageCacheManager;

public class YiXiaZaiCourseAdapter extends BaseAdapter{
	private Context mContext;
	private List<JiaoXueJiHua> courseList;
	private Intent it;
	private KeChengZhangJieDao keChengZhangJieDao;
	private Resources rs;
	
    public YiXiaZaiCourseAdapter(Context context){
    	it = new Intent();
    	this.mContext = context;
    	keChengZhangJieDao = new KeChengZhangJieDao();
    	rs = context.getResources();
    }
	@Override
	public int getCount() {
		return courseList == null ? 0 : courseList.size();
	}

	@Override
	public Object getItem(int position) {
		return courseList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
    public void setList(List<JiaoXueJiHua> list){
    	this.courseList = list;
    }
   
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if(convertView==null){
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.downded_item, null);
			viewHolder.cource_iv = (ImageView)convertView.findViewById(R.id.cource_iv);
			viewHolder.go_iv = (ImageView)convertView.findViewById(R.id.go_iv);
			viewHolder.kecheng_tv = (TextView)convertView.findViewById(R.id.kecheng_tv);
			viewHolder.yixizaicount_tv = (TextView)convertView.findViewById(R.id.yixizaicount_tv);
			viewHolder.rl = (RelativeLayout) convertView.findViewById(R.id.rl);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder)convertView.getTag();
		}
		final JiaoXueJiHua jxjh = courseList.get(position);
		viewHolder.kecheng_tv.setText(jxjh.getKeChengMingCheng());
		final List<CourseDetailsShuju> list = keChengZhangJieDao.getKeChengZhangJieList(jxjh.getKeChengDaiMa());
		viewHolder.yixizaicount_tv.setText(rs.getString(R.string.yixiazai_str)+list.size()+rs.getString(R.string.ke_str));
		ImageCacheManager.loadImage(mContext, jxjh.getKeChengZhaoPian(), viewHolder.cource_iv,ImageCacheManager.setDefaultImage(mContext, R.drawable.course_default), ImageCacheManager.setErrorImage(mContext, R.drawable.course_default),150,100);
		viewHolder.rl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Logger.d("onclick", "coursetwo_iv"+info.getName2());
				it.setClass(mContext, DownedManagerXQActivity.class);
				it.putExtra("list", (Serializable)list);
				it.putExtra("jxjh", jxjh);
				mContext.startActivity(it);
			}
		});
		return convertView;
	}
	
    static class ViewHolder{
    	ImageView cource_iv,go_iv;
    	TextView kecheng_tv,yixizaicount_tv;
    	RelativeLayout rl;
    }

}
