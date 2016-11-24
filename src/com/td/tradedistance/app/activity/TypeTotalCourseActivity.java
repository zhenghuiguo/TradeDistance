package com.td.tradedistance.app.activity;

import java.util.List;

import com.td.tradedistance.app.R;
import com.td.tradedistance.app.adapter.TypeTotalCourseAdapter;
import com.td.tradedistance.app.bean.JiaoXueJiHua;
import com.td.tradedistance.app.utils.Logger;
import com.td.tradedistance.app.widget.TitleBar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;
import api.ImageCacheManager;

public class TypeTotalCourseActivity extends BaseActivity implements OnScrollListener{
	private TitleBar mTitleBar;
	private ListView list;
	private List<JiaoXueJiHua> listJXJH;
	private TypeTotalCourseAdapter adapter;
	private String title;
    /**
	 * 记录是否刚打开程序，用于解决进入程序不滚动屏幕，不会下载图片的问题。
	 */
	private boolean isInit = false;
	/**
	 * 第一张可见图片的下标
	 */
	int _start_index;
	int _end_index;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
    	 setContentView(R.layout.activity_typetotalcourse);
    	
    	super.onCreate(savedInstanceState);
    }
	@Override
	protected void initVariables() {
		adapter = new TypeTotalCourseAdapter(TypeTotalCourseActivity.this);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void initViews(Bundle savedInstanceState) {
		 mTitleBar = (TitleBar) findViewById(R.id.titleBar);
		  Intent it = getIntent();
		  title = it.getStringExtra("title");
		  listJXJH =(List<JiaoXueJiHua>) it.getSerializableExtra("list");
		  mTitleBar.setTitle(title==null?"":title);
		 list = (ListView) findViewById(R.id.typetotalcourselist_lv);
		 adapter.setList(listJXJH);
		 list.setAdapter(adapter);
	}

	@Override
	protected void loadData() {
		
	}
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		isInit = true;
		adapter.setmBusy(isInit);
		switch (scrollState) {
		case OnScrollListener.SCROLL_STATE_IDLE:// list停止滚动时加载图片
			pageImgLoad(_start_index, _end_index, view);
			Logger.d("OnScrollListener", "OnScrollListener");
			break;
		case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
			Logger.d("SCROLL_STATE_TOUCH_SCROLL", "SCROLL_STATE_TOUCH_SCROLL");
			break;
		case OnScrollListener.SCROLL_STATE_FLING:
			Logger.d("SCROLL_STATE_FLING", "SCROLL_STATE_FLING");
			break;
		}
	}
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		Logger.d("onScroll", "onScroll firstVisibleItem=" + firstVisibleItem
				+ "visibleItemCount=" + visibleItemCount + "totalItemCount="
				+ totalItemCount);
		     // 设置当前屏幕显示的起始index和结束index
				if (firstVisibleItem == 0)
					_start_index = firstVisibleItem;
				else
					_start_index = firstVisibleItem - 1;
				Logger.d("_start_index", _start_index + "");
				_end_index = firstVisibleItem + visibleItemCount;
				if (_end_index >= totalItemCount) {
					_end_index = totalItemCount;
				}
	}
	
	private void pageImgLoad(int start_index, int end_index, AbsListView view) {
		if (list != null) {
			for (; start_index < end_index; start_index++) {
				setCourse(2,start_index,view);
			}
		}
	}
	
	public void setCourse(final int size,final int position,AbsListView view){
    	for(int i=0;i<size;i++){
    		 int pos = position*2+i;
			 final JiaoXueJiHua jxjh = listJXJH.get(pos);
			 ImageView iv = null;
			 
			if(i==0){
				iv= (ImageView)view.findViewWithTag(jxjh.getKeChengZhaoPian());
				if(iv!=null)
					ImageCacheManager.loadImage(TypeTotalCourseActivity.this, jxjh.getKeChengZhaoPian(), iv,ImageCacheManager.setDefaultImage(TypeTotalCourseActivity.this, R.drawable.course_default), ImageCacheManager.setErrorImage(TypeTotalCourseActivity.this, R.drawable.course_default));
			}
			if(i==1){
			    iv= (ImageView)view.findViewWithTag(jxjh.getKeChengZhaoPian());
			    if(iv!=null)
			    	ImageCacheManager.loadImage(TypeTotalCourseActivity.this, jxjh.getKeChengZhaoPian(), iv,ImageCacheManager.setDefaultImage(TypeTotalCourseActivity.this, R.drawable.course_default), ImageCacheManager.setErrorImage(TypeTotalCourseActivity.this, R.drawable.course_default));
			}
			
    }
 }
}
