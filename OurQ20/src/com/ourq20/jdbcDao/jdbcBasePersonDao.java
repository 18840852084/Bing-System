package com.ourq20.jdbcDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.ourq20.DAO.basePersonDao;
import com.ourq20.DAO.specProDao;
import com.ourq20.model.basePerson;
import com.ourq20.model.birthInfoOfPerson;

public class jdbcBasePersonDao extends JdbcDaoSupport implements basePersonDao{
	/**
	 * 根据某个属性名称得到该属性值为value的姓名列表
	 */
	@Override
	public List<String> getAllNamebyAttriAndYValue(String attrName, Object value,List<String>namelist) {
		String sql="select name from person where "+attrName+"=?";
		List<String> resultList=new ArrayList<String>();
		List<Map<String, Object>> map=getJdbcTemplate().queryForList(sql, value);
		Iterator<Map<String, Object>> it=map.iterator();
		while (it.hasNext()) {
			Map<String, Object> temp=it.next();
			for(String key:temp.keySet())
			{
				if(namelist.contains(temp.get(key).toString()))
				{
					resultList.add(temp.get(key).toString());
				}
			}
		}
		return resultList;
	}
	/**
	 *根据popuAttr属性得到符合的任务的名字列表 
	 */
	@Override
	public List<String> getAllNamebyPopuAttr(String attrName, int answer,
			List<String> namelist) {
		// TODO Auto-generated method stub
		
		String sql="select name from person where "+attrName+"=?";
		List<String> resultList=new ArrayList<String>();
		List<Map<String, Object>> map=getJdbcTemplate().queryForList(sql, answer);
		Iterator<Map<String, Object>> it=map.iterator();
		while (it.hasNext()) {
			Map<String, Object> temp=it.next();
			for(String key:temp.keySet())
			{
				if(namelist.contains(temp.get(key).toString()))
				{
					resultList.add(temp.get(key).toString());
				}
			}
		}
		return resultList;
	}
	/**
	 * 根据年龄age得到年龄大于age的人物姓名列表
	 */

