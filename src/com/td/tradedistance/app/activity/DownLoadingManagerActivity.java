package com.td.tradedistance.app.activity;


import com.td.tradedistance.app.R;
import com.td.tradedistance.app.adapter.DownLoadingAdapter;
import com.td.tradedistance.app.localstorage.DownLoad_Info_Dao;
import com.td.tradedistance.app.localstorage.KeChengZhangJieDao;
import com.td.tradedistance.app.utils.SDCardSizeUtil;
import com.td.tradedistance.app.widget.OnClickBianJiListener;
import com.td.tradedistance.app.widget.TitleBar;

import down.GlobalsListDownLoading;


import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class DownLoadingManagerActivity extends BaseActivity{
	private ListView mYiXiaZaiCourseList;
	private DownLoadingAdapter adapter;
	private TextView Kongjiandaxiao_tv;
	private LinearLayout lly;
	private int isXiaZai= 0;
	private GlobalsListDownLoading downloadManager;
	private DownLoad_Info_Dao downDao;
	private KeChengZhangJieDao keChengZhangJieDao;
	private TitleBar mTitleBar;
	private TextView rightImage;
	private int BianJiInt= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	setContentView(R.layout.activity_downloadingmanager);
    	super.onCreate(savedInstanceState);
    }
	@Override
	protected void initVariables() {
	}

	@Override
	protected void initViews(Bundle savedInstanceState) {
		 downDao = new DownLoad_Info_Dao();
		 keChengZhangJieDao =new KeChengZhangJieDao() ;
		downloadManager= GlobalsListDownLoading.getInstance();
		mTitleBar = (TitleBar) findViewById(R.id.titlebar);
		rightImage = mTitleBar.getRightImage();
		LayoutParams lp = rightImage.getLayoutParams();
	    lp.height = 100;
	    lp.width = 100;
	    rightImage.setLayoutParams(lp);
		mTitleBar.setRightText(getResources().getString(R.string.edit_str));
		mTitleBar.setBianJiListener(new OnClickBianJiListener() {
			
			@Override
			public void onBianJi() {
				switch (BianJiInt) {
				case 0:
					BianJiInt =1;
					mTitleBar.setRightText(getResources().getString(R.string.wancheng_str));
					adapter.setBianjiInt(BianJiInt);
					adapter.notifyDataSetChanged();
					break;
				case 1:
					BianJiInt =0;
					mTitleBar.setRightText(getResources().getString(R.string.edit_str));
					adapter.setBianjiInt(BianJiInt);
					adapter.notifyDataSetChanged();
					break;
				}
				
			}
		});
		lly = (LinearLayout)findViewById(R.id.lly);
		Kongjiandaxiao_tv = (TextView) findViewById(R.id.Kongjiandaxiao_tv);
		Kongjiandaxiao_tv.setText(SDCardSizeUtil.getKongJianDaXiao(DownLoadingManagerActivity.this,downDao,keChengZhangJieDao));
		mYiXiaZaiCourseList= (ListView) findViewById(R.id.yixiazaicourselist_lv);
		adapter = new DownLoadingAdapter(DownLoadingManagerActivity.this,downloadManager);
		adapter.setListView(mYiXiaZaiCourseList);
		mYiXiaZaiCourseList.setAdapter(adapter);
		mYiXiaZaiCourseList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			
			}
		});
		lly.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(isXiaZai!=1){
					isXiaZai = 1;
					adapter.setIsXiaZai(isXiaZai);
					adapter.notifyDataSetChanged();
				}
				/*if(isXiaZai==0){
					isXiaZai = 1;
					adapter.setIsXiaZai(isXiaZai);
					adapter.notifyDataSetChanged();
				}else if(isXiaZai==1){
					isXiaZai = 0;
					adapter.setIsXiaZai(isXiaZai);
					adapter.notifyDataSetChanged();
				}*/
			}
		});
	}

	@Override
	protected void loadData() {
		
	}
    @Override
    protected void onDestroy() {
    	downloadManager.stopAllDownloadTask();
    	super.onDestroy();
    }
}
