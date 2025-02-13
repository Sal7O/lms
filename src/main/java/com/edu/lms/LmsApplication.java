package com.edu.lms;

import com.edu.lms.dao.UserDAO;
import com.edu.lms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LmsApplication implements CommandLineRunner {

	private UserDAO userDAO;

	@Autowired
	public LmsApplication(UserDAO userDAO) {
		this.userDAO = userDAO;
	}


	public static void main(String[] args) {
		SpringApplication.run(LmsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello world\n");

//		System.out.println("Initializing User...");
//		User user = new User("Mahmoud", "Saleh", "sal77", "mahmoudsaleh@gmail.com", "1234", "01289982144", "STUDENT");
//
//		System.out.println("Saving User...");
//		userDAO.save(user);

//		userDAO.delete(3);

		if(userDAO.find_by_id(3) == null) {
			System.out.println("Not found!");
		}



	}

}
