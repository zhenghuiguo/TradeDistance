package com.td.tradedistance.app.activity;

import java.util.List;

import com.td.tradedistance.app.R;
import com.td.tradedistance.app.bean.CourseDetailsShuju;
import com.td.tradedistance.app.bean.JiaoXueJiHua;
import com.td.tradedistance.app.fragment.DaGangFragment;
import com.td.tradedistance.app.fragment.MuLuFragment;
import com.td.tradedistance.app.fragment.ZuoYeFragment;
import com.td.tradedistance.app.localstorage.KeChengZhangJieDao;
import com.td.tradedistance.app.utils.FileOperationUtils;
import com.td.tradedistance.app.utils.Logger;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GoStudyActivity extends FragmentActivity {
	private RelativeLayout mMuLuLayout;
	private RelativeLayout mZuoYeLayout;
	private RelativeLayout mDaGangLayout;
	private ImageView iv_play;
    private TextView coursename_tv,zhujiaojiaoshi_tv;
	private TextView mlcolor_tv, zy_color_tv, fxdgcolor_tv;
	private TextView ml_tv, zy_tv, fxdg_tv;
	private ImageView iv_back;
	private MuLuFragment mMuLuFragment;
	private ZuoYeFragment mZuoYeFragment;
	private DaGangFragment mDaGangFrament;
  
	private FragmentManager fragement_Manager;
	private FragmentTransaction transaction;

	private Resources resources;
    private List<CourseDetailsShuju> list;
    private Intent it;
    private String title;
    private String name;
    private String kcdm;
    private JiaoXueJiHua jxjh;
    private  KeChengZhangJieDao dao;
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_go_study);
		super.onCreate(savedInstanceState);
		dao = new KeChengZhangJieDao();
		it = getIntent();
		list = (List<CourseDetailsShuju>)it.getSerializableExtra("list");
		jxjh = (JiaoXueJiHua)it.getSerializableExtra("jxjh");
		title = it.getStringExtra("title");
		name = it.getStringExtra("name");
		kcdm = jxjh.getKeChengDaiMa();
		//Logger.d("GoStudyActivity", list.size());
		resources = getResources();
		// TODO Auto-generated method stub
		iv_back = (ImageView) findViewById(R.id.iv_back);
		mMuLuLayout = (RelativeLayout) findViewById(R.id.mulu_layout);
		mZuoYeLayout = (RelativeLayout) findViewById(R.id.zuoye_layout);
		mDaGangLayout = (RelativeLayout) findViewById(R.id.dagang_layout);
		coursename_tv = (TextView) findViewById(R.id.coursename_tv);
		zhujiaojiaoshi_tv = (TextView) findViewById(R.id.zhujiaojiaoshi_tv);
		mlcolor_tv = (TextView) findViewById(R.id.mlcolor_tv);
		zy_color_tv = (TextView) findViewById(R.id.zy_color_tv);
		fxdgcolor_tv = (TextView) findViewById(R.id.fxdgcolor_tv);
		iv_play = (ImageView) findViewById(R.id.iv_play);
		ml_tv = (TextView) findViewById(R.id.ml_tv);
		zy_tv = (TextView) findViewById(R.id.zy_tv);
		fxdg_tv = (TextView) findViewById(R.id.fxdg_tv);
		zhujiaojiaoshi_tv.setText("主讲教师："+name);
		coursename_tv.setText(title);
		setTabSelection(0);
		if(list!=null&&list.size()>1){
			iv_play.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					CourseDetailsShuju shuju = list.get(1);
					shuju.setPlay(true);
					String path = shuju.getShiPinDiZhi();
					String url = "";
					String zhangJieMingCheng = shuju.getZhangJieMingCheng();
					Intent it = new Intent(GoStudyActivity.this,VideoPlayerActivity.class);
				   
					if(dao.getKeChengZhangJieItem(path)){
						 url = FileOperationUtils.getMycoursePathAddYhwybs()+"/"+FileOperationUtils.getFileName(path);
					}else{
						url = path;
					}
					 it.putExtra("path", path);
					 it.putExtra("url", url);
					 it.putExtra("zhangjieming", zhangJieMingCheng);
					 startActivity(it);
				}
			});
	    }
		mMuLuLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setTabSelection(0);
			}
		});

		mZuoYeLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setTabSelection(1);
			}
		});
		mDaGangLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setTabSelection(2);
			}
		});
		iv_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	private void setTabSelection(int index) {
		clearSelection();
		fragement_Manager = this.getSupportFragmentManager();
		Logger.d("MainActivity", fragement_Manager + " fragement_Manager");
		transaction = fragement_Manager.beginTransaction();
		Logger.d("MainActivity", transaction + " transaction");
		hideFragments(transaction);
		switch (index) {
		case 0:
			mlcolor_tv.setBackgroundColor(resources.getColor(R.color.tv_tab_selected));
			ml_tv.setTextColor(resources.getColor(R.color.black));
			mMuLuLayout.setBackgroundColor(resources.getColor(R.color.white));
			if (mMuLuFragment == null) {
				mMuLuFragment = new MuLuFragment(list,jxjh);
				transaction.add(R.id.content_gostudyid, mMuLuFragment);
			} else {
				transaction.remove(mMuLuFragment);
				mMuLuFragment = new MuLuFragment(list,jxjh);
				transaction.add(R.id.content_gostudyid, mMuLuFragment);
				 //transaction.show(mMuLuFragment);
			}
			break;
		case 1:
			zy_color_tv.setBackgroundColor(resources.getColor(R.color.tv_tab_selected));
			zy_tv.setTextColor(resources.getColor(R.color.black));
			mZuoYeLayout.setBackgroundColor(resources.getColor(R.color.white));
			if (mZuoYeFragment == null) {
				mZuoYeFragment = new ZuoYeFragment(kcdm,title);
				transaction.add(R.id.content_gostudyid, mZuoYeFragment);
			} else {
				transaction.remove(mZuoYeFragment);
				mZuoYeFragment = new ZuoYeFragment(kcdm,title);
				transaction.add(R.id.content_gostudyid, mZuoYeFragment);
				transaction.show(mZuoYeFragment);
			}

			break;
		case 2:
			fxdgcolor_tv.setBackgroundColor(resources.getColor(R.color.tv_tab_selected));
			fxdg_tv.setTextColor(resources.getColor(R.color.black));
			mDaGangLayout.setBackgroundColor(resources.getColor(R.color.white));
			if (mDaGangFrament == null) {
				mDaGangFrament = new DaGangFragment(kcdm,title);
				transaction.add(R.id.content_gostudyid, mDaGangFrament);
			} else {
				transaction.remove(mDaGangFrament);
				mDaGangFrament = new DaGangFragment(kcdm,title);
				transaction.add(R.id.content_gostudyid, mDaGangFrament);
				//transaction.show(mDaGangFrament);
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
		clearML();
		clearZY();
		clearFXDG();
	}

	private void clearML() {
		mlcolor_tv.setBackgroundColor(resources.getColor(R.color.tab_grey));
		ml_tv.setTextColor(resources.getColor(R.color.white));
		mMuLuLayout.setBackgroundColor(resources.getColor(R.color.tab_grey));
	}

	private void clearZY() {
		zy_color_tv.setBackgroundColor(resources.getColor(R.color.tab_grey));
		zy_tv.setTextColor(resources.getColor(R.color.white));
		mZuoYeLayout.setBackgroundColor(resources.getColor(R.color.tab_grey));
	}

	private void clearFXDG() {
		fxdgcolor_tv.setBackgroundColor(resources.getColor(R.color.tab_grey));
		fxdg_tv.setTextColor(resources.getColor(R.color.white));
		mDaGangLayout.setBackgroundColor(resources.getColor(R.color.tab_grey));
	}

	/**
	 * 将所有的Fragment都置为隐藏状态。
	 * 
	 * @param transaction
	 *            用于对Fragment执行操作的事务
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (mMuLuFragment != null) {
			transaction.hide(mMuLuFragment);
		}
		if (mZuoYeFragment != null) {
			transaction.hide(mZuoYeFragment);
		}
		if (mDaGangFrament != null) {
			transaction.hide(mDaGangFrament);
		}
	}
}
