package com.training.assessment;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class UserLoader implements ApplicationListener<ContextRefreshedEvent>{

	private UserRepo userRepo;
	
	private Logger log = Logger.getLogger(UserLoader.class);
	
	@Autowired
	public void setUserRepo(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		User user1 = new User();
		user1.setUserId(1001);
		user1.setPassword("user1001");
		user1.setUserAccess(true);
		userRepo.save(user1);
		
		log.info("User Recorder - ID : "+user1.getUserId());
		
		User user2 = new User();
		user2.setUserId(1088);
		user2.setPassword("user1088");
		user2.setUserAccess(false);
		userRepo.save(user2);
		
		log.info("User Recorder - ID : "+user2.getUserId());
		
		User user3 = new User();
		user3.setUserId(2192);
		user3.setPassword("user2192");
		user3.setUserAccess(false);
		userRepo.save(user3);
		
		log.info("User Recorder - ID : "+user3.getUserId());
		
		User user4 = new User();
		user4.setUserId(3122);
		user4.setPassword("user3122");
		user4.setUserAccess(false);
		userRepo.save(user4);
		
		log.info("User Recorder - ID : "+user4.getUserId());
		
	}
}
