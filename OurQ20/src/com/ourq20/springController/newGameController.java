package com.ourq20.springController;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ourq20.model.requestParam;
import com.ourq20.model.specParm;

@Controller
@RequestMapping("/newGameController")
public class newGameController {
	@RequestMapping(method=RequestMethod.POST)
	public void newGameController(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		session.setAttribute("questList",new ArrayList<requestParam>());
		session.setAttribute("nameList", new ArrayList<String>());
		session.setAttribute("counter", new int [4]);
		session.setAttribute("specParamList", new ArrayList<specParm>());
		session.setAttribute("lastSpecParm", new specParm());
		//测试输出
		System.out.println("新的一局！");
	}
}
