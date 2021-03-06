package com.ticketStore.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ticketStore.domain.Ticket;
import com.ticketStore.domain.User;
import com.ticketStore.service.TicketService;
import com.ticketStore.service.UserService;

@Controller
public class SearchController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	TicketService ticketService;
	
	@RequestMapping("/searchByCategory")
	public String searchByCategory(
			@RequestParam("category") String category,
			Model model, Principal principal
			) {
		if(principal != null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		
		String classActiveCategory = "active" + category;
		classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
		classActiveCategory = classActiveCategory.replaceAll("&", "");
		model.addAttribute(classActiveCategory, true);
		
		List<Ticket> ticketList = ticketService.findByCategory(category);
		
		if(ticketList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "ticketshelf";		
		}
		
		model.addAttribute("ticketList", ticketList);
		
		return "ticketshelf";
	}
	
	@RequestMapping("/searchTicket")
	public String searchTicket(
			@ModelAttribute("keyword") String keyword,
			Principal principal, Model model) {
		if(principal != null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		
		List<Ticket> ticketList = ticketService.fuzzySearch(keyword);
		
		if(ticketList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "ticketshelf";		
		}
		model.addAttribute("ticketList", ticketList);
		
		return "ticketshelf";
		
	}
}
