package com.training.assessment;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AssMController {

	private UserRepo userRepo;
	private AssessRepo assessRepo;

	public ArrayList<User> findAllUsers() {
		ArrayList<User> users = new ArrayList<>();
		userRepo.findAll().forEach(users::add);
		return users;
	}
	
	public ArrayList<AssessmentTable> ass_tbl(){
		ArrayList<AssessmentTable> assess = new ArrayList<>();
		assessRepo.findAll().forEach(assess::add);
		return assess;
	}

	@RequestMapping(value = "/login")
	public String loginUser() {
		return "Choose_Assessment";
	}

	@RequestMapping(value = "/login/validate", method = RequestMethod.POST)
	@ResponseBody
	public String userValidation(@ModelAttribute("userid") int userid, @ModelAttribute("password") String password,
			@ModelAttribute("level") String level, HttpServletRequest request) {
		ArrayList<User> users = findAllUsers();
		User uu = users.get(userid);
		request.getSession().setAttribute("user", uu);
		if (users.get(userid) != null) {
			if (users.get(userid).getPassword().equals(password)) {
				if(users.get(userid).get_userAccess().name().equals(level)) {
					return "<html>"
							+ "<head>"
							+ "<title>Redirecting...</title>"
							+ "</head>"
							+ "<body>"
							+ "<a href='/admin/registeredlist'>Click here to view the list of Assessment Registered</a>"
							+ "</body>"
							+"</html>";
				} else {
					return "register";
				}
				
			}else {
				return "<a href='/login'>Wrong Password, Try Again</a>";
			}
		}else {
			return "<a href='/login'>User ID does not exist, Try Again</a>";
		}
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String registerPage(Model model,HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");
		model.addAttribute("userId",user.getUserId());
		return "Choose_Assessment";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	@ResponseBody
	public String logOutUser(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return "<html>"
		+ "<head>"
		+ "<title>Redirecting...</title>"
		+ "</head>"
		+ "<body>"
		+ "You have been logged out"
		+ "<a href='/login'>Click here to login again..!!</a>"
		+ "</body>"
		+"</html>";
	}
	
	@RequestMapping(value="/register/technical", method = RequestMethod.POST)
	@ResponseBody
	public String regTech(@ModelAttribute("userid") int userId, @ModelAttribute("assessment") String skill, @ModelAttribute("dateof") Date dateof, HttpServletRequest request) {
		
		return "OK";
	}

}
