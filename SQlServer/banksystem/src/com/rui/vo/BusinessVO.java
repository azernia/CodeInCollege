package com.rui.vo;

public class BusinessVO {
	private String businessID;
	private String businessName;
	private String description;
	
	public BusinessVO() {
	}
	public BusinessVO(String businessID, String businessName, String description) {
		super();
		this.businessID = businessID;
		this.businessName = businessName;
		this.description = description;
	}
	public String getBusinessID() {
		return businessID;
	}
	public void setBusinessID(String businessID) {
		this.businessID = businessID;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
