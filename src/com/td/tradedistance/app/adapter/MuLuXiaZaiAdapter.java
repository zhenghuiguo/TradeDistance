package com.td.tradedistance.app.adapter;


import java.util.List;

import com.td.tradedistance.app.ApiGlobal;
import com.td.tradedistance.app.R;
import com.td.tradedistance.app.TDApp;
import com.td.tradedistance.app.activity.VideoPlayerActivity;
import com.td.tradedistance.app.bean.CourseDetailsShuju;
import com.td.tradedistance.app.bean.JiaoXueJiHua;
import com.td.tradedistance.app.global.Global;
import com.td.tradedistance.app.localstorage.KeChengZhangJieDao;
import com.td.tradedistance.app.utils.FileOperationUtils;
import com.td.tradedistance.app.utils.NetUtils;
import com.td.tradedistance.app.utils.SDCardSizeUtil;
import com.td.tradedistance.app.utils.ToastUtil;

import down.Downloader;
import down.Globals;
import down.LoadInfo;
import down.QueueEntry;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MuLuXiaZaiAdapter extends BaseAdapter{
    private static final int REQUECT_CODE_SDCARD = 2;
	private Context mContext;
	private List<CourseDetailsShuju> list ;
	private JiaoXueJiHua jxjh;
	private String createPath;
    Resources rs;
	private Intent it;
	private KeChengZhangJieDao keChengZhangJieDao;
	/**
     * 利用消息处理机制适时更新进度条
     */
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
    	   String url = (String) msg.obj;
    	   Downloader loader = Globals.downloaders.get(url);
           int length = msg.arg1;
           int pos = msg.arg2;
           ProgressBar bar = Globals.ProgressBars.get(url);
            if (msg.what==1) {
                if (bar != null && loader!=null) {
                    // 设置进度条按读取的length长度更新
                    bar.incrementProgressBy(length);
                    if (bar.getProgress() == bar.getMax()) {
                    	Globals.setBackGroudxxzx(loader.getT(), Global.downed);
                        // 下载完成后清除进度条并将map中的数据清空
                    	bar.setVisibility(View.GONE);
                    	CourseDetailsShuju zhangjie = list.get(pos);
                    	Globals.delete(url,createPath+FileOperationUtils.getFileName(zhangjie.getShiPinDiZhi()));
                    	zhangjie.setTotalSize(String.valueOf(loader.getFileSize()));
                    	if(zhangjie!=null) zhangjie.setKeChengDaiMa(jxjh.getKeChengDaiMa());//由于服务端给的  教学计划列表的课程代码是大写字母 ，而课程信息接口给的课程代码是 小写字母 
                    	keChengZhangJieDao.SaveKeChengAndZhangJie(jxjh,zhangjie);
                    }
                }
            }else{
            	 if(msg.what == Global.no_file){
            		 if(loader != null)
            			 Globals.setBackGroudxxzx(loader.getT(), 1);
            		Globals.delInitData(url);
             		Toast.makeText(mContext, Global.noFile, Toast.LENGTH_LONG).show();
 	            	return;
            	 }
            	 if(msg.what == Global.error_sqm){
             		Globals.delInitData(url);
             		ToastUtil.failedTip(Global.error_sqm,mContext);
  	            	return;
             	 }
            	if(loader!=null && bar !=null){
            		if(!NetUtils.checkNet(TDApp.getContext())){
        				 Toast.makeText(TDApp.getContext(), Global.NoNetWorkTip, Toast.LENGTH_SHORT).show();
        				 loader.pause();
        				 //bar.setVisibility(View.VISIBLE);
      	        	     Globals.isDown = true;
      	        	     Globals.setBackGroudxxzx(loader.getT(), Global.stop);
        				return;
        			}else{
        				 Toast.makeText(TDApp.getContext(), Global.networdtip, Toast.LENGTH_SHORT).show();
        				 loader.pause();
        				 //bar.setVisibility(View.VISIBLE);
      	        	     Globals.isDown = true;
      	        	     Globals.setBackGroudxxzx(loader.getT(), Global.stop);
        				return;
        			}
            	}else if(loader!=null){
            		if(!NetUtils.checkNet(TDApp.getContext())){
	       				 Toast.makeText(TDApp.getContext(), Global.NoNetWorkTip, Toast.LENGTH_SHORT).show();
	       				 Globals.delInitData(url);
	     	        	 Globals.setBackGroudxxzx(loader.getT(), Global.down);
	     	        	 return;
	       			}else{
	       				 Toast.makeText(TDApp.getContext(), Global.networdtip, Toast.LENGTH_SHORT).show();
	       				  Globals.delInitData(url);
	     	        	  Globals.setBackGroudxxzx(loader.getT(), Global.down);
	       				return;
	       			}
            	}
            }
        }
    };
    public MuLuXiaZaiAdapter(Context context){
    	it = new Intent();
    	rs = context.getResources();
    	this.mContext = context;
    	this.createPath = FileOperationUtils.getMycoursePathAddYhwybs();
    	this.keChengZhangJieDao = new KeChengZhangJieDao();
    	
    }
	@Override
	public int getCount() {
		return list != null ? list.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
    public void setList(List<CourseDetailsShuju> list,JiaoXueJiHua jxjh){
    	this.list = list;
    	this.jxjh = jxjh;
    }
	@SuppressWarnings("null")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;
		if(convertView==null){
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.xiazai_item, null);
			viewHolder.rly = (RelativeLayout) convertView.findViewById(R.id.rly);
			viewHolder.zhang_lly = (LinearLayout) convertView.findViewById(R.id.zhang_lly);
			viewHolder.jie_lly = (LinearLayout) convertView.findViewById(R.id.jie_lly);
			viewHolder.zhang_tv = (TextView)convertView.findViewById(R.id.zhang_tv);
			viewHolder.jie_tv = (TextView)convertView.findViewById(R.id.jie_tv);
			viewHolder.xiazai_iv = (TextView)convertView.findViewById(R.id.xiazai_iv);
			viewHolder.progress_bar = (ProgressBar)convertView.findViewById(R.id.progress_bar);
			viewHolder.play_state = (ImageView)convertView.findViewById(R.id.play_state);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder)convertView.getTag();
		}
		viewHolder.progress_bar.setVisibility(View.GONE);
		visibleZhang(viewHolder);
		final CourseDetailsShuju shuju = list.get(position);
		//final String name;
		if(shuju!=null){
			
			if(shuju.getShiPinShiJian()==0){
				visibleZhang(viewHolder);
				viewHolder.rly.setBackgroundResource(R.color.white);
				viewHolder.zhang_tv.setText(shuju.getZhangJieMingCheng());
			}else{
				if(shuju.getPlay()) {
					viewHolder.play_state.setBackgroundResource(R.drawable.playred);
					viewHolder.jie_tv.setTextColor(rs.getColor(R.color.tv_tab_selected));
				}else{
					viewHolder.play_state.setBackgroundResource(R.drawable.playgray);
					viewHolder.jie_tv.setTextColor(rs.getColor(R.color.black));
				}
				//shuju.setUrl(ApiGlobal.getXiaZaiUrl(shuju));
				//shuju.setJie(shuju.getZhangJieMingCheng());
				viewHolder.rly.setBackgroundResource(R.color.coursedetails_bg);
				visibleJie(viewHolder);
				viewHolder.jie_tv.setText(shuju.getZhangJieMingCheng());
				
			   //name = FileOperationUtils.getFileName(shuju.getShiPinDiZhi());
			    
				dowm(shuju,shuju.getName(),viewHolder.xiazai_iv,viewHolder.progress_bar,position);
				
				if(keChengZhangJieDao.getKeChengZhangJieItem(shuju.getShiPinDiZhi())){
					 Globals.setBackGroudxxzx(viewHolder.xiazai_iv, Global.downed);
			    	 viewHolder.progress_bar.setVisibility(View.GONE);
			    	// viewHolder.xiazai_iv.setClickable(false);
				}
				 viewHolder.xiazai_iv.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							if(!keChengZhangJieDao.getKeChengZhangJieItem(shuju.getShiPinDiZhi())){
								download( shuju,shuju.getName(),v,viewHolder.progress_bar,position,false);
							}else{
								if(shuju.getShiPinShiJian()!=0){
									shuju.setPlay(true);
									viewHolder.play_state.setBackgroundResource(R.drawable.playred);
									viewHolder.jie_tv.setTextColor(rs.getColor(R.color.tv_tab_selected));
									Intent it = new Intent(mContext,VideoPlayerActivity.class);
									String zhangJieMingCheng = shuju.getZhangJieMingCheng();
									String path = shuju.getShiPinDiZhi();
									String url = FileOperationUtils.getMycoursePathAddYhwybs()+"/"+FileOperationUtils.getFileName(path);
									it.putExtra("path", path);
									it.putExtra("url", url);
									it.putExtra("zhangjieming", zhangJieMingCheng);
									mContext.startActivity(it);
								}
							}
						}
					});
			}
			
			
			
		}
		return convertView;
	}
	
	
	protected void showProgress1(TextView v,ProgressBar pBar,LoadInfo info,int pos,CourseDetailsShuju shuju,String localFile) {
		//pBar.setVisibility(View.VISIBLE);
		pBar.setMax(info.getFileSize());
		pBar.setProgress(info.getComplete());
		Globals.setBackGroudxxzx(v, Global.stop);
		Downloader downloader = new Downloader(jxjh.getKeChengDaiMa(),jxjh.getKeChengMingCheng(),shuju.getZhang(),shuju.getJie(),jxjh.getKeChengZhaoPian(),shuju.getUrl(), localFile, Integer.parseInt(ApiGlobal.threadCount), mContext, mHandler,pos,v);
		downloader.getDownloaderInfors();
		downloader.setState(Global.stop);
		Globals.downloaders.put(shuju.getUrl(),downloader);
    }
	protected void showProgress(String url,ProgressBar pBar,Downloader d) {
		LoadInfo loadInfo = d.getDownloaderInfors();
        ProgressBar bar = pBar;
        if (bar != null&&loadInfo != null) {
       	 //bar.setVisibility(View.VISIBLE);
       	 if(d.isdownloading()){//下载中
       		 Globals.setBackGroudxxzx(d.getT(),Global.downing);
       	 }else if(d.isdownPause()){//暂停
       		Globals.setBackGroudxxzx(d.getT(),Global.stop);
       	 }else if(d.isdownWait()){//等待中
       		Globals.setBackGroudxxzx(d.getT(),Global.wait);
       	 }
       	 bar.setMax(loadInfo.getFileSize());
       	 bar.setProgress(loadInfo.getComplete());
         Globals.ProgressBars.put(url, bar);
         if(d.isdownWait())  bar.setVisibility(View.GONE);
        }
    }
	public void dowm(CourseDetailsShuju shuju,String name,TextView v,ProgressBar pBar,int pos){
		Downloader d = Globals.downloaders.get(shuju.getUrl());
	 	if(d!=null){
	 		d.setT(v);
	 	 if (d.isdownloading()){
	 		showProgress(shuju.getUrl(),pBar,d);
	         // 调用方法开始下载
	         d.download();
	 	 }else if(d.isdownPause()){
	 		showProgress(shuju.getUrl(),pBar,d);
	 	 }else if(d.isdownWait()){
	 		showProgress(shuju.getUrl(),pBar,d);
	 	 }
	 	}else{
	 		String localFile = createPath+name;
	 		LoadInfo info = Globals.getInfo(mContext,shuju.getUrl(),localFile );
	 		if(info != null){
	 			showProgress1( v, pBar, info, pos, shuju, localFile);
	 		}else{
	 			if(keChengZhangJieDao.getKeChengZhangJieItem(shuju.getShiPinDiZhi())){
	 				pBar.setVisibility(View.GONE);
 			    	v.setBackgroundResource(Global.downedIcon);
	 			}else{
	 			     QueueEntry  entry = Globals.downParam.get(shuju.getUrl());
		 			 if(entry!=null){
			 			 entry.setV(v);
			 			 entry.setpBar(pBar);
						 if(entry!=null){//等待中
							 Globals.setBackGroudxxzx(entry.getV(),Global.wait);
							 pBar.setVisibility(View.GONE);
						 }
	 			     }else{
		 			    	pBar.setVisibility(View.GONE);
		 			    	v.setBackgroundResource(Global.downIcon);
		 			 }
	 			}
	 		}
	 	}
	}
	public void download(final CourseDetailsShuju shuju,final String name,final View v,final ProgressBar pBar,final int pos,boolean isPl){
		/*if(!SDCardSizeUtil.isAvaiableSpace(ApiGlobal.isAvaiableSpace)){
			Toast.makeText(mContext, Global.RemainingSpace, Toast.LENGTH_LONG).show();
			return;
		}*/
		if(!NetUtils.checkNet(TDApp.getContext())){
			Toast.makeText(TDApp.getContext(), Global.NoNetWorkTip, Toast.LENGTH_SHORT).show();
			return;
		}
		if(keChengZhangJieDao.getKeChengZhangJieItem(shuju.getShiPinDiZhi())){
			
			return;
		}
	
		if(name == null){
			Toast.makeText(mContext, Global.noFile, Toast.LENGTH_SHORT).show();
	    	return;
		}
		if(!FileOperationUtils.isSD()){
			Toast.makeText(mContext, Global.noSDTip, Toast.LENGTH_SHORT).show();
			return;
		}
		final Downloader d = Globals.downloaders.get(shuju.getUrl());
		/*if(isPl){
			startDown( shuju, name, v, pBar, pos, d );
			return;
		}*/
			if(NetUtils.isWifiConnected(mContext)){
				startDown( shuju, name, v, pBar, pos, d );
			}else{
				if(TDApp.manager.getWifiHuanJingXiaZaiShiPing()){
					
				   if(d==null){
						Dialog  dialog = new AlertDialog.Builder(mContext).
							setMessage(Global.w2G3GTip).setCancelable(false).
							setPositiveButton("下载", new DialogInterface.OnClickListener(){ 
			                     
								@Override 
			                    public void onClick(DialogInterface dialog, int which) {
									startDown( shuju, name, v, pBar, pos, d );
			                    } 
			                }).
			                setNegativeButton("取消", new DialogInterface.OnClickListener(){
	
								@Override
								public void onClick(DialogInterface dialog,int which) {
									dialog.cancel();	
								}
						
							}).
							create();
							dialog.show();
					}else{
						startDown( shuju, name, v, pBar, pos, d );
					}
				}else{
					ToastUtil.TipToast(rs.getString(R.string.sezhiwifixiazaishiping_str));
				}
			}
		
	
	}
	public void startDown(CourseDetailsShuju shuju,String name,View v,ProgressBar pBar,int pos,Downloader d ){
		//pBar.setVisibility(View.VISIBLE);
	    FileOperationUtils.MkDirs(createPath);
        String localFile = "";
	    localFile = createPath+name;
        if(d == null){
        	downIsNull( shuju, mContext, v, localFile, pBar, pos);
        }else{
        	downNOtNull( shuju, mContext, v, localFile, pBar, pos, d );
        }
	}
		public  void downIsNull(CourseDetailsShuju shuju,Context mcontext,View v,String localFile,ProgressBar pBar,int pos){
			TextView tv = (TextView)v;
	    	Globals.setBackGroudxxzx(tv, Global.wait);//wait
	    	QueueEntry entry = Globals.createQueueEntry(mcontext,jxjh.getKeChengDaiMa(),jxjh.getKeChengMingCheng(),shuju.getZhang(),shuju.getJie(),mHandler, tv,pBar,pos,shuju.getUrl(),localFile,true,Global.wait,jxjh.getKeChengZhaoPian());
	    	if(Globals.downParam.get(shuju.getUrl()) == null){
	    		Globals.downParam.put(shuju.getUrl(),entry);
	    		Globals.addTask(entry);
	    	}
		}
		public  void downNOtNull(CourseDetailsShuju shuju,Context mcontext,View v,String localFile,ProgressBar pBar,int pos,Downloader d ){
			d.setT((TextView)v); 
	        Globals.ProgressBars.put(shuju.getUrl(), pBar);
	        if(d.isdownloading()){//暂停
	     	   d.pause();
	     	   Globals.isDown = true;
	     	   Globals.setBackGroudxxzx(d.getT(),Global.stop);
	        }else{
	     	   if(d.isdownWait()){//等待中
	     		   Globals.setBackGroudxxzx(d.getT(),Global.wait);
	     	   }else{//下载中
		        	   d.download();
		        	   Globals.setBackGroudxxzx(d.getT(),Global.downing);
	     	   }
	        }
		}
	//显示章 
	public void visibleZhang(ViewHolder viewHolder){
		viewHolder.zhang_lly.setVisibility(View.VISIBLE);
		viewHolder.jie_lly.setVisibility(View.GONE);
		viewHolder.xiazai_iv.setVisibility(View.GONE);
	}
	//显示节
	public void visibleJie(ViewHolder viewHolder){
		viewHolder.zhang_lly.setVisibility(View.GONE);
		viewHolder.jie_lly.setVisibility(View.VISIBLE);
		viewHolder.xiazai_iv.setVisibility(View.VISIBLE);
	}
    static class ViewHolder{
    	RelativeLayout rly;
    	LinearLayout zhang_lly,jie_lly;
    	TextView xiazai_iv;
    	TextView zhang_tv,jie_tv;
    	ProgressBar progress_bar;
    	ImageView play_state;
    }
    
}
