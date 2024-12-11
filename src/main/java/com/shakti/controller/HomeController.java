package com.shakti.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shakti.model.ProductDetails;

@Controller
public class HomeController {

	@GetMapping("/")
	public ModelAndView home(Model model) {
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.jsp"+ "");
        ProductDetails pd =new ProductDetails();
        pd.setAllProducts(List.of("Shirts","Tshirts","Bags","Sweaters"));
        modelAndView.addObject("productDetails",pd);
        return modelAndView;
	}
}
