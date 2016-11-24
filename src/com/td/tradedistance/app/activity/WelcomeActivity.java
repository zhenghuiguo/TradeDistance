package com.td.tradedistance.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

import com.td.tradedistance.app.R;
import com.td.tradedistance.app.utils.Logger;



public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Logger.d("WelcomeActivity", Runtime.getRuntime().totalMemory()+"");
		setContentView(R.layout.activity_welcome);
		ImageView welcome = (ImageView) findViewById(R.id.kaiji);
		//welcome.setImageBitmap(new ImageLoader().decodeSampledBitmapFromResources(getResources(),R.drawable.welcome,HSApp.getInstance().deviceInfo.getWidthPixels(),HcmApp.getInstance().deviceInfo.getHeightPixels()));
		AlphaAnimation alpha = new AlphaAnimation(0.2f, 1.0f);
		alpha.setDuration(3000);
		welcome.startAnimation(alpha);
		alpha.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				Intent it = null;
				/*int flag = UserInfo.getHelpGuide(WelcomeActivity.this);
				if(flag==0){
					 it = new Intent(WelcomeActivity.this,GuideActivity.class);
					 startActivity(it);
				}else{*/
					 it = new Intent(WelcomeActivity.this,MainActivity.class);
					 startActivity(it);
				//}
				finish();
			}
		});
		
	}
	@Override
	protected void onPause() {
		super.onPause();
	}
	@Override
	protected void onResume() {
		super.onResume();
	}
}
