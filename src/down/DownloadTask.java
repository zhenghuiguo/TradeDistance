package down;

import com.td.tradedistance.app.global.Global;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
/**
 * <p>功能描述: 异步下载类</p>
 * @author  <a href="mailto:zhenghuiguo@yahoo.com.cn">郑惠国</a>
 * @version $Revision: 1.1 $
 */
public class DownloadTask extends AsyncTask<String, Integer, LoadInfo>{
	private Context context = null;
	private ProgressBar pBar = null;
	private Downloader downloader=null; 
	private Handler mHandler = null;
	private View v=null;
	private TextView text = null;
	private String urlStr=null;
	private String KeChengMingCheng = null;
	private String Zhang= null;
	private String Jie = null;
	private String KeChengDaiMa = null;
	private String KeChengZhaoPian= null;
	private int pos;
	private boolean b;
	public DownloadTask(Context context,String KeChengDaiMa,String KeChengMingCheng,String Zhang,String Jie,String keChengZhaoPian, Handler mHandler,View v,ProgressBar pBar,int pos,boolean b){
		this.v=v;
		this.context = context;
		this.mHandler = mHandler;
		this.pBar = pBar;
		this.pos = pos;
		this.b = b;
		this.KeChengDaiMa = KeChengDaiMa;
		this.KeChengMingCheng = KeChengMingCheng;
		this.Zhang = Zhang;
		this.Jie = Jie;
		this.KeChengZhaoPian = keChengZhaoPian;
	}  
	@Override
	protected void onPreExecute() {
		//Logger.d("onPreExecute", "onPreExecute");
		super.onPreExecute();
	}
	@Override
	protected LoadInfo doInBackground(String... params) {
		//Logger.d("doInBackground", "doInBackground");
		text = (TextView) v;
		urlStr = params[0];
		String localfile=params[1];
		int threadcount=Integer.parseInt(params[2]);
		 // 初始化一个downloader下载器
         downloader = Globals.downloaders.get(urlStr);
         if (downloader == null) {
             downloader = new Downloader( KeChengDaiMa, KeChengMingCheng, Zhang, Jie,KeChengZhaoPian,urlStr, localfile, threadcount, context, mHandler,pos,text);
             Globals.downloaders.put(urlStr, downloader);
         }
         if (downloader.isdownloading()){
        	 return null;
         }else{
        	 QueueEntry entry = Globals.downParam.get(urlStr);
        	 if(entry!=null){
        		 entry.setState(downloader.getDownloading());
        	 }
         }
         // 得到下载信息类的个数组成集合
         return downloader.getDownloaderInfors(); 
	}
	@Override
	protected void onPostExecute(LoadInfo result) {
		//Logger.d("onPostExecute", "onPostExecute");
		if(result!=null){
			 // 显示进度条
			 showProgress(result, urlStr, v);
	         // 调用方法开始下载
			 if(b){
			    Globals.setBackGroudxxzx(text,Global.downing);//学习中心
			    downloader.setB(true);
			 }else{
				Globals.setBackGroudzlk(text,Global.downing);//资料库
				 downloader.setB(false);
			 }
	         downloader.download();
		}
		super.onPostExecute(result);
	}
	protected void showProgress(LoadInfo loadInfo, String url, View v) {
         ProgressBar bar = pBar;
         if (bar != null) {
        	// bar.setVisibility(View.VISIBLE);
        	 bar.setMax(loadInfo.getFileSize());
        	 bar.setProgress(loadInfo.getComplete());
             Globals.ProgressBars.put(url, bar);
         }
     }
}
