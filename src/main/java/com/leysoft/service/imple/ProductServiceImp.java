package com.leysoft.service.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leysoft.document.Product;
import com.leysoft.repository.inter.CustomProductRepository;
import com.leysoft.service.inter.ProductService;

@Service
public class ProductServiceImp implements ProductService {

	@Autowired
	private CustomProductRepository customProductRepository;
	
	@Override
	public List<Product> findByName(String name) {
		return customProductRepository.findByName(name);
	}
}
