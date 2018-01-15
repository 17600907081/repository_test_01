package com.spring.web.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

public class GetTon {

	public static String getToken() {
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();

		// 获取token 地址
		String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
		// 官网获取的Secret Key 更新为你注册的
		String clientId = "m6q88e65cuElpsgAdpP3vi5X";
		String clientSecret = "kE3f9RYlbxCIKQrKSoAhLyLTzDCDN4bI";
		String getAccessTokenUrl = authHost
		// 1.grant_type为固定参数
				+ "grant_type=client_credentials"
				// 2.官网获得API Key
				+ "&client_id=" + clientId
				// 3.官网获取的Secret Key
				+ "&client_secret=" + clientSecret;
		try {
			// 创建url网络链接对象
			URL url = new URL(getAccessTokenUrl);
			// 打开链接
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			// 设置请求方法
			connection.setRequestMethod("POST");
			// 进行实际链接
			connection.connect();
			// 读取URL的响应
			br = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = JSONObject.fromObject(sb.toString());
		String token = jsonObject.getString("access_token");
		return token;
	}

	public static void main(String[] args) {
		String tonken = getToken();
		System.out.println(tonken);
	}
}
