package com.coderkamlesh.coderschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coderkamlesh.coderschool.model.Person;
import com.coderkamlesh.coderschool.repository.PersonRepository;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DashboardController {

	@Autowired
	PersonRepository  personRepository;
	
    @RequestMapping("/dashboard")
    public String displayDashboard(Model model,Authentication authentication,HttpSession session) {
    	Person person =personRepository.readByEmail(authentication.getName());
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        if(null != person.getCoderClass() && null != person.getCoderClass().getName()){
            model.addAttribute("enrolledClass", person.getCoderClass().getName());
        }
        session.setAttribute("loggedInPerson", person);
        return "dashboard.html";
    }

}