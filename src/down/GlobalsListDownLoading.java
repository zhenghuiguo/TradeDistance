package down;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.td.tradedistance.app.utils.Logger;

import android.os.Handler;
import android.os.Message;

public class GlobalsListDownLoading {
	private final static Object syncObj = new Object();
	private static GlobalsListDownLoading instance;
	public  Handler h;
	public Handler getH() {
		return h;
	}
	public void setH(Handler h) {
		this.h = h;
	}
	private  ExecutorService executorService;
	// 用来管理所有下载任务
    private  Map<String ,DownloadTask> taskList = new HashMap<String,DownloadTask>();
	//private ArrayList<DownloadTask> taskList = new ArrayList<DownloadTask>();
	
	private GlobalsListDownLoading()
	{
		// 最多只能同时下载3个任务，其余的任务排队等待
		executorService = Executors.newFixedThreadPool(5);
	}
	
	public ExecutorService getExecutorService() {
		return executorService;
	}
	public static GlobalsListDownLoading getInstance()
	{
		if(null == instance)
		{
			synchronized(syncObj) {
				instance = new GlobalsListDownLoading();
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
					taskList.remove(loader.getPosition());
					isWorking = false;
					break;
				}
			}
			
		}
		
	}
	
	public void startDownload(Downloader loader) {
			DownloadTask task = new DownloadTask(loader);
			taskList.put(loader.getUrlstr(), task);
			//new Thread(task).start();
			executorService.submit(task);
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
		
		taskList.clear();
	    //executorService.shutdownNow();  
		// 会停止正在进行的任务和拒绝接受新的任务
		/* try {  
			 executorService.shutdown();  
		   
		        if(!executorService.awaitTermination(5000, TimeUnit.MILLISECONDS)){  
		            // 超时的时候向线程池中所有的线程发出中断(interrupted)。  
		        	executorService.shutdownNow();  
		        }  
		    } catch (InterruptedException e) {  
		        // awaitTermination方法被中断的时候也中止线程池中全部的线程的执行。  
		        System.out.println("awaitTermination interrupted: " + e);  
		        executorService.shutdownNow();  
		    }  
		 executorService = null;*/
	}
    public  void stopTask(String url){
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
