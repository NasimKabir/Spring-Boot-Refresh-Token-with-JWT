package com.spring.db;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.annotations.SQLDelete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.spring.model.ERole;
import com.spring.model.Role;
import com.spring.repository.RoleRepository;

@Component
public class DatabaseInitiaizer {
	@Autowired
	private RoleRepository roleRepository;

	@PostConstruct
	public void init() {
		roleRepository.save(new Role(ERole.ROLE_ADMIN));
		roleRepository.save(new Role(ERole.ROLE_MODERATOR));
		roleRepository.save(new Role(ERole.ROLE_USER));
	}
	
}
