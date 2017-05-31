package com.ourq20.springController;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ourq20.Tools.SpecQuesHelper;
import com.ourq20.Tools.funHelper;
import com.ourq20.Tools.jdbcHelper;
import com.ourq20.model.requestParam;
import com.ourq20.model.specParm;
//用来处理特殊问题的请求
@Controller
@RequestMapping("/handSpecQues")
public class handSpecQuesController {
	private List<String>nameList=new ArrayList<String>();//用于记录此次答案后符合要求的人物的姓名
	private List<specParm> specParamList=new ArrayList<specParm>();
	private specParm lastSpecParm=new specParm();//上一个特殊问题

	@RequestMapping(method=RequestMethod.POST)
	public void handlerOrdAns(HttpServletRequest request,HttpServletResponse response)
	{
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
		boolean isContine=true;//表示是否继续生成特殊问题
		if(session.getAttribute("nameList")!=null)
		{
			nameList=(List<String>) session.getAttribute("nameList");
		}
		else {
			System.out.println("测试：namelist为空！");
		}
		if(session.getAttribute("specParamList")!=null)
		{
			specParamList=(List<specParm>) session.getAttribute("specParamList");
		}
		if(session.getAttribute("lastSpecParm")!=null)
		{
			lastSpecParm=(specParm) session.getAttribute("lastSpecParm");
		}

		//测试
		System.out.println(paramString);
		System.out.println("上一个问题是："+lastSpecParm.getName()+lastSpecParm.getValue());
		System.out.println("nameList:"+nameList);
		requestParam tempreqPam=funHelper.ParseJson(paramString);
		specParm newParam=new specParm();
		String name=SpecQuesHelper.getMostPouOfNameList(nameList);
		System.out.println("测试name="+name);
		specParm reqParm=SpecQuesHelper.getSpecParm(tempreqPam, name);
		System.out.println("测试reqParam"+reqParm.getValue());
		//否定回答
		if(reqParm.getAnswer()==0)
		{
			nameList.remove(name);
			lastSpecParm=reqParm;
			specParamList.add(reqParm);
		}
		//肯定回答
		else if(reqParm.getAnswer()==1)
		{
			//第一个特殊问题
			if(specParamList.size()==0)
			{
				System.out.println("测试：进入肯定回答第一个特殊问题");
				lastSpecParm=reqParm;
				specParamList.add(reqParm);
			}
			//不是第一个特殊问题
			else
			{
				//同一个人物
				if(lastSpecParm.getName().equals(name))
				{
					//上一个提问人物为同一个人并且问题答案也是肯定的，猜测结束，得到答案
					if(lastSpecParm.getAnswer()==1)
					{
						//表示猜测结束
						newParam.setFlag(3);
						int count=jdbcHelper.jspd.getCountByName(reqParm.getName());
						newParam.setValue(count+"");//将newParam中的answer设置为count
						newParam.setAttrName(reqParm.getName());//将newParam中的attrName设置为name
						isContine=false;//不再继续生成特殊问题
						specParamList.add(reqParm);
						lastSpecParm=reqParm;
						//map.put("param", funHelper.getResultJson(newParam,name , count));
						//return "viewResponse";
					}
					//上一个提问人物回答不知道
					else 
					{
						specParamList.add(reqParm);//将本次的新的问题及答案信息赋给lastSpecpram
						lastSpecParm=reqParm;
					}
				}
				//不是同一个人物
				else {
					lastSpecParm=reqParm;
					specParamList.add(reqParm);
				}
			}
			}
		//用户回答的是不知道
		else 
		{
			specParamList.add(reqParm);
			lastSpecParm=reqParm;
		}
		int []counter=(int[])session.getAttribute("counter");
		//int totalCount=counter[0]+counter[1]+counter[2]+specParamList.size();
		//此时正好剩下一个人物，得到答案
		if(nameList.size()==1)
		{
			newParam.setFlag(3);//表示正常结束
			newParam.setAttrName(nameList.get(0));
			int count=jdbcHelper.jspd.getCountByName(nameList.get(0));
			newParam.setValue(count+"");
			//isContine=false;//不再继续生成特殊问题
			newParam.setValue(count+"");//将newParam中的answer设置为count
		}
		//namlist中无有可选的人物，此时非正常结束，服务器没有猜中人物姓名
		else if(nameList.size()==0)
		{
			newParam.setFlag(4);
			newParam.setAttrName("");
			newParam.setValue("");
			//isContine=false;//不再继续生成特殊问题
		}
		//继续生成新的特殊问题
		else {
			//产生新的param
			while (isContine) {
				newParam=SpecQuesHelper.getSpecQuesBy(nameList);
				if(SpecQuesHelper.isNewSpecQuestionValid(newParam, lastSpecParm))
					break;
			}
		}
			
		//对session中的一些变量进行处理
		session.setAttribute("nameList", nameList);
		session.setAttribute("specParamList", specParamList);
		session.setAttribute("lastSpecParm", lastSpecParm);
		//测试
		System.out.println(nameList);
		//处理返回的数据
		//map.put("parm", funHelper.getJsonByParam(newParam));
		//向客户端返回json数据
		JSONObject jo=funHelper.getJsonByParam(newParam);
		response.setCharacterEncoding("utf-8");
	    response.setHeader("Content-type","text/html;charset=UTF-8");
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
				
		//return "viewResponse";
	}

}
