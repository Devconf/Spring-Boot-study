package com.example.SampleWebApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller // spring-boot-starter-thymeleaf dependencies를 설정 해주었기 때문에 view탬플릿으로 동작 가능
public class HeloController {

	@GetMapping("/{id}")
	public ModelAndView index(@PathVariable int id, ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("id",id);
		mav.addObject("check",id>=0);
		mav.addObject("trueValue","POSITIVE!");
		mav.addObject("falseValue","negative...");
		return mav; 
	}
}

