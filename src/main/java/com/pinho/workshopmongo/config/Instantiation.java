package com.pinho.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.pinho.workshopmongo.domain.User;
import com.pinho.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
	 userRepository.deleteAll();
		
	 User user0 = new User(null, "Maria Brown", "maria@gmail.com");
	 User user1 = new User(null, "Alex Green", "alex@gmail.com");
	 User user2 = new User(null, "Bob Grey", "bob@gmail.com");
	 
	 userRepository.saveAll(Arrays.asList(user0, user1, user2));
	 
	}

}
