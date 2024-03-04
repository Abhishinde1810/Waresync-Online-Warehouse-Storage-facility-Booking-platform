package com.app;

import org.apache.catalina.core.ApplicationContext;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import lombok.ToString;

@SpringBootApplication

public class Application {

	public static void main(String[] args) {
	ConfigurableApplicationContext  ctx	=SpringApplication.run(Application.class, args);
//	String[] beanNames = ctx.getBeanDefinitionNames();
//    for (String beanName : beanNames) {
//        System.out.println(beanName);
//    }
	}

	@Bean // equivalent to <bean id ..../> in xml file
	public ModelMapper mapper() {
		ModelMapper modelMapper = new ModelMapper();		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
	.setPropertyCondition(Conditions.isNotNull());
		return modelMapper;
	}

}
