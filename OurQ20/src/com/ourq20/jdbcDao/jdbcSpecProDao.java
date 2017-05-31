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
	 * ���specPro�������е���������
	 */
	@Override
	public List<String> getAllName() {
		String sql="select name from specPro";
		return getJdbcTemplate().queryForList(sql, String.class);
	}
	/**
	 *����������specPro���еõ�һ��specPro����
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
	 * ��specPro���еõ����е�specPro����
	 */
	@Override
	public List<specPro> getAllspecPro() {
		String sql="select * from specPro";
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(specPro.class));
	}
	/**
	 * ����һ�������б�õ���Щ�˵���Ϣ
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
	 * ��������ɾ��������¼
	 */

	@Override
	public int deleteSpecProByName(String name) {
		String sql="delete from specPro where name=?";
		return getJdbcTemplate().update(sql, name);
	}
	/**
	 * ����specPro����������
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
	 * ���specPro�м���ĳ��һ���������Ϣ,����-1��ʾ�������Ѿ�����
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
	 * ����name�����ж�count����ÿ�ε��ü�1
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
	 * ���������õ����˵ĵ�num����������
	 */
	@Override
	public String getAttrValue(String name, int num) {
		// TODO Auto-generated method stub
		String attrName="attr"+num;
		String sql="select "+attrName+" from specPro where name=?";
		return getJdbcTemplate().queryForObject(sql, String.class,name);
	}
	/**
	 * ���������õ����˵����ж�count
	 */
	@Override
	public int getCountByName(String name) {
		// TODO Auto-generated method stub
		String sql="select count from specPro where name=?";
		return getJdbcTemplate().queryForInt(sql, name);
	}
}
