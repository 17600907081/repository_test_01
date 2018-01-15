package com.spring.web.action;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.web.entity.User;
import com.spring.web.service.FaceService;
import com.spring.web.util.GetTon;

@Controller
public class UserControll {

	private static String accessToken;

	@Resource
	private FaceService faceService;

	@ResponseBody
	@RequestMapping("/facelogin.action")
	public String onListStudent(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// ��ȡǰ��ҳ�洫�����Ĳ���
		String base = request.getParameter("base");
		System.out.println(base);
		try {

			List<User> users = this.faceService.selectAllUsers();
			String base64 = "";
			PrintWriter writer = response.getWriter();
			response.reset();

			for (User user : users) {
				base64 = new String(user.getBase64());
				boolean result = getResult(base, base64);
				if (result) {
					request.getSession().setAttribute("user", user);
					// ��resultת����json��ʽ�ַ���
					System.out.println(result);
					// ���͸��ͻ���
					writer.print(result);
					writer.close();
					return null;
				} else {

					System.out.println(result);
					writer.print(result);
					writer.close();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/page/404.jsp";
		}

		return null;
	}

	/** ����ʶ�� �ȶ� */
	public boolean getResult(String imStr1, String imgStr2) {

		accessToken = GetTon.getToken();
		boolean flag = false;
		BufferedReader br = null;
		String result = "";

		// ���������ַ
		String mathUrl = "https://aip.baidubce.com/rest/2.0/face/v2/match";
		try {
			// ƴ�Ӳ���
			String params = URLEncoder.encode("images", "UTF-8") + "="

			+ URLEncoder.encode(imStr1 + imgStr2, "UTF-8");
			// ƴ�������ַ
			String genrearlURL = mathUrl + "?access_token=" + accessToken;
			// �����������
			URL url = new URL(genrearlURL);
			// ����������
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			// �������󷽷�
			connection.setRequestMethod("POST");
			// ����ͨ�õ���������
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			// ����������������
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());
			out.writeBytes(params);
			// ˢ����
			out.flush();
			// �ر���
			out.close();
			// ����ʵ������
			connection.connect();
			// ��ȡURL����Ӧ
			br = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line = "";
			while ((line = br.readLine()) != null) {
				result += line;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(result);

		// result ="{"error_msg":"Unsupported openapi method","error_code":3}"

		JSONObject fromObject = JSONObject.fromObject(result);

		JSONArray jsonArray = fromObject.getJSONArray("result");

		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject object = (JSONObject) jsonArray.get(i);
			double resultList = object.getDouble("score");
			if (resultList >= 90) {
				flag = true;

			}
		}
		return flag;
	}

	@Test
	public void test() {
		getResult(null, null);
		System.out.println();

	}

}
