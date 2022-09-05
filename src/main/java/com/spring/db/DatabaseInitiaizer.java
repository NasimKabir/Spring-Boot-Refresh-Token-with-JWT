package com.spring.db;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.spring.model.User;
import com.spring.repository.UserRepository;
import org.hibernate.annotations.SQLDelete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.spring.model.ERole;
import com.spring.model.Role;
import com.spring.repository.RoleRepository;

import java.util.*;

@Component
public class DatabaseInitiaizer {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void init() {
		Set<Role> roles = new HashSet<>();
		roles.add(new Role(ERole.ROLE_USER));

		roleRepository.saveAll(roles);

		User user=new User();
		user.setUsername("nasim");
		user.setPassword("$2a$10$1t3pSZ8CZkJSI5Tn8COaQezfNPcwO72M3LWYTU/EwBT4zyZpIFLnG");
		user.setEmail("nasim@gmail.com");
		user.setRoles( roles);
		userRepository.save(user);

	}
	
}
