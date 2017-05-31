package com.ourq20.Tools;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ourq20.jdbcDao.jdbcAttrLevelDao;
import com.ourq20.jdbcDao.jdbcAttrValueDao;
import com.ourq20.jdbcDao.jdbcBasePersonDao;
import com.ourq20.jdbcDao.jdbcSpecProDao;

public class jdbcHelper {
	public  static ApplicationContext act=new ClassPathXmlApplicationContext("BasicDataSource.xml");
	public  static jdbcAttrLevelDao jald=(jdbcAttrLevelDao) act.getBean("attrLevelDao");
	public  static jdbcAttrValueDao javd=(jdbcAttrValueDao)act.getBean("attrValueDao");
	public  static jdbcBasePersonDao jpbd=(jdbcBasePersonDao) act.getBean("basePersonDao");
	public  static jdbcSpecProDao jspd=(jdbcSpecProDao) act.getBean("specProDao");
	

}
