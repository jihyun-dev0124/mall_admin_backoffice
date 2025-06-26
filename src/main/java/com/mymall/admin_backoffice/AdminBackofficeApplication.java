package com.mymall.admin_backoffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class AdminBackofficeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminBackofficeApplication.class, args);
	}

}
