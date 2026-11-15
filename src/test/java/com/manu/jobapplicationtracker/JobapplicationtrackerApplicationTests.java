package com.manu.jobapplicationtracker;

import com.manu.jobapplicationtracker.dto.UserRequestDto;
import com.manu.jobapplicationtracker.repository.UserRepo;
import com.manu.jobapplicationtracker.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JobapplicationtrackerApplicationTests {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepo userRepo;

	@Test
	void contextLoads() {
		UserRequestDto userRequestDto = new UserRequestDto();
		userRequestDto.setName("Manu");
		userRequestDto.setEmail("manupaul@gmail.com");
		userRequestDto.setPassword("abc");
		userService.deleteUser("871af861-6466-4e50-9753-dab300fc3db3");
	}

}
