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
	 * 得到一个特殊问题
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
			par.setFlag(1);//表示特殊问题
			return par;
		}
		else {
			return null;
		}
		
		
		
	}
	/**
	 * 判断产生的特殊问题是否有效
	 * @param par
	 * @param lastSpecPro
	 * @param answer
	 * @return
	 */
	public static boolean isNewSpecQuestionValid(specParm par,specParm lastSpecPro)
	{
		//此人的该属性为“”无效
		if(par.getValue().equals(""))
		{
			return false;
		}
		//属性不为空
		else 
		{
			//如果是第一个特殊问题
			if(lastSpecPro==null)
			{
				return true;
			}
			else 
			{
				//前后两次问的是同一个人
				if(par.getName().equals(lastSpecPro.getName()))
				{
					//并且是同一个问题
					if(par.getAttrName().equals(lastSpecPro.getAttrName()))
					{
						return false;
					}
					//同一个人，但不是同一个问题
					else {
						//问题回答是否，可以因此排除此人
						if(lastSpecPro.getAnswer()==0)
						{
							return false;
						}
						//问题回答是肯定或者不知道
						else {
							return true;
						}
					}
				}
				//前后两次不是同一个人
				else {
					return true;
				}
			}
		}
		
	}
	/**
	 *从特殊问题表中 得到namelist中count最大的人的姓名
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
			int countMostIndex=0;//count最大人物的下表
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
	 * 从1,2,3,4中得到一个随机数
	 */
	public static int getRandom()
	{
		Random random=new Random();
		int ran=random.nextInt(4)+1;
		return ran;
	}
	/**
	 * 构造新的specParm对象
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
