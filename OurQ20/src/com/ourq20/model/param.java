package com.ourq20.model;
public class param {
	private String attrName;
	private String value;
	private int flag;//flag=0表示用户回答的问题是一般问题，1表示用户回答的问题是特殊问题，2表示客服端通知服务器开始新游戏，3表示游戏结束
	public String getAttrName()
	{
		return attrName;
	}
	public void setAttrName(String attrName)
	{
		this.attrName=attrName;
	}
	public void setValue(String value)
	{
		this.value=value;
	}
	public String getValue()
	{
		return value;
	}
	public int getFlag()
	{
		return flag;
	}
	public void setFlag(int flag)
	{
		this.flag=flag;
	}
}
