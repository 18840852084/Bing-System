package com.ourq20.DAO;
import java.util.*;
import com.ourq20.model.specPro;

public interface specProDao {
	public List<String> getAllName();
	public specPro getSpecProByName(String name);
	public List<specPro> getAllspecPro();
	public List<specPro> getSpecProsByNameList(List<String> namelist);
	public int deleteSpecProByName(String name);
	public int updateSpecProByName(specPro specpro);
	public int addSpecCountByName(String name);
	public int addToSpecPro(specPro spro);
	public String getAttrValue(String name, int num);
	public int getCountByName(String name);
}
