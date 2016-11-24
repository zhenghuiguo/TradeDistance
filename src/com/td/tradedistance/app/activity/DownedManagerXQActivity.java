package com.td.tradedistance.app.activity;

import java.util.List;

import com.td.tradedistance.app.R;
import com.td.tradedistance.app.adapter.DownedXQAdapter;
import com.td.tradedistance.app.adapter.DownloadingManagerCourseAdapter;
import com.td.tradedistance.app.bean.CourseDetailsShuju;
import com.td.tradedistance.app.bean.JiaoXueJiHua;
import com.td.tradedistance.app.localstorage.DownLoad_Info_Dao;
import com.td.tradedistance.app.localstorage.KeChengZhangJieDao;
import com.td.tradedistance.app.utils.FileOperationUtils;
import com.td.tradedistance.app.utils.Logger;
import com.td.tradedistance.app.utils.SDCardSizeUtil;
import com.td.tradedistance.app.widget.OnClickBianJiListener;
import com.td.tradedistance.app.widget.TitleBar;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class DownedManagerXQActivity extends BaseActivity{
	private ListView mYiXiaZaiCourseList;
	private DownedXQAdapter adapter;
	private List<CourseDetailsShuju> list;
	private TitleBar mTitleBar;
	private JiaoXueJiHua jxjh;
	private LinearLayout lly;
	private Intent it;
	private TextView rightImage,Kongjiandaxiao_tv;
    private DownLoad_Info_Dao downDao;
    private KeChengZhangJieDao keChengZhangJieDao;

	   
	private int BianJiInt= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	setContentView(R.layout.activity_downedmanager);
    	super.onCreate(savedInstanceState);
    }
	@SuppressWarnings("unchecked")
	@Override
	protected void initVariables() {
		it = getIntent();
		list = (List<CourseDetailsShuju>)it.getSerializableExtra("list");
		jxjh =  (JiaoXueJiHua)it.getSerializableExtra("jxjh");
	}

	@Override
	protected void initViews(Bundle savedInstanceState) {
		 downDao = new DownLoad_Info_Dao();
		 keChengZhangJieDao =new KeChengZhangJieDao() ;
		mTitleBar = (TitleBar) findViewById(R.id.titlebar);
		Kongjiandaxiao_tv = (TextView) findViewById(R.id.Kongjiandaxiao_tv);
		Kongjiandaxiao_tv.setText(SDCardSizeUtil.getKongJianDaXiao(DownedManagerXQActivity.this,downDao,keChengZhangJieDao));
		mTitleBar.setTitle(jxjh.getKeChengMingCheng());
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
		lly = (LinearLayout) findViewById(R.id.lly);
		lly.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				List<CourseDetailsShuju> list = new KeChengZhangJieDao().getKeChengZhangJieList(jxjh.getKeChengDaiMa());
				if(list!=null&&list.size()>0){
					 it = new Intent(DownedManagerXQActivity.this,CourseDetailsActivity.class);
					 it.putExtra("jxjh", jxjh);
					 startActivity(it);
				}
			}
		});
		mYiXiaZaiCourseList= (ListView) findViewById(R.id.yixiazaicourselist_lv);
		adapter = new DownedXQAdapter(DownedManagerXQActivity.this);
		adapter.setList(list,jxjh);
		mYiXiaZaiCourseList.setAdapter(adapter);
		mYiXiaZaiCourseList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			     Logger.d("onclick", "onItemClick");
			     Intent it = new Intent(DownedManagerXQActivity.this,VideoPlayerActivity.class);
			     CourseDetailsShuju shuju =(CourseDetailsShuju)adapter.getItem(position);
					String zhangJieMingCheng = shuju.getJie();
					String path = shuju.getShiPinDiZhi();
					String  url = "";
						  url = FileOperationUtils.getMycoursePathAddYhwybs()+"/"+FileOperationUtils.getFileName(path);
					it.putExtra("path", path);
					it.putExtra("url", url);
					it.putExtra("zhangjieming", zhangJieMingCheng);
					startActivity(it);
			}
		});
	}

	@Override
	protected void loadData() {
		
	}
    @Override
    protected void onResume() {
    	if(list!=null){
    		list = new KeChengZhangJieDao().getKeChengZhangJieList(jxjh.getKeChengDaiMa());
    		adapter.setList(list,jxjh);
    		adapter.notifyDataSetChanged();
    	}
    	super.onResume();
    }
}
