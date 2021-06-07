package com.example.SampleWebApp;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller // spring-boot-starter-thymeleaf dependencies를 설정 해주었기 때문에 view탬플릿으로 동작 가능
public class HeloController {

	@GetMapping("/{num}")
	public ModelAndView index(@PathVariable int num, ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("num",num);
		if(num>=0) {
			mav.addObject("check","num>=data.size() ? 0 : num");
		}
		else {
			mav.addObject("check","num<=data.size()*-1 ? 0 : num*-1");
		}
		ArrayList<DataObject> data = new ArrayList<DataObject>();
		data.add(new DataObject(0, "park","park@yamada"));
		data.add(new DataObject(1, "lee", "lee@flower"));
		data.add(new DataObject(2, "choi","choi@happy"));
		mav.addObject("data",data);
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

