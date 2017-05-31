package com.ourq20.jdbcDao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.ourq20.DAO.attrvalueDao;

public class jdbcAttrValueDao extends JdbcDaoSupport implements attrvalueDao{
    /**
     * 根据属性的id得到该属性所有的值得情况，用于生成问题
     */
	@Override
	public List<String> getAttrValueById(int attrID) {
		// TODO Auto-generated method stub
		String sql="select value from attrvalue where attrID=?";
		List<String> resultList=new ArrayList<String>();
		List tempList=getJdbcTemplate().queryForList(sql, attrID);
		Iterator it=tempList.iterator();
		while(it.hasNext())
		{
			Map map=(Map)it.next();
			resultList.add(map.get("value").toString());
		}
		
		return resultList;
	}

}
