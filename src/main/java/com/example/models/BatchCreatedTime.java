package com.example.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="BATCH_CREATED_TIME_T")
public class BatchCreatedTime {
	private Integer id;
    private String batchCreatedDate;
    
    @DynamoDBHashKey(attributeName="Id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@DynamoDBAttribute(attributeName="BatchCreatedDate")
	public String getBatchCreatedDate() {
		return batchCreatedDate;
	}
	public void setBatchCreatedDate(String batchCreatedDate) {
		this.batchCreatedDate = batchCreatedDate;
	}
}
