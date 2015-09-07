package com.common;

public class CommonConstants {
	
	private CommonConstants () {}
	
	// InstagramAPI Base
	public static String API_URL_BASE = "https://api.instagram.com/v1/tags/";
	
	// InstagramAPI after Tag
	public static String aPI_URL_AFTER_TAG = "/media/recent?";
	
	// InstagramAPI(Bayas) URL
	public static String API_URL_BABYS = "https://api.instagram.com/v1/tags/babyfashion/media/recent?";

	// InstagramAPI(Kids) URL
	public static String API_URL_KIDS = "https://api.instagram.com/v1/tags/kidsfashion/media/recent?";
	
	// InstagramAPI(Snap) URL
	public static String API_URL_SNAP = "https://api.instagram.com/v1/tags/babysnap/media/recent?";
	
	// access_token URL
	public static String ACCESS_TOKEN_URL = "access_token=";
	
	// client_id URL
	public static String CLIENT_ID_URL = "&client_id=";
	
	// count URL
	public static String COUNT_URL = "&count=";
	
	// API 取得件数
	public static String GET_COUNT = "20";
	
	// テーブル名 SEARCH_INSTA_TAGS_M
	public static String TB_SEARCH_INSTA_TAGS_M = "SEARCH_INSTA_TAGS_M";
	
	// テーブル名 COUNT_INSTA_TAGS_M
	public static String TB_COUNT_INSTA_TAGS_M = "COUNT_INSTA_TAGS_M";
		
	// テーブル名 INSTA_INFO_T
	public static String TB_INSTA_INFO_T = "INSTA_INFO_T";
	
	// テーブル名 BATCH_CREATED_TIME_T
		public static String TB_BATCH_CREATED_TIME_T = "BATCH_CREATED_TIME_T";

}
