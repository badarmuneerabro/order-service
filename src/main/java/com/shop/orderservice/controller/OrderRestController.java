package com.shop.orderservice.controller;

import java.net.URI;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shop.orderservice.dto.OrderDTO;
import com.shop.orderservice.exception.ResourceNotFoundException;
import com.shop.orderservice.model.Item;
import com.shop.orderservice.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderRestController 
{
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/")
	public ResponseEntity<?> saveOrder(@Valid @RequestBody OrderDTO orderDTO) throws SQLIntegrityConstraintViolationException
	{
		orderService.saveOrder(orderDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(orderDTO.getId()).toUri();
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.setLocation(uri);
		
		return new ResponseEntity<>(null, headers, HttpStatus.CREATED);
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderDTO> getOrderById(@PathVariable("orderId") long orderId) throws ResourceNotFoundException
	{
		OrderDTO orderDTO = orderService.getOrderById(orderId);
		
		if(orderDTO == null)
			throw new ResourceNotFoundException("Order with id=" + orderId + " not found.");
		
		return new ResponseEntity<OrderDTO>(orderDTO, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<OrderDTO>> getAllOrders()
	{
		List<OrderDTO> list = orderService.getAllOrders();
		
		return new ResponseEntity<List<OrderDTO>>(list, HttpStatus.OK);
	}
	
	@PutMapping("/{orderId}")
	public ResponseEntity<OrderDTO> updateOrder(@PathVariable("orderId") long orderId, @Valid @RequestBody OrderDTO order)
	{
		OrderDTO orderDTO = orderService.getOrderById(orderId);
		if(orderDTO == null)
			throw new ResourceNotFoundException("Order with id=" + orderId + " not found");
		order.setId(orderId);
		orderService.updateOrder(order);
		
		return new ResponseEntity<OrderDTO>(order, HttpStatus.OK);
	}
}
