package com.example.db;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

public class DynamoDBClient {
		
		private AmazonDynamoDBClient amazonDynamoDBClient = null;
		private DynamoDB dynamoDB = null;
		private DynamoDBMapper dynamoDBMapper = null;
		
	    
		// コンストラクタ
	    public DynamoDBClient(String awsAccessKeyId, String awsSecretAccessKey) {
	        
	        BasicAWSCredentials credentials = new BasicAWSCredentials(awsAccessKeyId,awsSecretAccessKey);
	        this.amazonDynamoDBClient = new AmazonDynamoDBClient(credentials);
	        // 東京リージョンを指定
	        amazonDynamoDBClient.setRegion(Region.getRegion(Regions.AP_NORTHEAST_1));
	        
	        this.dynamoDB = new DynamoDB(amazonDynamoDBClient);
	        this.dynamoDBMapper = new DynamoDBMapper(amazonDynamoDBClient);
	    }

		public AmazonDynamoDBClient getAmazonDynamoDBClient() {
			return amazonDynamoDBClient;
		}

		public DynamoDB getDynamoDB() {
			return dynamoDB;
		}

		public DynamoDBMapper getDynamoDBMapper() {
			return dynamoDBMapper;
		}

}
