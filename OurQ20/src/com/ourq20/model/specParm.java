package com.ourq20.model;
//用于记录用户回答过的特殊问题信息
public class specParm extends requestParam
{
	private String name;
	public String  getName() {
		return name;
	}
	public void setName(String name)
	{
		this.name=name;
	}
}
