package com.example.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.common.CommonConstants;
import com.common.CommonUtil;
import com.example.db.DynamoDBClient;
import com.example.models.BatchCreatedTime;
import com.example.models.ExclusionInstaUser;
import com.example.models.InstaInfo;
import com.example.models.InstaInfoForDisp;
import com.example.models.InstagramInfo;
import com.example.models.InstagramRespDto;
import com.example.models.Time;
import com.example.rest.RestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public class ApiService {
	
	// DynamoDBMapper
	private static DynamoDBMapper mapper = null;

    @GET
    public String get() {
        return "OK";
    }

    @GET
    @Path("snap")
    public InstaInfoForDisp getBabys() {
    	
    	// DynamoDBのClient取得
    	DynamoDBClient dynamoDBClient = new DynamoDBClient(CommonUtil.getAwsAccessKeyId(), CommonUtil.getAwsSecretAccessKey());
    	mapper = dynamoDBClient.getDynamoDBMapper();
    	
    	// バッチ実行時間の取得
    	BatchCreatedTime batchCreatedTime = new BatchCreatedTime();
    	batchCreatedTime = mapper.load(BatchCreatedTime.class, 1);
    	
    	// 検索条件の指定
    	InstaInfo CondInstaInfo = new InstaInfo();
    	CondInstaInfo.setBatchCreatedId(batchCreatedTime.getBatchCreatedDate());
     
    	DynamoDBQueryExpression<InstaInfo> queryExpression = new DynamoDBQueryExpression<InstaInfo>()
    			.withHashKeyValues(CondInstaInfo)
    			.withScanIndexForward(false);
    	// 画像データの取得
     	List<InstaInfo> InstaInfoList = mapper.query(InstaInfo.class, queryExpression);
     	
     	InstaInfoForDisp dtoDisp = new InstaInfoForDisp();
     	dtoDisp.setInstaInfos(InstaInfoList);
     	try {
			Long dateTimeLong = new SimpleDateFormat("yyyyMMddHHmm").parse(batchCreatedTime.getBatchCreatedDate()).getTime();
			dtoDisp.setBatchCreatedDate(dateTimeLong.toString());
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
     	
     	// 全件検索のパラメータ用に生成
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
     	// 除外するユーザリストを取得
        List<ExclusionInstaUser> exclusionInstaUsers = mapper.scan(ExclusionInstaUser.class, scanExpression);
        List<String> exclusionInstaUsersList = new ArrayList<String>();
        for (ExclusionInstaUser exclusionInstaUser : exclusionInstaUsers) {
        	exclusionInstaUsersList.add(exclusionInstaUser.getUserName());
        }
     	
     	// petitmoタグの画像データ格納用のリスト
        List<InstaInfo> petitmoInfoList = new ArrayList<InstaInfo>();
     	
     	// petitmoダグの画像を取得
     	InstagramRespDto dto = apiCall(CommonConstants.TAG_PETITMO, CommonConstants.INSTA_GET_COUNT);
     	// データの詰め替え
     	for (InstagramInfo  instagramInfo : dto.getData()) {
     		// 除外対象ユーザかどうかチェック
        	if (exclusionInstaUsersList.contains(instagramInfo.getUser().getUsername())) {
        		continue;
        	}
     		
     		InstaInfo instaInfo = new InstaInfo();
     		// オンラインによる取得のためNullを設定
            instaInfo.setBatchCreatedId(null);
            instaInfo.setCreateDateUserId(instagramInfo.getCreatedTime().concat("_").concat(instagramInfo.getUser().getId()));
            instaInfo.setUserName(instagramInfo.getUser().getUsername());
            instaInfo.setUserPictureUrl(instagramInfo.getUser().getProfilePicture());
            instaInfo.setLink(instagramInfo.getLink());
            instaInfo.setCreateDate(instagramInfo.getCreatedTime());
            instaInfo.setImageUrl(instagramInfo.getImages().getLowResolution().getUrl());
            
            // petitmoタグの画像Listに追加
            petitmoInfoList.add(instaInfo);
     	}
     	dtoDisp.setPetitmoInfos(petitmoInfoList);
     	
    	
        return dtoDisp;
    }
    
    private InstagramRespDto apiCall(String tag, String count) {
    	// APICallを行うクライアント生成
        RestClient client = new RestClient("admin", "admin");
        
        // API URLの生成
        StringBuilder buf = new StringBuilder();
        buf.append(CommonConstants.API_URL_BASE);
        try {
			buf.append(URLEncoder.encode(tag, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
        buf.append(CommonConstants.aPI_URL_AFTER_TAG);
        buf.append(CommonConstants.ACCESS_TOKEN_URL);
        buf.append(CommonConstants.CLIENT_ID_URL);
        buf.append(CommonUtil.getClientApiKey());
        buf.append(CommonConstants.COUNT_URL);
        buf.append(count);
        String url = buf.toString();
        
        // API 呼び出し
        String json = client.getString(url, MediaType.APPLICATION_JSON_TYPE);

        // Json to POJO
        ObjectMapper mapper = new ObjectMapper();
        InstagramRespDto dto = new InstagramRespDto();
        
		try {
			// 変換処理
			dto = mapper.readValue(json,InstagramRespDto.class);
		} catch (JsonParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
        return dto;
    }
}

