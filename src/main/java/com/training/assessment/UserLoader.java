package com.training.assessment;

import java.time.LocalDate;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.training.assessment.User.userAccess;

@Component
public class UserLoader implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private AssessRepo assessRepo;
	
	private Logger log = Logger.getLogger(UserLoader.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		User user1 = new User(1001,"user1001",userAccess.Admin);
		userRepo.save(user1);
		
		log.info("User Recorder - ID : "+user1.getUserId());
		
		User user2 = new User(1088,"user1088",userAccess.Employee);
		userRepo.save(user2);
		
		log.info("User Recorder - ID : "+user2.getUserId());
		
		User user3 = new User(2192,"user2192",userAccess.Employee);
		userRepo.save(user3);
		
		log.info("User Recorder - ID : "+user3.getUserId());
		
		User user4 = new User(3122,"user3122",userAccess.Employee);
		userRepo.save(user4);
		
		log.info("User Recorder - ID : "+user4.getUserId());
		
		AssessmentTable tbl1 = new AssessmentTable(1088,"Java",LocalDate.of(2019, 12, 12),"TECH");
		assessRepo.save(tbl1);
		
		AssessmentTable tbl2 = new AssessmentTable(1088,"Python",LocalDate.of(2019, 12, 13),"TECH");
		assessRepo.save(tbl2);
		
		AssessmentTable tbl3 = new AssessmentTable(1088,"C++",LocalDate.of(2019, 12, 14),"TECH");
		assessRepo.save(tbl3);
		
		AssessmentTable tbl4 = new AssessmentTable(1088,"Step Plus",LocalDate.of(2019, 12, 15),"BEHV");
		assessRepo.save(tbl4);
		
	}
}
