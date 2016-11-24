package com.td.tradedistance.app.bean;
/**
 * <p>功能描述:终端信息 </p>
 * @author  <a href="mailto:zhenghuiguo@yahoo.com.cn">郑惠国</a>
 * @version $Revision: 1.1 $
 * [日期2013-5-13]
 */
public class DeviceInfo {
	/**
	 * IMEI
	 */
	private String deviceId;
	/**
	 * iccid
	 */
	private String simSerialNumber;
	/**
	 * imsi
	 */
	private String subscriberId;
	/**
	 * 屏幕宽
	 */
	private int widthPixels;
	/**
	 * 屏幕高
	 */
	private int heightPixels;
	/**
	 * 手机型号
	 */
	private String mobilModel;
	/**
	 * 手机版本
	 */
	private String mobilVersion;
	
	/**
	 * 设备类型 平板还是 手机
	 */
	private boolean type;
	public boolean getType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getSimSerialNumber() {
		return simSerialNumber;
	}
	public void setSimSerialNumber(String simSerialNumber) {
		this.simSerialNumber = simSerialNumber;
	}
	public String getSubscriberId() {
		return subscriberId;
	}
	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}
	public int getWidthPixels() {
		return widthPixels;
	}
	public void setWidthPixels(int widthPixels) {
		this.widthPixels = widthPixels;
	}
	public int getHeightPixels() {
		return heightPixels;
	}
	public void setHeightPixels(int heightPixels) {
		this.heightPixels = heightPixels;
	}
	public String getMobilModel() {
		return mobilModel;
	}
	public void setMobilModel(String mobilModel) {
		this.mobilModel = mobilModel;
	}
	public String getMobilVersion() {
		return mobilVersion;
	}
	public void setMobilVersion(String mobilVersion) {
		this.mobilVersion = mobilVersion;
	}
	/**
	 * <p>功能描述:获取终端信息</p>
	 * @param mobilModel
	 * @param mobilVersion
	 * @return ""
	 * @author:郑惠国
	 * @update:[日期2013-5-13] [更改人姓名][变更描述]
	 */
	public String getZDXX(String mobilModel,String mobilVersion){
		StringBuilder sb = new StringBuilder();
		if(mobilModel != null && mobilVersion!=null )
			return sb.append(mobilModel).append(",").append(mobilVersion).toString();
	    return "";
	}
}
