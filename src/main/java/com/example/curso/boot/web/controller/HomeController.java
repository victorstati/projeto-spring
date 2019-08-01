package com.example.curso.boot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/") //Acessa a raiz do projeto
	public String home() {
		return "/home";
	}
}
