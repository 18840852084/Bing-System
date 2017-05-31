package com.ourq20.springController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.http.protocol.HTTP;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ourq20.Tools.OrdinQuesHelper;
import com.ourq20.Tools.SpecQuesHelper;
import com.ourq20.Tools.funHelper;
import com.ourq20.Tools.jdbcHelper;
import com.ourq20.model.param;
import com.ourq20.model.requestParam;
import com.ourq20.model.specParm;


//用来处理一般问题的请求
@Controller
@RequestMapping("/handOrdinQues")
public class handOrdinQuesContronller {
	private int []counter=new int[4];
	private List<requestParam>questList=new ArrayList<requestParam>(); //用于记录用户回答的问题及答案
	private List<String>nameList=new ArrayList<String>();//用于记录此次答案后符合要求的人物的姓名
	//private String specName;
	private int count=0;
	@RequestMapping(method=RequestMethod.POST)
	public void handlerOrdAns(HttpServletRequest request,HttpServletResponse response){
		 response.setContentType("text/html;charset=utf-8");
	     response.setCharacterEncoding("utf-8");
	     try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpSession session=request.getSession();
		session.setMaxInactiveInterval(1800);//设置session最大存活时间
		String paramString=request.getParameter("param");
		//测试
		System.out.println(paramString);
		if(session.getAttribute("counter")!=null)
		{
			counter=(int[]) session.getAttribute("counter");
		}
		if(session.getAttribute("questList")!=null)
		{
			questList=(List<requestParam>) session.getAttribute("questList");
		}
		if(session.getAttribute("nameList")!=null)
		{
			nameList=(List<String>) session.getAttribute("nameList");
		}
		int level=0;//用于生成某一层的问题
		requestParam reqPam=funHelper.ParseJson(paramString);
		int flag=reqPam.getFlag();
		param newParam=new param();
		//flag=2表示游戏刚刚开始，开始生成问题
		if(flag==2)
		{
			//初始化一些变量值
			nameList=jdbcHelper.jspd.getAllName();
		//	System.out.println("counter.length"+counter.length);
			for(int i=0;i<counter.length;i++)
			{
				counter[i]=0;
			}
			while(true)
			{
				level=OrdinQuesHelper.getStartRandom();
				newParam=OrdinQuesHelper.getQrdinQuesByLevel(level);
				if((!newParam.getAttrName().equals("nativePlace")))
				{
					break;
				}
			}
			//测试
			System.out.println(newParam.getAttrName());
		//	map.put("param", funHelper.getJsonByParam(newParam));
			counter[level]++;
		}
		//flag=0表示用户回答的是一般问题
		else if(flag==0) 
		{
			//测试
			System.out.println("这是一般的问题："+reqPam.getAttrName()+reqPam.getAnswer());
			String attrName=reqPam.getAttrName();
			int answer=reqPam.getAnswer();
			String value=reqPam.getValue();
			System.out.println(value);
			//用户回答的不是
			if(answer==0)
			{
				if(attrName.equals("dateOfBorn"))
				{
					int age=new Integer(value);
					nameList=jdbcHelper.jpbd.getAllNamebyNAge(age, nameList); 
				}
				else if(attrName.equals("occupation"))
				{
					nameList=jdbcHelper.jpbd.getAllNamebyNOccu(value, nameList);
				}
				else if(attrName.equals("popuAttr1")||attrName.equals("popuAttr2")||attrName.equals("popuAttr3")||attrName.equals("popuAttr4")){
					nameList=jdbcHelper.jpbd.getAllNamebyPopuAttr(attrName, answer, nameList);
					
				}
				else {
					nameList=jdbcHelper.jpbd.getAllNamebyAttriAndNValue(attrName, value,nameList);
				}
				questList.add(reqPam);
			}
			//用户回答的是
			else if(answer==1)
			{
				if(attrName!=null)
				{
					if(attrName.equals("dateOfBorn"))
					{
						int age=new Integer(value);
						nameList=jdbcHelper.jpbd.getAllNamebyAge(age, nameList);
					}
					else if(attrName.equals("occupation")) {
						nameList=jdbcHelper.jpbd.getAllNamebyOccu(value, nameList);
					}
					else if(attrName.equals("popuAttr1")||attrName.equals("popuAttr2")||attrName.equals("popuAttr3")||attrName.equals("popuAttr4")){
						nameList=jdbcHelper.jpbd.getAllNamebyPopuAttr(attrName, answer, nameList);
						
					}
					else {
						nameList=jdbcHelper.jpbd.getAllNamebyAttriAndYValue(attrName,value,nameList);
					}
				}
				questList.add(reqPam);
			}
			//用户回答的不知道
			else {
				questList.add(reqPam);
			}
			//如果nameList中人物的数量还是多于某个值，则继续询问一般问题,生成新的问题
			count=counter[0]+counter[1]+counter[2]+counter[3];
			if(nameList.size()>3)
			{
				//基本问题最多问9个
				if(count<9)
				{
					while(true)
					{
						level=OrdinQuesHelper.getRandom();
						newParam=OrdinQuesHelper.getQrdinQuesByLevel(level);
						boolean isValid=OrdinQuesHelper.isNewQuestionValid(level, newParam, counter, questList);
						if(isValid)
						{
							break;
						}
					}
					counter[level]++;
				}
				//如果基本问题已经询问结束
				else {
					specParm specTemp=SpecQuesHelper.getSpecQuesBy(nameList);
					//theFirstSpecName=specTemp.getName();
					newParam=specTemp;
					//session.setAttribute("theFirstSpecName", theFirstSpecName);
				}
				
				//map.put("param", funHelper.getJsonByParam(newParam));
			}
			//否则开始询问特殊问题
			else if(nameList.size()>0&&nameList.size()<=3){
				specParm specTemp=SpecQuesHelper.getSpecQuesBy(nameList);
				//specName=specTemp.getName();
				newParam=specTemp;
				//session.setAttribute("specName", specName);
			}
			else {
				newParam=null;
			}
		}
		//测试
		System.out.println(nameList);
		for(int i=0;i<counter.length;i++)
		{
			System.out.println("已询问的第"+i+"层问题数目："+counter[i]);
		}
		
		//表示非正常结束，服务器没有猜中人物姓名
		if(newParam==null)
		{
			newParam=new param();
			newParam.setFlag(4);
			newParam.setAttrName("");
			newParam.setValue("");
		}
		//处理session中的变量
		session.setAttribute("nameList",nameList);
		session.setAttribute("questList", questList);
		session.setAttribute("counter", counter);
		//向客户端返回json数据
		
		//测试
		System.out.println(newParam.getAttrName());
		if(questList.isEmpty())
		{
			System.out.println("questlist is empty!");
		}
		else {
			for(int i=0;i<questList.size();i++)
			{
				String value=questList.get(i).getValue();
				System.out.println(questList.get(i).getAttrName()+":"+value);
			}
			
		}
		
		JSONObject jo=funHelper.getJsonByParam(newParam);
		response.setCharacterEncoding("utf-8");
	    response.setHeader("Content-type","text/html;charset=utf-8");
		PrintWriter out = null;  
	    try {  
	        out = response.getWriter();  
	        out.append(jo.toString());  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        if (out != null) {  
	        	out.flush();
	            out.close();  
	        }  
	    }  
	//	return "viewResponse";
	}
}
