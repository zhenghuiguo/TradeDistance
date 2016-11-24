package down;
/**
 * <p>功能描述:记载下载器详细信息的类  </p>
 * @author  <a href="mailto:zhenghuiguo@yahoo.com.cn">郑惠国</a>
 * @version $Revision: 1.1 $
 */
 public class LoadInfo {
	 /**
	  * 文件大小
	  */
     public int fileSize;
     /**
      * 完成度
      */
     private int complete;
     /**
      * 下载器标识
      */
     private String urlstring;
     
     private String KeChengMingCheng;
     private String Zhang;
     private String Jie;
     private String ZhaoPian;
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
	public String getZhaoPian() {
		return ZhaoPian;
	}
	public void setZhaoPian(String zhaoPian) {
		ZhaoPian = zhaoPian;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	private int state;
     public LoadInfo(int fileSize, int complete, String urlstring,String KeChengMingCheng,String Zhang,String Jie,String ZhaoPian) {
         this.fileSize = fileSize;
         this.complete = complete;
         this.urlstring = urlstring;
         this.KeChengMingCheng = KeChengMingCheng;
         this.Zhang = Zhang;
         this.Jie = Jie;
         this.ZhaoPian = ZhaoPian;
     }
     public LoadInfo() {
     }
     public int getFileSize() {
         return fileSize;
     }
     public void setFileSize(int fileSize) {
         this.fileSize = fileSize;
     }
     public int getComplete() {
         return complete;
     }
     public void setComplete(int complete) {
         this.complete = complete;
     }
     public String getUrlstring() {
         return urlstring;
     }
     public void setUrlstring(String urlstring) {
         this.urlstring = urlstring;
     }
     @Override
     public String toString() {
         return "LoadInfo [fileSize=" + fileSize + ", complete=" + complete
                 + ", urlstring=" + urlstring + "]";
     }
 }