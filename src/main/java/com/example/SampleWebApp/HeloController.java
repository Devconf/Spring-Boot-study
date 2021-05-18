package com.example.SampleWebApp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // spring-boot-starter-thymeleaf dependencies를 설정 해주었기 때문에 view탬플릿으로 동작 가능
public class HeloController {

	@RequestMapping("/{num}")
	public String index(@PathVariable int num, Model model) {
		int res = 0;
		for(int i=0;i<=num;i++) {
			res+=i;
		}
		model.addAttribute("msg","total: "+ res);
		return "index";
	}
	
	@RequestMapping("/main/{name}")
	public ModelAndView mainPage(@PathVariable String name, ModelAndView mav) {
		mav.addObject("name",name);
		mav.setViewName("main");
		return mav;
	}
}
