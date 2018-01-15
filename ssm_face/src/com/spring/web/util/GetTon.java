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

		// ��ȡtoken ��ַ
		String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
		// ������ȡ��Secret Key ����Ϊ��ע���
		String clientId = "m6q88e65cuElpsgAdpP3vi5X";
		String clientSecret = "kE3f9RYlbxCIKQrKSoAhLyLTzDCDN4bI";
		String getAccessTokenUrl = authHost
		// 1.grant_typeΪ�̶�����
				+ "grant_type=client_credentials"
				// 2.�������API Key
				+ "&client_id=" + clientId
				// 3.������ȡ��Secret Key
				+ "&client_secret=" + clientSecret;
		try {
			// ����url�������Ӷ���
			URL url = new URL(getAccessTokenUrl);
			// ������
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			// �������󷽷�
			connection.setRequestMethod("POST");
			// ����ʵ������
			connection.connect();
			// ��ȡURL����Ӧ
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
