package com.ourq20.model;

import java.io.Serializable;

public class requestParam extends param implements Serializable {
	private int answer;//1表示答案正确，0表示错误，2表示不知道
	public int getAnswer()
	{
		return answer;
	}
	public void setAnswer(int answer)
	{
		this.answer=answer;
	}

}
