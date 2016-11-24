package com.td.tradedistance.app.activity;

import java.text.NumberFormat;
import java.util.LinkedList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue.IdleHandler;
import android.text.TextUtils;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.td.tradedistance.app.R;
import com.td.tradedistance.app.TDApp;
import com.td.tradedistance.app.localstorage.KeChengZhangJieDao;
import com.td.tradedistance.app.utils.Logger;
import com.td.tradedistance.app.utils.ProgressUtils;
import com.td.tradedistance.app.widget.SoundView;
import com.td.tradedistance.app.widget.SoundView.OnVolumeChangedListener;
import com.td.tradedistance.app.widget.VideoView;
import com.td.tradedistance.app.widget.VideoView.MySizeChangeLinstener;




public class VideoPlayerActivity extends Activity {
	
	private final static String TAG = "VideoPlayerActivity";
	private final static String ACTION_SUOPing="IsSuoPing";
	private boolean isOnline = false; 
	private boolean isChangedVideo = false;
	private boolean isSuoPing = false;
	private KeChengZhangJieDao dao;
	public static LinkedList<MovieInfo> playList = new LinkedList<MovieInfo>();
	public class MovieInfo{
		String displayName;  
		String path;
	}
	private static int position ;
	private int playedTime;
	//private ScreenObserver mScreenObserver;
	
	private VideoView vv = null;
	private SeekBar seekBar = null;  
	private TextView durationTextView = null;
	private TextView playedTextView = null;
	private ImageView playerenlarge_iv;
	private GestureDetector mGestureDetector = null;
	private AudioManager mAudioManager = null;  
	private int maxVolume = 0;
	private int currentVolume = 0;  
	
	private ImageView bn3;
	//private ImageView bn5;
	/**
	 * 视频操作view
	 */
	private View controlView = null;
	private PopupWindow controler = null;
	
	private SoundView mSoundView = null;
	private PopupWindow mSoundWindow = null;
	
	private View extralView = null;
	private PopupWindow extralWindow = null;
	
	private static int screenWidth = 0;
	private static int screenHeight = 0;
	private static int controlHeight = 0;  
	
	private final static int TIME = 6868;  
	
