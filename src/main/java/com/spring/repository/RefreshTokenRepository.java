package com.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.spring.model.RefreshToken;
import com.spring.model.User;
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	  Optional<RefreshToken> findByToken(String token);

	  @Modifying
	  int deleteByUser(User user);
}
