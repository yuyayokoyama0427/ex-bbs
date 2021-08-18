package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.repository.ArticleRepository;

@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	
	@RequestMapping("")
	public String index() {
		return "/article/list";
	}

}
