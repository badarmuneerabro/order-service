package com.shop.orderservice.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shop.orderservice.dto.ProductDTO;

@FeignClient(name = "PRODUCT-SERVICE", path = "/products")
public interface ProductServiceFeignClient 
{
	@GetMapping("/{productId}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable("productId") String productId);

}
