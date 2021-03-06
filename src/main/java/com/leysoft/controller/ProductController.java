package com.leysoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leysoft.document.Product;
import com.leysoft.service.inter.ProductService;

@RestController
@RequestMapping(value = {"/product"})
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(value = {"/name/{name}"})
	public ResponseEntity<List<Product>> getByName(@PathVariable(name = "name") String name) {
		return ResponseEntity.ok(productService.findByName("Hamburguesa Exprés"));
	}
}
