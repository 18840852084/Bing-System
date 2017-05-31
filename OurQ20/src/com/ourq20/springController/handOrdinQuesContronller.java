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


//��������һ�����������
@Controller
@RequestMapping("/handOrdinQues")
public class handOrdinQuesContronller {
	private int []counter=new int[4];
	private List<requestParam>questList=new ArrayList<requestParam>(); //���ڼ�¼�û��ش�����⼰��
	private List<String>nameList=new ArrayList<String>();//���ڼ�¼�˴δ𰸺����Ҫ������������
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
		session.setMaxInactiveInterval(1800);//����session�����ʱ��
		String paramString=request.getParameter("param");
		//����
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
		int level=0;//��������ĳһ�������
		requestParam reqPam=funHelper.ParseJson(paramString);
		int flag=reqPam.getFlag();
		param newParam=new param();
		//flag=2��ʾ��Ϸ�ոտ�ʼ����ʼ��������
		if(flag==2)
		{
			//��ʼ��һЩ����ֵ
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
			//����
			System.out.println(newParam.getAttrName());
		//	map.put("param", funHelper.getJsonByParam(newParam));
			counter[level]++;
		}
		//flag=0��ʾ�û��ش����һ������
		else if(flag==0) 
		{
			//����
			System.out.println("����һ������⣺"+reqPam.getAttrName()+reqPam.getAnswer());
			String attrName=reqPam.getAttrName();
			int answer=reqPam.getAnswer();
			String value=reqPam.getValue();
			System.out.println(value);
			//�û��ش�Ĳ���
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
			//�û��ش����
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
			//�û��ش�Ĳ�֪��
			else {
				questList.add(reqPam);
			}
			//���nameList��������������Ƕ���ĳ��ֵ�������ѯ��һ������,�����µ�����
			count=counter[0]+counter[1]+counter[2]+counter[3];
			if(nameList.size()>3)
			{
				//�������������9��
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
				//������������Ѿ�ѯ�ʽ���
				else {
					specParm specTemp=SpecQuesHelper.getSpecQuesBy(nameList);
					//theFirstSpecName=specTemp.getName();
					newParam=specTemp;
					//session.setAttribute("theFirstSpecName", theFirstSpecName);
				}
				
				//map.put("param", funHelper.getJsonByParam(newParam));
			}
			//����ʼѯ����������
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
		//����
		System.out.println(nameList);
		for(int i=0;i<counter.length;i++)
		{
			System.out.println("��ѯ�ʵĵ�"+i+"��������Ŀ��"+counter[i]);
		}
		
		//��ʾ������������������û�в�����������
		if(newParam==null)
		{
			newParam=new param();
			newParam.setFlag(4);
			newParam.setAttrName("");
			newParam.setValue("");
		}
		//����session�еı���
		session.setAttribute("nameList",nameList);
		session.setAttribute("questList", questList);
		session.setAttribute("counter", counter);
		//��ͻ��˷���json����
		
		//����
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
