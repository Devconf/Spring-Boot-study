package com.example.SampleWebApp;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.SampleWebApp.repository.MsgDataRepository;

@Controller
public class MsgDataController {
	@PostConstruct
	public void init() {
		System.out.println("ok");
		dao = new MsgDataDaoImpl(entityManager);
	}
	
	@Autowired
	MsgDataRepository repository;
	
	@PersistenceContext
	EntityManager entityManager;
	
	MsgDataDaoImpl dao;
	
	@GetMapping(value = "/msg")
	public ModelAndView msg(ModelAndView mav) {
		mav.setViewName("showMsgData");
		mav.addObject("title", "Sample");
		mav.addObject("msg", "MsgData의 예제입니다.");
		MsgData msgdata = new MsgData();
		mav.addObject("formModel", msgdata);
		List<MsgData> list = dao.getAll();
		mav.addObject("datalist",list);
		return mav;
	}
	
	@PostMapping(value = "/msg")
	public ModelAndView msgform(@ModelAttribute @Valid MsgData msgdata, Errors result, ModelAndView mav) {
		if(result.hasErrors()) {
			mav.setViewName("showMsgData");
			mav.addObject("title", "Sample[ERROR]");
			mav.addObject("msg","값을 다시 확인해주세요!");
			MsgData msgdata1 = new MsgData();
			mav.addObject("formModel", msgdata1);
			List<MsgData> list = dao.getAll();
			mav.addObject("datalist",list);
			return mav;
		}
		else {
			repository.save(msgdata);
			return new ModelAndView("redirect:/msg");
		}
	}
}
