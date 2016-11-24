package down;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;


public class DownloadManager {
	
	public static final int DOWNLOAD_STATE_NORMAL = 0x00;
	public static final int DOWNLOAD_STATE_PAUSE = 0x01;
	public static final int DOWNLOAD_STATE_DOWNLOADING = 0x02;
	public static final int DOWNLOAD_STATE_FINISH = 0x03;
	public static final int DOWNLOAD_STATE_WAITING = 0x04;
	
	private SparseArray<DownloadFile> downloadFiles = new SparseArray<DownloadFile>();
	private ArrayList<DownloadTask> taskList = new ArrayList<DownloadTask>();
	private Handler mHandler;
	private final static Object syncObj = new Object();
	private static DownloadManager instance;
	private ExecutorService executorService;
	
	private DownloadManager()
	{
		executorService = Executors.newFixedThreadPool(3);
	}
	
	public static DownloadManager getInstance()
	{
		if(null == instance)
		{
			synchronized(syncObj) {
				instance = new DownloadManager();
			}
			return instance;
		}
		return instance;
	}
	
	public void setHandler(Handler handler) {
		this.mHandler =  handler;
	}

	public void startDownload(DownloadFile file) {
		if(downloadFiles.get(file.downloadID)==null){
			downloadFiles.put(file.downloadID, file);
			DownloadTask task = new DownloadTask(file.downloadID);
			taskList.add(task);
			executorService.submit(task);
		}
	}
	
	public void stopAllDownloadTask() {
		while(taskList.size() != 0)
		{
			DownloadTask task = taskList.remove(0);
			task.stopTask();
		}
		executorService.shutdownNow();
		
	}

	class DownloadTask implements Runnable {

		private boolean isWorking = false;
		private int downloadId;

		public DownloadTask(int id)
		{
			this.isWorking = true;
			this.downloadId = id;
		}
		
		public void stopTask()
		{
			this.isWorking = false;
		}
		
		public void update(DownloadFile downloadFile)
		{
			Message msg = mHandler.obtainMessage();
			if(downloadFile.totalSize == downloadFile.downloadSize)
				downloadFile.downloadState = DOWNLOAD_STATE_FINISH;
			msg.obj = downloadFile;
			msg.sendToTarget();
			
		}
		
		public void run() {
			DownloadFile downloadFile = downloadFiles.get(downloadId);
			downloadFile.downloadState = DOWNLOAD_STATE_DOWNLOADING;
			while(isWorking)
			{
				if(downloadFile.downloadState != DOWNLOAD_STATE_DOWNLOADING)
				{
					downloadFiles.remove(downloadFile.downloadID);
					taskList.remove(this);
					isWorking = false;
					break;
				}
				if(downloadFile.downloadSize <= downloadFile.totalSize)
				{
					this.update(downloadFile);
				}
				
				if(downloadFile.downloadSize < downloadFile.totalSize)
				{
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
						downloadFile.downloadState = DOWNLOAD_STATE_PAUSE;
						this.update(downloadFile);
						downloadFiles.remove(downloadId);
						isWorking = false;
						break;
					}
					
					downloadFile.downloadSize=downloadFile.d.getCompelete_Size();
				}
			}
		
		}
	}

}

