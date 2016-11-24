package com.td.tradedistance.app.widget;


import com.td.tradedistance.app.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class TabItem extends Tab{
    public void setTabLayout(RelativeLayout tabLayout) {
		this.tabLayout = tabLayout;
	}
	public void setTabImage(int tabImageId) {
		this.tabImage.setBackgroundResource(tabImageId);
	}
	public void setCountText(TextView countText) {
		this.countText = countText;
	}
	public void setNameTextColor(int color) {
		this.nameText.setTextColor(color);
	}
	protected RelativeLayout tabLayout;
    protected ImageView tabImage;
    protected TextView countText;
    protected TextView nameText;
	public TabItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}
	@Override
	public void init(Context context, AttributeSet attrs) {
		View view = LayoutInflater.from(context).inflate(R.layout.widget_tab_item, this);
		tabLayout = (RelativeLayout) view.findViewById(R.id.tab_layout);
		tabImage = (ImageView) view.findViewById(R.id.tab_image);
		nameText = (TextView) view.findViewById(R.id.name_text);
		//countText = (TextView) view.findViewById(R.id.count_text);
	}
	@Override
	public void parseStyle(Context context, AttributeSet attrs) {
		if(attrs!=null){
			TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TabItem);
			Drawable d =array.getDrawable(R.styleable.TabItem_tab_image_bg);
			if(d!=null)
				tabImage.setBackgroundDrawable(d);
			nameText.setText(array.getString(R.styleable.TabItem_name_text_string));
			nameText.setTextColor(array.getColor(R.styleable.TabItem_name_text_color, R.color.tv_tab_selected));
			//nameText.setTextSize(array.getDimensionPixelSize(R.styleable.TabItem_name_text_size, 10));
			/*Drawable c =array.getDrawable(R.styleable.TabItem_count_text_bg);
			if(c!=null)
				countText.setBackgroundDrawable(c);
			countText.setTextSize(array.getDimension(R.styleable.TabItem_count_text_size, 8));*/
		
		array.recycle();
		}
	}

}
