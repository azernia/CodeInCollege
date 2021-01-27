package com.rui.vo;
/**
 * 工作人员实体类
 * @author Rui
 * @date 2019年12月18日下午11:19:04
 * @version 1.0
 */
public class StaffVO {
	private int staffID;
	private String staffPwd;
	private int rank;
	
	public StaffVO() {
	}
	public StaffVO(int staffID, String staffPwd) {
		this.staffID = staffID;
		this.staffPwd = staffPwd;
	}
	public StaffVO(int staffID, String staffPwd, int rank) {
		super();
		this.staffID = staffID;
		this.staffPwd = staffPwd;
		this.rank = rank;
	}
	public int getStaffID() {
		return staffID;
	}
	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}
	public String getStaffPwd() {
		return staffPwd;
	}
	public void setStaffPwd(String staffPwd) {
		this.staffPwd = staffPwd;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
}
