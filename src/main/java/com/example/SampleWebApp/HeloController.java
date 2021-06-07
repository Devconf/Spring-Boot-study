package com.example.SampleWebApp;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller // spring-boot-starter-thymeleaf dependencies를 설정 해주었기 때문에 view탬플릿으로 동작 가능
public class HeloController {

	@GetMapping("/")
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		ArrayList<String[]> data = new ArrayList<String[]>();
		data.add(new String[] {"park","park@yamada","090-999-999"});
		data.add(new String[] {"lee","lee@flower","080-888-888"});
		data.add(new String[] {"choi","choi@happy","080-888-888"});
		mav.addObject("data",data);
		return mav; 
	}
}

