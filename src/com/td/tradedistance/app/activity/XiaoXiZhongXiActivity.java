package com.td.tradedistance.app.activity;


import com.td.tradedistance.app.R;
import com.td.tradedistance.app.fragment.WoDeGongGaoCourseFragment;
import com.td.tradedistance.app.fragment.WoDeXiaoXiCourseFragment;
import com.td.tradedistance.app.utils.Logger;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class XiaoXiZhongXiActivity extends BaseActivity {
	@SuppressWarnings("unused")
	private static final String Tag ="MainActivity";// StudyFragment.class.getSimpleName();
	private RelativeLayout mWeXiaoXiLayout;
	private RelativeLayout mWoDeGongGaoLayout;

	private TextView wexiaoxicolor_tv,tzgg_color_tv;
	private TextView wexiaoxi_tv, tzgg_tv;
	private WoDeXiaoXiCourseFragment mWoDeXiaoXiCourseFragment;
	private WoDeGongGaoCourseFragment mWoDeGongGaoCourseFragment;

	private FragmentManager fragement_Manager;
	private FragmentTransaction transaction;

	private Resources resources;
	@SuppressWarnings("unused")
	private Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_xiaoxizhongxi);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initVariables() {
		// TODO Auto-generated method stub
		resources = getResources();
	}

	@Override
	protected void initViews(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		mWeXiaoXiLayout = (RelativeLayout) findViewById(R.id.wodexiaoxi_layout);
		mWoDeGongGaoLayout = (RelativeLayout) findViewById(R.id.tzgg_layout);

		wexiaoxicolor_tv = (TextView) findViewById(R.id.wdxxcolor_tv);
		
		tzgg_color_tv = (TextView) findViewById(R.id.tzgg_color_tv);
		wexiaoxi_tv = (TextView) findViewById(R.id.wdxx_tv);

		tzgg_tv = (TextView) findViewById(R.id.tzgg_tv);
		

		setTabSelection(0);

		mWeXiaoXiLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setTabSelection(0);
			}
		});

		mWoDeGongGaoLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setTabSelection(1);
			}
		});
		
	}

	@Override
	protected void loadData() {
		// TODO Auto-generated method stub

	}
		private void setTabSelection(int index) {
			/*
			 * if(index!=0){ if(mCourse_Fragment!=null)
			 * mCourse_Fragment.getViewpaper().setContinue(false); }
			 */
			clearSelection();
			fragement_Manager = this.getSupportFragmentManager();
			transaction = fragement_Manager.beginTransaction();
			Logger.d("MainActivity", transaction + " transaction");
			hideFragments(transaction);
			switch (index) {
			case 0:
				wexiaoxicolor_tv.setBackgroundColor(resources.getColor(R.color.tv_tab_selected));
				wexiaoxi_tv.setTextColor(resources.getColor(R.color.black));
				mWeXiaoXiLayout.setBackgroundColor(resources.getColor(R.color.white));
				if (mWoDeXiaoXiCourseFragment == null) {
					mWoDeXiaoXiCourseFragment = new WoDeXiaoXiCourseFragment();
					transaction.add(R.id.content_xxzxid, mWoDeXiaoXiCourseFragment);
				} else {
					transaction.remove(mWoDeXiaoXiCourseFragment);
					mWoDeXiaoXiCourseFragment = new WoDeXiaoXiCourseFragment();
					transaction.add(R.id.content_xxzxid, mWoDeXiaoXiCourseFragment);
					// transaction.show(mCourse_Fragment);
				}
				break;
			case 1:
				tzgg_color_tv.setBackgroundColor(resources.getColor(R.color.tv_tab_selected));
				tzgg_tv.setTextColor(resources.getColor(R.color.black));
				mWoDeGongGaoLayout.setBackgroundColor(resources.getColor(R.color.white));
				if (mWoDeGongGaoCourseFragment == null) {
					mWoDeGongGaoCourseFragment = new WoDeGongGaoCourseFragment();
					transaction.add(R.id.content_xxzxid, mWoDeGongGaoCourseFragment);
				} else {
					transaction.remove(mWoDeGongGaoCourseFragment);
					mWoDeGongGaoCourseFragment = new WoDeGongGaoCourseFragment();
					transaction.add(R.id.content_xxzxid, mWoDeGongGaoCourseFragment	);				// transaction.show(mStudy_Fragment);
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
			wexiaoxicolor_tv.setBackgroundColor(resources.getColor(R.color.tab_grey));
			wexiaoxi_tv.setTextColor(resources.getColor(R.color.white));
			mWeXiaoXiLayout.setBackgroundColor(resources.getColor(R.color.tab_grey));
		}

		private void clearWJ() {
			tzgg_color_tv.setBackgroundColor(resources.getColor(R.color.tab_grey));
			tzgg_tv.setTextColor(resources.getColor(R.color.white));
			mWoDeGongGaoLayout.setBackgroundColor(resources.getColor(R.color.tab_grey));
		}


		/**
		 * 将所有的Fragment都置为隐藏状态。
		 * 
		 * @param transaction
		 *            用于对Fragment执行操作的事务
		 */
		private void hideFragments(FragmentTransaction transaction) {
			if (mWoDeXiaoXiCourseFragment != null) {
				transaction.hide(mWoDeXiaoXiCourseFragment);
			}
			if (mWoDeGongGaoCourseFragment != null) {
				transaction.hide(mWoDeGongGaoCourseFragment);
			}
		}
}
