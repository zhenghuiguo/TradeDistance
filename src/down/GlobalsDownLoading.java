package down;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Queue;

import com.td.tradedistance.app.bean.DownLoading;
import com.td.tradedistance.app.utils.Logger;


import android.os.Message;

public class GlobalsDownLoading {
	private static final Queue<DownLoading> TASK_QUEUE = new LinkedList<DownLoading>();
	// 用来管理所有下载任务
	public static  Map<String ,DownLoading> taskList = new HashMap<String,DownLoading>();
	public static DownLoading temp =null;
	public static boolean isDown = true;
	static {
		// 初始化创建线程,并等待处理
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
						final DownLoading loader = TASK_QUEUE.poll();
						
						 if(loader != null){
							   temp = loader;
								loader.setFlagDowning(true);
								 new Thread(new Runnable() {
									
									@Override
									public void run() {
										while(loader.isFlagDowning()){
											 Logger.d(Thread.currentThread().getId()+"--downing---");
											int size  = Globals.downloaders.size();//getDownLingSize();
											Downloader d =loader.getLoader();
											loader.getpBar().setMax(d.getFileSize());
											loader.getpBar().setProgress(d.getCompelete_Size());
											
											Message msg = new Message();
											msg.obj = d;
											msg.arg2 = size;
											msg.arg1 = d.getCompelete_Size();
											try {
												Thread.sleep(100);
											} catch (InterruptedException e) {
												e.printStackTrace();
											}
											loader.getH().sendMessage(msg);
											 if(loader.getpBar().getProgress()== loader.getpBar().getMax()){
												 isDown = true;
												 loader.setFlagDowning(false);
											 }
										}
										
										isDown = true;
									}
								}).start();
							   
						 }
					    }
					}
				}
			}
		}.start();

	}
	public static void addTask(DownLoading entry) {
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
    public static int getDownLingSize(){
    	Set  set=Globals.downloaders.entrySet();  
    	 Iterator   iterator=set.iterator();  
    	 int downingSize = 0;
         while (iterator.hasNext()){  
            Map.Entry  mapentry = (Map.Entry) iterator.next();  
             final Downloader d = (Downloader)mapentry.getValue();
             if(d.getState()==2){
            	 downingSize++;
             }
         }
         return downingSize;
    }
    public static Downloader getDownLoading(){
    	Set  set=Globals.downloaders.entrySet();  
    	 Iterator   iterator=set.iterator();  
         while (iterator.hasNext()){  
            Map.Entry  mapentry = (Map.Entry) iterator.next();  
              Downloader d = (Downloader)mapentry.getValue();
             //if(d.getState()==2){
            	 return d;
             //}
         }
         return null;
    }
    public static void deleteAll(){
    	Set set =taskList.entrySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry mapentry = (Map.Entry) iterator.next();
			DownLoading task = (DownLoading) mapentry.getValue();
			if(task!=null){
				task.setFlagDowning(false);
				task = null;
			}
		}
		
		taskList.clear();
  	  if(TASK_QUEUE!=null)
  		  TASK_QUEUE.clear();
  	  isDown = true;
   }
}
