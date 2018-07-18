package com.leysoft.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.vanroy.springdata.jest.JestElasticsearchTemplate;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;

@Configuration
public class ElasticsearchConfiguration {
	
	@Value(value = "${spring.data.jest.uri}")
	private String clusterNode;
	
	@Value(value = "${spring.data.jest.multi-threaded:true}")
	private boolean multiThreaded;
	
	@Value(value = "${spring.data.jest.default-max-total-connection-per-route:2}")
	private int defaultMaxConnection;
	
	@Value(value = "${spring.data.jest.max-total-connection:10}")
	private int maxConnection;
	
	@Value(value = "${spring.data.jest.read-timeout}")
	private int readTimeout;
	
	@Value(value = "${spring.data.jest.connection-timeout}")
	private int connectionTimeout;
	
	@Bean
	public HttpClientConfig httpClientConfig() {
		return new HttpClientConfig.Builder(clusterNode)
				.multiThreaded(multiThreaded)
				.defaultMaxTotalConnectionPerRoute(defaultMaxConnection)
				.maxTotalConnection(maxConnection)
				.connTimeout(connectionTimeout)
				.readTimeout(readTimeout).build();
	}
	
	@Bean
	public JestClientFactory jestClientFactory(HttpClientConfig config) {
		JestClientFactory factory = new JestClientFactory();
		factory.setHttpClientConfig(config);
		return factory;
	}
	
	@Bean
	public JestClient jestClient(JestClientFactory factory) {
		return factory.getObject();
	}
	
	@Bean
	public JestElasticsearchTemplate jestElasticsearchTemplate(JestClient jestClient) {
		return new JestElasticsearchTemplate(jestClient);
	}
}
