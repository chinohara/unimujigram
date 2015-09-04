package com.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
* 共通ユーティルクラス
*/
public class CommonUtil {
	
	private CommonUtil () {}
	
	/**
	 * Instagramの API クライアントのKeyを取得する.
	 * @return Client API Key
	 */
	public static String getClientApiKey() {
		String clientId = getPropertyValueOrEnvValue("apiClientId","INSTAGRAM_CLIENT_API_KEY");
		return clientId;
	}
	
	/**
	 * AWSのAccessKeyを取得する.
	 * @return AWSのAccessKey
	 */
	public static String getAwsAccessKeyId() {
		String awsAccessKeyId = getPropertyValueOrEnvValue("awsAccessKeyId","AWS_ACCESS_KEY_ID");
		return awsAccessKeyId;
	}
	/**
	 * AWSのSecretAccessKeyを取得する.
	 * @return AWSのSecretAccessKey
	 */
	public static String getAwsSecretAccessKey() {
		String clientId = getPropertyValueOrEnvValue("awsSecretAccessKey","AWS_SECRET_ACCESS_KEY");
		return clientId;
	}
	
	/**
	 * プロパティファイル or EnvファイルからValueを取得する
	 * @param プロパティファイルのキー,Envファイルのキー
	 * @return Value
	 */
	private static String getPropertyValueOrEnvValue(String propertyKey, String envKey){
		String value = "";
		
		InputStream inputStream;
		Properties properties = new Properties();
		
		// プロパティファイルから、Valueを取得
		try {
			inputStream = new FileInputStream(new File("env.properties"));
			properties.load(inputStream);
			value =properties.getProperty(propertyKey);
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			// 環境変数から取得
			value = System.getenv(envKey);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return value;
	};
}
