package com.bib.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ControlleMyApp {
	
	@GetMapping("/public/fct")
	public String fct1() {
		return "hello to guest";
	}
	
	@GetMapping("/api/fct_api")
	public String fct2() {
		return "hello to user";
	}
	@GetMapping("/admin/fct_api")
	public String fct3() {
		return "hello to admin";
	}
}
