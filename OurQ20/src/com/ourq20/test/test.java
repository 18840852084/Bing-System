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
//		nameList.add("刘德华");
//		nameList.add("林俊杰");
//		nameList.add("柳岩");
//		nameList.add("刘亦菲");
//		nameList.add("宋智孝");
//		String occString="模特儿";
//		List<String> namelist2=jdbcHelper.jpbd.getAllNamebyOccu(occString, nameList);
//		System.out.println(namelist2);
//		namelist2=jdbcHelper.jpbd.getAllNamebyAge(40, nameList);
//		System.out.println(namelist2);
//		namelist2=jdbcHelper.jpbd.getAllNamebyAttriAndNValue("nationality","美国", nameList);
//		System.out.println(namelist2);
//		String nameString="朴信惠";
//		String result=jdbcHelper.jspd.getAttrValue(nameString, 4);
//		if(result==null)
//		{
//			System.out.println("null");
//		}
//		else if(result.equals("")) {
//			System.out.println("空字符");
//		}
//		else {
//			System.out.println(result);
//		}
//		requestParam re=new requestParam();
//		if(re.getAttrName()==null)
//		{
//			System.out.println("对象为空");
//		}
//		List<String> nameList1=new ArrayList<String>();
//		nameList1.add("刘德华");
//		nameList1.add("林俊杰");
//		nameList1.add("刘亦菲");
//		nameList1.add("张智霖");
//		String attrValue="澳大利亚";
//		String attrNameString="nationality";
//		List<String> namelist=jdbcHelper.jpbd.getAllNamebyAttriAndYValue(attrNameString, attrValue, nameList1);
//		System.out.println(namelist);
//		nameList1.remove("刘德华");
//		System.out.println(nameList1);
//		List<String> countryList=jdbcHelper.javd.getAttrValueById(4);
//		System.out.println(countryList);
		int []counter=new int[4];
		System.out.println("数组长度是:"+counter.length);
		for(int i=0;i<counter.length;i++)
		{
			System.out.println(counter[i]);
		}
		
		
		
		
		
	}
	
	

}
