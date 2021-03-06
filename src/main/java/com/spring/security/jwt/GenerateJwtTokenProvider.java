package com.spring.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.spring.security.service.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
@Component
public class GenerateJwtTokenProvider {
	 private static final Logger logger = LoggerFactory.getLogger(GenerateJwtTokenProvider.class);
	 

	  @Value("${jwtSecret}")
	  private String jwtSecret;

	  @Value("${jwtExpirationMs}")
	  private long jwtExpirationMs;
	  
	  public String generateJwtToken(UserDetailsImpl userPrincipal) {
		    return generateTokenFromUsername(userPrincipal.getUsername());
		  }
	  

		  public String generateTokenFromUsername(String username) {
			  return Jwts.builder()
						.setSubject(username)
						.setIssuedAt(new Date())
						.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
						.signWith(SignatureAlgorithm.HS512, jwtSecret)
						.compact();
		  }
	   

		public String getUsernameFormJwtToken(String token) {
			return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
		}

		public boolean validateJwtToken(String token) {
			try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
			}catch(MalformedJwtException e) {
				logger.error("Invalid Jwt token {}", e.getMessage());
			}catch(ExpiredJwtException e) {
				logger.error("Jwt token expired {}", e.getMessage());
			}catch(UnsupportedJwtException e) {
				logger.error("Jwt token is unsupported {}", e.getMessage());
			}catch(IllegalArgumentException e) {
				logger.error("Jwt claims string is empty {}", e.getMessage());
			}
			return false;
		}
}
