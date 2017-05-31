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
//���������������������
@Controller
@RequestMapping("/handSpecQues")
public class handSpecQuesController {
	private List<String>nameList=new ArrayList<String>();//���ڼ�¼�˴δ𰸺����Ҫ������������
	private List<specParm> specParamList=new ArrayList<specParm>();
	private specParm lastSpecParm=new specParm();//��һ����������

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
		session.setMaxInactiveInterval(1800);//����session�����ʱ��
		String paramString=request.getParameter("param");
		boolean isContine=true;//��ʾ�Ƿ����������������
		if(session.getAttribute("nameList")!=null)
		{
			nameList=(List<String>) session.getAttribute("nameList");
		}
		else {
			System.out.println("���ԣ�namelistΪ�գ�");
		}
		if(session.getAttribute("specParamList")!=null)
		{
			specParamList=(List<specParm>) session.getAttribute("specParamList");
		}
		if(session.getAttribute("lastSpecParm")!=null)
		{
			lastSpecParm=(specParm) session.getAttribute("lastSpecParm");
		}

		//����
		System.out.println(paramString);
		System.out.println("��һ�������ǣ�"+lastSpecParm.getName()+lastSpecParm.getValue());
		System.out.println("nameList:"+nameList);
		requestParam tempreqPam=funHelper.ParseJson(paramString);
		specParm newParam=new specParm();
		String name=SpecQuesHelper.getMostPouOfNameList(nameList);
		System.out.println("����name="+name);
		specParm reqParm=SpecQuesHelper.getSpecParm(tempreqPam, name);
		System.out.println("����reqParam"+reqParm.getValue());
		//�񶨻ش�
		if(reqParm.getAnswer()==0)
		{
			nameList.remove(name);
			lastSpecParm=reqParm;
			specParamList.add(reqParm);
		}
		//�϶��ش�
		else if(reqParm.getAnswer()==1)
		{
			//��һ����������
			if(specParamList.size()==0)
			{
				System.out.println("���ԣ�����϶��ش��һ����������");
				lastSpecParm=reqParm;
				specParamList.add(reqParm);
			}
			//���ǵ�һ����������
			else
			{
				//ͬһ������
				if(lastSpecParm.getName().equals(name))
				{
					//��һ����������Ϊͬһ���˲��������Ҳ�ǿ϶��ģ��²�������õ���
					if(lastSpecParm.getAnswer()==1)
					{
						//��ʾ�²����
						newParam.setFlag(3);
						int count=jdbcHelper.jspd.getCountByName(reqParm.getName());
						newParam.setValue(count+"");//��newParam�е�answer����Ϊcount
						newParam.setAttrName(reqParm.getName());//��newParam�е�attrName����Ϊname
						isContine=false;//���ټ���������������
						specParamList.add(reqParm);
						lastSpecParm=reqParm;
						//map.put("param", funHelper.getResultJson(newParam,name , count));
						//return "viewResponse";
					}
					//��һ����������ش�֪��
					else 
					{
						specParamList.add(reqParm);//�����ε��µ����⼰����Ϣ����lastSpecpram
						lastSpecParm=reqParm;
					}
				}
				//����ͬһ������
				else {
					lastSpecParm=reqParm;
					specParamList.add(reqParm);
				}
			}
			}
		//�û��ش���ǲ�֪��
		else 
		{
			specParamList.add(reqParm);
			lastSpecParm=reqParm;
		}
		int []counter=(int[])session.getAttribute("counter");
		//int totalCount=counter[0]+counter[1]+counter[2]+specParamList.size();
		//��ʱ����ʣ��һ������õ���
		if(nameList.size()==1)
		{
			newParam.setFlag(3);//��ʾ��������
			newParam.setAttrName(nameList.get(0));
			int count=jdbcHelper.jspd.getCountByName(nameList.get(0));
			newParam.setValue(count+"");
			//isContine=false;//���ټ���������������
			newParam.setValue(count+"");//��newParam�е�answer����Ϊcount
		}
		//namlist�����п�ѡ�������ʱ������������������û�в�����������
		else if(nameList.size()==0)
		{
			newParam.setFlag(4);
			newParam.setAttrName("");
			newParam.setValue("");
			//isContine=false;//���ټ���������������
		}
		//���������µ���������
		else {
			//�����µ�param
			while (isContine) {
				newParam=SpecQuesHelper.getSpecQuesBy(nameList);
				if(SpecQuesHelper.isNewSpecQuestionValid(newParam, lastSpecParm))
					break;
			}
		}
			
		//��session�е�һЩ�������д���
		session.setAttribute("nameList", nameList);
		session.setAttribute("specParamList", specParamList);
		session.setAttribute("lastSpecParm", lastSpecParm);
		//����
		System.out.println(nameList);
		//�����ص�����
		//map.put("parm", funHelper.getJsonByParam(newParam));
		//��ͻ��˷���json����
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
