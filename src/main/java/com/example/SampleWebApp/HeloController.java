package com.example.SampleWebApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller // spring-boot-starter-thymeleaf dependencies를 설정 해주었기 때문에 view탬플릿으로 동작 가능
public class HeloController {

	@GetMapping("/")
	public ModelAndView index(ModelAndView mav) {
		mav.addObject("msg","이름을 적어서 전송해주세요.");
		mav.setViewName("index");
		return mav;
	}
	
	@PostMapping("/")
	public ModelAndView send(@RequestParam("text1") String str,ModelAndView mav) {
		mav.addObject("msg","안녕하세요! "+ str +"님!");
		mav.setViewName("index");
		return mav;
	}
}
