package com.example.batch;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;

import sun.nio.cs.StandardCharsets;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.common.CommonUtil;
import com.example.db.DynamoDBClient;
import com.example.models.CountInstaTag;
import com.example.models.SearchInstaTag;

public class CreateDataProcess {
    public static void main(String[] args)
    {
        System.out.println("CreateDataProcess executed.");
        // Herokuで休眠しないようにアプリにリクエストを送る。
        
        // DynamoDBのClient取得
     	DynamoDBClient dynamoDBClient = new DynamoDBClient(CommonUtil.getAwsAccessKeyId(), CommonUtil.getAwsSecretAccessKey());
        DynamoDBMapper mapper = dynamoDBClient.getDynamoDBMapper();
        
        // 全件検索のパラメータ用に生成
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        // Instagramのパラメータに指定するタグの取得
        List<SearchInstaTag> searchTags = mapper.scan(SearchInstaTag.class, scanExpression);
        
        // 取得した情報に優先度をつけるタグを取得
        List<CountInstaTag> countTags = mapper.scan(CountInstaTag.class, scanExpression);
//        for (CountInstaTag countInstaTag :countTags) {
//        	System.out.println(countInstaTag.getId().toString() + ":" + countInstaTag.getTag());
//        }
        
        // Instagramから、タグで数回データの取得
        for (SearchInstaTag searchInstaTag :searchTags) {
        	System.out.println(searchInstaTag.getId().toString() + ":" + searchInstaTag.getTag());
        }
        
        // Listに詰めて、複数タグがあるものを、残して、あとは捨てる
        
        // 最新更新日時で降順にする
        
        // DBのデータを削除
        // DBにデータを保存
        System.out.println("CreateDataProcess Done.");
    }
}
