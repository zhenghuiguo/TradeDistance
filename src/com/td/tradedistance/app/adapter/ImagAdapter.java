package com.td.tradedistance.app.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import api.ImageCacheManager;

import com.td.tradedistance.app.R;
import com.td.tradedistance.app.TDApp;
import com.td.tradedistance.app.activity.WebActivity;
import com.td.tradedistance.app.bean.GuangGaoWeiShuJu;
import com.td.tradedistance.app.bean.Image;

/**
 * viewpager图片适配器
 * 
 * 
 */
public class ImagAdapter extends PagerAdapter {

	private List<GuangGaoWeiShuJu> urls;
	private Context mContext;
	private int mpage;

	public ImagAdapter(List<GuangGaoWeiShuJu> urls, Context context) {
		this.urls = urls;
		this.mContext = context;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	public void change(List<Image> urls) {
	}

	@Override
	public int getCount() {
		return urls == null ? 0 : urls.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	public void setSelection(int page) {
		this.mpage = page;
	}

	@Override
	public Object instantiateItem(final View container, final int position) {
		final ImageView iv = new ImageView(TDApp.getContext());
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		iv.setLayoutParams(lp);

		try {
			 final GuangGaoWeiShuJu shuju = urls.get(position);
			iv.setScaleType(ScaleType.FIT_XY);
			ImageCacheManager.loadImage(mContext, shuju.getTuPianDiZhi(), iv,ImageCacheManager.setDefaultImage(mContext, R.drawable.banner_default), ImageCacheManager.setErrorImage(mContext, R.drawable.banner_default));
			
			iv.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
	                   Intent it = new Intent(mContext,WebActivity.class);
	                   it.putExtra("title", shuju.getMingCheng());
	                   it.putExtra("url", shuju.getLianJieDiZhi());
	                   mContext.startActivity(it);
				}
			});
			((ViewPager) container).addView(iv, 0);
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		}

		
		return iv;
	}
}
