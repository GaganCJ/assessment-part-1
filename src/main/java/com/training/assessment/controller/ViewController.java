package com.training.assessment.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.training.assessment.bean.AssessmentTable;
import com.training.assessment.bean.User;
import com.training.assessment.repository.AssessRepo;
import com.training.assessment.repository.UserRepo;

@Controller
public class ViewController {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private AssessRepo assessRepo;

	public ArrayList<User> findAllUsers() {
		ArrayList<User> users = new ArrayList<>();
		userRepo.findAll().forEach(users::add);
		return users;
	}

	public ArrayList<AssessmentTable> ass_tbl() {
		ArrayList<AssessmentTable> assess = new ArrayList<>();
		assessRepo.findAll().forEach(assess::add);
		return assess;
	}

	@RequestMapping(value = "/login")
	public String loginUser() {
		return "login";
	}

	@RequestMapping(value = "/register")
	public String registerPage() {
		return "Choose_Assessment";
	}

	@RequestMapping(value = "/admin/registeredlist")
	public String returnList(Model model) {
		model.addAttribute("assess_list", ass_tbl());
		return "AssessmentList";
	}

}
