package com.ourq20.model;

import java.io.Serializable;

public class requestParam extends param implements Serializable {
	private int answer;//1��ʾ����ȷ��0��ʾ����2��ʾ��֪��
	public int getAnswer()
	{
		return answer;
	}
	public void setAnswer(int answer)
	{
		this.answer=answer;
	}

}
