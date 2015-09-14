package com.example.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="NG_WORD_M")
public class NgWord {
	private Integer id;
    private String ngWord;
    

	@DynamoDBHashKey(attributeName="Id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@DynamoDBAttribute(attributeName="NgWord")
	public String getNgWord() {
		return ngWord;
	}
	public void setNgWord(String ngWord) {
		this.ngWord = ngWord;
	}
    
    
}
