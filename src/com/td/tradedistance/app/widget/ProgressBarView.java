package com.td.tradedistance.app.widget;


import com.td.tradedistance.app.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


public class ProgressBarView extends LinearLayout{
    private ProgressBar pb;
	public void getHide() {
		 pb.setVisibility(View.INVISIBLE);
	}
	public void getShow() {
		pb.setVisibility(View.VISIBLE);
	}
	public ProgressBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		View view = inflate(context, R.layout.widget_progress_bar, null);
		this.addView(view);
		pb = (ProgressBar) view.findViewById(R.id.loading);
	}

}
