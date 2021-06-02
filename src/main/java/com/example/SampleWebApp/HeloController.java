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
		mav.addObject("msg", "current data.");
		mav.setViewName("index");
		DataObject obj = new DataObject(123, "lee", "lee@lower");
		mav.addObject("object",obj);
		return mav; 
	}
	
	class DataObject{
		private int id;
		private String name;
		private String value;
		
		public DataObject(int id, String name, String value) {
			super();
			this.id= id;
			this.name=name;
			this.value=value;
		}
		
		public int getId() {
			return this.id;
		}
		
		public void setId(int id) {
			this.id=id;
		}
		
		public String getName() {
			return this.name;
		}
		
		public void setName(String name) {
			this.name=name;
		}
		
		public String getValue() {
			return this.value;
		}
		
		public void setValue(String value) {
			this.value=value;
		}
	}
}
