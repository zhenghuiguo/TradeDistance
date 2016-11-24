package com.td.tradedistance.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public abstract class Tab extends FrameLayout {

	public Tab(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
		parseStyle(context, attrs);
	}
	public void init(Context context, AttributeSet attrs){};
	public void parseStyle(Context context, AttributeSet attrs){}
    
}
