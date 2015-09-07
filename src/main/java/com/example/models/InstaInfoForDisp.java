package com.example.models;

import java.util.List;

public class InstaInfoForDisp {
	private List<InstaInfo> instaInfos;
	
	private String batchCreatedDate;

	public List<InstaInfo> getInstaInfos() {
		return instaInfos;
	}

	public void setInstaInfos(List<InstaInfo> instaInfos) {
		this.instaInfos = instaInfos;
	}

	public String getBatchCreatedDate() {
		return batchCreatedDate;
	}

	public void setBatchCreatedDate(String batchCreatedDate) {
		this.batchCreatedDate = batchCreatedDate;
	}

}
