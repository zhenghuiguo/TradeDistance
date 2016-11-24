package com.td.tradedistance.app.adapter;

import java.util.List;

import com.td.tradedistance.app.ApiGlobal;
import com.td.tradedistance.app.R;
import com.td.tradedistance.app.bean.CourseDetailsShuju;
import com.td.tradedistance.app.utils.FileOperationUtils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ZhiShiJieGouAdapter extends BaseAdapter{
	private Context mContext;
	List<CourseDetailsShuju> list ;
	Resources rs;
	private Intent it;
    public ZhiShiJieGouAdapter(Context context){
    	it = new Intent();
    	rs = context.getResources();
    	this.mContext = context;
    	
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
    public void setList(List<CourseDetailsShuju> list){
    	this.list = list;
    }
	 @SuppressWarnings("null")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if(convertView==null){
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.zhishijigguo_item, null);
			viewHolder.zsjg_tv = (TextView)convertView.findViewById(R.id.zsjg_tv);
			viewHolder.zsjg_iv = (ImageView)convertView.findViewById(R.id.zsjg_iv);
			viewHolder.view = (View)convertView.findViewById(R.id.view);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder)convertView.getTag();
		}
		viewHolder.view.setVisibility(View.VISIBLE);
		CourseDetailsShuju shuju = list.get(position);
	
		if(shuju!=null){
			if(shuju.getShiPinShiJian()==0){
				viewHolder.zsjg_iv.setBackgroundResource(R.drawable.knowledgestructureredicon);
				viewHolder.zsjg_tv.setTextColor(rs.getColor(R.color.black));
				viewHolder.zsjg_tv.setTextSize(16);
			}else{
				viewHolder.zsjg_iv.setBackgroundResource(R.drawable.knowledgestructurebuleicon);
				viewHolder.zsjg_tv.setTextColor(rs.getColor(R.color.zsjg_color));
				viewHolder.zsjg_tv.setTextSize(14);
				shuju.setUrl(ApiGlobal.getXiaZaiUrl(shuju));
				shuju.setName(FileOperationUtils.getFileName(shuju.getShiPinDiZhi()));
				shuju.setJie(shuju.getZhangJieMingCheng());
				if(position>0){
					CourseDetailsShuju shuju1 = list.get(position-1);
					if(shuju1!=null)
						shuju.setZhang(shuju1.getZhangJieMingCheng());
					
				}
			}
			viewHolder.zsjg_tv.setText(shuju.getZhangJieMingCheng());
			if(getCount()-1 == position){
				viewHolder.view.setVisibility(View.GONE);
			}
		}
		return convertView;
	}
	
    static class ViewHolder{
    	ImageView zsjg_iv;
    	TextView zsjg_tv;
    	View view;
    }

}
