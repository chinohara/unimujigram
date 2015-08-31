package com.example.services;

import java.io.IOException;

import com.common.CommonConstants;
import com.common.CommonUtil;
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

    @GET
    public Time get() {
        return new Time();
    }

    @GET
    @Path("babys")
    public InstagramRespDto getBabys() {		
		// APIを呼び出す
        InstagramRespDto dto = apiCall(CommonConstants.API_URL_BABYS);
        return dto;
    }
    
    @GET
    @Path("kids")
    public InstagramRespDto getKids() {		
		// APIを呼び出す
        InstagramRespDto dto = apiCall(CommonConstants.API_URL_KIDS);
        return dto;
    }
    
    @GET
    @Path("snap")
    public InstagramRespDto getSnap() {		
		// APIを呼び出す
        InstagramRespDto dto = apiCall(CommonConstants.API_URL_SNAP);
        return dto;
    }
    
    private InstagramRespDto apiCall(String uri) {
    	// APICallを行うクライアント生成
        RestClient client = new RestClient("admin", "admin");
        
        // API URLの生成
        StringBuilder buf = new StringBuilder();
        buf.append(uri);
        buf.append(CommonConstants.ACCESS_TOKEN_URL);
        buf.append(CommonConstants.CLIENT_ID_URL);
        buf.append(CommonUtil.getClientApiKey());
        buf.append(CommonConstants.COUNT_URL);
        buf.append(CommonConstants.GET_COUNT);
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

