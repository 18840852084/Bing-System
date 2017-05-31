package com.ourq20.DAO;

import java.util.List;
import com.ourq20.model.basePerson;
import com.ourq20.model.birthInfoOfPerson;

public interface basePersonDao {
	public List<String> getAllNamebyAttriAndYValue(String attrName,Object value,List<String>namelist);
	public List<String> getAllNamebyAttriAndNValue(String attrName,Object value,List<String>namelist);
	public List<String> getAllNamebyAge(int age,List<String>namelist);
	public List<String> getAllNamebyNAge(int age,List<String>namelist);
	public List<String> getAllNamebyOccu(String occupation, List<String>namelist);
	public List<String> getAllNamebyNOccu(String occupation, List<String>namelist);
	public birthInfoOfPerson getAllBirthInfoByName(String name);
	public List<String>getAllName();
	public List<String>getAllNamebyPopuAttr(String attrName,int answer,List<String>nameList);
	public int deleteBasePersonByName(String name);
	public int updateBasePersonByName(basePerson bps);
	public int addBasePerson(basePerson bps);
	

}
