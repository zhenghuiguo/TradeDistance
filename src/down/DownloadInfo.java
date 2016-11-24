package down;
 /**
  * <p>功能描述: 下载信息的实体类</p>
  * @author  <a href="mailto:zhenghuiguo@yahoo.com.cn">郑惠国</a>
  * @version $Revision: 1.1 $
  */
 public class DownloadInfo {
	 /**
	  * 下载器id
	  */
     private int threadId;
     /**
	  * 开始点
	  */
     private int startPos;
     /**
	  * 结束点
	  */
     private int endPos;
     /**
	  * 完成度
	  */
     private int compeleteSize;
     /**
	  * 下载器网络标识
	  */
     private String url;
     /**
      *用户唯一标识
      */
     private String yhwybs;
     private String localUrl;
     public String getLocalUrl() {
		return localUrl;
	}
	public void setLocalUrl(String localUrl) {
		this.localUrl = localUrl;
	}
	/**
      * 文件最大值
      */
     private int MaxSize;
     
     private String KeChengDaiMa;
     private String KeChengMingCheng;
     private String Zhang;
     private String Jie;
     private int state;
     public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
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
	public String getZhaoPain() {
		return ZhaoPain;
	}
	public void setZhaoPain(String zhaoPain) {
		ZhaoPain = zhaoPain;
	}
	private String ZhaoPain;
     public int getMaxSize() {
		return MaxSize;
	}
	public void setMaxSize(int maxSize) {
		MaxSize = maxSize;
	}
	public DownloadInfo(int threadId, int startPos, int endPos,
             int compeleteSize,String url,String localUrl,String KeChengMingCheng,String Zhang,String Jie,String ZhaoPian,int fileSize) {
         this.threadId = threadId;
         this.startPos = startPos;
         this.endPos = endPos;
         this.compeleteSize = compeleteSize;
         this.url=url;
         this.KeChengMingCheng = KeChengMingCheng;
         this.Zhang = Zhang;
         this.Jie = Jie;
         this.ZhaoPain = ZhaoPian;
         this.state = state;
         this.MaxSize = fileSize;
         this.localUrl = localUrl;
     }
     public DownloadInfo() {
     }
     public String getUrl() {
         return url;
     }
     public void setUrl(String url) {
         this.url = url;
     }
     public int getThreadId() {
         return threadId;
     }
     public void setThreadId(int threadId) {
         this.threadId = threadId;
     }
     public int getStartPos() {
         return startPos;
     }
     public void setStartPos(int startPos) {
         this.startPos = startPos;
     }
     public int getEndPos() {
         return endPos;
     }
     public void setEndPos(int endPos) {
         this.endPos = endPos;
     }
     public int getCompeleteSize() {
         return compeleteSize;
     }
     public void setCompeleteSize(int compeleteSize) {
         this.compeleteSize = compeleteSize;
     }
 
     public String getYhwybs() {
		return yhwybs;
	 }
	 public void setYhwybs(String yhwybs) {
		this.yhwybs = yhwybs;
	 }
	@Override
     public String toString() {
         return "DownloadInfo [threadId=" + threadId
                 + ", startPos=" + startPos + ", endPos=" + endPos
                 + ", compeleteSize=" + compeleteSize +"]";
     }
 }