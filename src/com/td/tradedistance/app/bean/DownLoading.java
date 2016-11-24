package com.td.tradedistance.app.bean;


import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import down.Downloader;

public class DownLoading {
	public String daxiao;
	public int state;
	public int downloadSize;
	public int getDownloadSize() {
		return downloadSize;
	}

	public void setDownloadSize(int downloadSize) {
		this.downloadSize = downloadSize;
	}
	public boolean flagDowning = true;
	public boolean isFlagDowning() {
		return flagDowning;
	}

	public void setFlagDowning(boolean flagDowning) {
		this.flagDowning = flagDowning;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public int pos;
	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}
	public int jdt;
	public int getJdt() {
		return jdt;
	}

	public void setJdt(int jdt) {
		this.jdt = jdt;
	}

	public TextView getTv() {
		return tv;
	}

	public void setTv(TextView tv) {
		this.tv = tv;
	}
	private String url;
    private String kechengming;
	public String getKechengming() {
		return kechengming;
	}

	public void setKechengming(String kechengming) {
		this.kechengming = kechengming;
	}

	public String getDaxiao() {
		return daxiao;
	}

	public void setDaxiao(String daxiao) {
		this.daxiao = daxiao;
	}
	private Downloader loader;
    private TextView tv;
	private ProgressBar pBar;
    private boolean flag;
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Downloader getLoader() {
		return loader;
	}

	public void setLoader(Downloader loader) {
		this.loader = loader;
	}

	public ProgressBar getpBar() {
		return pBar;
	}

	public void setpBar(ProgressBar pBar) {
		this.pBar = pBar;
	}

	public Handler getH() {
		return h;
	}

	public void setH(Handler h) {
		this.h = h;
	}

	private Handler h;

	
}
