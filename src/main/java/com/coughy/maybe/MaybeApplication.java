package com.coughy.maybe;

import com.coughy.maybe.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class MaybeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaybeApplication.class, args);
	}

}
