package com.coderkamlesh.coderschool.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.coderkamlesh.coderschool.model.Holiday;
import com.coderkamlesh.coderschool.repository.HolidaysRepository;
import com.coderkamlesh.coderschool.service.ContactService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HolidaysController {
	
	@Autowired
	private HolidaysRepository holidayRepository;

	@GetMapping("/holidays/{display}")
	public String displayHolidays(@PathVariable String display, Model model) {
		if (null != display && display.equals("all")) {
			model.addAttribute("festival", true);
			model.addAttribute("federal", true);
		} else if (null != display && display.equals("federal")) {
			model.addAttribute("federal", true);
		} else if (null != display && display.equals("festival")) {
			model.addAttribute("festival", true);
		}

		Iterable<Holiday> holidays =holidayRepository.findAll();
		List<Holiday> holidayList=StreamSupport.stream(holidays.spliterator(), false).collect(Collectors.toList());
		Holiday.Type[] types = Holiday.Type.values();
		for (Holiday.Type type : types) {
			model.addAttribute(type.toString(),
					(holidayList.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
		}
		return "holidays.html";
	}
}
