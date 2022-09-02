package com.spring.model;

import java.time.Instant;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "refresh_token")
public class RefreshToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, unique = true)
	private String token;

	@Column(nullable = false)
	private Instant expiryDate;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
}
