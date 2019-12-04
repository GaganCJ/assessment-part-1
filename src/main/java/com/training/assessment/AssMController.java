package com.training.assessment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AssMController {
	
	@RequestMapping(value="/login")
	public String loginUser() {
		return "Choose_Assessment";
	}

}
