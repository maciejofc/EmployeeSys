package pl.maciejowsky.employeemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class EmployeemanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeemanagementApplication.class, args);
	}

}
