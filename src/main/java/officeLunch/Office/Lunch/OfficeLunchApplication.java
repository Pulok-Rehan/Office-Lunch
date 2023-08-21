package officeLunch.Office.Lunch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
public class OfficeLunchApplication {

	public static void main(String[] args) {
		SpringApplication.run(OfficeLunchApplication.class, args);
	}

}
