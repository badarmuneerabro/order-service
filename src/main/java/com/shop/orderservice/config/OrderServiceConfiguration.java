package com.shop.orderservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderServiceConfiguration 
{
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

}
