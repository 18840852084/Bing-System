package com.ourq20.springController;

import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ourq20.Tools.funHelper;
import com.ourq20.Tools.jdbcHelper;
import com.ourq20.model.requestAddCount;
import com.ourq20.model.requestParam;
import com.ourq20.model.specParm;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.IntArrayData;

@Controller
@RequestMapping("/VerifyAnswer")
public class VerifyAnswerController {
	@RequestMapping(method=RequestMethod.POST)
	public void handleVerifyAnswer(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		String paramsString=request.getParameter("param");
		requestAddCount result=funHelper.ParseAddCount(paramsString);
		String name=result.getName();
		int answer=result.getAnswer();
		//增加人物的count值
		if(answer==1)
		{
			jdbcHelper.jspd.addSpecCountByName(name);
		}
		//
		else {
			
		}
		
		
	}

}
