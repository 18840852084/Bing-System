package com.ourq20.Tools;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import com.ourq20.model.param;
import com.ourq20.model.requestAddCount;
import com.ourq20.model.requestParam;
import com.ourq20.springController.newGameController;

public class funHelper {
	/**
	 * 合并两个list列表，得到一个含有相同元素的新的list
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static List<String> MergeList(List<String >list1,List<String>list2)
	{
		List<String>list=new ArrayList<String>();
		Iterator<String> it=list1.iterator();
		while(it.hasNext())
		{
			if(list2.contains(it.next()))
			{
				list.add(it.next());
			}
		}
		return list;
	}
	/**
	 * 对向用户发送的param数据包装成json格式
	 * @param par
	 * @return
	 */
	public static JSONObject getJsonByParam(param par)
	{
		Map<String,Object> map=new HashMap<String, Object>();
		if(par!=null)
		{
			map.put("value", par.getValue());
			map.put("flag",par.getFlag());
			map.put("attrName", par.getAttrName());
		}
		JSONObject jObject=new JSONObject(map);
		return jObject;
	}
	public static JSONObject getJsonByRequstParam(requestParam par)
	{
		Map<String,Object> map=new HashMap<String, Object>();
		if(par!=null)
		{
			map.put("value", par.getValue());
			map.put("flag",par.getFlag());
			map.put("attrName", par.getAttrName());
			map.put("answer", par.getAnswer());
		}
		JSONObject jObject=new JSONObject(map);
		return jObject;
		
	}
	/**
	 * 得到一个resultParm封装的json数据
	 * @param par
	 * @param name
	 * @param count
	 * @return
	 */
	public static JSONObject getResultJson(param par,String name,int count)
	{
		Map<String, Object>map=new HashMap<String,Object>();
		if(par!=null)
		{
			map.put("value", par.getValue());
			map.put("flag",par.getFlag());
			map.put("attrName", par.getAttrName());
		}
		map.put("answer", name);
		map.put("count", count);
		JSONObject jObject=new JSONObject(map);
		return jObject;
	}
	/**
	 * 解析json数据并返回一个requestParam对象
	 * @param jsonString
	 * @return 
	 */
	public static requestParam ParseJson(String jsonString)
	{
		requestParam reqPar=new requestParam();
		JSONObject jb;
		try {
				jb = new JSONObject(jsonString);
				reqPar.setAttrName(jb.getString("attrName"));
				reqPar.setValue(jb.getString("value"));
				reqPar.setFlag(jb.getInt("flag"));
				reqPar.setAnswer(jb.getInt("answer"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reqPar;

	}
	public static param ParseClientJson(String jsonString)
	{
		param reqPar=new requestParam();
		JSONObject jb;
		try {
				jb = new JSONObject(jsonString);
				reqPar.setAttrName(jb.getString("attrName"));
				reqPar.setValue(jb.getString("value"));
				reqPar.setFlag(jb.getInt("flag"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reqPar;
	}
	
	public static requestAddCount ParseAddCount(String jsonString)
	{
		requestAddCount result=new requestAddCount();
		try {
			JSONObject jb=new JSONObject(jsonString);
			result.setAnswer(jb.getInt("answer"));
			result.setName(jb.getString("name"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}
