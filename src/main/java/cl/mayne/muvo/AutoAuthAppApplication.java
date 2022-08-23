package cl.mayne.muvo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class AutoAuthAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoAuthAppApplication.class, args);
	}

}
