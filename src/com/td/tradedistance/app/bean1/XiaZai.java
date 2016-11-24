package com.td.tradedistance.app.bean1;

import java.io.Serializable;

/**
 * <p>功能描述: 下载参数类</p>
 * @author  <a href="mailto:zhenghuiguo@yahoo.com.cn">郑惠国</a>
 * @version $Revision: 1.1 $
 */
public class XiaZai implements Serializable{

	
	/** <p>功能描述:</p>
	 */
	private static final long serialVersionUID = 2083821386960344890L;
    private String sqm;
    private String xt;
    private String zdsbm;
    private String lmdm;
    private int xxbs;
    private String hqccxx;
	public String getHqccxx() {
		return hqccxx;
	}
	public void setHqccxx(String hqccxx) {
		this.hqccxx = hqccxx;
	}
	public String getSqm() {
		return sqm;
	}
	public void setSqm(String sqm) {
		this.sqm = sqm;
	}
	public String getXt() {
		return xt;
	}
	public void setXt(String xt) {
		this.xt = xt;
	}
	public String getZdsbm() {
		return zdsbm;
	}
	public void setZdsbm(String zdsbm) {
		this.zdsbm = zdsbm;
	}
	public String getLmdm() {
		return lmdm;
	}
	public void setLmdm(String lmdm) {
		this.lmdm = lmdm;
	}
	public int getXxbs() {
		return xxbs;
	}
	public void setXxbs(int xxbs) {
		this.xxbs = xxbs;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
