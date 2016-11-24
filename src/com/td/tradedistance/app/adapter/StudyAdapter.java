package com.td.tradedistance.app.adapter;

import java.util.List;

import com.td.tradedistance.app.R;
import com.td.tradedistance.app.activity.CourseDetailsActivity;
import com.td.tradedistance.app.bean.JiaoXueJiHua;
import com.td.tradedistance.app.bean.JiaoXueJiHuaShuJu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import api.ImageCacheManager;

 public class StudyAdapter extends BaseAdapter{
	 private Context mContext;
		private Intent it;
		private List<JiaoXueJiHuaShuJu> list;

		public StudyAdapter(Context context) {
			it = new Intent();
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

		public void setList(List<JiaoXueJiHuaShuJu> list) {
			this.list = list;
		}

		@SuppressWarnings("null")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			if (convertView == null) {
				viewHolder = new ViewHolder();
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.study_list_item, null);
				viewHolder.corusetype_tv = (TextView) convertView
						.findViewById(R.id.corusetype_tv);
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
			final JiaoXueJiHuaShuJu type = list.get(position);
			if (type != null) {
				final String typeStr = type.getType();
				viewHolder.corusetype_tv.setText(typeStr == null ? "" : typeStr);
				List<JiaoXueJiHua> typeList = type.getZShuJu();
				if (typeList != null) {
					int size = typeList.size();
						if (size == 1) {
							final JiaoXueJiHua jxjh1 = typeList.get(0);
							viewHolder.coursename1_tv.setText(jxjh1.getKeChengMingCheng());
							int xueWeiKe = jxjh1.getXueWeiKe();
							if(xueWeiKe==1){
								viewHolder.isxuewei1_iv.setVisibility(View.VISIBLE);
							}else{
								viewHolder.isxuewei1_iv.setVisibility(View.GONE);
							}
							ImageCacheManager.loadImage(mContext, jxjh1.getKeChengZhaoPian(), viewHolder.courseone_iv,ImageCacheManager.setDefaultImage(mContext, R.drawable.course_default), ImageCacheManager.setErrorImage(mContext, R.drawable.course_default));
							viewHolder.courseone_iv.setOnClickListener(new OnClickListener() {

										@Override
										public void onClick(View v) {
											// Logger.d("onclick",
											// "courseone_iv"+info.getName1());

											it.setClass(mContext,
													CourseDetailsActivity.class);
											it.putExtra("jxjh", jxjh1);
											mContext.startActivity(it);
										}
									});
						} else if (size >= 2) {
							final JiaoXueJiHua jxjh1 = typeList.get(0);
							final JiaoXueJiHua jxjh2 = typeList.get(1);
							int xueWeiKe1 = jxjh1.getXueWeiKe();
							int xueWeiKe2 = jxjh2.getXueWeiKe();
							if(xueWeiKe1==1){
								viewHolder.isxuewei1_iv.setVisibility(View.VISIBLE);
							}else{
								viewHolder.isxuewei1_iv.setVisibility(View.GONE);
							}
							if(xueWeiKe2==2){
								viewHolder.isxuewei2_iv.setVisibility(View.VISIBLE);
							}else{
								viewHolder.isxuewei2_iv.setVisibility(View.GONE);
							}
							viewHolder.coursename1_tv.setText(jxjh1
									.getKeChengMingCheng());
							viewHolder.coursename2_tv.setText(jxjh2
									.getKeChengMingCheng());
							ImageCacheManager.loadImage(mContext, jxjh1.getKeChengZhaoPian(), viewHolder.courseone_iv,ImageCacheManager.setDefaultImage(mContext, R.drawable.course_default), ImageCacheManager.setErrorImage(mContext, R.drawable.course_default));
							ImageCacheManager.loadImage(mContext, jxjh2.getKeChengZhaoPian(), viewHolder.coursetwo_iv,ImageCacheManager.setDefaultImage(mContext, R.drawable.course_default), ImageCacheManager.setErrorImage(mContext, R.drawable.course_default));

							viewHolder.courseone_iv
									.setOnClickListener(new OnClickListener() {

										@Override
										public void onClick(View v) {
											// Logger.d("onclick",
											// "courseone_iv"+info.getName1());

											it.setClass(mContext,
													CourseDetailsActivity.class);
											it.putExtra("jxjh", jxjh1);
											mContext.startActivity(it);
										}
									});
							viewHolder.coursetwo_iv
									.setOnClickListener(new OnClickListener() {

										@Override
										public void onClick(View v) {
											// Logger.d("onclick",
											// "coursetwo_iv"+info.getName2());
											it.setClass(mContext,
													CourseDetailsActivity.class);
											it.putExtra("jxjh", jxjh2);
											mContext.startActivity(it);
										}
									});
						}
					
				}


			}

			return convertView;
		}

		static class ViewHolder {
			ImageView coursetwo_iv, courseone_iv,isxuewei1_iv,isxuewei2_iv;
			TextView coursename1_tv, coursename2_tv, corusetype_tv;
		}

		/*
		 * public enum KeChengShuXing { RED, GREEN, BLANK, YELLOW }
		 */
		public enum KeChengShuXing {

			BiXiuKe(1, "必修课"), XuanXiuKe(2, "绿色"), KaoChaKe(3, "考查课");
			private String name;
			private int index;

			private KeChengShuXing(int index, String name) {
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
