package down;

import java.io.Serializable;

import android.content.Context;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * <p>
 * 功能描述:参数类
 * </p>
 * 
 * @author <a href="mailto:zhenghuiguo@yahoo.com.cn">郑惠国</a>
 * @version $Revision: 1.1 $
 */
public class QueueEntry implements Serializable {

	/**
	 * <p>
	 * 功能描述:
	 * </p>
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 下载地址
	 */
	private String urlstr;
	/**
	 * 本地存储地址
	 */
	private String localFile;
	/**
	 * 线程数设置
	 */
	private String threadcount;
	/**
	 * 文件所在列表的位置
	 */
	private int pos;
	/**
	 * 事件控件
	 */
	private TextView v;
	/**
	 * 进度条
	 */
	private ProgressBar pBar;
	/**
	 * handler
	 */
	private Handler mHandler;
	/**
	 * 上下文
	 */
	private Context context;
	/**
	 * 状态
	 */
	private int state;
	/**
	 * 是否是zlk还是xxzx
	 */
	private boolean b;

	private String KeChengDaiMa;
	private String KeChengMingCheng;
	private String Zhang;
	private String Jie;
	private String ZhaoPian;
    private String shiPingDiZhi;
	public String getShiPingDiZhi() {
		return shiPingDiZhi;
	}

	public void setShiPingDiZhi(String shiPingDiZhi) {
		this.shiPingDiZhi = shiPingDiZhi;
	}

	public String getZhaoPian() {
		return ZhaoPian;
	}

	public void setZhaoPian(String zhaoPian) {
		ZhaoPian = zhaoPian;
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

	public boolean getB() {
		return b;
	}

	public void setB(boolean b) {
		this.b = b;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getUrlstr() {
		return urlstr;
	}

	public void setUrlstr(String urlstr) {
		this.urlstr = urlstr;
	}

	public String getLocalFile() {
		return localFile;
	}

	public void setLocalFile(String localFile) {
		this.localFile = localFile;
	}

	public String getThreadcount() {
		return threadcount;
	}

	public void setThreadcount(String threadcount) {
		this.threadcount = threadcount;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public TextView getV() {
		return v;
	}

	public void setV(TextView v) {
		this.v = v;
	}

	public ProgressBar getpBar() {
		return pBar;
	}

	public void setpBar(ProgressBar pBar) {
		this.pBar = pBar;
	}

	public Handler getmHandler() {
		return mHandler;
	}

	public void setmHandler(Handler mHandler) {
		this.mHandler = mHandler;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}
}
