package com.example.SampleWebApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller //spring-boot-starter-thymeleaf dependencies를 설정 해주었기 때문에 view탬플릿으로 동작 가능
public class HeloController {
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
