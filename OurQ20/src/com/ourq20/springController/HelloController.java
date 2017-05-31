package com.ourq20.springController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ourq20.Tools.funHelper;
import com.ourq20.model.param;

@Controller
public class HelloController {
	@RequestMapping("/hello")
	public void PrintHello(HttpServletRequest request,HttpServletResponse response,ModelMap model)
	{
//		String name=request.getParameter("name").toString();
//		String sex=request.getParameter("sex").toString(); 
//		model.addAttribute("name",name);
//		model.addAttribute("sex", sex);
		param param=new param();
		param.setAttrName("‘¨«Ì«Ì");
		param.setFlag(1);
		param.setValue("22");
		JSONObject jo=funHelper.getJsonByParam(param);
		response.setCharacterEncoding("utf-8");
	    response.setHeader("Content-type","text/html;charset=UTF-8");
		PrintWriter out = null;  
	    try {  
	        out = response.getWriter();  
	        out.append(jo.toString());  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        if (out != null) {  
	        	out.flush();
	            out.close();  
	        }  
	    }  
	//	return "hello";
	}
	@RequestMapping("/form")
	public String HandleForm(ModelMap map)
	{
		return "view";
	}
	

}
