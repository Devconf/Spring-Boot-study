package com.example.SampleWebApp;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
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

	@Autowired
	MyDataRepository repository;	
	
	@PersistenceContext
	EntityManager entityManager;
	
	MyDataDaoImpl dao;
	
	@PostConstruct
	public void init() {
		dao = new MyDataDaoImpl(entityManager);
		
		MyData d1 =new MyData();
		d1.setName("kim");
		d1.setAge(123);
		d1.setMail("kim@gilbut.co.kr");
		d1.setMemo("090999999");
		repository.saveAndFlush(d1);
		
		MyData d2 =new MyData();
		d2.setName("lee");
		d2.setAge(15);
		d2.setMail("lee@flower");
		d2.setMemo("080888888");
		repository.saveAndFlush(d2);
		
		MyData d3 =new MyData();
		d3.setName("choi");
		d3.setAge(37);
		d3.setMail("choi@happy");
		d3.setMemo("070777777");
		repository.saveAndFlush(d3);
	}
	
	@GetMapping("/")
	public ModelAndView index(@ModelAttribute("formModel") MyData mydata, ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("msg","this id sample content.");
		mav.addObject("formModel",mydata);
		Iterable<MyData> list = dao.getAll();
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
		MyData data = dao.findById((long)id);
		mav.addObject("formModel",data);
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
	public ModelAndView remove(@RequestParam String name, ModelAndView mav) {
		List<MyData> list = dao.findByName(name);
		repository.deleteById(list.get(0).getId());
		return new ModelAndView("redirect:/");
	}
	
	@GetMapping(value = "/find")
	public ModelAndView find(ModelAndView mav) {
		mav.setViewName("find");
		mav.addObject("title","Find Page");
		mav.addObject("msg","MyData의 예제입니다.");
		mav.addObject("value","");
		Iterable<MyData> list = repository.findByAge(10, 20);
		mav.addObject("datalist", list);
		return mav;
	}
	
	@PostMapping(value = "/find")
	public ModelAndView search(HttpServletRequest request, ModelAndView mav) {
		mav.setViewName("find");
		String param = request.getParameter("fstr");
		if(param == "") {
			mav= new ModelAndView("redirect:/find");
		}
		else {
			mav.addObject("title","Find result");
			mav.addObject("msg", "「" + param + "」의 검색 결과" );
			mav.addObject("value", param);
			List<MyData> list = dao.find(param);
			mav.addObject("datalist",list);
		}
		return mav;
	}
}