	private boolean isControllerShow = true;
	private boolean isPaused = false;
	private boolean isFullScreen = false;
	private boolean isSilent = false;
	private boolean isSoundShow = false;
	private ProgressUtils proDailog;
	private String  url,path;
	private String  zhangjieming;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState); 
        
        setContentView(R.layout.videoview);
        Logger.d1(TAG, "onCreate");
       /* mScreenObserver = new ScreenObserver(this);
        mScreenObserver.requestScreenStateUpdate(new com.td.tradedistance.app.activity.ScreenObserver.ScreenStateListener() {
			
			@Override
			public void onScreenOn() {
				Logger.d("onScreenOn");
				isChangedVideo = true;
			}
			
			@Override
			public void onScreenOff() {
				Logger.d("onScreenOff");
				isChangedVideo = true;
			}
		});*/
		
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS); 
       // mFilter.addAction(Intent.ACTION_SCREEN_ON);  //开屏
       // mFilter.addAction(Intent.ACTION_SCREEN_OFF);//锁屏
       // mFilter.addAction(Intent.ACTION_USER_PRESENT);//解锁
        registerReceiver(mHomeKeyEventReceiver,mFilter); 
       
        dao = new KeChengZhangJieDao();
       
        proDailog = ProgressUtils.createDialog(VideoPlayerActivity.this);
        ProgressUtils.showDialog();
        
        Looper.myQueue().addIdleHandler(new IdleHandler(){

			@Override
			public boolean queueIdle() {
				
				if(controler != null && vv.isShown()){
					controler.showAtLocation(vv, Gravity.BOTTOM, 0, 0);
					controler.update(0, 0, screenWidth, 200);//controlHeight
				}
				
				if(extralWindow != null && vv.isShown()){
					extralWindow.showAtLocation(vv,Gravity.TOP,0, 0);
					extralWindow.update(0, 0, screenWidth, 100);
				}
				
				return false;  
			}
        });
        
        controlView = getLayoutInflater().inflate(R.layout.controler1, null);
        controler = new PopupWindow(controlView);
        durationTextView = (TextView) controlView.findViewById(R.id.duration);
        playedTextView = (TextView) controlView.findViewById(R.id.has_played);
        playerenlarge_iv = (ImageView)controlView.findViewById(R.id.playerenlarge_iv);
        playerenlarge_iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
        mSoundView = new SoundView(this);
        mSoundView.setOnVolumeChangeListener(new OnVolumeChangedListener(){

			@Override
			public void setYourVolume(int index) {
				 
				cancelDelayHide();
				updateVolume(index);
				hideControllerDelay();
			}
        });
        
        mSoundWindow = new PopupWindow(mSoundView);
        
        extralView = getLayoutInflater().inflate(R.layout.extral, null);
        extralWindow = new PopupWindow(extralView);
        
        ImageButton backButton = (ImageButton) extralView.findViewById(R.id.back);
        
        position = -1;
        
        backButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				finish();
			}
        	
        });
        
        bn3 = (ImageView) controlView.findViewById(R.id.button3);
        //bn5 = (ImageView) controlView.findViewById(R.id.button5);
        
        vv = (VideoView) findViewById(R.id.vv);
        
        vv.setOnErrorListener(new OnErrorListener(){

			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				
				vv.stopPlayback();
				isOnline = false;
				
				new AlertDialog.Builder(VideoPlayerActivity.this)
                .setTitle("对不起")
                .setMessage("您所播的视频格式不正确，播放已停止")
                .setPositiveButton("知道了",
                        new AlertDialog.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								
								vv.stopPlayback();
								proDailog.dismiss();
								finish();
							}
           
                        })
                .setCancelable(false)
                .show();
				return false;
			}
        	
        });
        
        Intent it = getIntent();
        path = it.getStringExtra("path");
        //url ="/sdcard/Movies/Honor.mp4";//
        url =it.getStringExtra("url");
        zhangjieming = it.getStringExtra("zhangjieming");
        if(url != null&&!"".equals(url)){
        	int progress = TDApp.manager.getPlayProgress(path+TDApp.manager.getNoDESYongHuWeiYiBiaoShi());//.getProgress(VideoPlayerActivity.this, zhangjieming);
        	playedTime =progress;
        	vv.setVideoPath(url);
        	bn3.setImageResource(R.drawable.pause);
        }else{
        	bn3.setImageResource(R.drawable.play1);
        	proDailog.dismiss();
        }
        
        TextView sp_name = (TextView) extralView.findViewById(R.id.sp_name);
        sp_name.setText(zhangjieming);

        vv.setMySizeChangeLinstener(new MySizeChangeLinstener(){

			@Override
			public void doMyThings() {
				setVideoScale(SCREEN_FULL);//注释
			}
        	
        });
              
        bn3.setAlpha(0xBB);
        
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
       // bn5.setAlpha(findAlphaFromSound());
        
        bn3.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				cancelDelayHide();
				if(isPaused){
					isChangedVideo = false;
					vv.start();
					savaData();
					bn3.setImageResource(R.drawable.pause);
					hideControllerDelay();
				}else{
					vv.pause();
					isChangedVideo = true;
					bn3.setImageResource(R.drawable.play1);
					savaData();
				}
				isPaused = !isPaused;
				
			}
        	
        });
        
        
        seekBar = (SeekBar) controlView.findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

				@Override
				public void onProgressChanged(SeekBar seekbar, int progress, boolean fromUser) {
					
					if(fromUser){
						
						if(!isOnline){
							vv.seekTo(progress);
						}
						
					}
					
				}
	
				@Override
				public void onStartTrackingTouch(SeekBar arg0) {
					myHandler.removeMessages(HIDE_CONTROLER);
				}
	
				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {
					myHandler.sendEmptyMessageDelayed(HIDE_CONTROLER, TIME);
				}
        	});
        
        getScreenSize();
       
        mGestureDetector = new GestureDetector(new SimpleOnGestureListener(){

			@Override
			public boolean onDoubleTap(MotionEvent e) {
				return super.onDoubleTap(e);
				//return true;
			}

			@Override
			public boolean onSingleTapConfirmed(MotionEvent e) {
				if(!isControllerShow){
					showController();
					hideControllerDelay();
				}else {
					cancelDelayHide();
					hideController();
				}
				return true;
			}

			@Override
			public void onLongPress(MotionEvent e) {
				if(isPaused){
					vv.start();
					bn3.setImageResource(R.drawable.pause);
					cancelDelayHide();
					hideControllerDelay();
				}else{
					vv.pause();
					bn3.setImageResource(R.drawable.play1);
					cancelDelayHide();
					showController();
				}
				isPaused = !isPaused;
			}	
        });
                
        vv.setOnPreparedListener(new OnPreparedListener(){

				@Override
				public void onPrepared(MediaPlayer arg0) {
					
					setVideoScale(SCREEN_FULL);
					isFullScreen = false; 
					if(isControllerShow){
						showController();  
					}
					
					int i = vv.getDuration();
					seekBar.setMax(i);
					i/=1000;
					int minute = i/60;
					int hour = minute/60;
					int second = i%60;
					minute %= 60;
					durationTextView.setText(String.format("%02d:%02d:%02d", hour,minute,second));
					
					proDailog.dismiss();
					vv.start();  
					bn3.setImageResource(R.drawable.pause);
					hideControllerDelay();
					myHandler.sendEmptyMessage(PROGRESS_CHANGED);
				}	
	        });
        
        vv.setOnCompletionListener(new OnCompletionListener(){

				@Override
				public void onCompletion(MediaPlayer arg0) {
					
					int n = playList.size();
					isOnline = false;
					playedTime = 0;
					
					if(++position < n){
						vv.setVideoPath(playList.get(position).path);
					}else{
						//vv.stopPlayback(zhangjieming);
						vv.stopPlayback(path+TDApp.manager.getNoDESYongHuWeiYiBiaoShi());
						pase();
					}
					finish();
				}
        	});
    }

	private final static int PROGRESS_CHANGED = 0;
    private final static int HIDE_CONTROLER = 1;
    
    Handler myHandler = new Handler(){
    
		@Override
		public void handleMessage(Message msg) {
			
			switch(msg.what){
			
				case PROGRESS_CHANGED:
					
					int i = vv.getCurrentPosition();
					seekBar.setProgress(i);
					
					if(isOnline){
						int j = vv.getBufferPercentage();
						seekBar.setSecondaryProgress(j * seekBar.getMax() / 100);
					}else{
						seekBar.setSecondaryProgress(0);
					}
					
					i/=1000;
					int minute = i/60;
					int hour = minute/60;
					int second = i%60;
					minute %= 60;
					playedTextView.setText(String.format("%02d:%02d:%02d", hour,minute,second));
					sendEmptyMessageDelayed(PROGRESS_CHANGED, 100);
					break;
					
				case HIDE_CONTROLER:
					hideController();
					break;
			}
			
			super.handleMessage(msg);
		}	
    };

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		boolean result = mGestureDetector.onTouchEvent(event);
		
		if(!result){
			if(event.getAction()==MotionEvent.ACTION_UP){
				
			}
			result = super.onTouchEvent(event);
		}
		
		return result;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		/*if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}*/
		Logger.d1(TAG, "onConfigurationChanged");
		
		getScreenSize();
		if(isControllerShow){
			Logger.d(TAG, "isControllerShow");
			cancelDelayHide();
			hideController();
			showController();
			hideControllerDelay();
		}
		
		super.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onPause() {
		Logger.d1(TAG, "onPause");
		playedTime = vv.getCurrentPosition();
		savaData();
		vv.pause();
		bn3.setImageResource(R.drawable.play1);
		super.onPause();   
	}
	private String getGuanKanZhi(int playedTime,int totalTime){
		String gkjdz = "";
		if(totalTime>=0&&playedTime>0){
			 NumberFormat numberFormat = NumberFormat.getInstance();  
		        // 设置精确到小数点后2位  
		     numberFormat.setMaximumFractionDigits(2);  
		      gkjdz = numberFormat.format((float) playedTime / (float) totalTime * 100);  
		      float value = Float.parseFloat(gkjdz);
		     gkjdz = String.valueOf(Math.round(value));
		}
			
		return gkjdz+"%";
	}

	@Override
	protected void onResume() {
		Logger.d1(TAG, "onResume");
		vv.seekTo(playedTime);
		if(!isChangedVideo){
			vv.start();  
		}else{
			vv.start(); 
			vv.pause();
		}
		isChangedVideo = false;
		if(vv.isPlaying()){
			bn3.setImageResource(R.drawable.pause);
			hideControllerDelay();
		}
		//Logger.d("REQUEST", "NEW AD !");
		/*if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}*/
		
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		Logger.d1(TAG, "onDestroy");
		clear();
		//停止监听screen状态
		//mScreenObserver.stopScreenStateUpdate();
		super.onDestroy();
	}     
    private void clear(){
    	//FileOperationUtils.delFile(url);
    	if(controler.isShowing()){
			controler.dismiss();
			extralWindow.dismiss();
		}
		if(mSoundWindow.isShowing()){
			mSoundWindow.dismiss();
		}
		
		myHandler.removeMessages(PROGRESS_CHANGED);
		myHandler.removeMessages(HIDE_CONTROLER);
		
		if(vv.isPlaying()){
			vv.stopPlayback();
		}
		unregisterReceiver(mHomeKeyEventReceiver);
	
		playList.clear();
    }
	private void getScreenSize()
	{
		Display display = getWindowManager().getDefaultDisplay();
        screenHeight = display.getHeight();
        screenWidth = display.getWidth();
        controlHeight = screenHeight/4;
	}
	
	private void hideController(){
		if(controler.isShowing()){
			controler.update(0,0,0, 0);
			extralWindow.update(0,0,screenWidth,0);
			isControllerShow = false;
		}
		if(mSoundWindow.isShowing()){
			mSoundWindow.dismiss();
			isSoundShow = false;
		}
	}
	
	private void hideControllerDelay(){
		myHandler.sendEmptyMessageDelayed(HIDE_CONTROLER, TIME);
	}
	
	private void showController(){
		controler.update(0,0,screenWidth, 200);
		if(isFullScreen){
			extralWindow.update(0,0,screenWidth, 100);
		}else{
			extralWindow.update(0,0,screenWidth, 100);
		}
		
		isControllerShow = true;
	}
	
	private void cancelDelayHide(){
		myHandler.removeMessages(HIDE_CONTROLER);
	}

    private final static int SCREEN_FULL = 0;
    private final static int SCREEN_DEFAULT = 1;
    
    private void setVideoScale(int flag){
    	
    	
    	switch(flag){
    		case SCREEN_FULL:
    			
    			//Logger.d(TAG, "screenWidth: "+screenWidth+" screenHeight: "+screenHeight);
    			vv.setVideoScale(screenWidth, screenHeight);
    			getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    			
    			break;
    			
    		case SCREEN_DEFAULT:
    			
    			int videoWidth = vv.getVideoWidth();
    			int videoHeight = vv.getVideoHeight();
    			int mWidth = screenWidth;
    			int mHeight = screenHeight - 25;
    			
    			if (videoWidth > 0 && videoHeight > 0) {
    	            if ( videoWidth * mHeight  > mWidth * videoHeight ) {
    	            	mHeight = mWidth * videoHeight / videoWidth;
    	            } else if ( videoWidth * mHeight  < mWidth * videoHeight ) {
    	            	mWidth = mHeight * videoWidth / videoHeight;
    	            } else {
    	                
    	            }
    	        }
    			
    			vv.setVideoScale(mWidth, mHeight);

    			getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    			
    			break;
    	}
    }

    private int findAlphaFromSound(){
    	if(mAudioManager!=null){
    		int alpha = currentVolume * (0xCC-0x55) / maxVolume + 0x55;
    		return alpha;
    	}else{
    		return 0xCC;
    	}
    }

    private void updateVolume(int index){
    	if(mAudioManager!=null){
    		if(isSilent){
    			mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
    		}else{
    			mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, index, 0);
    		}
    		currentVolume = index;
    		//bn5.setAlpha(findAlphaFromSound());
    	}
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
        	back();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    private void pase(){
    	 int progress = vv.getCurrentPosition();
    	 vv.stopPlayback();
    	 if(path.length()>0&&progress>0)
    		 TDApp.manager.setPlayProgress(path+TDApp.manager.getNoDESYongHuWeiYiBiaoShi(), progress);
    	 if(path.length()>0&&url.length()>=0)
    		 if(!path.equals(url))
    			 dao.updateZhangJie(getGuanKanZhi(progress,vv.getDuration()), path);
	 }
    private void back(){
    	finish();
	 }
    @Override
    protected void onStart() {
    	Logger.d1(TAG, "onStart");
    	super.onStart();
    	//Logger.d("onStart", "onStart");
    }
    @Override
    protected void onRestart() {
    	Logger.d1(TAG, "onRestart");
    	// TODO Auto-generated method stub
    	super.onRestart();
    	//Logger.d("onRestart", "onRestart");
    }
    @Override
    protected void onStop() {
    	Logger.d1(TAG, "onStop");
    	super.onStop();
    }
      
    
    
    /** 
     * 监听是否点击了home键将客户端推到后台 
     */  
    private BroadcastReceiver mHomeKeyEventReceiver = new BroadcastReceiver() {  
        String SYSTEM_REASON = "reason";  
        String SYSTEM_HOME_KEY = "homekey";  
        String SYSTEM_HOME_KEY_LONG = "recentapps";  
           
        @Override  
        public void onReceive(Context context, Intent intent) {  
            String action = intent.getAction();  
            if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {  
                String reason = intent.getStringExtra(SYSTEM_REASON);  
                if (TextUtils.equals(reason, SYSTEM_HOME_KEY)) {  
                     //表示按了home键,程序到了后台  
                    //Toast.makeText(getApplicationContext(), "home", 1).show();  
                	savaData();
                }else if(TextUtils.equals(reason, SYSTEM_HOME_KEY_LONG)){  
                    //表示长按home键,显示最近使用的程序列表  
                }  
            }/*else if(action.equals(Intent.ACTION_SCREEN_ON)){ 
            	Toast.makeText(getApplicationContext(), "ACTION_SCREEN_ON", 1).show();  
            	isChangedVideo = false;
            }else if(action.equals(Intent.ACTION_SCREEN_OFF)){ 
            	Toast.makeText(getApplicationContext(), "ACTION_SCREEN_OFF", 1).show();  
            	isChangedVideo = true;
            }  else{// 解锁
                //TODO
            }*/
        }  
    };  
    
    public void savaData(){
    	if(path.length()>0&&playedTime>0)
			TDApp.manager.setPlayProgress(path+TDApp.manager.getNoDESYongHuWeiYiBiaoShi(), playedTime);
		if(path.length()>0&&url.length()>=0){
			if(!path.equals(url))
				dao.updateZhangJie(getGuanKanZhi(playedTime,vv.getDuration()), path);
		}
    }
}