package com.coderkamlesh.coderschool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.coderkamlesh.coderschool.model.Contact;
import com.coderkamlesh.coderschool.service.ContactService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ContactController {

	private final ContactService contactService;

	@Autowired
	public ContactController(ContactService contactService) {
		this.contactService = contactService;
	}

	@GetMapping("/contact")
	public String displayContactPage(Model model) {

		model.addAttribute("contact", new Contact());
		return "contact.html";
	}

//	@PostMapping("/saveMsg")
//	public ModelAndView saveMessage(@RequestParam String name, @RequestParam String mobileNum,
//			@RequestParam String email, @RequestParam String subject, @RequestParam String message) {
//		log.info("name: " + name);
//		log.info("mobile Number : " + mobileNum);
//		log.info("email: " + email);
//		log.info("Subject : " + subject);
//		log.info("Message : " + message);
//
//		return new ModelAndView("redirect:/contact");
//	}

	@PostMapping("/saveMsg")
	public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {

		if (errors.hasErrors()) {
			log.error("Contact form validation failed due to: " + errors.toString());
			return "contact.html";
		}

		contactService.SaveMessageDetails(contact);
		return "redirect:/contact";
	}

	@RequestMapping("/displayMessages/page/{pageNum}")
	public ModelAndView displayMessages(Model model, @PathVariable(name = "pageNum") int pageNum,
			@RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir) {
		Page<Contact> msgPage = contactService.findMsgsWithOpenStatus(pageNum, sortField, sortDir);
		List<Contact> contactMsgs = msgPage.getContent();
		ModelAndView modelAndView = new ModelAndView("messages.html");
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", msgPage.getTotalPages());
		model.addAttribute("totalMsgs", msgPage.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		modelAndView.addObject("contactMsgs", contactMsgs);
		return modelAndView;
	}

	@GetMapping(value = "/closeMsg")
	public String closeMsg(@RequestParam int id) {
		contactService.updateMsgStatus(id);
		return "redirect:/displayMessages/page/1?sortField=name&sortDir=desc";
	}

}
