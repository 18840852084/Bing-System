package com.ourq20.test;

import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.client.params.CookiePolicy;
import org.apache.commons.httpclient.Cookie;
import org.json.JSONObject;

import com.ourq20.Tools.funHelper;
import com.ourq20.model.param;
import com.ourq20.model.requestParam;

public class testServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url="";
		String url1="http://localhost:8080/OurQ20/handOrdinQues";
		String url2="http://192.168.1.100:8080/OurQ20/handSpecQues";
		String result="";
		HttpClient httpClient=new HttpClient();
		httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
		PostMethod postMethod=new PostMethod(url1);
		requestParam startparam=new requestParam();
		startparam.setAttrName("123");
		startparam.setValue("-1");
		startparam.setFlag(2);
		startparam.setAnswer(-1);
		JSONObject jo=funHelper.getJsonByParam(startparam);
		NameValuePair paramNameValuePair[]={new NameValuePair("param",jo.toString())};
		postMethod.setRequestBody(paramNameValuePair);
		try {
			httpClient.executeMethod(postMethod);
			//NameValuePair nameValuePa=postMethod.getParameter("param");
		    //result=nameValuePa.getValue();
			result=postMethod.getResponseBodyAsString();
			System.out.println(result);
			param param=funHelper.ParseClientJson(result);
			System.out.println(param.getAttrName());
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		String tempcookie="";
//		Cookie[] cookies = httpClient.getState().getCookies();
//		for(Cookie c:cookies)
//		{
//			tempcookie=tempcookie+c.toString()+";";
//		}
//		System.out.println(tempcookie);
//		param param2=funHelper.ParseClientJson(result);
//		System.out.println("您描述的对象是"+param2.getValue()+"吗？");
//		requestParam reqParam=new requestParam();
//		reqParam.setAttrName(param2.getAttrName());
//		reqParam.setFlag(param2.getFlag());
//		reqParam.setValue(param2.getValue());
//		 Scanner sc = new Scanner(System.in);
//		 // System.out.println(); //换行
//		  int answer = sc.nextInt(); 
//		  reqParam.setAnswer(answer);
//		jo=funHelper.getJsonByRequstParam(reqParam);
//		if(param2.getFlag()==0)
//		{
//			url=url1;
//		}
//		else{
//			url=url2;
//		}
//		paramNameValuePair[0]=new NameValuePair("param",jo.toString());
//		postMethod.setRequestHeader("cookie", tempcookie);
//		postMethod.setRequestBody(paramNameValuePair);
//		try {
//			httpClient.executeMethod(postMethod);
//			result=postMethod.getResponseBodyAsString();
//			System.out.println(result);
//		} catch (HttpException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
	}

}
