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
	 * �������0,1��2,3�е��κ�һ����
	 * @return
	 */
	public static int getRandom()
	{
		Random random=new Random();
		int ran=random.nextInt(4);
		return ran;
		
	}
	/**
	 * �������0,1��2�е��κ�һ����
	 * @return
	 */
	public static int getStartRandom()
	{
		Random random=new Random();
		int ran=random.nextInt(3);
		return ran;
		
	}
	/**
	 * �������⼶��õ�һ��һ������
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
		result.setFlag(0);//��ʾһ������ѯ��
		return result;
	}
	/**
	 * �жϲ������µ������Ƿ����
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
		//�ڶ����ǹ����;�ס�ص��ж�2������
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
							//�ش���
							else if(quesList.get(index).getAnswer()==1)
							{
								return false;
							}
							//�ش�֪��
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
						if(quesList.get(index).getValue().equals("�й�")&&quesList.get(index).getAnswer()==1)
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
		//��1�㣬ְҵ��������2������
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
		//��3�㣬�ò�����ÿ������ֻ����һ�Σ�ֻ����һ������,���ұ���ȷ��ְҵΪ��Ա���߲���ȷ��������Ա�ǲ������������
		else if(level==3) {
			if((!attrNameList.contains("occupation"))||counter[level]>=1)
			{
				return false;
			}
			else {
				List<requestParam>tempReqList=getListByAttrName(quesList, "occupation");
				int size=tempReqList.size();
				//"occupation"������������������size���Ϊ2
				if(size==1)
				{
					if(tempReqList.get(0).getValue().equals("��Ա"))
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
				//sizeΪ2
				else {
					requestParam temp1=tempReqList.get(0);
					requestParam temp2=tempReqList.get(1);
					if(temp1.getValue().equals("��Ա"))
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
						if(temp2.getValue().equals("��Ա"))
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
//				if(quesList.get(index).getValue().equals("��Ա"))
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
		//��0�㣬�ò�����ÿ������ֻ����һ�Σ�Ŀǰ��4������
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
	 * �õ�requesList���Ѿ�ѯ�ʵ�������������ΪattrName�����е�requesList�б�
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
	 * �õ��Ѿ��ʹ������б��У��ʹ��Ĺ���ĳ�����Ե��±�
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
