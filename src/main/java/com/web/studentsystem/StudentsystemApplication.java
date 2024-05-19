package com.web.studentsystem;

import com.web.studentsystem.entities.Role;
import com.web.studentsystem.entities.Student;
import com.web.studentsystem.entities.User;
import com.web.studentsystem.repository.StudentRepo;
import com.web.studentsystem.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class StudentsystemApplication implements CommandLineRunner {
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private StudentRepo studentRepo;

	public static void main(String[] args) {
		SpringApplication.run(StudentsystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User adminAccount = userRepo.findByRole(Role.ADMIN);
		if(null == adminAccount) {
			User user = new User();
			user.setEmail("admin@gmail.com");
			user.setFirstName("admin");
			user.setSecondName("admin");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepo.save(user);
		}
		if(studentRepo.findAll().isEmpty()) {
			studentRepo.save(new Student("2002001", "A", LocalDate.parse("2024-02-29", DateTimeFormatter.ISO_DATE), "T-CLC", 9.0));
			User user = new User();
			user.setEmail("2002001@vnu.edu.vn");
			user.setFirstName("2002001");
			user.setSecondName("2002001");
			user.setRole(Role.USER);
			user.setPassword(new BCryptPasswordEncoder().encode("2002001"));
			userRepo.save(user);
		}
	}
}
