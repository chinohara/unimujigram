package com.example.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.TimeZone;

import com.common.CommonConstants;
import com.common.CommonUtil;
import com.example.models.InstagramRespDto;
import com.example.models.TestDto;
import com.example.models.Time;
import com.example.models.User;
import com.example.rest.RestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

@Path("/time")
@Produces(MediaType.APPLICATION_JSON)
public class TimeService {
	private Properties properties = new Properties();

    @GET
    public Time get() {
        return new Time();
    }
    
    @GET
    @Path("/{timezone}")
    public Time get(@PathParam("timezone") String timezone) {
        return new Time(TimeZone.getTimeZone(timezone.toUpperCase()));
    }
    
    @GET
    @Path("/hello/{param}")
    public TestDto getHello(@PathParam("param") String param) {
    	TestDto dto = new TestDto();
    	dto.setStr("Hello!!" + param);
        return dto;
    }

    @GET
    @Path("/api")
    public InstagramRespDto getApi() {		
		// APICallを行うクライアント生成
        RestClient client = new RestClient("admin", "admin");
        
        // API URLの生成
        StringBuilder buf = new StringBuilder();
        buf.append(CommonConstants.API_URL);
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

