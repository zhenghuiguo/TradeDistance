package com.td.tradedistance.app.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.td.tradedistance.app.R;
import com.td.tradedistance.app.utils.Logger;

public class StudyFragment1 extends Fragment{
	private static final String Tag ="MainActivity";// StudyFragment.class.getSimpleName();
	private RelativeLayout mZaiXueLayout;
	private RelativeLayout mWanJieLayout;

	private TextView zxcolor_tv,wj_color_tv;
	private TextView zx_tv, wj_tv;
	private ZaiXueCourseFragment mZaiXueCourseFragment;
	private WanJieCourseFragment mWanJieCourseFragment;

	private FragmentManager fragement_Manager;
	private FragmentTransaction transaction;

	private Resources resources;
	private Context mContext;
	public StudyFragment1(FragmentManager fragement_Manager){
		this.fragement_Manager = fragement_Manager;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Logger.d(Tag,"onCreate StudyFragment");
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_study1, container, false);
		Logger.d(Tag,"onCreateView StudyFragment");
		resources = getResources();
		mZaiXueLayout = (RelativeLayout) view.findViewById(R.id.zaixue_layout);
		mWanJieLayout = (RelativeLayout) view.findViewById(R.id.wanjie_layout);

		zxcolor_tv = (TextView) view.findViewById(R.id.zxcolor_tv);
		
		wj_color_tv = (TextView) view.findViewById(R.id.wj_color_tv);
		zx_tv = (TextView) view.findViewById(R.id.zx_tv);

		wj_tv = (TextView) view.findViewById(R.id.wj_tv);
		

		setTabSelection(0);

		mZaiXueLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setTabSelection(0);
			}
		});

		mWanJieLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setTabSelection(1);
			}
		});
		
		
		
		return view;
	}
	  
	   
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	Logger.d(Tag,"onActivityCreated StudyFragment");
    	super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onStart() {
    	Logger.d(Tag,"onStart StudyFragment");
    	super.onStart();
    }
    @Override
    public void onResume() {
    	Logger.d(Tag,"onResume StudyFragment");
    	super.onResume();
    }
    @Override
    public void onPause() {
    	Logger.d(Tag,"onPause StudyFragment");
    	super.onPause();
    }
    @Override
    public void onStop() {
    	Logger.d(Tag,"onStop StudyFragment");
    	super.onStop();
    }
    @Override
    public void onDestroyView() {
    	Logger.d(Tag,"onDestroyView StudyFragment");
    	super.onDestroyView();
    }
    @Override
    public void onDestroy() {
    	Logger.d(Tag,"onDestroy StudyFragment");
    	super.onDestroy();
    }
    @Override
    public void onDetach() {
    	Logger.d(Tag,"onDetach StudyFragment");
    	super.onDetach();
    }
    
	private void setTabSelection(int index) {
		/*
		 * if(index!=0){ if(mCourse_Fragment!=null)
		 * mCourse_Fragment.getViewpaper().setContinue(false); }
		 */
		clearSelection();
		//fragement_Manager = mContext.getSupportFragmentManager();
		transaction = fragement_Manager.beginTransaction();
		Logger.d("MainActivity", transaction + " transaction");
		hideFragments(transaction);
		switch (index) {
		case 0:
			zxcolor_tv.setBackgroundColor(resources.getColor(R.color.tv_tab_selected));
			zx_tv.setTextColor(resources.getColor(R.color.black));
			mZaiXueLayout.setBackgroundColor(resources.getColor(R.color.white));
			if (mZaiXueCourseFragment == null) {
				mZaiXueCourseFragment = new ZaiXueCourseFragment();
				transaction.add(R.id.content_id, mZaiXueCourseFragment);
			} else {
				transaction.remove(mZaiXueCourseFragment);
				mZaiXueCourseFragment = new ZaiXueCourseFragment();
				transaction.add(R.id.content_id, mZaiXueCourseFragment);
				// transaction.show(mCourse_Fragment);
			}
			break;
		case 1:
			wj_color_tv.setBackgroundColor(resources.getColor(R.color.tv_tab_selected));
			wj_tv.setTextColor(resources.getColor(R.color.black));
			mWanJieLayout.setBackgroundColor(resources.getColor(R.color.white));
			if (mWanJieCourseFragment == null) {
				mWanJieCourseFragment = new WanJieCourseFragment();
				transaction.add(R.id.content_id, mWanJieCourseFragment);
			} else {
				transaction.remove(mWanJieCourseFragment);
				mWanJieCourseFragment = new WanJieCourseFragment();
				transaction.add(R.id.content_id, mWanJieCourseFragment);
				// transaction.show(mStudy_Fragment);
			}

			break;

		default:
			break;
		}
		transaction.commit();
	}

	/**
	 * 清除掉所有的选中状态。
	 */
	private void clearSelection() {
		clearZX();
		clearWJ();
	}

	private void clearZX() {
		zxcolor_tv.setBackgroundColor(resources.getColor(R.color.tab_grey));
		zx_tv.setTextColor(resources.getColor(R.color.white));
		mZaiXueLayout.setBackgroundColor(resources.getColor(R.color.tab_grey));
	}

	private void clearWJ() {
		wj_color_tv.setBackgroundColor(resources.getColor(R.color.tab_grey));
		wj_tv.setTextColor(resources.getColor(R.color.white));
		mWanJieLayout.setBackgroundColor(resources.getColor(R.color.tab_grey));
	}


	/**
	 * 将所有的Fragment都置为隐藏状态。
	 * 
	 * @param transaction
	 *            用于对Fragment执行操作的事务
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (mZaiXueCourseFragment != null) {
			transaction.hide(mZaiXueCourseFragment);
		}
		if (mWanJieCourseFragment != null) {
			transaction.hide(mWanJieCourseFragment);
		}
	}
}
