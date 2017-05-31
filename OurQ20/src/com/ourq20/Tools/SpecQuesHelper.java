package com.ourq20.Tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ourq20.model.param;
import com.ourq20.model.requestParam;
import com.ourq20.model.specParm;
import com.ourq20.model.specPro;
import com.sun.org.apache.xml.internal.serializer.utils.StringToIntTable;

public class SpecQuesHelper {
	/**
	 * �õ�һ����������
	 * @param nameList
	 * @return
	 */
	public static specParm getSpecQuesBy(List<String> nameList)
	{
		specParm par=new specParm();
		String name=getMostPouOfNameList(nameList);
		if(name!=null)
		{
			int ranNum=getRandom();
			String attrName="attr"+ranNum;
			String value=jdbcHelper.jspd.getAttrValue(name, ranNum);
			par.setName(name);
			par.setAttrName(attrName);
			par.setValue(value);
			par.setFlag(1);//��ʾ��������
			return par;
		}
		else {
			return null;
		}
		
		
		
	}
	/**
	 * �жϲ��������������Ƿ���Ч
	 * @param par
	 * @param lastSpecPro
	 * @param answer
	 * @return
	 */
	public static boolean isNewSpecQuestionValid(specParm par,specParm lastSpecPro)
	{
		//���˵ĸ�����Ϊ������Ч
		if(par.getValue().equals(""))
		{
			return false;
		}
		//���Բ�Ϊ��
		else 
		{
			//����ǵ�һ����������
			if(lastSpecPro==null)
			{
				return true;
			}
			else 
			{
				//ǰ�������ʵ���ͬһ����
				if(par.getName().equals(lastSpecPro.getName()))
				{
					//������ͬһ������
					if(par.getAttrName().equals(lastSpecPro.getAttrName()))
					{
						return false;
					}
					//ͬһ���ˣ�������ͬһ������
					else {
						//����ش��Ƿ񣬿�������ų�����
						if(lastSpecPro.getAnswer()==0)
						{
							return false;
						}
						//����ش��ǿ϶����߲�֪��
						else {
							return true;
						}
					}
				}
				//ǰ�����β���ͬһ����
				else {
					return true;
				}
			}
		}
		
	}
	/**
	 *������������� �õ�namelist��count�����˵�����
	 * @param nameList
	 * @return
	 */
	public static String getMostPouOfNameList(List<String> nameList)
	{
			List<Integer> countlist=new ArrayList<Integer>();
			for(int i=0;i<nameList.size();i++)
			{
				specPro tempSpecPro=jdbcHelper.jspd.getSpecProByName(nameList.get(i));
				countlist.add(tempSpecPro.getCount());
			}
			int countMostIndex=0;//count���������±�
			int countMost;
			if(!countlist.isEmpty())
			{
				countMost=countlist.get(0);
				for(int i=1;i<countlist.size();i++)
				{
					if(countlist.get(i)>countMost)
					{
						countMost=countlist.get(i);
						countMostIndex=i;
					}
				}
				return nameList.get(countMostIndex);
			}
			else {
				return null;
			}
	}
	/**
	 * ��1,2,3,4�еõ�һ�������
	 */
	public static int getRandom()
	{
		Random random=new Random();
		int ran=random.nextInt(4)+1;
		return ran;
	}
	/**
	 * �����µ�specParm����
	 * @param reqParam
	 * @param name
	 * @return
	 */
	public static specParm getSpecParm(requestParam reqParam,String name)
	{
		specParm speParm=new specParm();
		speParm.setName(name);
		speParm.setAnswer(reqParam.getAnswer());
		speParm.setAttrName(reqParam.getAttrName());
		speParm.setValue(reqParam.getValue());
		speParm.setFlag(reqParam.getFlag());
		return speParm;
		
	}

}
