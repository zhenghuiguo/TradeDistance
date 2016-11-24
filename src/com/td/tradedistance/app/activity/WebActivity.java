package com.td.tradedistance.app.activity;

import com.td.tradedistance.app.R;
import com.td.tradedistance.app.utils.Logger;
import com.td.tradedistance.app.widget.ProgressBarView;
import com.td.tradedistance.app.widget.TitleBar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class WebActivity extends BaseActivity {
	private TitleBar mTitleBar;
	private WebView mWb;
	private Intent it;
	private String url;
	private String title;
    private ProgressBarView pb;
    private boolean isBack;
    private ImageView back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_web);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initVariables() {
		it = getIntent();
		title = it.getStringExtra("title");
		url = it.getStringExtra("url");
		Logger.d("weburl", url);
	}

	@Override
	protected void initViews(Bundle savedInstanceState) {
		mTitleBar = (TitleBar) findViewById(R.id.titlebar);
		back = (ImageView) mTitleBar.getLeftImage();
		mWb = (WebView) findViewById(R.id.wb);
	}

	@Override
	protected void loadData() {
		mTitleBar.setTitle(title);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				back();
			}
		});
		 pb = (ProgressBarView)findViewById(R.id.wb_loading);
		WebSettings ws = mWb.getSettings();
		ws.setJavaScriptEnabled(true);
		mWb.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				isBack = true;
				view.loadUrl(url);// 根据传入的参数再去加载新的网页
				return true;// 表示当前WebView可以处理打开新网页的请求，不用借助系统浏览器
			}
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
			}
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				pb.getHide();
			}
		});
		if (!"".equals(url)) {
			mWb.loadUrl(url);
		} else {
			 pb.getHide();
		}
		mWb.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View view) {
				return true;
			}
		});
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			onBackPressed();
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public void onBackPressed() {
		back();
	}
	public void back(){
		if(isBack){
			mWb.goBack();
			isBack = false;
		}else{
			finish();
		}
	}
}
