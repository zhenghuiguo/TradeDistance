package com.td.tradedistance.app.widget;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.td.tradedistance.app.R;
import com.td.tradedistance.app.adapter.ImagAdapter;
import com.td.tradedistance.app.bean.GuangGaoWeiShuJu;
import com.td.tradedistance.app.utils.Logger;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
public class ViewPaperIndicator extends LinearLayout{
	private ViewPager viewPager;
	private ProgressBarView pb_Loading;
	private ImageView[] imagesViews;
	private ImageView imageView;
    private ViewGroup group ;
    private TextView title_tv;
    private ImagAdapter adapter;
   	private AtomicInteger what = new AtomicInteger(0);
   	
   	private boolean isContinue = true;
   	private TextView tv_title;
   	private List<GuangGaoWeiShuJu> tuPian_list;
   	
	public ProgressBarView getPb_Loading() {
		return pb_Loading;
	}
	public void setPb_Loading(ProgressBarView pb_Loading) {
		this.pb_Loading = pb_Loading;
	}
   	public boolean isContinue() {
		return isContinue;
	}
	public void setContinue(boolean isContinue) {
		this.isContinue = isContinue;
	}
	private Handler viewHandler;
	public ViewPaperIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		View view = inflate(context, R.layout.widget_viewpaper, null);
		addView(view);
		viewPager = (ViewPager) view.findViewById(R.id.viewpager);
		pb_Loading = (ProgressBarView) view.findViewById(R.id.pb_loading);
		group = (ViewGroup) view.findViewById(R.id.viewGroup);
		title_tv = (TextView) view.findViewById(R.id.title_tv);
	}
	@SuppressWarnings("deprecation")
	public void initViewPager(List<GuangGaoWeiShuJu> urls) {
		//pb_Loading.getHide();
		this.tuPian_list = urls;
		if(group != null) group.removeAllViews();
		adapter = new ImagAdapter(urls,getContext());
		if(urls==null||urls.size()==0){
			viewPager.setAdapter(adapter);
			return;
		}
		viewPager.setAdapter(adapter);
		initHandle();
		imagesViews = new ImageView[urls.size()];
		for (int i = 0; i < urls.size(); i++) {
			imageView = new ImageView(getContext());
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
			params.leftMargin = 8;
			//imageView.setPadding(3, 3, 3, 3);
			imageView.setLayoutParams(params);
			imagesViews[i] = imageView;
			if (i == 0) {
				imagesViews[i].setBackgroundResource(R.drawable.dots_press);
			} else {
				imagesViews[i].setBackgroundResource(R.drawable.dots_normal);
			}
			group.addView(imagesViews[i]);
			GuangGaoWeiShuJu sr =(GuangGaoWeiShuJu)urls.get(0);
			title_tv.setText(sr.getMingCheng());
		}
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				for (int i = 0; i < imagesViews.length; i++) {
					if (arg0 != i){
						imagesViews[i].setBackgroundResource(R.drawable.dots_normal);
					}else{
						imagesViews[arg0].setBackgroundResource(R.drawable.dots_press);
						setTitleAndTuPian(arg0);
					}
				}
				
				adapter.setSelection(arg0);
				what.set(arg0);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				//Logger.d("onPageScrolled arg0"+arg0+"arg1"+arg1+"arg2"+arg2);
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				Logger.d("onPageScrollStateChanged arg=="+arg0);
			}
		});
		if(urls.size()>1)
			startThread();
	}
	public void startThread(){
		isContinue = true;
		what.set(0);
		new Thread(new Runnable() {

		@Override
		public void run() {
			
			while (isContinue) {
				    
					
					
					if (what.get() > imagesViews.length - 1) {
						what.getAndAdd(-imagesViews.length);
					}
					try {
						Thread.sleep(3000);
						viewHandler.sendEmptyMessage(what.get());
						Logger.d(Thread.currentThread()+"Thread-what.get()"+what.get()+"viewHandler");
						what.incrementAndGet();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
		}
	}).start();
	}
	
	private void initHandle(){
		viewHandler = new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				//Logger.d("handleMessage", msg.what+"");
				viewPager.setCurrentItem(msg.what);
				setTitleAndTuPian(msg.what);
				super.handleMessage(msg);
			}
	   };
	}
	
	private void setTitleAndTuPian(int i){
		int size = tuPian_list.size();
		if(tuPian_list!=null && size>0){
			try {
				GuangGaoWeiShuJu sr =(GuangGaoWeiShuJu)tuPian_list.get(i);
				title_tv.setText(sr.getMingCheng());
			} catch (Exception e) {
				Logger.d(e.getMessage());
			}
			
		}
	}
	/*public void setTitleSZJXBg1(){
		rll_title_bg.setBackgroundColor(getResources().getColor(R.color.szjx_bg_color));
	}
	public void setTitleFhlyBg1() {
		rll_title_bg.setBackgroundColor(getResources().getColor(R.color.honghuolangyan_title_text));
		
	}*/
}
