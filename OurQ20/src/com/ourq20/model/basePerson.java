package com.ourq20.model;

import java.sql.Date;

public class basePerson {
	private String name;//����
	private String gender;//�Ա�
	private Date dateOfBorn;//��������
	private String occupation;//ְҵ
	private String nationality;//����
	private String nativePlace;//�漮
	private int isMarriage;//���0��ʾ�ѻ飬1��ʾδ��
	private int isAlive;//�Ƿ��ڣ�1��ʾ���ڣ�0��ʾȥ��
	private int popuAttr1;
	private int popuAttr2;
	private int popuAttr3;
	private int popuAttr4;
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public String getGender()
	{
		return gender;
	}
	public void setGender(String gender)
	{
		this.gender=gender;
	}
	public Date getDateOfBorn()
	{
		return dateOfBorn;
	}
	public void setDateOfBorn(Date dateOfBorn)
	{
		this.dateOfBorn=dateOfBorn;
	}
	public String getOccupation()
	{
		return occupation;
	}
	public void setOccupation(String occupation)
	{
		this.occupation=occupation;
	}
	public String getNationality()
	{
		return nationality;
	}
	public void setNationality(String nationality)
	{
		this.nationality=nationality;
	}
	public String getNativePlace()
	{
		return nativePlace;
	}
	public void setNativePlace(String nativePlace)
	{
		this.nativePlace=nativePlace;
	}
	public int getIsMarriage()
	{
		return isMarriage;
	}
	public void setIsMarriage(int isMarriage)
	{
		this.isMarriage=isMarriage;
	}
	public int getIsAlive()
	{
		return isAlive;
	}
	public void setIsAlive(int isAlive)
	{
		this.isAlive=isAlive;
	}
	public int getPopuAttr1()
	{
		return popuAttr1;
	}
	public void setPopuAttr1(int popuAttr1)
	{
		this.popuAttr1=popuAttr1;
	}
	public int getPopuAttr2()
	{
		return popuAttr2;
	}
	public void setPopuAttr2(int popuAttr2)
	{
		this.popuAttr2=popuAttr2;
	}
	public int getPopuAttr3()
	{
		return popuAttr3;
	}
	public void setPopuAttr3(int popuAttr3)
	{
		this.popuAttr3=popuAttr3;
	}
	public int getPopuAttr4()
	{
		return popuAttr4;
	}
	public void setPopuAttr4(int popuAttr4)
	{
		this.popuAttr4=popuAttr4;
	}
}
