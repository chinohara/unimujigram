package com.example.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="SEARCH_INSTA_TAGS_M")
public class SearchInstaTag {
	
	private Integer id;
    private String tag;
    
    @DynamoDBHashKey(attributeName="Id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@DynamoDBAttribute(attributeName="Tag")
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
    
    
}
