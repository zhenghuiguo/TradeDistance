package com.td.tradedistance.app.widget;

import com.td.tradedistance.app.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TitleBar extends RelativeLayout {
	protected RelativeLayout leftLayout;
	protected ImageView leftImage;
	protected RelativeLayout rightLayout;
	protected TextView rightImage;
	public TextView getRightImage() {
		return rightImage;
	}

	public void setRightImage(TextView rightImage) {
		this.rightImage = rightImage;
	}
    public void setRightImageBg(int rightImageResid) {
		this.rightImage.setBackgroundResource(rightImageResid);
	}
	protected TextView titleView;
	protected RelativeLayout titleLayout;
    protected OnClickXiaoXiZhongXinListener xxzxListener;
    protected OnClickBianJiListener bianJiListener;
	public OnClickBianJiListener getBianJiListener() {
		return bianJiListener;
	}

	public void setBianJiListener(OnClickBianJiListener bianJiListener) {
		this.bianJiListener = bianJiListener;
	}

	public OnClickXiaoXiZhongXinListener getXxzxListener() {
		return xxzxListener;
	}

	public void setXxzxListener(OnClickXiaoXiZhongXinListener xxzxListener) {
		this.xxzxListener = xxzxListener;
	}

	public TitleBar(Context context, AttributeSet attrs, int defStyle) {
		this(context, attrs);
	}

	public TitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public TitleBar(Context context) {
		super(context);
		init(context, null);
	}

	private void init(final Context context, AttributeSet attrs) {
		LayoutInflater.from(context).inflate(R.layout.widget_title_bar,
				this);
		leftLayout = (RelativeLayout) findViewById(R.id.left_layout);
		leftImage = (ImageView) findViewById(R.id.left_image);
		rightLayout = (RelativeLayout) findViewById(R.id.right_layout);
		rightImage = (TextView) findViewById(R.id.right_image);
		titleView = (TextView) findViewById(R.id.title);
		titleLayout = (RelativeLayout) findViewById(R.id.root);

		parseStyle(context, attrs);
		
		leftImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((Activity)context).finish();
				
			}
		});
		rightImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(xxzxListener!=null){
					xxzxListener.onXiaoXiZhongXin();
				}
				if(bianJiListener!=null){
					bianJiListener.onBianJi();
				}
			}
		});
	}

	public ImageView getLeftImage() {
		return leftImage;
	}

	public void setLeftImage(ImageView leftImage) {
		this.leftImage = leftImage;
	}

	private void parseStyle(Context context, AttributeSet attrs) {
		if (attrs != null) {
			TypedArray ta = context.obtainStyledAttributes(attrs,
					R.styleable.TitleBar);
			String title = ta.getString(R.styleable.TitleBar_titleBarTitle);
			titleView.setText(title);

			Drawable leftDrawable = ta
					.getDrawable(R.styleable.TitleBar_titleBarLeftImage);
			if (null != leftDrawable) {
				leftImage.setImageDrawable(leftDrawable);
			}
			Drawable rightDrawable = ta
					.getDrawable(R.styleable.TitleBar_titleBarRightImage);
			if (null != rightDrawable) {
				rightImage.setBackgroundDrawable(rightDrawable);
			}

			Drawable background = ta
					.getDrawable(R.styleable.TitleBar_titleBarBackground);
			if (null != background) {
				titleLayout.setBackgroundDrawable(background);
			}

			ta.recycle();
		}
	}

	public void setLeftImageResource(int resId) {
		leftImage.setImageResource(resId);
	}

	public void setRightImageResource(int resId) {
		rightImage.setBackgroundResource(resId);
	}
	public void setRightText(String str) {
		rightImage.setText(str);
	}
	public void setLeftLayoutClickListener(OnClickListener listener) {
		leftLayout.setOnClickListener(listener);
	}

	public void setRightLayoutClickListener(OnClickListener listener) {
		rightLayout.setOnClickListener(listener);
	}

	public void setLeftLayoutVisibility(int visibility) {
		leftLayout.setVisibility(visibility);
	}

	public void setRightLayoutVisibility(int visibility) {
		rightLayout.setVisibility(visibility);
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}

	public void setBackgroundColor(int color) {
		titleLayout.setBackgroundColor(color);
	}

	public RelativeLayout getLeftLayout() {
		return leftLayout;
	}

	public RelativeLayout getRightLayout() {
		return rightLayout;
	}
}
