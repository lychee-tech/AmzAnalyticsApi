package com.lychee.amz.analytics;


import com.lychee.amz.analytics.advice.ConfigurationAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;


@SpringBootApplication
@PropertySource("classpath:/messages.properties")
@PropertySource("classpath:/codes.properties")
public class AmzAnalyticsApiApplication {
	@Autowired
	private ConfigurationAdvice configurationAdvice;

	@Bean(name = "messageSource")
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public Validator localValidatorFactoryBean() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(messageSource());
		return validator;
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(AmzAnalyticsApiApplication.class);
		app.run(args);
	}
}
