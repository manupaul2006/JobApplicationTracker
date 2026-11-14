package com.manu.jobapplicationtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JobapplicationtrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobapplicationtrackerApplication.class, args);
	}

}
