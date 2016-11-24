package com.td.tradedistance.app.adapter;


import java.util.List;

import com.td.tradedistance.app.R;
import com.td.tradedistance.app.bean.CourseDetailsShuju;
import com.td.tradedistance.app.bean.JiaoXueJiHua;
import com.td.tradedistance.app.localstorage.KeChengDao;
import com.td.tradedistance.app.localstorage.KeChengZhangJieDao;
import com.td.tradedistance.app.utils.CommonUtil;
import com.td.tradedistance.app.utils.SDCardSizeUtil;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import api.ImageCacheManager;

 public class DownedXQAdapter extends BaseAdapter{
	private Context mContext;
	private List<CourseDetailsShuju> list;
	private JiaoXueJiHua jxjh;
	private Intent it;
	private int bianJiInt= 0;
	private KeChengZhangJieDao dao;
	private KeChengDao kcDao;
    public DownedXQAdapter(Context context){
    	it = new Intent();
    	this.mContext = context;
    	dao = new KeChengZhangJieDao();
    	kcDao = new KeChengDao();
    }
	@Override
	public int getCount() {
		return list == null ?0:list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	public void setList(List<CourseDetailsShuju> list,JiaoXueJiHua jxjh){
		this.list = list;
		this.jxjh = jxjh;
	}
	public void setBianjiInt(int bianJiInt) {
		this.bianJiInt = bianJiInt;
	}
	@SuppressWarnings("null")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if(convertView==null){
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.downded_xq_item, null);
			viewHolder.cource_iv = (ImageView)convertView.findViewById(R.id.cource_iv);
			viewHolder.kecheng_tv = (TextView)convertView.findViewById(R.id.kecheng_tv);
			viewHolder.del_tv = (TextView)convertView.findViewById(R.id.del_tv);
			viewHolder.state_tv = (TextView)convertView.findViewById(R.id.state_tv);
			viewHolder.daxiao_tv = (TextView)convertView.findViewById(R.id.daxiao_tv);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder)convertView.getTag();
		}
		
		final CourseDetailsShuju shuju = list.get(position);
		viewHolder.del_tv.setVisibility(View.GONE);
		switch (bianJiInt) {
		case 0:
			viewHolder.del_tv.setVisibility(View.GONE);
			break;
		case 1:
			viewHolder.del_tv.setVisibility(View.VISIBLE);
			viewHolder.del_tv.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Dialog  dialog = new AlertDialog.Builder(mContext).
							setMessage("是否删除"+CommonUtil.getKeChengText(jxjh.getKeChengMingCheng(), shuju.getZhang(), shuju.getJie())).setCancelable(false).
							setPositiveButton("删除", new DialogInterface.OnClickListener(){ 
			                     
								@Override 
			                    public void onClick(DialogInterface dialog, int which) {
									int delValue = (int)dao.delKeChengZhangJie(shuju.getShiPinDiZhi());
									if(delValue >0 ){
										List<CourseDetailsShuju> listZJ = dao.getKeChengZhangJieList(jxjh.getKeChengDaiMa());
										if(listZJ.size()<=0)  kcDao.deleteKeCheng(jxjh.getKeChengDaiMa());
										list.remove(position);
										notifyDataSetChanged();
									}
			                    } 
			                }).
			                setNegativeButton("取消", new DialogInterface.OnClickListener(){

								@Override
								public void onClick(DialogInterface dialog,int which) {
									dialog.cancel();	
								}
						
							}).
							create();
							dialog.show();
				}
			});
			break;
		}
		viewHolder.kecheng_tv.setText(CommonUtil.getKeChengText(jxjh.getKeChengMingCheng(), shuju.getZhang(), shuju.getJie()));
		//viewHolder.zhang_tv.setText(shuju.getZhang());
		//viewHolder.jie_tv.setText(shuju.getJie());
		viewHolder.state_tv.setText(shuju.getGuangKanBaiFenBi()==null?"已观看至0%":"已观看至"+shuju.getGuangKanBaiFenBi());
		viewHolder.daxiao_tv.setText(shuju.getTotalSize()==null?"0M":SDCardSizeUtil.getFileSize(Integer.parseInt(shuju.getTotalSize())));
		ImageCacheManager.loadImage(mContext, jxjh.getKeChengZhaoPian(), viewHolder.cource_iv,ImageCacheManager.setDefaultImage(mContext, R.drawable.course_default), ImageCacheManager.setErrorImage(mContext, R.drawable.course_default),150,100);
		return convertView;
	}
	
    static class ViewHolder{
    	ImageView cource_iv;
    	TextView kecheng_tv,state_tv,daxiao_tv,del_tv;
    }

}
