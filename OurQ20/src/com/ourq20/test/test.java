package com.ourq20.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ourq20.Tools.OrdinQuesHelper;
import com.ourq20.Tools.jdbcHelper;
import com.ourq20.model.param;
import com.ourq20.model.requestParam;

public class test {
	public static void main(String[] args) {
//		int level=0;
//		requestParam param=new requestParam();
//		param.setFlag(2);
//		level=OrdinQuesHelper.getRandom();
//		param newParam=OrdinQuesHelper.getQrdinQuesByLevel(level);
//		System.out.println(newParam.getAttrName()+","+newParam.getValue());
//		List<String> nameList1=jdbcHelper.jpbd.getAllName();
//		List<String> nameList2=jdbcHelper.jspd.getAllName();
//		Iterator<String> iterator=nameList2.iterator();
//		while(iterator.hasNext())
//		{
//			String tempName=iterator.next();
//			if(!nameList1.contains(tempName))
//			{
//				jdbcHelper.jspd.deleteSpecProByName(tempName);
//			}
//		}
//		List<String> nameList=new ArrayList<String>();
//		nameList.add("���»�");
//		nameList.add("�ֿ���");
//		nameList.add("����");
//		nameList.add("�����");
//		nameList.add("����Т");
//		String occString="ģ�ض�";
//		List<String> namelist2=jdbcHelper.jpbd.getAllNamebyOccu(occString, nameList);
//		System.out.println(namelist2);
//		namelist2=jdbcHelper.jpbd.getAllNamebyAge(40, nameList);
//		System.out.println(namelist2);
//		namelist2=jdbcHelper.jpbd.getAllNamebyAttriAndNValue("nationality","����", nameList);
//		System.out.println(namelist2);
//		String nameString="���Ż�";
//		String result=jdbcHelper.jspd.getAttrValue(nameString, 4);
//		if(result==null)
//		{
//			System.out.println("null");
//		}
//		else if(result.equals("")) {
//			System.out.println("���ַ�");
//		}
//		else {
//			System.out.println(result);
//		}
//		requestParam re=new requestParam();
//		if(re.getAttrName()==null)
//		{
//			System.out.println("����Ϊ��");
//		}
//		List<String> nameList1=new ArrayList<String>();
//		nameList1.add("���»�");
//		nameList1.add("�ֿ���");
//		nameList1.add("�����");
//		nameList1.add("������");
//		String attrValue="�Ĵ�����";
//		String attrNameString="nationality";
//		List<String> namelist=jdbcHelper.jpbd.getAllNamebyAttriAndYValue(attrNameString, attrValue, nameList1);
//		System.out.println(namelist);
//		nameList1.remove("���»�");
//		System.out.println(nameList1);
//		List<String> countryList=jdbcHelper.javd.getAttrValueById(4);
//		System.out.println(countryList);
		int []counter=new int[4];
		System.out.println("���鳤����:"+counter.length);
		for(int i=0;i<counter.length;i++)
		{
			System.out.println(counter[i]);
		}
		
		
		
		
		
	}
	
	

}
