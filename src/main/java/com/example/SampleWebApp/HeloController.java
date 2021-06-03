package com.example.SampleWebApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller // spring-boot-starter-thymeleaf dependencies를 설정 해주었기 때문에 view탬플릿으로 동작 가능
public class HeloController {

	@GetMapping("/{month}")
	public ModelAndView index(@PathVariable int month, ModelAndView mav) {
		mav.setViewName("index");
		int m= Math.abs(month)%12;
		m = m==0 ? 12 : m;
		mav.addObject("month",m);
		mav.addObject("check",Math.floor(m/3));
		return mav; 
	}
}

