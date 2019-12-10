package com.training.assessment.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.assessment.bean.AssessmentTable;
import com.training.assessment.bean.User;
import com.training.assessment.repository.AssessRepo;
import com.training.assessment.repository.UserRepo;

@RestController
public class RestAPIController {

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

	@GetMapping(value = "/user")
	public String getBySession(HttpServletRequest request) throws JsonProcessingException {
		User user = (User) request.getSession().getAttribute("user");
		ObjectMapper Obj = new ObjectMapper();
		String jsonStr = "";
		jsonStr = Obj.writeValueAsString(user);
		return jsonStr;
	}

	@PostMapping(value = "/login/validate")
	public String userValidation(@ModelAttribute("userid") int userid, @ModelAttribute("password") String password,
			@ModelAttribute("level") String level, HttpServletRequest request) {
		ArrayList<User> users = findAllUsers();
		for (User u1 : users) {
			if (u1.getUserId() == userid) {
				if (u1.getPassword().equals(password)) {
					request.getSession().setAttribute("user", u1);
					if (u1.get_userAccess().name().equals(level)) {
						if (u1.get_userAccess().name() == "Admin") {
							return "<html>" + "<head>" + "<title>Redirecting...</title>" + "</head>" + "<body>"
									+ "<a href='/admin/registeredlist'>Click here to view the list of Assessment Registered</a>"
									+ "</body>" + "</html>";
						} else {
							return "<html>" + "<head>" + "<title>Redirecting...</title>" + "</head>" + "<body>"
									+ "<a href='/register'>Click here to Register for an Assessment</a>" + "</body>"
									+ "</html>";
						}
					}
				} else {
					return "<a href='/login'>Wrong Password, Try Again</a>";
				}
			}
		}
		return "<a href='/login'>User ID does not exist, Try Again</a>";
	}

	@GetMapping(value = "/logout")
	public String logOutUser(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return "<html>" + "<head>" + "<title>Redirecting...</title>" + "</head>" + "<body>"
				+ "You have been logged out..!! " + "<a href='/login'>Click here to login again..!!</a>" + "</body>"
				+ "</html>";
	}

	@PostMapping(value = "/register/technical")
	public String regTech(@ModelAttribute("assessment") String skill, @ModelAttribute("dateof") String dateof,
			HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		int userId = user.getUserId();
		int year = Integer.parseInt(dateof.substring(0, 4));
		int month = Integer.parseInt(dateof.substring(5, 7));
		int day = Integer.parseInt(dateof.substring(8, 10));
		AssessmentTable a1 = new AssessmentTable(userId, skill, LocalDate.of(year, month, day), "TECH");
		assessRepo.save(a1);
		return "OK";
	}

	@PostMapping(value = "/register/behavioral")
	public String regBehv(@ModelAttribute("assessment") String skill, @ModelAttribute("dateof") String dateof,
			HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		int userId = user.getUserId();
		int year = Integer.parseInt(dateof.substring(0, 4));
		int month = Integer.parseInt(dateof.substring(5, 7));
		int day = Integer.parseInt(dateof.substring(8, 10));
		AssessmentTable a1 = new AssessmentTable(userId, skill, LocalDate.of(year, month, day), "BEHV");
		assessRepo.save(a1);
		return "OK";
	}

}
