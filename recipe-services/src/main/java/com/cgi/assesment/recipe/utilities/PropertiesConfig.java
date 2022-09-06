package com.cgi.assesment.recipe.utilities;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
@PropertySources({
	@PropertySource("classpath:message-catalog.properties")
})
public class PropertiesConfig implements EnvironmentAware {
	
	public static Environment environment;

	@Override
	public void setEnvironment(Environment environment) {
		PropertiesConfig.environment= environment;
		
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfig() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
