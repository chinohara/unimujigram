package com.example.batch;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.common.CommonConstants;
import com.common.CommonUtil;
import com.example.db.DynamoDBClient;
import com.example.models.BatchCreatedTime;
import com.example.models.CountInstaTag;
import com.example.models.InstaInfo;
import com.example.models.InstagramInfo;
import com.example.models.InstagramRespDto;
import com.example.models.SearchInstaTag;
import com.example.rest.RestClient;

public class CreateDataProcess {
	
    public static void main(String[] args)
    {
        System.out.println("CreateDataProcess executed.");
        // バッチ実行時間を取得
        Timestamp datetime = new Timestamp(System.currentTimeMillis());
        String dateTimeStr = new SimpleDateFormat("yyyyMMddHHmm").format(datetime);
        System.out.println("dateTimeStart:" + dateTimeStr);
        
        // DynamoDBのClient取得
     	DynamoDBClient dynamoDBClient = new DynamoDBClient(CommonUtil.getAwsAccessKeyId(), CommonUtil.getAwsSecretAccessKey());
        DynamoDBMapper mapper = dynamoDBClient.getDynamoDBMapper();
        
        // 全件検索のパラメータ用に生成
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        
        //Batchの実行時間を登録
        BatchCreatedTime batchCreatedTime = new BatchCreatedTime();
        batchCreatedTime.setId(1);
        batchCreatedTime.setBatchCreatedDate(dateTimeStr);
        
        // DBのデータを削除
        mapper.batchDelete(mapper.scan(BatchCreatedTime.class, scanExpression));
        mapper.save(batchCreatedTime);

        // Instagramのパラメータに指定するタグの取得
        List<SearchInstaTag> searchTags = mapper.scan(SearchInstaTag.class, scanExpression);
        
        // 取得した情報に優先度をつけるタグを取得
        List<CountInstaTag> countTags = mapper.scan(CountInstaTag.class, scanExpression);
        
        // InstagramIdのset(重複確認に使用)
        Set<String> set = new HashSet<String>();
        
        // 保存データ格納用のリスト
        List<InstaInfo> instaInfoList = new ArrayList<InstaInfo>();
        
        // Instagramから、タグで数回データの取得
        for (SearchInstaTag searchInstaTag :searchTags) {
        	// タグを元にInstagramからデータを取得
        	InstagramRespDto dto = apiCall(searchInstaTag.getTag(), CommonConstants.INSTA_GET_COUNT);
        	
        	// 取得したデータの詰替えとカウント
            for (InstagramInfo  instagramInfo : dto.getData()) {
              String instaId = instagramInfo.getId();
              // 重複を除外、表示の優先度を決めるため、タグをカウントして、1より大きいもののみ対象とする
              if (!set.contains(instaId) && countTag(instagramInfo.getTags(), countTags) > 1) {
                // 存在しない場合、setに追加、Listに追加
                set.add(instaId);
                
                // 値の詰め替え
                InstaInfo instaInfo = new InstaInfo();
                instaInfo.setBatchCreatedId(dateTimeStr);
                instaInfo.setCreateDateUserId(instagramInfo.getCreatedTime().concat("_").concat(instagramInfo.getUser().getId()));
                instaInfo.setUserName(instagramInfo.getUser().getUsername());
                instaInfo.setUserPictureUrl(instagramInfo.getUser().getProfilePicture());
                instaInfo.setLink(instagramInfo.getLink());
                instaInfo.setCreateDate(instagramInfo.getCreatedTime());
                instaInfo.setImageUrl(instagramInfo.getImages().getLowResolution().getUrl());
                
                // 保存用Listに追加
                instaInfoList.add(instaInfo);
              }
            }
        }
        // DBのデータを削除
        mapper.batchDelete(mapper.scan(InstaInfo.class, scanExpression));
        
        // DBにデータを保存
        System.out.println("Create data:" + instaInfoList.size());
        mapper.batchSave(instaInfoList);
        System.out.println("CreateDataProcess Done.");
        
        Timestamp datetimeEnd = new Timestamp(System.currentTimeMillis());
        String dateTimeEnd = new SimpleDateFormat("yyyyMMddHHmm").format(datetimeEnd);
        System.out.println("dateTimeEnd:" + dateTimeEnd);
    }
    
    private static InstagramRespDto apiCall(String tag, String count) {
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
    
    /**
	 * 優先度を判断するため、カウント対象のタグがいくつあるかカウントする
	 * @param targetList カウントされるタグのリスト. countTags 数え上げるタグのリスト
	 * @return AWSのAccessKey
	 */
    private static int countTag(List<String> targetList, List<CountInstaTag> countTags){
    	int count = 0;
        for (CountInstaTag tag : countTags) {
        	// 一致する場合(indexが返される)、カウント
        	if (targetList.indexOf(tag.getTag()) >= 0) {
        		count = count + 1;
        	}
        }
    	return count;
    }
}
