package com.shakti.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shakti.model.ProductDetails;
import com.shakti.service.PdfExporter;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class BillingController {

	List<ProductDetails> products = new ArrayList<ProductDetails>(); 
	
	
	int sno =1;
	 @RequestMapping(value="/save", method = { RequestMethod.GET, RequestMethod.POST })  
	 public ModelAndView home(@ModelAttribute("productDetails") ProductDetails productDetails) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("billing.jsp");
        productDetails.setSno(sno++);
        products.add(productDetails);
        products = sortListByDate(products);
        modelAndView.addObject("products", products);
        return modelAndView;
	}
	 
	 @RequestMapping(value="/savefinal", method = { RequestMethod.GET, RequestMethod.POST })  
	 public ModelAndView savefinal(@ModelAttribute("productDetails") ProductDetails productDetails) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("result.jsp");
        modelAndView.addObject("products", products);
        return modelAndView;
	}
	
	 @RequestMapping(value="/bill", method = { RequestMethod.GET, RequestMethod.POST })
	 public ModelAndView bill(@ModelAttribute("productDetails") ProductDetails productDetails,Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("billing.jsp");
        return modelAndView;
	}
	 
	 @RequestMapping(value="/newbill", method = { RequestMethod.GET, RequestMethod.POST })
	 public ModelAndView newbill(@ModelAttribute("productDetails") ProductDetails productDetails,Model model) {
        ModelAndView modelAndView = new ModelAndView();
        products=new ArrayList<>();
        modelAndView.setViewName("billing.jsp");
        return modelAndView;
	}
	
	 @RequestMapping(value="/delete", method = { RequestMethod.GET, RequestMethod.POST })  
	 public ModelAndView deleteRecord(@ModelAttribute("productDetails") ProductDetails productDetails, @RequestParam("sno") int sNumber) {
        ModelAndView modelAndView = new ModelAndView();
        products.removeIf(i->i.getSno()==sNumber);
        products = sortListByDate(products);
        modelAndView.setViewName("billing.jsp");
        modelAndView.addObject("products", products);
        return modelAndView;
	}
	 
	 @RequestMapping(value="/print", method = { RequestMethod.GET, RequestMethod.POST })  
	 public ModelAndView print(@ModelAttribute("productDetails") ProductDetails productDetails, HttpServletResponse response) {
		response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=ShaktiEmbroidery_"+ products.get(0).getPartyName()+"_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
		print(response,currentDateTime);
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("result.jsp");
        modelAndView.addObject("products", products);
        return modelAndView;
	}
	
	 private List<ProductDetails> sortListByDate(List<ProductDetails> products) {
		return products.stream().sorted((i1,i2)->i1.getDate().compareTo(i2.getDate())).collect(Collectors.toList());
	 }
	 
	 private void print(HttpServletResponse response,String date) {
			PdfExporter exporter= new PdfExporter(products);
			try {
				exporter.export(response,date);
			} catch (IOException e) {
				e.printStackTrace();
			}
	 }
	
}
