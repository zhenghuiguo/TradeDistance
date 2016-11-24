package com.td.tradedistance.app.adapter;

import java.util.ArrayList;
import java.util.List;

import com.td.tradedistance.app.R;
import com.td.tradedistance.app.bean.XiaoXiInfo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class XiaoXiZhongXinAdapter extends BaseAdapter{
	private Context mContext;
	private List<XiaoXiInfo> courseList;
	private Intent it;
    public XiaoXiZhongXinAdapter(Context context){
    	it = new Intent();
    	this.mContext = context;
    	courseList = new ArrayList<XiaoXiInfo>();
    	
    }
	@Override
	public int getCount() {
		return 10;//courseList != null ? courseList.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return courseList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	 @SuppressWarnings("null")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if(convertView==null){
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.xiaoxizhongxi_item, null);
			viewHolder.content_tv = (TextView)convertView.findViewById(R.id.content_tv);
			viewHolder.riqi_tv = (TextView)convertView.findViewById(R.id.riqi_tv);
			viewHolder.weidu_iv = (ImageView)convertView.findViewById(R.id.weidu_iv);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder)convertView.getTag();
		}
		/*final CourseImageInfo info = courseList.get(position);	
		viewHolder.content_tv.setText(info.getFenlei());
		viewHolder.riqi_tv.setText(info.getName1());*/
		//viewHolder.weidu_iv.setBackgroundResource(info.getResId2());
		
		return convertView;
	}
	
    static class ViewHolder{
    	ImageView weidu_iv;
    	TextView content_tv,riqi_tv;
    }

}
