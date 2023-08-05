package com.shop.orderservice.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shop.orderservice.dto.UserDTO;

@FeignClient(name = "USER-SERVICE", path = "/users")
public interface UserServiceFeignClient 
{
	@GetMapping("/{userId}")
	ResponseEntity<UserDTO> getUserById(@PathVariable("userId") long id);

}
