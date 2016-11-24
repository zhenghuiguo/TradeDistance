package down;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.td.tradedistance.app.utils.Logger;

import down.GlobalsListDownLoading.DownloadTask;
import android.os.Handler;
import android.os.Message;

public class GlobalDownLoading1 {
	private final static Object syncObj = new Object();
	private static GlobalDownLoading1 instance;
	private boolean isDown = true;
	public boolean isDown() {
		return isDown;
	}
	public void setDown(boolean isDown) {
		this.isDown = isDown;
	}
	public  Handler h;
	public Handler getH() {
		return h;
	}
	public void setH(Handler h) {
		this.h = h;
	}
	// 用来管理所有下载任务
    private  Map<String ,DownloadTask> taskList = new HashMap<String,DownloadTask>();
    private   Queue<DownloadTask> TASK_QUEUE = new LinkedList<DownloadTask>();
    public  void addTask(Downloader entry) {
    	DownloadTask task = new DownloadTask(entry);
		synchronized (TASK_QUEUE) {
			if (!TASK_QUEUE.contains(task)) {
				TASK_QUEUE.add(task);
				taskList.put(entry.getUrlstr(), task);
				TASK_QUEUE.notify();
			}
		}
	}
    private GlobalDownLoading1(){
    	startDownload();
    }
	public  void delTask(DownloadTask entry){
		synchronized (TASK_QUEUE) {
			if (TASK_QUEUE.contains(entry)) {
				TASK_QUEUE.remove(entry);
			}
		}
	}
	
	public static GlobalDownLoading1 getInstance()
	{
		if(null == instance)
		{
			synchronized(syncObj) {
				instance = new GlobalDownLoading1();
			}
			return instance;
		}
		return instance;
	}
	
	
	 class DownloadTask implements Runnable{
		Downloader loader;
		private boolean isWorking = false;
         private DownloadTask(Downloader loader){
        	 this.loader =loader;
        	 this.isWorking = true;
         }
         public void stopTask(){
 			this.isWorking = false;
 		}
		@Override
		public void run() {
			//Logger.d("------------"+Thread.currentThread().getId());
			while(isWorking){
				Logger.d("-----"+isWorking+"-------"+Thread.currentThread().getId());
				Message msg = new Message();
				msg.obj = loader;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				h.sendMessage(msg);
				if(loader.getCompelete_Size() == loader.getFileSize())
				{
					isWorking = false;
					isDown = true;
					stopLoaderTask(loader.getUrlstr());
					break;
				}
			}
			
		}
		
	}
	
	public void startDownload() {
		new Thread() {
			/*{
				setDaemon(true);
			}*/
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
					if(isDown){
						isDown = false;
					    if(TASK_QUEUE.size()>0){
					    	
						 DownloadTask task = TASK_QUEUE.poll();
						
						 if(task != null){
								 
							 new Thread(task).start();
						 }
					    }
					}
				}
			}
		}.start();
	}
    public Map<String ,DownloadTask> getTaskList() {
		return taskList;
	}
	public void setTaskList(Map<String ,DownloadTask> taskList) {
		this.taskList = taskList;
	}
	public  void stopAllDownloadTask() {
		Set set =taskList.entrySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry mapentry = (Map.Entry) iterator.next();
			DownloadTask task = (DownloadTask) mapentry.getValue();
			if(task!=null)
				// 可以在这里做其他的处理
				task.stopTask();
		}
		
		TASK_QUEUE.clear();
		taskList.clear();
		isDown = true;
	}
	
	public  void stopLoadingDownloadTask() {
		Set set =taskList.entrySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry mapentry = (Map.Entry) iterator.next();
			DownloadTask task = (DownloadTask) mapentry.getValue();
			if(task!=null)
				// 可以在这里做其他的处理
				task.stopTask();
			isDown = false;
		}
		
		TASK_QUEUE.clear();
		taskList.clear();
		
	}
    public  void stopLoaderTask(String url){
    	DownloadTask task = taskList.get(url);
    	if(task!=null)
    		task.stopTask();
    }
    public  void getTask(String url){
    	DownloadTask task = (DownloadTask)taskList.get(url);
    	if(task!=null)
    		task.isWorking = true;
    }
    public void stopTaskAndRemove(String url) {
		DownloadTask task = taskList.get(url);
    	if(task!=null)
    		task.stopTask();
    	taskList.remove(url);
		
	}
}
