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
	 * API のクライアントのKeyを取得する.
	 * @return Client API Key
	 */
	public static String getClientApiKey() {
		
		InputStream inputStream;
		String clientId = "";
		Properties properties = new Properties();
		
		// Envファイルから、ClientAPIKeyを取得
		try {
			inputStream = new FileInputStream(new File("env.properties"));
			properties.load(inputStream);
			clientId =properties.getProperty("apiClientId");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			// 環境変数から取得
			clientId = System.getenv("INSTAGRAM_CLIENT_API_KEY");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return clientId;
	}
}
