package com.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	@GetMapping("/admin")
	public String admin() {

		return "admin";
	}

	@GetMapping("/user")
	public String user() {

		return "user";
	}
	
	@GetMapping("/manager")
	public String manager() {

		return "manager";
	}

	@GetMapping("/all")
	public String all() {

		return "<h2>Hello Everyone!</h2>";
	}
}
