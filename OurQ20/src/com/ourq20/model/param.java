package com.ourq20.model;
public class param {
	private String attrName;
	private String value;
	private int flag;//flag=0��ʾ�û��ش��������һ�����⣬1��ʾ�û��ش���������������⣬2��ʾ�ͷ���֪ͨ��������ʼ����Ϸ��3��ʾ��Ϸ����
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
