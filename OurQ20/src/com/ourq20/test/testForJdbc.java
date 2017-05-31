package com.ourq20.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.http.client.CookieStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.Cookie;

import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.http.HttpResponse;
import  org.apache.http.NameValuePair;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.http.util.EntityUtils; 
import com.ourq20.Tools.funHelper;
import com.ourq20.model.param;
import com.ourq20.model.requestParam;

public class testForJdbc {
	private static DefaultHttpClient httpClient=new DefaultHttpClient();
	private static CookieStore cookieStore;
	public static void main(String []args) 
	{
	
		String url="";
		String url1="http://localhost:8080/OurQ20/handOrdinQues";
		String url2="http://localhost:8080/OurQ20/handSpecQues";
		int count=0;
		requestParam startparam=new requestParam();
		startparam.setAttrName("gender");
		startparam.setValue("女");
		startparam.setFlag(2);
		startparam.setAnswer(1);
		JSONObject jo=funHelper.getJsonByParam(startparam);
		String result=doPost(url1, jo);
		System.out.println(result);
		
//        cookieStore=httpClient.getCookieStore();
//		while(true)
//		{
//			System.out.println(result);
//			count++;
//			param param2=funHelper.ParseClientJson(result);
//			if(param2.getFlag()==3)
//			{
//				break;
//			}
//			System.out.println("您描述的对象是"+param2.getValue()+"吗？");
//			requestParam reqParam=new requestParam();
//			reqParam.setAttrName(param2.getAttrName());
//			reqParam.setFlag(param2.getFlag());
//			reqParam.setValue(param2.getValue());
//			 Scanner sc = new Scanner(System.in);
//			 // System.out.println(); //换行
//			  int answer = sc.nextInt(); 
//			  reqParam.setAnswer(answer);
//			jo=funHelper.getJsonByRequstParam(reqParam);
//			System.out.println(jo.toString());
//			if(param2.getFlag()==0)
//			{
//				url=url;
//			}
//			else{
//				url=url2;
//			}
//			httpClient.setCookieStore(cookieStore);
//            if(result=="")
//            {
//            	System.out.println("null");
//            	break;
//            }
//        
//			
//		}
		
	//	System.out.println(result);
	}
	public static String doPost(String url,JSONObject jo)
	{
		HttpPost httpPost=new HttpPost(url);
		List<NameValuePair> paramList=new ArrayList<NameValuePair>();
		NameValuePair param=new BasicNameValuePair("param", jo.toString());
		paramList.add(param);
		HttpEntity entity;
		try {
			entity = new UrlEncodedFormEntity(paramList,"utf-8");
			httpPost.setEntity(entity);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		StringBuilder sb=new StringBuilder();
		String result="";
		try {
			HttpResponse response=httpClient.execute(httpPost);
			if(response.getStatusLine().getStatusCode()==200)
			{
				result=EntityUtils.toString(response.getEntity()); 
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			httpPost.releaseConnection();
		}
		return result;
	}
	
	
}
