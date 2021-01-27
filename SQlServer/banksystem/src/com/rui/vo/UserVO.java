package com.rui.vo;
/**
 * 用户实体类
 * @author Rui
 * @date 2019年12月18日下午11:10:16
 * @version 1.0
 */
public class UserVO {
	private String bUserID;
	private String name;
	private String sex;
	private String iDCard;
	private String phoneNum;
	private String address;
	
	public UserVO() {
	}
	public UserVO(String bUserID, String name, String sex, String iDCard, String phoneNum, String address) {
		super();
		this.bUserID = bUserID;
		this.name = name;
		this.sex = sex;
		this.iDCard = iDCard;
		this.phoneNum = phoneNum;
		this.address = address;
	}
	public String getbUserID() {
		return bUserID;
	}
	public void setbUserID(String bUserID) {
		this.bUserID = bUserID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getiDCard() {
		return iDCard;
	}
	public void setiDCard(String iDCard) {
		this.iDCard = iDCard;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
