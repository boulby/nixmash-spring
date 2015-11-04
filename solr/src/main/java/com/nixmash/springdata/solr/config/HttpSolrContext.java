package com.nixmash.springdata.solr.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.support.HttpSolrServerFactoryBean;

/**
 * @author Petri Kainulainen
 */
@Configuration
@EnableSolrRepositories("com.nixmash.springdata.solr.repository")
@Profile("prod")
public class HttpSolrContext {

	private static final String PROPERTY_NAME_SOLR_SERVER_URL = "solr.server.url";

	@Resource
	private Environment environment;

	@Bean
	public HttpSolrServerFactoryBean solrServerFactoryBean() {
		HttpSolrServerFactoryBean factory = new HttpSolrServerFactoryBean();

		factory.setUrl(environment.getRequiredProperty(PROPERTY_NAME_SOLR_SERVER_URL));

		return factory;
	}

	@Bean
	public SolrTemplate solrTemplate() throws Exception {
		return new SolrTemplate(solrServerFactoryBean().getObject());
	}
}