package com.ourq20.Tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.jdt.internal.compiler.ast.FalseLiteral;

import sun.print.resources.serviceui;

import com.ourq20.model.attrlevel;
import com.ourq20.model.param;
import com.ourq20.model.requestParam;
import com.ourq20.springController.newGameController;

public class OrdinQuesHelper {
	/**
	 * 随机生成0,1，2,3中的任何一个数
	 * @return
	 */
	public static int getRandom()
	{
		Random random=new Random();
		int ran=random.nextInt(4);
		return ran;
		
	}
	/**
	 * 随机生成0,1，2中的任何一个数
	 * @return
	 */
	public static int getStartRandom()
	{
		Random random=new Random();
		int ran=random.nextInt(3);
		return ran;
		
	}
	/**
	 * 根据问题级别得到一个一般问题
	 * @return
	 */
	public static param getQrdinQuesByLevel(int level)
	{
		param result=new param();
		List<attrlevel> attrlevelInfo=jdbcHelper.jald.getArrLevelByLevel(level);
		int size1=attrlevelInfo.size();
		Random  random=new Random();
		int index1=random.nextInt(size1);
		int attrID=attrlevelInfo.get(index1).getAttrID();
		List<String> valueList=jdbcHelper.javd.getAttrValueById(attrID);
		int size2=valueList.size();
		int index2=random.nextInt(size2);
		result.setAttrName(attrlevelInfo.get(index1).getAttrName());
		result.setValue(valueList.get(index2));
		result.setFlag(0);//表示一般问题询问
		return result;
	}
	/**
	 * 判断产生的新的问题是否合理
	 * @param level
	 * @param par
	 * @param counter
	 * @param quesList
	 * @return
	 */
	public static boolean isNewQuestionValid(int level,param par,int[]counter,List<requestParam> quesList)
	{
		List<String> attrNameList=new ArrayList<String>();
		int index=0;
		for(int i=0;i<quesList.size();i++)
		{
			attrNameList.add(quesList.get(i).getAttrName());
		}
		//第二层是国籍和居住地的判断2个问题
		if(level==2)
		{
			if(counter[2]<2)
			{
				if(par.getAttrName().equals("nationality"))
				{
					if(attrNameList.contains("nationality"))
					{
						index=getIndexOfListByValue(attrNameList, par.getAttrName());
						if(quesList.get(index).getValue().equals(par.getValue()))
							return false;
						else 
						{
							if(quesList.get(index).getAnswer()==0)
								return true;
							//回答是
							else if(quesList.get(index).getAnswer()==1)
							{
								return false;
							}
							//回答不知道
							else {
								return true;
							}
						}
					}
					else {
						return true;
					}
				}
				//par.getAttrName()=nativePlace
				else {
					if (!attrNameList.contains("nationality")) {
						return false;
					}
					else {
						index=getIndexOfListByValue(attrNameList, par.getAttrName());
						if(quesList.get(index).getValue().equals("中国")&&quesList.get(index).getAnswer()==1)
						{
							return true;
						}
						else {
							return false;
						}
					}
				}
			}
			else {
				return false;
			}
		}
		//第1层，职业，共产生2个问题
		else if(level==1){
			if(counter[1]<2)
			{
				if(attrNameList.size()==0)
				{
					return true;
				}
				else {
					index=getIndexOfListByValue(attrNameList, par.getAttrName());
					if(par.getValue().equals(quesList.get(index).getValue()))
					{
						return false;
					}
					else {
						return true;
					}
				}
			}
			else {
				return false;
			}
			
		}
		//第3层，该层问题每个属性只能问一次，只能问一个问题,并且必须确定职业为演员或者不能确定不是演员是才能用这个问题
		else if(level==3) {
			if((!attrNameList.contains("occupation"))||counter[level]>=1)
			{
				return false;
			}
			else {
				List<requestParam>tempReqList=getListByAttrName(quesList, "occupation");
				int size=tempReqList.size();
				//"occupation"问题最多有两个，因此size最大为2
				if(size==1)
				{
					if(tempReqList.get(0).getValue().equals("演员"))
					{
						if(quesList.get(0).getAnswer()==1)
						{
							return true;
						}
						else {
							return false;
						}
					}
					else {
						if(tempReqList.get(0).getAnswer()==1)
						{
							return false ;
						}
						else {
							return true;
						}
					}
				}
				//size为2
				else {
					requestParam temp1=tempReqList.get(0);
					requestParam temp2=tempReqList.get(1);
					if(temp1.getValue().equals("演员"))
					{
						if(temp1.getAnswer()==1)
						{
							return true;
						}
						else {
							 return false;
						}
					}
					else {
						if(temp2.getValue().equals("演员"))
						{
							if(temp2.getAnswer()==1)
							{
								return true;
							}
							else {
								 return false;
							}
						}
						else {
							if(temp1.getAnswer()==0&&temp2.getAnswer()==0)
							{
								return true;
							}
							else {
								return false;
							}
						}
					}
				}
//				index=getIndexOfListByValue(attrNameList,"occupation");
//				if(quesList.get(index).getValue().equals("演员"))
//				{
//					if(quesList.get(index).getAnswer()==1)
//					{
//						return true;
//					}
//					else {
//						return false;
//					}
//				}
//				else {
//					if(quesList.get(index).getAnswer()==1)
//					{
//						return false;
//					}
//					else {
//						return true;
//					}
//				}
				
			}
		}
		//第0层，该层问题每个属性只能问一次，目前是4个问题
		else {
			if(attrNameList.contains(par.getAttrName())||counter[level]>=4)
			{
				return false;
			}
			else {
				return true;
			}
		}
	}
	/**
	 * 得到requesList中已经询问的问题属性名称为attrName的所有的requesList列表
	 * @param quesList
	 * @param attrName
	 * @return
	 */
	public static List<requestParam>getListByAttrName(List<requestParam> quesList,String attrName)
	{
		List<requestParam>resultList=new ArrayList<requestParam>();
		for(int i=0;i<quesList.size();i++)
		{
			if(quesList.get(i).getAttrName().equals(attrName))
			{
				resultList.add(quesList.get(i));
			}
		}
		return resultList;
	}
	/**
	 * 得到已经问过问题列表中，问过的关于某个属性的下表
	 * @param attrName
	 * @param value
	 * @return
	 */
	public static  int getIndexOfListByValue(List<String>attrName,String value)
	{
		int index=0;
		for(int i=0;i<attrName.size();i++)
		{
			if(attrName.get(i).equals(value))
			{
				index=i;
				break;
			}
		}
		return index;
	}
	

}
