package down;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

import com.td.tradedistance.app.ApiGlobal;
import com.td.tradedistance.app.global.Global;
import com.td.tradedistance.app.localstorage.DownLoad_Info_Dao;

import android.content.Context;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Globals {
	private static final Queue<QueueEntry> TASK_QUEUE = new LinkedList<QueueEntry>();
	public static boolean isDown = true;
	static {
		// 初始化创建线程,并等待处理
		new Thread() {
			{
				setDaemon(true);
			}
			public void run() {
				while (true) {
					synchronized (TASK_QUEUE) {
						if (TASK_QUEUE.isEmpty()) {
							try {
								TASK_QUEUE.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					//Logger.d("thread", "thread");
					//if(isDown){
						 QueueEntry entry = TASK_QUEUE.poll();
						 if(entry != null){
								 DownloadTask downloadTask=new DownloadTask(entry.getContext(),entry.getKeChengDaiMa(),entry.getKeChengMingCheng(),entry.getZhang(),entry.getJie(),entry.getZhaoPian(),entry.getmHandler(),entry.getV(),entry.getpBar(),entry.getPos(),entry.getB());
							     downloadTask.execute(entry.getUrlstr(),entry.getLocalFile(),entry.getThreadcount());
							     isDown = false;
						 }
					//}
				}
			}
		}.start();

	}
	public static void addTask(QueueEntry entry) {
		synchronized (TASK_QUEUE) {
			if (!TASK_QUEUE.contains(entry)) {
				TASK_QUEUE.add(entry);
				TASK_QUEUE.notify();
			}
		}
	}
	public static void delTask(QueueEntry entry){
		synchronized (TASK_QUEUE) {
			if (TASK_QUEUE.contains(entry)) {
				TASK_QUEUE.remove(entry);
			}
		}
	}
  public static Map<String, Downloader> downloaders = new HashMap<String, Downloader>();
  public static Map<String, ProgressBar> ProgressBars = new HashMap<String, ProgressBar>();
  public static Map<String, QueueEntry> downParam = new HashMap<String, QueueEntry>();
	  
  public static void setBackGroudxxzx(TextView t,int state){
	  if(t == null) return;
	  if(state == 0){
		  t.setBackgroundResource(Global.waitIcon);
		  //t.setText(waitStr);
	  }
	  if(state == 1){
		  t.setBackgroundResource(Global.downIcon);
		 //t.setText(downStr);
	  }
	  if(state == 2){
		  t.setBackgroundResource(Global.downingIcon);
		 // t.setText(downingStr);
	  }
	  if(state == 3){
		  t.setBackgroundResource(Global.stopIcon);
		  //t.setText(stopStr);
	  }
	  if(state == 4){
		  t.setBackgroundResource(Global.downedIcon);
		  //t.setText(downedStr);
	  }
  }
  
  public static void setBackGroudzlk(TextView t,int state){
	  /*if(t == null) return;
	  if(state == 0){
		  t.setBackgroundResource(Global.waitIconzlk);
		  //t.setText(waitStr);
	  }
	  if(state == 1){
		  t.setBackgroundResource(Global.downIconzlk);
		 //t.setText(downStr);
	  }
	  if(state == 2){
		  t.setBackgroundResource(Global.downingIconzlk);
		 // t.setText(downingStr);
	  }
	  if(state == 3){
		  t.setBackgroundResource(Global.stopIconzlk);
		  //t.setText(stopStr);
	  }
	  if(state == 4){
		 // t.setBackgroundResource(downedIconzlk);
		  //t.setText(downedStr);
	  }*/
  }
  public static void delete(String url,String localUrl){
	  isDown = true;
 	  ProgressBars.remove(url);
      downloaders.get(url).delete(localUrl);
      downloaders.get(url).reset();
      downloaders.remove(url);
      delTask(Globals.downParam.get(url));
      downParam.remove(url);
 }
  public static void delInitData(String url){
	  isDown = true;
	  downloaders.remove(url);
	  delTask(Globals.downParam.get(url));
      downParam.remove(url);
  }
  public static void deleteAll(){
	  if(ProgressBars!=null)
		  ProgressBars.clear();
	  if(downloaders!=null){
		  Iterator<?> it = downloaders.entrySet().iterator();
		  while(it.hasNext()){
			  Entry<?, ?> entry = (Entry<?, ?>)it.next();
			  Downloader loader = (Downloader) entry.getValue();
			  loader.setState(Global.stop);
		  }
		  downloaders.clear();
		  }
	  if(TASK_QUEUE!=null)
		  TASK_QUEUE.clear();
	  if(downParam!=null)
		  downParam.clear();
      isDown = false;
 }
 public static LoadInfo getInfo (Context context,String urlstr,String localUrl){
	 List<DownloadInfo> infos = new DownLoad_Info_Dao().getInfos(urlstr,localUrl);
	 if(infos.size()<=0) return null;
     //Logger.d("TAG", "not isFirst size=" + infos.size());
     int size = 0;
     int compeleteSize = 0;
     String keChengMingCheng=null,Zhang=null,Jie=null,ZhaoPian=null;
     int state=0;
     
     for (DownloadInfo info : infos) {
         compeleteSize += info.getCompeleteSize();
         size += info.getEndPos() - info.getStartPos() + 1;
         keChengMingCheng = info.getKeChengMingCheng();
         Zhang = info.getZhang();
         Jie = info.getJie();
         ZhaoPian = info.getZhaoPain();
     }
     return new LoadInfo(size,compeleteSize,urlstr,keChengMingCheng,Zhang,Jie,ZhaoPian);
 }
 
 public static QueueEntry createQueueEntry(Context mcontext,String KeChengDaiMa,String KeChengMingCheng,String Zhang,String Jie,Handler mHandler,TextView v,ProgressBar pBar,int pos,String urlstr,String localFile,boolean b,int state,String KeChengZhaoPian){
 	QueueEntry entry = new QueueEntry();
 	entry.setContext(mcontext);
 	entry.setmHandler(mHandler);
 	entry.setV((TextView)v);
 	entry.setpBar(pBar);
 	entry.setPos(pos);
 	entry.setUrlstr(urlstr);
 	entry.setLocalFile(localFile);
 	entry.setThreadcount(ApiGlobal.threadCount);
 	entry.setState(state);
 	entry.setB(b);//true 代表学习中心， false代表资料库
 	entry.setKeChengDaiMa(KeChengDaiMa);
 	entry.setKeChengMingCheng(KeChengMingCheng);
 	entry.setZhang(Zhang);
 	entry.setJie(Jie);
 	entry.setZhaoPian(KeChengZhaoPian);
 	return entry;
 }
}
