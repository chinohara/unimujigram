package com.example.models;

import java.util.List;

public class InstaInfoForDisp {
	private List<InstaInfo> instaInfos;
	
	private List<InstaInfo> petitmoInfos;
	
	private String batchCreatedDate;

	public List<InstaInfo> getInstaInfos() {
		return instaInfos;
	}

	public void setInstaInfos(List<InstaInfo> instaInfos) {
		this.instaInfos = instaInfos;
	}

	public List<InstaInfo> getPetitmoInfos() {
		return petitmoInfos;
	}

	public void setPetitmoInfos(List<InstaInfo> petitmoInfos) {
		this.petitmoInfos = petitmoInfos;
	}

	public String getBatchCreatedDate() {
		return batchCreatedDate;
	}

	public void setBatchCreatedDate(String batchCreatedDate) {
		this.batchCreatedDate = batchCreatedDate;
	}

}
