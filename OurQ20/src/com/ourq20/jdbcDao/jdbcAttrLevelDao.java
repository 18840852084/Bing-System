package com.ourq20.jdbcDao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.ourq20.DAO.attrlevelDao;
import com.ourq20.model.attrlevel;

public class jdbcAttrLevelDao extends JdbcDaoSupport implements attrlevelDao {
	/**
	 * 得到某个level的一个attrlevel对象
	 */
	@Override
	public List<attrlevel> getArrLevelByLevel(int attrLevel) {
		// TODO Auto-generated method stub
		String sql="select * from attrlevel where attrLevel=?";
		List<attrlevel> resultList=new ArrayList<attrlevel>();
		List templist=getJdbcTemplate().queryForList(sql, attrLevel);
		Iterator it=templist.iterator();
		while(it.hasNext())
		{
			Map mapTemp=(Map)it.next();
			attrlevel attrlevelTemp=new attrlevel();
			attrlevelTemp.setAtrrID((int)mapTemp.get("attrID"));
			attrlevelTemp.setAttrName((String)mapTemp.get("attrName"));
			attrlevelTemp.setAttrLevel((int)mapTemp.get("attrLevel"));
			resultList.add(attrlevelTemp);
		}
		return resultList;
	}

}
