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
		mav.addObject("msg", "폼을 전송해주세요.");
		mav.setViewName("index");
		return mav;
	}

	@PostMapping("/")
	public ModelAndView send(@RequestParam(value = "check1", required = false) boolean check1,
			@RequestParam(value = "radio1", required = false) String radio1,
			@RequestParam(value = "select1", required = false) String select1,
			@RequestParam(value = "select2", required = false) String[] select2, ModelAndView mav) {

		String res = "";
		try {
			res = "check1 : " + check1 + " radio1 : " + radio1 + " select1 : " + select1 + "\nselect2 : ";
		} catch (Exception e) {
		}
		try {
			res += select2[0];
			for (int i = 1; i < select2.length; i++) {
				res += ", " + select2[i];
			}

		} catch (Exception e) {
			res += null;
		}
		mav.addObject("msg", res);
		mav.setViewName("index");
		return mav;
	}

	
	@PostMapping(value = "/main", name = "signIn") 
	public ModelAndView signIn(
			@RequestParam(value = "userID", required = true) String userId,
			@RequestParam(value = "userPW",required = true) String userPW,
			ModelAndView mav) {
		if( userId.equals("devconf") && userPW.equals("132600qa@")) {
			mav.addObject("name", "hyunsik");
			System.out.println("success");
			}
		mav.setViewName("main");
		return mav; 
	}		 
	
	@GetMapping(value = "/main", name = "signOut") 
	public ModelAndView signOut(
			ModelAndView mav) {
		mav.setViewName("redirect:/");
		return mav; 
	}		 
}
