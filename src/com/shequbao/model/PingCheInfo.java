package com.shequbao.model;

import java.io.Serializable;

/**
 **拼车的实体类
 * */
public class PingCheInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String suser;//用户名
	private String iusericon;//头像图片
	private String sstarttime;//出发时间
	private String sstartdest;//起点
	private String senddest;//目的地
	private String sdistance;//距离我多远
	private String sseatplace;//余下的席位
	private String sprice;//价格
	private String sphonenum;//电话
	private String longitude;
	private String latitude;
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getSuser() {
		return suser;
	}
	public void setSuser(String suser) {
		this.suser = suser;
	}
	public String getIusericon() {
		return iusericon;
	}
	public void setIusericon(String iusericon) {
		this.iusericon = iusericon;
	}
	public String getSstarttime() {
		return sstarttime;
	}
	public void setSstarttime(String sstarttime) {
		this.sstarttime = sstarttime;
	}
	public String getSstartdest() {
		return sstartdest;
	}
	public void setSstartdest(String sstartdest) {
		this.sstartdest = sstartdest;
	}
	public String getSenddest() {
		return senddest;
	}
	public void setSenddest(String senddest) {
		this.senddest = senddest;
	}
	public String getSdistance() {
		return sdistance;
	}
	public void setSdistance(String sdistance) {
		this.sdistance = sdistance;
	}
	public String getSseat() {
		return sseatplace;
	}
	public void setSseat(String sseat) {
		this.sseatplace = sseat;
	}
	public String getSprice() {
		return sprice;
	}
	public void setSprice(String sprice) {
		this.sprice = sprice;
	}
	public String getSphonenum() {
		return sphonenum;
	}
	public void setSphonenum(String sphonenum) {
		this.sphonenum = sphonenum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
