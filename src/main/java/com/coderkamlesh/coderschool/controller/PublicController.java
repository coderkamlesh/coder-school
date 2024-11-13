package com.coderkamlesh.coderschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coderkamlesh.coderschool.model.Person;
import com.coderkamlesh.coderschool.service.PersonService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("public")
public class PublicController {

	@Autowired
	PersonService personService;
	
	@GetMapping("/register")
	public String displayRegisterPage(Model model) {
		model.addAttribute("person", new Person());
		return "register.html";
	}
	
	@PostMapping("/createUser")
	public String createUser(@Valid @ModelAttribute("person") Person person,Errors errors ) {
		if(errors.hasErrors()) {
			return "register.html";
		}
		
		boolean isSaved=personService.createNewPerson(person);
		
		if(isSaved) {
			return "redirect:/login?resgister=true";
		}else {
			return "register.html";
		}
		
		
	}
}
