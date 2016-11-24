/*package com.td.tradedistance.app.activity;

import com.td.tradedistance.app.R;
import com.td.tradedistance.app.adapter.TestAdapter;
import com.td.tradedistance.app.bean.DownLoading;
import com.td.tradedistance.app.utils.Logger;


import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public class TestListActivity extends Activity implements OnScrollListener{
	private ListView list;
	*//**
	 * 记录是否刚打开程序，用于解决进入程序不滚动屏幕，不会下载图片的问题。
	 *//*
	private boolean isInit = false;
	*//**
	 * 第一张可见图片的下标
	 *//*
	int _start_index;
	int _end_index;
	TestAdapter1 adapter;
	private SparseArray<DownLoading> appList = new SparseArray<DownLoading>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testlist);
		initData();
		initUI();
	}
	

	
	

	private void initData()
	{
		for(int i =0; i<20; i++)
		{
			DownLoading app = new DownLoading();
		    app.setDaxiao(i+"M/"+i+"M");
		    app.setUrl("egoieyogieogh"+i);
		    app.setKechengming("test"+i);
		    appList.put(i, app);
		}
	}

	private void initUI()
	{
		list = (ListView)this.findViewById(R.id.list);
	    adapter = new TestAdapter1(this);
		adapter.setListView(list);
		adapter.setList(appList);
		list.setAdapter(adapter);
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
	private void pageImgLoad(int start_index, int end_index, AbsListView view) {
		if (appList != null) {
			for (; start_index < end_index; start_index++) {
				DownLoading loader = appList.get(start_index);
				adapter.updateView(loader, loader.getPos());
				ImageView iv = (ImageView)view.findViewWithTag(sr.getUrl());//sr.getImg();

				String url = sr.getTuPian();
				Logger.d("url"+start_index, "url="+url);
				if (iv != null) {
					Logger.d("iv", "iv=notnull");
					ImageCacheManager.loadImage(getApplicationContext(), url, iv,ImageCacheManager.setDefaultImage(FengHouLangYanActivity.this, R.drawable.picture),ImageCacheManager.setErrorImage(FengHouLangYanActivity.this, R.drawable.picture), Global.tupian_width, Global.tupian_heidth);
				} else {
					Logger.d("iv", "iv=null");
				}
			}
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
}
*/