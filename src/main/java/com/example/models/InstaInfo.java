package com.example.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="INSTA_INFO_T")
public class InstaInfo {
	private String batchCreatedId;
	private String createDateUserId;
    private String userName;
    private String userPictureUrl;
    private String link;
    private String imageUrl;
    private String createDate;
    
    @DynamoDBHashKey(attributeName="BatchCreatedId")
    public String getBatchCreatedId() {
		return batchCreatedId;
	}
	public void setBatchCreatedId(String batchCreatedId) {
		this.batchCreatedId = batchCreatedId;
	}
	
	@DynamoDBRangeKey(attributeName="CreateDateUserId")
	public String getCreateDateUserId() {
		return createDateUserId;
	}
	public void setCreateDateUserId(String createDateUserId) {
		this.createDateUserId = createDateUserId;
	}
	
	@DynamoDBAttribute(attributeName="UserName")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@DynamoDBAttribute(attributeName="UserPictureUrl")
	public String getUserPictureUrl() {
		return userPictureUrl;
	}
	public void setUserPictureUrl(String userPictureUrl) {
		this.userPictureUrl = userPictureUrl;
	}
	
	@DynamoDBAttribute(attributeName="Link")
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@DynamoDBAttribute(attributeName="ImageUrl")
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	@DynamoDBAttribute(attributeName="CreateDate")
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
    
}
