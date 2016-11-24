package com.td.tradedistance.app.fragment;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.td.tradedistance.app.R;

public class StudyFragment extends Fragment{
	/*private static final String Tag ="MainActivity";// MessageFragment.class.getSimpleName();
	 private FragmentManager framentManager;
	public StudyFragment(FragmentManager framentManager){
		this.framentManager = framentManager;
	}
	private List<Fragment> list = new ArrayList<Fragment>();
    private kyindicator k;
    private ViewPager viewpaper;
    private String nameArr[] ={"推荐","热点","北京","视频","社会","财经","汽车","热点","图片"};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Logger.d(Tag,"onCreate MessageFragment");
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View messageLayout = inflater.inflate(R.layout.fragment_study, container, false);
		Logger.d(Tag,"onCreateView MessageFragment");
		
		k =(kyindicator) messageLayout.findViewById(R.id.kyindicator);
        viewpaper =(ViewPager)messageLayout.findViewById(R.id.viewpager);
        addEvent();
        addTab();
		return messageLayout;
	}
	  private void addEvent(){
	    	k.setL(new OnSelectedListener() {
				
				@Override
				public void OnSelected(View tab, int index) {
					TextView textTv = (TextView)tab.findViewById(R.id.tv_text);
					textTv.setTextColor(Color.parseColor("#33CC00"));
					viewpaper.setCurrentItem(index);
					Animation a = AnimationUtils.loadAnimation(getActivity(), R.anim.popup_enter);
	    			a.setFillAfter(true);
	    			textTv.startAnimation(a);
	    			//textTv.setTextColor(R.drawable.shape);
				}
				
				@Override
				public void OnNOSelected(View tab, int index) {
					TextView textTv = (TextView)tab.findViewById(R.id.tv_text);
					textTv.setTextColor(Color.parseColor("#CC33FF"));
				   if(k.getSelectedIndex()==index){
						Animation a = AnimationUtils.loadAnimation(getActivity(), R.anim.popup_exit);
		    			a.setFillAfter(true);
		    			textTv.startAnimation(a);
				   }
				
				}
			});
	    }
	    private void addTab(){
	    	if(nameArr!=null&&nameArr.length>0){
	    		for(int i=0;i<nameArr.length;i++){
	    			View view = LayoutInflater.from(getActivity()).inflate(R.layout.tab, null);
	    	    	TextView tv = (TextView)view.findViewById(R.id.tv_text);
	    	    	tv.setText(nameArr[i]);
	    	    	k.addTab(view,i);
	    	    	list.add(new MyFragment());
	    		}
	    	}
	    	
	    	k.setCurrnetTab(0);
	    	
	    	
	    	//framentManager =this.getSupportFragmentManager();
	    	viewpaper.setAdapter(new MyPagerAdapter(framentManager));
	    	viewpaper.setOnPageChangeListener(new OnPageChangeListener() {
				
				@Override
				public void onPageSelected(int arg0) {
					// TODO Auto-generated method stub
					k.setCurrnetTab(arg0);
				}
				
				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onPageScrollStateChanged(int arg0) {
					// TODO Auto-generated method stub
					
				}
			});
	    }
	    
	    *//**
		 * 定义viewpager适配器
		 * @author pc
		 *
		 *//*
		private class MyPagerAdapter extends FragmentPagerAdapter{
			public MyPagerAdapter(FragmentManager fm) {
				super(fm);
			}
			
			@Override
			public android.support.v4.app.Fragment getItem(int arg0) {
				return list.get(arg0);
			}
			@Override
			public int getCount() {
				return list.size();
			}		
		}
		*//**
		 * 定义OnPageChangeListener  监听器
		 * @author pc
		 *
		 *//*
		private class MyOnPageChangeListener implements OnPageChangeListener{
			@Override
			public void onPageScrollStateChanged(int arg0) {			
			}
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			}

			@Override
			public void onPageSelected(int position) {
				k.setCurrnetTab(position);
			}		
		}
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	Logger.d(Tag,"onActivityCreated MessageFragment");
    	super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onStart() {
    	Logger.d(Tag,"onStart MessageFragment");
    	super.onStart();
    }
    @Override
    public void onResume() {
    	Logger.d(Tag,"onResume MessageFragment");
    	super.onResume();
    }
    @Override
    public void onPause() {
    	Logger.d(Tag,"onPause MessageFragment");
    	super.onPause();
    }
    @Override
    public void onStop() {
    	Logger.d(Tag,"onStop MessageFragment");
    	super.onStop();
    }
    @Override
    public void onDestroyView() {
    	Logger.d(Tag,"onDestroyView MessageFragment");
    	super.onDestroyView();
    }
    @Override
    public void onDestroy() {
    	Logger.d(Tag,"onDestroy MessageFragment");
    	super.onDestroy();
    }
    @Override
    public void onDetach() {
    	Logger.d(Tag,"onDetach MessageFragment");
    	super.onDetach();
    }*/
}
