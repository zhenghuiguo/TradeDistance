package com.td.tradedistance.app.utils;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.widget.ImageView;

import com.td.tradedistance.app.R;


public class ProgressUtils extends Dialog {


	private static ProgressUtils mProgressDialog = null;
	public ProgressUtils(Context context) {
		super(context);
	
	}

	public ProgressUtils(Context context, int theme) {
		  super(context, theme);
	}
	public static ProgressUtils GetProgressUtils() {
		return mProgressDialog;
	}
	
	//
	public static ProgressUtils createDialog(Context context){
		mProgressDialog = new ProgressUtils(context,R.style.CustomProgressDialog);
		
		mProgressDialog.setContentView(R.layout.progressdialog);
		
		mProgressDialog.getWindow().getAttributes().gravity=Gravity.CENTER;		
		return mProgressDialog;
	}
	public static void showDialog(){
		//mProgressDialog.setMessage("载入中...");	
		mProgressDialog.show();
	}
	
	 public void onWindowFocusChanged(boolean hasFocus){
	    	//Logger.d("onWindowFocusChanged", hasFocus+"");
	    	if (mProgressDialog == null){
	    		return;
	    	}
	    	
	        ImageView imageView = (ImageView) mProgressDialog.findViewById(R.id.loadingImageView);
	        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
	        animationDrawable.start();
	    }
	 //setTitile 
	  public ProgressUtils setTitile(String strTitle){
	    	return mProgressDialog;
	    }
	/*  public ProgressUtils setMessage(String strMessage){
	    	TextView tvMsg = (TextView)mProgressDialog.findViewById(R.id.id_tv_loadingmsg);
	    	
	    	if (tvMsg != null){
	    		tvMsg.setText(strMessage);
	    	}
	    	
	    	return mProgressDialog;
	    }*/
	  public static void Dismiss(){
    	  if(mProgressDialog !=null){
    		  mProgressDialog.dismiss();
    	  }
      }
      public void destry(){
    	  if(mProgressDialog !=null){
    		  mProgressDialog.dismiss();
    		  mProgressDialog = null;
    	  }
      }
}
