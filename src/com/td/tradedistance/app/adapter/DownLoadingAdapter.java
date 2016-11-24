package com.td.tradedistance.app.adapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.td.tradedistance.app.R;
import com.td.tradedistance.app.bean.CourseDetailsShuju;
import com.td.tradedistance.app.bean.DownLoading;
import com.td.tradedistance.app.global.Global;
import com.td.tradedistance.app.localstorage.DownLoad_Info_Dao;
import com.td.tradedistance.app.utils.CommonUtil;
import com.td.tradedistance.app.utils.Logger;
import com.td.tradedistance.app.utils.SDCardSizeUtil;

import down.Downloader;
import down.GlobalDownLoading1;
import down.Globals;
import down.GlobalsListDownLoading;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import api.ImageCacheManager;

public class DownLoadingAdapter extends BaseAdapter {
	public int getBianJiInt() {
		return bianJiInt;
	}

	public void setBianJiInt(int bianJiInt) {
		this.bianJiInt = bianJiInt;
	}

	private ListView listView;
	@SuppressWarnings("unused")
	private RelativeLayout rl;
	private GlobalsListDownLoading downloadManager;
	private boolean mBusy;
    private int isXiaZai =0;
	private int bianJiInt= 0;
	public boolean ismBusy() {
		return mBusy;
	}
  
	private Handler mHandler = new Handler() {

		public void handleMessage(Message msg) {
			Downloader loader = (Downloader) msg.obj;
			if (loader != null) {
				updateView(loader,loader.getPosition());
			}
		}
	};

	private Context mContext;
	private SparseArray<DownLoading> dataList = null;
	private List<Downloader> list;
	private  DownLoad_Info_Dao downDao;

	@SuppressWarnings("static-access")
	public DownLoadingAdapter(Context context,GlobalsListDownLoading downloadManager) {
		this.mContext = context;
		getDownLoaderList();
		this.downloadManager= downloadManager;
		this.downloadManager.setH(mHandler);
		this.downDao = new DownLoad_Info_Dao();
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void setList(SparseArray<DownLoading> dataList) {
		this.dataList = dataList;
	}
	public void setBianjiInt(int bianJiInt) {
		this.bianJiInt = bianJiInt;
	}
	public void setListView(ListView view) {
		this.listView = view;
	}
	public void setIsXiaZai(int isXiaZai) {
		this.isXiaZai = isXiaZai;
	}
	public void getDownLoaderList() {
		if (Globals.downloaders.size() > 0) {
			@SuppressWarnings("rawtypes")
			Set set = Globals.downloaders.entrySet();
			@SuppressWarnings("rawtypes")
			Iterator iterator = set.iterator();
			list = new ArrayList<Downloader>();
			while (iterator.hasNext()) {
				@SuppressWarnings("rawtypes")
				Map.Entry mapentry = (Map.Entry) iterator.next();
				Downloader loader = (Downloader) mapentry.getValue();
				list.add(loader);
			}
		} else {
			list = null;
		}
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;
		Logger.d("getView"+position);
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.downloading_item, null);
			viewHolder.cource_iv = (ImageView) convertView
					.findViewById(R.id.cource_iv);
			viewHolder.kecheng_tv = (TextView) convertView
					.findViewById(R.id.kecheng_tv);
			viewHolder.state_tv = (TextView) convertView
					.findViewById(R.id.state_tv);
			viewHolder.daxiao_tv = (TextView) convertView
					.findViewById(R.id.daxiao1_tv);
			viewHolder.xiazai_iv = (TextView) convertView
					.findViewById(R.id.xiazai_iv);
			viewHolder.progress_bar = (ProgressBar) convertView
					.findViewById(R.id.progress_bar1);
			viewHolder.rl = (RelativeLayout) convertView
					.findViewById(R.id.rly_img);
			viewHolder.del_tv = (TextView)convertView.findViewById(R.id.del_tv);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		final Downloader loader = list.get(position);
		loader.setPosition(position);
		viewHolder.progress_bar.setMax(0);
		viewHolder.progress_bar.setProgress(0);
        
		if (loader != null) {
			
			
			viewHolder.del_tv.setVisibility(View.GONE);
			switch (bianJiInt) {
			case 0:
				viewHolder.del_tv.setVisibility(View.GONE);
				break;
			case 1:
				viewHolder.del_tv.setVisibility(View.VISIBLE);
				viewHolder.del_tv.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Dialog  dialog = new AlertDialog.Builder(mContext).
								setMessage("是否删除"+CommonUtil.getKeChengText(loader.getKeChengMingCheng(), loader.getZhang(), loader.getJie())).setCancelable(false).
								setPositiveButton("删除", new DialogInterface.OnClickListener(){ 
				                     
									@Override 
				                    public void onClick(DialogInterface dialog, int which) {
										if (loader.isdownloading()) {
											Globals.setBackGroudxxzx(viewHolder.xiazai_iv,
													Global.stop);
											loader.setState(Global.stop);
										}
										downloadManager.stopTaskAndRemove(loader.getUrlstr());
										Globals.delete(loader.getUrlstr(), loader.getLocalfile());
										GlobalDownLoading1.getInstance().stopTaskAndRemove(loader.getUrlstr());
										GlobalDownLoading1.getInstance().setDown(true);
										downDao.deleteDownInfo(loader.getLocalfile());
										list.remove(position);
										notifyDataSetChanged();
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
					}
				});
				break;
			}
			//viewHolder.progress_bar.setTag(loader.getUrlstr()); // 将要下载的url设置为progressbar的TAG
			//viewHolder.daxiao_tv.setTag(loader.getUrlstr()+ position);
			ImageCacheManager.loadImage(mContext, loader
					.getZhaoPain(), viewHolder.cource_iv, ImageCacheManager
					.setDefaultImage(mContext, R.drawable.course_default),
					ImageCacheManager.setErrorImage(mContext,
							R.drawable.course_default));
			viewHolder.kecheng_tv.setText(CommonUtil.getKeChengText(loader
					.getKeChengMingCheng(), loader
					.getZhang(), loader.getJie()));
			viewHolder.progress_bar
			.setMax(loader.getFileSize());
			viewHolder.progress_bar.setProgress(loader.getCompelete_Size());
			
			switch (loader.getState()) {
			case 2:
				viewHolder.state_tv.setText("下载中");
				down(loader, viewHolder.daxiao_tv, viewHolder.progress_bar,
						position);
				break;
			case 3:
				if(isXiaZai== 1) {
					loader.download();
					if(downloadManager.getTaskList().get(loader.getUrlstr())!=null){
						downloadManager.getTaskList().remove(loader.getUrlstr());
					}
					viewHolder.state_tv.setText("下载中");
					downloadManager.startDownload(loader);
				}else{
					viewHolder.state_tv.setText("下载暂停");
					viewHolder.progress_bar
							.setMax(loader.getFileSize());
					viewHolder.progress_bar.setProgress(loader
							.getCompelete_Size());
					Globals.setBackGroudxxzx(viewHolder.xiazai_iv, Global.stop);
				}
				break;
			}

			viewHolder.daxiao_tv.setText(SDCardSizeUtil.getFileSize(loader
					.getCompelete_Size())
					+ "/"
					+ SDCardSizeUtil.getFileSize(loader
							.getFileSize()));

			if (loader.isdownloading()) {
				Globals.setBackGroudxxzx(viewHolder.xiazai_iv, Global.downing);
			} else if (loader.isdownPause()) {
				Globals.setBackGroudxxzx(viewHolder.xiazai_iv, Global.stop);
			}
			viewHolder.rl.setOnClickListener(new OnClickListener() {

				@SuppressWarnings({ "static-access", "static-access" })
				@Override
				public void onClick(View v) {
					if (loader.isdownloading()) {
						Globals.setBackGroudxxzx(viewHolder.xiazai_iv,
								Global.stop);
						loader.setState(Global.stop);
						downloadManager.stopTask(loader.getUrlstr());
						viewHolder.state_tv.setText("下载暂停");
					} else if (loader.isdownPause()) {
						Globals.setBackGroudxxzx(viewHolder.xiazai_iv,
								Global.downing);
						viewHolder.state_tv.setText("下载中");
						loader.download();
						if(downloadManager.getTaskList().get(loader.getUrlstr())!=null){
							downloadManager.getTaskList().remove(loader.getUrlstr());
						}
						downloadManager.startDownload(loader);
					}
				}
			});
		}
		return convertView;
	}

