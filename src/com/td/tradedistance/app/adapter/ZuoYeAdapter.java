package com.td.tradedistance.app.adapter;

import java.util.List;

import com.td.tradedistance.app.R;
import com.td.tradedistance.app.bean.ZuoYeShuju;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

 public class ZuoYeAdapter extends BaseAdapter{
	private Context mContext;
	private List<ZuoYeShuju> list;
	private Intent it;
	private Resources rs;
    public ZuoYeAdapter(Context context){
    	it = new Intent();
    	rs=context.getResources();
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
    public void setList(List<ZuoYeShuju> list){
    	this.list = list;
    }
	 @SuppressWarnings("null")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if(convertView==null){
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.zuoye_item, null);
			viewHolder.zuoye_tv = (TextView)convertView.findViewById(R.id.zuoye_tv);
			viewHolder.fenshu_tv = (TextView)convertView.findViewById(R.id.fenshu_tv);
			viewHolder.fz_tv = (TextView)convertView.findViewById(R.id.fz_tv);
			
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder)convertView.getTag();
		}
		ZuoYeShuju zy =list.get(position);
		if(zy!=null){
			viewHolder.zuoye_tv.setText(zy.getMingCheng());
			if(zy.getWanChengZhuangTai()==1){
				viewHolder.fenshu_tv.setText(zy.getFenShu());
				viewHolder.fenshu_tv.setTextColor(rs.getColor(R.color.tv_tab_selected));
				viewHolder.fz_tv.setVisibility(View.VISIBLE);
				viewHolder.fenshu_tv.setTextSize(22);
			}else{
				if(zy.getShiFouKeYiZuoZuoYe()==1){
					viewHolder.fz_tv.setVisibility(View.GONE);
					viewHolder.fenshu_tv.setText(rs.getString(R.string.kaishidati_str));
					viewHolder.fenshu_tv.setTextColor(rs.getColor(R.color.zsjg_color));
					viewHolder.fenshu_tv.setTextSize(14);
				}else{
					viewHolder.fenshu_tv.setText("");
					viewHolder.fz_tv.setVisibility(View.GONE);
				}
			}
		}
		return convertView;
	}
	
    static class ViewHolder{
    	TextView zuoye_tv,fenshu_tv,fz_tv;
    }

}
