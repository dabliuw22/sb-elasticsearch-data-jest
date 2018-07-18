package com.leysoft.repository.imple;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilterBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.core.query.SourceFilter;
import org.springframework.stereotype.Repository;

import com.github.vanroy.springdata.jest.JestElasticsearchTemplate;
import com.leysoft.document.Product;
import com.leysoft.extractor.ProductResultsExtractor;
import com.leysoft.repository.inter.CustomProductRepository;

@Repository
public class CustomProductRepositoryImp implements CustomProductRepository {

	@Autowired
	private JestElasticsearchTemplate jestElasticsearchTemplate;
	
	@Override
	public List<Product> findByName(String name) {
		QueryBuilder matchQuery = matchQuery("name", name);
		SourceFilter sourceFilter = new FetchSourceFilterBuilder().withIncludes("id", "name", "price", "store-id").build();
		SearchQuery query = new NativeSearchQueryBuilder().withQuery(matchQuery)
				.withSourceFilter(sourceFilter).build();
		return jestElasticsearchTemplate.query(query, new ProductResultsExtractor());
	}
}
