package down;
 

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.td.tradedistance.app.ApiGlobal;
import com.td.tradedistance.app.global.Global;
import com.td.tradedistance.app.localstorage.DownLoad_Info_Dao;
import com.td.tradedistance.app.utils.Logger;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

 /**
  * <p>功能描述: 下载服务类</p>
  * @author  <a href="mailto:zhenghuiguo@yahoo.com.cn">郑惠国</a>
  * @version $Revision: 1.1 $
  */
 public class Downloader {
	 private ProgressBar progressBar;
	 private TextView daxiao;
	 public ProgressBar getProgressBar() {
		return progressBar;
	}
	public void setProgressBar(ProgressBar progressBar) {
		this.progressBar = progressBar;
	}
	public TextView getDaxiao() {
		return daxiao;
	}
	public void setDaxiao(TextView daxiao) {
		this.daxiao = daxiao;
	}
	private int position;
	 public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public Handler getmHandler() {
		return mHandler;
	}
	public void setmHandler(Handler mHandler) {
		this.mHandler = mHandler;
	}
    DownloadInfo info;
    int loadingPos;
	public int getLoadingPos() {
		return loadingPos;
	}
	public void setLoadingPos(int loadingPos) {
		this.loadingPos = loadingPos;
	}
	/**
	  * 下载的地址
	  */
     private String urlstr;
     public String getUrlstr() {
		return urlstr;
	}
	public void setUrlstr(String urlstr) {
		this.urlstr = urlstr;
	}
	/**
	  * 保存路径
	  */
     private String localfile;
     public String getLocalfile() {
		return localfile;
	}
	public void setLocalfile(String localfile) {
		this.localfile = localfile;
	}
	/**
	  *  线程数
	  */
     private int threadcount;
     
     private String KeChengDaiMa;
     private String KeChengMingCheng;
     private String Zhang;
     private String Jie;
     private String ZhaoPain;
     
     public DownloadInfo getInfo() {
		return info;
	}
	public void setInfo(DownloadInfo info) {
		this.info = info;
	}
	public String getZhaoPain() {
		return ZhaoPain;
	}
	public void setZhaoPain(String zhaoPain) {
		ZhaoPain = zhaoPain;
	}
	private int length;
     public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}

	private int Compelete_Size;
     public int getCompelete_Size() {
		return Compelete_Size;
	}
	public void setCompelete_Size(int compelete_Size) {
		Compelete_Size = compelete_Size;
	}

	/**
	  * 消息处理器 
	  */
     private Handler mHandler;
     /**
      * 正在下载列表消息处理器
      */
     private Handler mDownloadingHandler;
     public Handler getmDownloadingHandler() {
		return mDownloadingHandler;
	}
	public void setmDownloadingHandler(Handler mDownloadingHandler) {
		this.mDownloadingHandler = mDownloadingHandler;
	}
	private DownLoad_Info_Dao downLoadDao;
     /**
      * b = true 代表学习中心 b = false 代表资料库
      */
     private boolean b;
     int count = 0;
     public boolean isB() {
		return b;
	}
	public void setB(boolean b) {
		this.b = b;
	}

	/**
	  * 所要下载的文件的大小
	  */
     private int fileSize; 
    
     private Context context; 
     /**
	  * 存放下载信息类的集合
	  */
     private List<DownloadInfo> infos;
     /**
	  * 下载的状态:初始化状态
	  */
     private static final int WAIT = 0;
     /**
	  * 下载的状态:初始化状态
	  */
     private static final int INIT = 1;
     /**
	  * 下载的状态:正在下载状态
	  */
     private static final int DOWNLOADING = 2;
     /**
	  * 下载的状态:暂停状态
	  */
     private static final int PAUSE = 3;
     /**
	  * 下载的状态
	  */
     private int state = WAIT;
     public String getKeChengDaiMa() {
		return KeChengDaiMa;
	}
	public void setKeChengDaiMa(String keChengDaiMa) {
		KeChengDaiMa = keChengDaiMa;
	}
	public String getKeChengMingCheng() {
		return KeChengMingCheng;
	}
	public void setKeChengMingCheng(String keChengMingCheng) {
		KeChengMingCheng = keChengMingCheng;
	}
	public String getZhang() {
		return Zhang;
	}
	public void setZhang(String zhang) {
		Zhang = zhang;
	}
	public String getJie() {
		return Jie;
	}
	public void setJie(String jie) {
		Jie = jie;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public  int getWait() {
		return WAIT;
	}
	public  int getInit() {
		return INIT;
	}
	public  int getDownloading() {
		return DOWNLOADING;
	}
	public static int getPause() {
		return PAUSE;
	}

	private int pos;
     private TextView t ;
     /**
      * 正在下载列表中的是否下载按钮
      */
     private TextView tDownLoading_tv ;
     public TextView gettDownLoading_tv() {
		return tDownLoading_tv;
	}
	public void settDownLoading_tv(TextView tDownLoading_tv) {
		this.tDownLoading_tv = tDownLoading_tv;
	}
	public TextView getT() {
		return t;
	 }
     public void setT(TextView t){
    	this.t = t; 
     }
     
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Downloader(String KeChengDaiMa,String KeChengMingCheng,String Zhang,String Jie, String keChengZhaoPian,String urlstr, String localfile, int threadcount,
             Context context, Handler mHandler,int pos,TextView t ) {
         this.urlstr = urlstr;
         this.localfile = localfile;
         this.threadcount = threadcount;
         this.mHandler = mHandler;
         this.context = context;
         this.pos = pos;
         this.t = t;
         this.KeChengDaiMa = KeChengDaiMa;
         this.KeChengMingCheng = KeChengMingCheng;
         this.Zhang = Zhang;
         this.Jie = Jie;
         this.ZhaoPain = keChengZhaoPian;
         this.downLoadDao = new DownLoad_Info_Dao();
         
     }
     /**
      *判断是否正在下载 
      */
     public boolean isdownloading() {
         return state == DOWNLOADING;
     }
     
     /**
      *判断是否正在等待中
      */
     public boolean isdownWait() {
         return state == WAIT;
     }
     
     /**
      *判断是否暂停
      */
     public boolean isdownPause() {
         return state == PAUSE;
     }
     /**
      * 得到downloader里的信息
      * 首先进行判断是否是第一次下载，如果是第一次就要进行初始化，并将下载器的信息保存到数据库中
      * 如果不是第一次下载，那就要从数据库中读出之前下载的信息（起始位置，结束为止，文件大小等），并将下载信息返回给下载器
      */
     public LoadInfo getDownloaderInfors() {
         if (isFirst(localfile)) {
             //Logger.d("TAG", "isFirst");
             if(!init()){
            	 return null;
             }
             int range = fileSize / threadcount;
             infos = new ArrayList<DownloadInfo>();
             for (int i = 0; i < threadcount - 1; i++) {
                 DownloadInfo info = new DownloadInfo(i, i * range, (i + 1)* range - 1, 0, getUrlstr(),localfile,getKeChengMingCheng(),getZhang(),getJie(),getZhaoPain(),getFileSize());
                 infos.add(info);
             }
             DownloadInfo info = new DownloadInfo(threadcount - 1,(threadcount - 1) * range, fileSize - 1, 0, getUrlstr(),localfile,getKeChengMingCheng(),getZhang(),getJie(),getZhaoPain(),getFileSize());
             infos.add(info);
             //保存infos中的数据到数据库
             downLoadDao.saveInfos(infos);
             //创建一个LoadInfo对象记载下载器的具体信息
             LoadInfo loadInfo = new LoadInfo(fileSize, 0, localfile,getKeChengMingCheng(),getZhang(),getJie(),getZhaoPain());
             return loadInfo;
         } else {
             //得到数据库中已有的urlstr的下载器的具体信息
             infos = downLoadDao.getInfos(urlstr,localfile);
             //Logger.d("TAG", "not isFirst size=" + infos.size());
             int size = 0;
             int compeleteSize = 0;
            
             for (DownloadInfo info : infos) {
                 compeleteSize += info.getCompeleteSize();
                 size += info.getEndPos() - info.getStartPos() + 1;
                 setKeChengMingCheng(info.getKeChengMingCheng());
                 setZhang(info.getZhang());
                 setJie(info.getJie());
                 setZhaoPain(info.getZhaoPain());
                 setFileSize(info.getMaxSize());
                // setState(info.getState());
             }
             return new LoadInfo(size, compeleteSize, localfile,getKeChengMingCheng(),getZhang(),getJie(),getZhaoPain());
         }
     }
 
     /**
      * 初始化
      */
     private boolean init() {
    	 HttpURLConnection conn = null;
         try {
             URL url = new URL(urlstr);//ApiGlobal.getXiaZaiUrl(urlstr)
             conn = (HttpURLConnection) url.openConnection();
             conn.setConnectTimeout(ApiGlobal.REQUEST_TIMEOUT);
             conn.setReadTimeout(ApiGlobal.REQUEST_TIMEOUT);
             conn.setRequestMethod("GET");
             fileSize = conn.getContentLength();
             
             setFileSize(fileSize);
             //Logger.d("fileSize-getContentLength", "----"+fileSize);
             int ret = Integer.parseInt(conn.getHeaderField("Ret"));
				if(ret == Global.no_file){
					if(mHandler!=null)
					   createMessage(ret,0);
					return false;
				}
				if(ret == Global.error_sqm){
					if(mHandler!=null)
						createMessage(ret,0);
				 	return false;
				}
             File file = new File(localfile);
             if (!file.exists()) {
                 file.createNewFile();
             }
             // 本地访问文件
             RandomAccessFile accessFile = new RandomAccessFile(file, "rwd");
             accessFile.setLength(fileSize);
             accessFile.close();
             conn.disconnect();
             return true;
         } catch (Exception e) {
        	 if(mHandler!=null)
        		 createMessage(0,0);
        	 return false;
         }
     }  
     /**
      * 判断是否是第一次 下载
      */
     private boolean isFirst(String urlstr) {
         return downLoadDao.isHasInfors(urlstr);
     }
 
     /**
      * 利用线程开始下载数据
      */
     public void download() {
    	 getDownloaderInfors();
         if (infos != null) {
             if (state == DOWNLOADING)
                 return;
             state = DOWNLOADING;
             for (DownloadInfo info : infos) {
                 new MyThread(info.getThreadId(), info.getStartPos(),
                         info.getEndPos(), info.getCompeleteSize(),
                         info.getUrl(),info.getLocalUrl()).start();
             }
         }
     }
     //http://192.168.100.88:8010/Interface/YiDongApp/xiazai.ashx?xitongID=10036&sqm=q3CLS9OxLgQvC743Bp396SWryMf2%2F8LYov3of4Us%2B%2B6BbxmxqVIw6w%3D%3D&zdsbm=ll5CP0OGrqwKhKtmpESV5UGj7aOImElS5quIaHb15iOBbxmxqVIw6w%3D%3D&wybs=%2FMYT091R1Ms%3D&Kcdm=RwN%2F3cD33aQ%3D&Zjbs=YbMYtHVGng8%3D
     //http://192.168.100.88:8010/Interface/YiDongApp/xiazai.ashx?xitongID=10036&sqm=q3CLS9OxLgQvC743Bp396SWryMf2%2F8LYov3of4Us%2B%2B6BbxmxqVIw6w%3D%3D&zdsbm=ll5CP0OGrqwKhKtmpESV5UGj7aOImElS5quIaHb15iOBbxmxqVIw6w%3D%3D&wybs=%2FMYT091R1Ms%3D&Kcdm=RwN%2F3cD33aQ%3D&Zjbs=YbMYtHVGng8%3D
     public class MyThread extends Thread {
         private int threadId;
         private int startPos;
         private int endPos;
         private int compeleteSize;
         private String urlstr;
         private String localUrl;
         
 
         public MyThread(int threadId, int startPos, int endPos,
                 int compeleteSize, String urlstr,String localUrl) {
             this.threadId = threadId;
             this.startPos = startPos;
             this.endPos = endPos;
             this.compeleteSize = compeleteSize;
             this.urlstr = urlstr;
             this.localUrl = localUrl;
         }
		@Override
         public void run()  {
             HttpURLConnection connection = null;
             RandomAccessFile randomAccessFile = null;
             InputStream is = null;
             
             int code = 0;
             try {
                 URL url = new URL(urlstr);//ApiGlobal.getXiaZaiUrl(urlstr)
                 connection = (HttpURLConnection) url.openConnection();
                 connection.setConnectTimeout(ApiGlobal.REQUEST_TIMEOUT);
                 connection.setReadTimeout(ApiGlobal.SO_TIMEOUT);
                 connection.setRequestMethod("GET");
                // fileSize = connection.getContentLength();
                // setFileSize(fileSize);
                 if((startPos + compeleteSize)>endPos){
                	 if(mHandler!=null)
                	    createMessage(1,endPos);
                	 close( connection, randomAccessFile, is);
                	 return;
                 }
                 if(count!=0){
                	 compeleteSize = compeleteSize-ApiGlobal.RollBuffer;
                 }
                 // 设置范围，格式为Range：bytes x-y;
                 connection.setRequestProperty("Range", "bytes="+(startPos + compeleteSize) + "-");
                 code = connection.getResponseCode();
                 int ret = Integer.parseInt(connection.getHeaderField("Ret"));
 				 if(ret == Global.no_file){
 					if(mHandler!=null)
 					 createMessage(ret,0);
 					return ;
 				 }
 				 if(ret == Global.error_sqm){
 					if(mHandler!=null)
 					 createMessage(ret,0);
 				 	return ;
 				 }
                // Logger.d("init()-ret", ret);
                 if(code!=206){
                	  Logger.d("createMessage", code+"");
                	  if(mHandler!=null)
                	     createMessage(0,0);
                	 return;
                 }
                 //Logger.d("init-code-down", String.valueOf(code));
                 randomAccessFile = new RandomAccessFile(localfile, "rwd");
                 randomAccessFile.seek(startPos + compeleteSize);
                 is = connection.getInputStream();
                 byte[] buffer = new byte[ApiGlobal.BufferSize];//4096 8192 100920
                 int length = -1;
                 while ((length = is.read(buffer)) != -1) {
                     randomAccessFile.write(buffer, 0, length);
                     compeleteSize += length;
                     setCompelete_Size(compeleteSize);
                    // Logger.d(Thread.currentThread().getId()+"handleMessage"+"downloading1"+compeleteSize);
                     //setLength(length);
                     // 更新数据库中的下载信息
                     Logger.d1("updataInfos", localfile);
                     downLoadDao.updataInfos(threadId, compeleteSize, urlstr,localUrl);
                     // 用消息将下载信息传给进度条，对进度条进行更新
                     if(mHandler!=null)
                    	 createMessage(1,length);
                     if (state == PAUSE) {
                         return;
                     }
                 }
                 if(fileSize == compeleteSize){
                	 //Logger.d("isequal", fileSize+ "==" +compeleteSize);
                	 close( connection, randomAccessFile, is);
                 }
             } catch (Exception e) {
            	// Logger.d("createMessage", code+"");
            	 count++;
            	 if(count == 4){
            		 if(mHandler!=null)
            			 createMessage(0,0);
            		 //Logger.d("count", count+"===");
            		 count = 0;
            	 }else{
            		// Logger.d("download", code+"==="+"state"+state);
            		 reset();
            		 download();
            	}
             }finally{
            	 //Logger.d("finally", "finally");
             }

         }
     }
     private void close(HttpURLConnection connection,RandomAccessFile randomAccessFile,InputStream is) throws IOException{
    		connection.disconnect();
     	    connection = null;
     	    if(is!=null){
     	    	is.close();
     	    	is = null;
     	    }
     	    if(randomAccessFile!=null){
     	       randomAccessFile.close();
     	       randomAccessFile = null;
     	    }
     }
     private void createMessage(int code,int length){
    	 //Logger.d("createMessage", code+"  ");
    	 Message message = Message.obtain();
         message.what = code;
         message.obj = urlstr;
         message.arg1 = length;
         message.arg2 = pos;
         mHandler.sendMessage(message);
     }
    
     /**
      * 删除数据库中urlstr对应的下载器信息
      */
     public void delete(String urlstr) {
    	 downLoadDao.deleteDownInfo(urlstr);
     }
     /**
      * 设置暂停
      */
     public void pause() {
         state = PAUSE;
     }
     
     /**
      * 重置下载状态
      */
     public void reset() {
         state = INIT;
     }
 }