package com.training.assessment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AssMController {
	
	@RequestMapping(value="/login")
	public String loginUser(HttpServletRequest request,HttpServletResponse response) {
		return "login";
	}

}
