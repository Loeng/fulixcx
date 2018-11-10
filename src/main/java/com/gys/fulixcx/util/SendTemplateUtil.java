package com.gys.fulixcx.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


import com.gys.fulixcx.mode.TemplateMessage;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.sun.org.apache.bcel.internal.generic.IFNULL;


/**
 * 发送模板消息工具类
 *
 * @author 钮豪
 * @date 2018年1月31日
 */
public class SendTemplateUtil {
    private static Object SendTemplate;

    public static String SendTemplates(String openId, String form_id, String access_token) {
/*	String getH5OpenidUrl = DefinedChars.getXCXccessToken();
    String rec = httpGet(getH5OpenidUrl);
	JSONObject json = JSON.parseObject(rec);
	String access_token = json.getString("access_token");
	System.out.println("---"+access_token);*/
		
    /*	String openId="ow1c95cNcEuukFbO5Q6sXtYjA50Y";
    	String template_id="M1YJVuvm9Po91L9mlhmuiUx5P9K3ibRy6ZdkNguDhQ8";
    	String form_id="1517365450606";*/
        String responseStr = "";
        String accessToken = access_token;
        //发送模板消息
        String urlStr = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + accessToken;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setUseCaches(false);
            urlConnection.setInstanceFollowRedirects(true);
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("Charset", "UTF-8");
            urlConnection.connect();
            DataOutputStream out = new DataOutputStream(urlConnection.getOutputStream());
            /*String first = "";
            String keyword1 = "";
            String keyword2 = "";
            String keyword3 = "";
            String keyword4 = "";
            String keyword5 = "";
            String keyword6 = "";
            keyword1 = "\"keyword1\":{" +
                    "\"value\":\"分钱活动\"" +
                    //"\"color\":\"" + templateMessage.get(0).getTemplateMessage_keyword1color() + "\"" +
                    "}";
            keyword2 = "\"keyword2\":{" +
                    "\"value\":\"参加活动\"" +
                    "}";
            keyword3 = "\"keyword3\":{" +
                    "\"value\":\"钱被扣了\"" +
                    "}";

            String params = "{" +
                    "\"touser\":\"" + openId + "\"," +
                    "\"template_id\":\"_Yd6tNC_OtkWrRpjgyuDW1oeiWKPOBj4T046WgemJ14\"," +
                    "\"page\":\"pages/index/index\"," +
                    "\"form_id\":\"" + form_id + "\"," +
                    "\"emphasis_keyword\":\"钱被扣了\"," +
                    "\"data\":{" +
                    first + keyword1 + keyword2 + keyword3 + keyword4 + keyword5 + keyword6 +
                    "}" +
                    "}";*/
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("touser",openId);
            jsonObject.put("template_id","_Yd6tNC_OtkWrRpjgyuDW1oeiWKPOBj4T046WgemJ14");
            jsonObject.put("page","pages/index/index");
            jsonObject.put("form_id",form_id);
            JSONObject object = new JSONObject();
            JSONObject keyword1 = new JSONObject();
            keyword1.put("value","邀请好友领红包");
            object.put("keyword1",keyword1);
            JSONObject keyword2 = new JSONObject();
            keyword2.put("value","现金红包，到账零钱");
            object.put("keyword2",keyword2);
            JSONObject keyword3 = new JSONObject();
            keyword3.put("value","好友领红包，你也同样得红包哦！");
            object.put("keyword3",keyword3);
            jsonObject.put("data",object);
            jsonObject.put("emphasis_keyword","keyword1.DATA");
            //System.out.println(params);
            out.write(jsonObject.toString().getBytes("UTF-8"));
            out.flush();
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String line;

            while ((line = reader.readLine()) != null) {
                responseStr += line;
            }
            reader.close();
            urlConnection.disconnect();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return responseStr;
    }
}  