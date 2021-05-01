package com.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	// Roles for users
	private static final String ROLE_1 = "ADMIN";
	private static final String ROLE_2 = "USER";
	private static final String ROLE_3 = "MANAGER";
	
	// In-memory users with roles
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {		
		auth.inMemoryAuthentication()
				.withUser("test@test.com")
				.password(passwordEncoder().encode("test"))
				.roles(ROLE_1)
				.and()
				.withUser("user@user.com")
				.password(passwordEncoder().encode("user"))
				.roles(ROLE_2)
				.and()
				.withUser("manager@manager.com")
				.password(passwordEncoder().encode("manager"))
				.roles(ROLE_3);
	}
	
	// Password encoding
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Authorized the request based on role
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/admin").hasRole(ROLE_1)
				.antMatchers("/user").hasAnyRole(ROLE_2)
				.antMatchers("/manager").hasAnyRole(ROLE_3)
				.antMatchers("/all").permitAll()
				.and().formLogin();
	}
}
