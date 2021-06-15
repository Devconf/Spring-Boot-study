package com.example.SampleWebApp;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.SampleWebApp.repository.MyDataRepository;

@Controller // spring-boot-starter-thymeleaf dependencies를 설정 해주었기 때문에 view탬플릿으로 동작 가능
public class HeloController {
	
	@PostConstruct
	public void init() {
		MyData d1 =new MyData();
		d1.setName("kim");
		d1.setAge(123);
		d1.setMail("kim@gilbut.co.kr");
		d1.setMemo("090-999-999");
		repository.saveAndFlush(d1);
		
		MyData d2 =new MyData();
		d2.setName("lee");
		d2.setAge(15);
		d2.setMail("lee@flower");
		d2.setMemo("080-888-888");
		repository.saveAndFlush(d2);
		
		MyData d3 =new MyData();
		d3.setName("choi");
		d3.setAge(37);
		d3.setMail("choi@happy");
		d3.setMemo("070-777-777");
		repository.saveAndFlush(d3);
	}

	@Autowired
	MyDataRepository repository;	
	
	@GetMapping("/")
	public ModelAndView index(@ModelAttribute("formModel") MyData mydata, ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("msg","this id sample content.");
		mav.addObject("formModel",mydata);
		Iterable<MyData> list = repository.findAll();
		mav.addObject("datalist",list);
		return mav; 	
	}
	
	@PostMapping(value = "/")
	@Transactional(readOnly = false)
	public ModelAndView form(@ModelAttribute("formModel") @Validated MyData mydata , BindingResult result, ModelAndView mav) {
		ModelAndView res=null;
		if(!result.hasErrors()) {
			repository.saveAndFlush(mydata);
			res= new ModelAndView("redirect:/");
		}
		else {
			mav.setViewName("index");
			mav.addObject("msg","sorry, error is occured...");
			Iterable<MyData> list = repository.findAll();
			mav.addObject("datalist",list);
			res= mav;
		}
		return res;
	}
	
	@GetMapping(value = "/edit/{id}")
	public ModelAndView edit(@ModelAttribute("formModel") MyData mydata, @PathVariable int id, ModelAndView mav) {
		mav.setViewName("edit");
		mav.addObject("title","edit my mydata");
		Optional<MyData> data = repository.findById((long)id);
		mav.addObject("formModel",data.get());
		return mav;
	}
	
	@PostMapping(value = "/edit")
	@Transactional(readOnly = false)
	public ModelAndView updata(@ModelAttribute("formModel") MyData mydata, ModelAndView mav) {
		repository.saveAndFlush(mydata);
		return new ModelAndView("redirect:/");
	}
	
	@GetMapping(value = "/delete/{id}")
	public ModelAndView delete(@PathVariable int id, ModelAndView mav) {
		mav.setViewName("delete");
		mav.addObject("title","delete my mydata");
		Optional<MyData> data = repository.findById((long)id);
		mav.addObject("formModel",data.get());
		return mav;
	}
	
	@PostMapping(value = "/delete")
	@Transactional(readOnly = false)
	public ModelAndView remove(@RequestParam long id, ModelAndView mav) {
		repository.deleteById(id);
		return new ModelAndView("redirect:/");
	}
}

