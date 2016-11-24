package com.td.tradedistance.app.widget;

import com.td.tradedistance.app.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyTabView extends Tab {
	public ImageView getIv() {
		return iv;
	}

	public void setIv(Drawable d) {
		this.iv.setBackgroundDrawable(d);
	}

	public TextView getTv() {
		return tv;
	}

	public void setTv(String str) {
		this.tv.setText(str);
	}

	private ImageView iv;
	private TextView tv;

	public MyTabView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void init(Context context, AttributeSet attrs) {
		View view = LayoutInflater.from(context).inflate(
				R.layout.widget_my_tab, this);
		iv = (ImageView) view.findViewById(R.id.iv);
		tv = (TextView) view.findViewById(R.id.tv);

	}

	@Override
	public void parseStyle(Context context, AttributeSet attrs) {
		if (attrs != null) {
			TypedArray ta = context.obtainStyledAttributes(attrs,
					R.styleable.MyTabView);
			Drawable d = ta.getDrawable(R.styleable.MyTabView_myTabViewImage);
			if (d != null)
				iv.setBackgroundDrawable(d);
			String str = ta.getString(R.styleable.MyTabView_textstr);
			if (str != null)
				tv.setText(str);
			ta.recycle();
		}
	}
}
