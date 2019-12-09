package com.training.assessment;

import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AssMController {

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

	@PostMapping(value = "/login/validate")
	@ResponseBody
	public String userValidation(@ModelAttribute("userid") int userid, @ModelAttribute("password") String password,
			@ModelAttribute("level") String level, HttpServletRequest request, HttpServletResponse response) {
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

	@GetMapping(value = "/register")
	public String registerPage(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		model.addAttribute("userId", user.getUserId());
		return "Choose_Assessment";
	}

	@GetMapping(value = "/logout")
	@ResponseBody
	public String logOutUser(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return "<html>" + "<head>" + "<title>Redirecting...</title>" + "</head>" + "<body>"
				+ "You have been logged out..!! " + "<a href='/login'>Click here to login again..!!</a>" + "</body>"
				+ "</html>";
	}

	@PostMapping(value = "/register/technical")
	@ResponseBody
	public String regTech(@ModelAttribute("assessment") String skill, @ModelAttribute("dateof") String dateof,
			HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		int userId = user.getUserId();
		int year = Integer.parseInt(dateof.substring(0, 4));
		int month = Integer.parseInt(dateof.substring(5, 7));
		int day = Integer.parseInt(dateof.substring(8, 10));
		AssessmentTable a1 = new AssessmentTable(userId, skill, LocalDate.of(year, month, day), "TECH");
		assessRepo.save(a1);
		request.getSession().removeAttribute("user");
		return "OK";
	}

	@PostMapping(value = "/register/behavioral")
	@ResponseBody
	public String regBehv(@ModelAttribute("assessment") String skill, @ModelAttribute("dateof") String dateof,
			HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		int userId = user.getUserId();
		int year = Integer.parseInt(dateof.substring(0, 4));
		int month = Integer.parseInt(dateof.substring(5, 7));
		int day = Integer.parseInt(dateof.substring(8, 10));
		AssessmentTable a1 = new AssessmentTable(userId, skill, LocalDate.of(year, month, day), "BEHV");
		assessRepo.save(a1);
		request.getSession().removeAttribute("user");
		return "OK";
	}

	@GetMapping(value = "/admin/registeredlist")
	public String returnList(Model model) {
		model.addAttribute("assess_list", ass_tbl());
		return "AssessmentList";
	}

}
