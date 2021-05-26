package com.ticketStore.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ticketStore.domain.CartItem;
import com.ticketStore.domain.ShoppingCart;
import com.ticketStore.domain.Ticket;
import com.ticketStore.domain.User;
import com.ticketStore.service.CartItemService;
import com.ticketStore.service.ShoppingCartService;
import com.ticketStore.service.TicketService;
import com.ticketStore.service.UserService;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private TicketService ticketService;
	
	@RequestMapping("/cart")
	public String shoppingCart(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		ShoppingCart shoppingCart = user.getShoppingCart();
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		shoppingCartService.updateShoppingCart(shoppingCart);
		
		model.addAttribute("cartItemList", cartItemList);
		model.addAttribute("shoppingCart", shoppingCart);
		
		return "shoppingCart";
	}
	
	@RequestMapping("/addItem")
	public String addItem(
			@ModelAttribute("ticket") Ticket ticket,
			@ModelAttribute("qty") String qty,
			Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		ticket = ticketService.findOne(ticket.getId());
		
		if(Integer.parseInt(qty) > ticket.getInStockNumber()) {
			model.addAttribute("notEnoughStock", true);
			return "forward:/ticketDetail?id=" + ticket.getId();
		}
		
		CartItem cartItem = cartItemService.addTicketToCartItem(ticket, user, Integer.parseInt(qty));
		model.addAttribute("addTicketSuccess", true);
		
		return "forward:/ticketDetail?id=" + ticket.getId();
	}
	
	@RequestMapping("/updateCartItem")
	public String updateShoppingCart(
			@ModelAttribute("id") Long cartItemId,
			@ModelAttribute("qty") int qty
			) {
		CartItem cartItem = cartItemService.findById(cartItemId);
		cartItem.setQty(qty);
		cartItemService.updateCartItem(cartItem);
		
		return "forward:/shoppingCart/cart";
	}
	
	@RequestMapping("/removeItem")
	public String removeItem(@RequestParam("id") Long id) {
		cartItemService.removeCartItem(cartItemService.findById(id));
		return "forward:/shoppingCart/cart";
	}
}
