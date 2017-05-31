package com.ourq20.jdbcDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.ourq20.DAO.specProDao;
import com.ourq20.model.specPro;

public class jdbcSpecProDao extends JdbcDaoSupport implements specProDao{
	/**
	 * 获得specPro表中所有的人物姓名
	 */
	@Override
	public List<String> getAllName() {
		String sql="select name from specPro";
		return getJdbcTemplate().queryForList(sql, String.class);
	}
	/**
	 *根据姓名从specPro表中得到一个specPro对象
	 * @param name
	 * @return
	 */
	@Override
	public specPro getSpecProByName(String name) {
		String sql="select * from specPro where name=?";
		final specPro spro=new specPro();
	    getJdbcTemplate().query(sql,new Object[]{name},new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				spro.setAttr1(rs.getString("attr1"));
				spro.setAttr2(rs.getString("attr2"));
				spro.setAttr3(rs.getString("attr3"));
				spro.setAttr4(rs.getString("attr4"));
				spro.setName(rs.getString("name"));
				spro.setCount(Integer.valueOf(rs.getString("count")));
			}
		});
	    return spro;
	}
	/**
	 * 从specPro表中得到所有的specPro对象
	 */
	@Override
	public List<specPro> getAllspecPro() {
		String sql="select * from specPro";
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(specPro.class));
	}
	/**
	 * 根据一个姓名列表得到这些人的信息
	 */
	@Override
	public List<specPro> getSpecProsByNameList(List<String> namelist) {
		String sql="select * from specPro where name=?";
		List<specPro> specProList=new ArrayList<specPro>();
		for(int i=0;i<namelist.size();i++)
		{
			specPro temPro=getSpecProByName(namelist.get(i));
			specProList.add(temPro);
		}
		return specProList;
	}
	/**
	 * 根据姓名删除此条记录
	 */

	@Override
	public int deleteSpecProByName(String name) {
		String sql="delete from specPro where name=?";
		return getJdbcTemplate().update(sql, name);
	}
	/**
	 * 更新specPro的特殊属性
	 */

	@Override
	public int updateSpecProByName(specPro spro) {
		String sql="update specPro set attr1=?,attr2=?,attr3=?,attr4=? where name=?";
		final String attr1=spro.getAttr1();
		final String attr2=spro.getAttr2();
		final String attr3=spro.getAttr3();
		final String attr4=spro.getAttr4();
		final String name=spro.getName();
		int temp=getJdbcTemplate().update(sql,new PreparedStatementSetter()
		{
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, attr1);
				ps.setString(2, attr2);
				ps.setString(3, attr3);
				ps.setString(4, attr4);
				ps.setString(5, name);
				
			}
		});
	
		return temp;
	}
	/**
	 * 向表specPro中加入某个一个人物的信息,返回-1表示该人物已经存在
	 */

	@Override
	public int addToSpecPro(specPro spro) {
		String sql="insert into specPro values(?,?,?,?,?,?)";
		List<String> nameList=getAllName();
		final String attr1=spro.getAttr1();		
		final String attr2=spro.getAttr2();
		final String attr3=spro.getAttr3();
		final String attr4=spro.getAttr4();
		final String name=spro.getName();
		final Integer count=spro.getCount();
		if(!nameList.contains(name))
		{
			return getJdbcTemplate().update(sql, new PreparedStatementSetter()
			{

				@Override
				public void setValues(PreparedStatement ps)
						throws SQLException {
					// TODO Auto-generated method stub
					ps.setString(1, name);
					ps.setString(2, attr1);
					ps.setString(3, attr2);
					ps.setString(4, attr3);
					ps.setString(5, attr4);
					ps.setInt(6, count.intValue());
				}
			});
		}
		else {
			return -1;
		}
	}
	/**
	 * 增加name的流行度count，即每次调用加1
	 */
	@Override
	public int addSpecCountByName(final String name) {
		String sql1="select count from specPro where name=?";
		String sql2="update specPro set count=? where name=?";
		final int tempCount=getJdbcTemplate().queryForInt(sql1, name)+1;
		return getJdbcTemplate().update(sql2, new PreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, tempCount);
				ps.setString(2, name);
			}
			
		});
	}
	/**
	 * 根据姓名得到该人的第num个特殊属性
	 */
	@Override
	public String getAttrValue(String name, int num) {
		// TODO Auto-generated method stub
		String attrName="attr"+num;
		String sql="select "+attrName+" from specPro where name=?";
		return getJdbcTemplate().queryForObject(sql, String.class,name);
	}
	/**
	 * 根据姓名得到该人的流行度count
	 */
	@Override
	public int getCountByName(String name) {
		// TODO Auto-generated method stub
		String sql="select count from specPro where name=?";
		return getJdbcTemplate().queryForInt(sql, name);
	}
}
