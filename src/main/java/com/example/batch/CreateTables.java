package com.example.batch;

import java.util.ArrayList;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.CreateTableResult;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.common.CommonConstants;
import com.common.CommonUtil;
import com.example.db.DynamoDBClient;

public class CreateTables {
	// DBClinet
	private static AmazonDynamoDBClient dbClient = null;
	
	// DynamoDB
	private static DynamoDB dynamoDB = null;

	/**
	 * AWS DynamoDBにアプリケーションで必要なテーブルを作成する.
	 * @return AWSのAccessKey
	 */
	public static void main(String[] args)
    {
        // DynamoDBのClient取得
		DynamoDBClient dynamoDBClient = new DynamoDBClient(CommonUtil.getAwsAccessKeyId(), CommonUtil.getAwsSecretAccessKey());
		dbClient = dynamoDBClient.getAmazonDynamoDBClient();
		dynamoDB = dynamoDBClient.getDynamoDB();
		
		// SEARCH_INSTA_TAGS_M Table を作成
		createSearchInstaTagsTb();
		
		// COUNT_INSTA_TAGS_M Table を作成
		createCountInstaTagsTb();
		
		// INSTA_INFO_T Table を作成
		createInstaInfoTb();
		
		// テーブルを削除したい場合にコメントアウト
//		dynamoDB.getTable("INSTAGRAM_INFO").delete();
		
    }

	private static void createInstaInfoTb() {
		// テーブルの存在チェック
		if (IsExistedTable(CommonConstants.TB_INSTA_INFO_T)) {
			System.out.println("Existed Table:" + CommonConstants.TB_INSTA_INFO_T);
			return;
		}
		
		// テーブル情報の指定
		ArrayList<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
        attributeDefinitions.add(new AttributeDefinition()
            .withAttributeName("Id")
            .withAttributeType("N"));

        ArrayList<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
        keySchema.add(new KeySchemaElement()
            .withAttributeName("Id")
            .withKeyType(KeyType.HASH));

        CreateTableRequest request = new CreateTableRequest()
            .withTableName(CommonConstants.TB_INSTA_INFO_T)
            .withKeySchema(keySchema)
            .withAttributeDefinitions(attributeDefinitions)
            .withProvisionedThroughput(new ProvisionedThroughput()
                .withReadCapacityUnits(1L)
                .withWriteCapacityUnits(1L));

        System.out.println("Issuing CreateTable request for " + CommonConstants.TB_INSTA_INFO_T);
        CreateTableResult createTableResult = dbClient.createTable(request);
        
        System.out.println("Created Table:" + createTableResult.toString());
        return;
		
	}

	private static void createCountInstaTagsTb() {
		// テーブルの存在チェック
		if (IsExistedTable(CommonConstants.TB_COUNT_INSTA_TAGS_M)) {
			System.out.println("Existed Table:" + CommonConstants.TB_COUNT_INSTA_TAGS_M);
			return;
		}
		
		// テーブル情報の指定
		ArrayList<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
        attributeDefinitions.add(new AttributeDefinition()
            .withAttributeName("Id")
            .withAttributeType("N"));

        ArrayList<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
        keySchema.add(new KeySchemaElement()
            .withAttributeName("Id")
            .withKeyType(KeyType.HASH));

        CreateTableRequest request = new CreateTableRequest()
            .withTableName(CommonConstants.TB_COUNT_INSTA_TAGS_M)
            .withKeySchema(keySchema)
            .withAttributeDefinitions(attributeDefinitions)
            .withProvisionedThroughput(new ProvisionedThroughput()
                .withReadCapacityUnits(1L)
                .withWriteCapacityUnits(1L));

        System.out.println("Issuing CreateTable request for " + CommonConstants.TB_COUNT_INSTA_TAGS_M);
        CreateTableResult createTableResult = dbClient.createTable(request);
        
        System.out.println("Created Table:" + createTableResult.toString());
        return;
		
	}

	private static void createSearchInstaTagsTb() {
		
		// テーブルの存在チェック
		if (IsExistedTable(CommonConstants.TB_SEARCH_INSTA_TAGS_M)) {
			System.out.println("Existed Table:" + CommonConstants.TB_SEARCH_INSTA_TAGS_M);
			return;
		}
		
		// テーブル情報の指定
		ArrayList<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
        attributeDefinitions.add(new AttributeDefinition()
            .withAttributeName("Id")
            .withAttributeType("N"));

        ArrayList<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
        keySchema.add(new KeySchemaElement()
            .withAttributeName("Id")
            .withKeyType(KeyType.HASH));

        CreateTableRequest request = new CreateTableRequest()
            .withTableName(CommonConstants.TB_SEARCH_INSTA_TAGS_M)
            .withKeySchema(keySchema)
            .withAttributeDefinitions(attributeDefinitions)
            .withProvisionedThroughput(new ProvisionedThroughput()
                .withReadCapacityUnits(1L)
                .withWriteCapacityUnits(1L));

        System.out.println("Issuing CreateTable request for " + CommonConstants.TB_SEARCH_INSTA_TAGS_M);
        CreateTableResult createTableResult = dbClient.createTable(request);
        
        System.out.println("Created Table:" + createTableResult.toString());
        return;
		
	}

	// テーブルの存在チェック
	private static boolean IsExistedTable(String tableName) {
		try {
			dynamoDB.getTable(tableName).describe();
		} catch (ResourceNotFoundException e) {
			// DBが見つからない場合
			return false;
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		if (dynamoDB.getTable(tableName) == null) {
			
		}
		return true;
	}
	
	

}
