package com.coderkamlesh.coderschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping(value = { "", "/", "home" })
	public String displayHomePage(Model model) {
		model.addAttribute("username", "Saheb kumar");
		return "home.html";
	}
}
