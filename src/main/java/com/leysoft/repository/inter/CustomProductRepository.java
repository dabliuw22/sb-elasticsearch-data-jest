package com.leysoft.repository.inter;

import java.util.List;

import com.leysoft.document.Product;

public interface CustomProductRepository {
	
	public List<Product> findByName(String name);
}
