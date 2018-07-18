package com.leysoft.extractor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.vanroy.springdata.jest.mapper.JestResultsExtractor;
import com.leysoft.document.Product;

import io.searchbox.core.SearchResult;

public class ProductResultsExtractor implements JestResultsExtractor<List<Product>> {

	@Override
	@SuppressWarnings(value = { "rawtypes", "unchecked" })
	public List<Product> extract(SearchResult response) {
		List<SearchResult.Hit<HashMap, Void>> hits = response.getHits(HashMap.class);
		List<Product> result = new ArrayList<>();
		hits.forEach(hit -> {
			Map<String, Object> map = hit.source;
			Product product = new Product();
			product.setId((String) map.get("id"));
			product.setName((String) map.get("name"));
			product.setPrice((Double) map.get("price"));
			product.setStoreId((Double) map.get("store-id"));
		});
		return result;
	}
}
