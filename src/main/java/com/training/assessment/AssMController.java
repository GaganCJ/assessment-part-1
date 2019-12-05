package com.training.assessment;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AssMController {

	private UserRepo userRepo;

	public ArrayList<User> findAll() {
		ArrayList<User> users = new ArrayList<>();
		userRepo.findAll().forEach(users::add);
		return users;
	}

	@RequestMapping(value = "/login")
	public String loginUser() {
		return "login";
	}

	@RequestMapping(value = "/login/validate", method = RequestMethod.POST)
	@ResponseBody
	public String userValidation(@ModelAttribute("userid") int userid, @ModelAttribute("password") String password,
			@ModelAttribute("level") String level, HttpServletRequest request) {
		ArrayList<User> users = findAll();
		User uu = users.get(userid);
		request.getSession().setAttribute("user", uu);
		if (users.get(userid) != null) {
			if (users.get(userid).getPassword().equals(password)) {
				if(users.get(userid).isUserAccess() == true) {
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
	public String registerPage() {
		return "Choose_Assessment";
	}

}