	@Override
	public List<String> getAllNamebyAge(int age,List<String>namelist) {
		// TODO Auto-generated method stub
		List<String> name=new ArrayList<String>();
		Iterator<String> it=namelist.iterator();
	    int nowYear=new Date(System.currentTimeMillis()).getYear();
		int tempAge=0;
		while (it.hasNext()) {
			birthInfoOfPerson temp=getAllBirthInfoByName(it.next());
			tempAge=nowYear-temp.getDateofBorn().getYear();
			if(tempAge>age)
			{
				name.add(temp.getName());
			}
		}
		return name;
	}
	/**
	 * 根据姓名获得name的出生日期信息
	 */
	@Override
	public birthInfoOfPerson getAllBirthInfoByName(String name) {
		// TODO Auto-generated method stub
		String sql="select name,dateOfBorn from person where name=?";
		final birthInfoOfPerson tempBirthInfoOfPerson=new birthInfoOfPerson();
		getJdbcTemplate().query(sql,new Object[]{name},new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				tempBirthInfoOfPerson.setName(rs.getString(1));
				tempBirthInfoOfPerson.setDateofBorn(rs.getDate(2));
			}
		});
		return tempBirthInfoOfPerson;
	}
	/**
	 * 获取数据库中所有人物姓名的列表
	 */
	@Override
	public List<String> getAllName() {
		// TODO Auto-generated method stub
		String sql="select name from person";
		return getJdbcTemplate().queryForList(sql,String.class);
	}
	/**
	 * 根据姓名删除数据表person中人物name的信息
	 */
	@Override
	public int deleteBasePersonByName(String name) {
		// TODO Auto-generated method stub
		String sql="delete from person where name=?";
		return getJdbcTemplate().update(sql, name);
	}
	/**
	 * 根据姓名更新person中的bps
	 */
	@Override
	public int updateBasePersonByName(basePerson bps) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 在数据表person中添加bps,返回-1表示person中已经存在该人物
	 */
	@Override
	public int addBasePerson(basePerson bps) {
		// TODO Auto-generated method stub
		String sql="insert into person values(?,?,?,?,?,?,?,?,?)";
		List<String> allName=getAllName();
		final String name=bps.getName();
		final String gender=bps.getGender();
		final Date dateOfBorn=bps.getDateOfBorn();
		final String occupation=bps.getOccupation();
		final String nationality=bps.getNationality();
		final String nativePlace=bps.getNativePlace();
		final int isMarriage=bps.getIsMarriage();
		//final String works=bps.getWorks();
		final int isAlive=bps.getIsAlive();
		if(!(allName.contains(bps.getName())))
		{
			return getJdbcTemplate().update(sql, new PreparedStatementSetter(){
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					// TODO Auto-generated method stub
					ps.setString(1, name);
					ps.setString(2, gender);
					ps.setDate(3, (java.sql.Date) dateOfBorn);
					ps.setString(4, occupation);
					ps.setString(5, nationality);
					ps.setString(6, nativePlace);
					ps.setInt(7, isMarriage);
				//	ps.setString(8, works);
					ps.setInt(8, isAlive);
					
				}
			});
		}
		else {
			return -1;
		}
		
		
	}
	/**
	 * 从数据库中查找attrName！=value的人
	 */
	@Override
	public List<String> getAllNamebyAttriAndNValue(String attrName, Object value,List<String>namelist) {
		String sql="select name from person where "+attrName+"!=?";
		List<String> resultList=new ArrayList<String>();
		List<Map<String, Object>> map=getJdbcTemplate().queryForList(sql, value);
		Iterator<Map<String, Object>> it=map.iterator();
		while (it.hasNext()) {
			Map<String, Object> temp=it.next();
			for(String key:temp.keySet())
			{
				if(namelist.contains(temp.get(key).toString()))
				{
					resultList.add(temp.get(key).toString());
				}
			}
		}
		return resultList;
	}
	/**
	 * 找到naelist中年龄小于age的任务的列表
	 */
	@Override
	public List<String> getAllNamebyNAge(int age, List<String> namelist) {
		List<String> name=new ArrayList<String>();
		Iterator<String> it=namelist.iterator();
	    int nowYear=new Date(System.currentTimeMillis()).getYear();
		int tempAge=0;
		while (it.hasNext()) {
			birthInfoOfPerson temp=getAllBirthInfoByName(it.next());
			tempAge=nowYear-temp.getDateofBorn().getYear();
			if(tempAge<=age)
			{
				name.add(temp.getName());
			}
		}
		return name;
	}
	/**
	 * 根据职业得到符合的人物的姓名的列表
	 */
	@Override
	public List<String> getAllNamebyOccu(String occupation,List<String> namelist) {
		// TODO Auto-generated method stub
		List<String> resultList=new ArrayList<String>();
		Iterator<String>it=namelist.iterator();
		String sql="select occupation from person where name=?";
		while(it.hasNext())
		{
			String name=it.next();
			String occuTemp=getJdbcTemplate().queryForObject(sql, String.class,name);
			if(occuTemp.contains(occupation))
			{
				resultList.add(name);
			}
		}
		return resultList;
	}
	/**
	 * 根据职业得到不符合的人物的姓名的列表
	 */
	@Override
	public List<String> getAllNamebyNOccu(String occupation,
			List<String> namelist) {
		// TODO Auto-generated method stub
		List<String> resultList=new ArrayList<String>();
		Iterator<String>it=namelist.iterator();
		String sql="select occupation from person where name=?";
		while(it.hasNext())
		{
			String name=it.next();
			String occuTemp=getJdbcTemplate().queryForObject(sql, String.class,name);
			if(!occuTemp.contains(occupation))
			{
				resultList.add(name);
			}
		}
		return resultList;
	}
	

	

}