	public void setmBusy(boolean mBusy) {
		this.mBusy = mBusy;
	}

	public void down(final Downloader loader, final TextView v,
			final ProgressBar pBar, int pos) {
		loader.setProgressBar(pBar);
		loader.setDaxiao(v);
		if(downloadManager.getTaskList().get(loader.getUrlstr())==null){
			downloadManager.startDownload(loader);
		}
	}

	class ViewHolder {
		RelativeLayout rl;
		ProgressBar progress_bar;
		ImageView cource_iv;
		TextView kecheng_tv, zhang_tv, jie_tv, state_tv, daxiao_tv, xiazai_iv,del_tv;
	}

	// 更新指定item的数据
	@SuppressWarnings("static-access")
	public void updateView(Downloader loader,int index) {
		int visiblePos = listView.getFirstVisiblePosition();
		int offset = index - visiblePos;
		//Log.e("", "index="+index+"visiblePos="+visiblePos+"offset="+offset);
		// 只有在可见区域才更新
		if(offset < 0) return;
		
		View view = listView.getChildAt(offset);
		if(view ==null) return;
		Downloader d = null;
		try {
			d = list.get(index);
			if(d==null) return;
			ViewHolder holder = (ViewHolder)view.getTag();
			if(holder == null) return;
			Logger.d(offset+"===========");
			
			holder.daxiao_tv.setText(SDCardSizeUtil.getFileSize(d
					.getCompelete_Size())
					+ "/"
					+ SDCardSizeUtil.getFileSize(d.getFileSize()));
			holder.progress_bar
					.setProgress(d.getCompelete_Size());
			if (holder.progress_bar.getProgress() == holder.progress_bar
					.getMax()) {
				downloadManager.stopTask(loader.getUrlstr());
				getDownLoaderList();
				notifyDataSetChanged();
			}
		} catch (Exception e) {
			getDownLoaderList();
			notifyDataSetChanged();
		}
		
		
	}
}
