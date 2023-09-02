package com.shop.orderservice.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shop.orderservice.dto.OrderDTO;
import com.shop.orderservice.dto.ProductDTO;
import com.shop.orderservice.dto.UserDTO;
import com.shop.orderservice.feignclients.ProductServiceFeignClient;
import com.shop.orderservice.feignclients.UserServiceFeignClient;
import com.shop.orderservice.model.Item;
import com.shop.orderservice.model.Order;
import com.shop.orderservice.repo.OrderRepository;

@Service
public class OrderServiceImp implements OrderService
{
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserServiceFeignClient userRestClient;
	
	@Autowired
	private ProductServiceFeignClient productClient;
	
	@Override
	public void saveOrder(OrderDTO orderDTO) throws SQLIntegrityConstraintViolationException 
	{
		double totalPrice = 0.0;
		try
		{
			ResponseEntity<UserDTO> responseEntity = userRestClient.getUserById(orderDTO.getUserId());
			
			
			
			for(Item item : orderDTO.getItems())
			{
				ResponseEntity<ProductDTO> productResponseEntity = productClient.getProductById(item.getProductId());
				
				if(productResponseEntity.getStatusCode() == HttpStatus.OK)
				{
					ProductDTO productDTO = productResponseEntity.getBody();
					totalPrice = totalPrice + (productDTO.getUnitPrice() * item.getQuantity());
					item.setName(productDTO.getName());
				}
				
			}
		}catch(Exception e)
		{
			throw new SQLIntegrityConstraintViolationException(e.getMessage());
		}
		orderDTO.setTotalPrice(totalPrice);
		Order order = modelMapper.map(orderDTO, Order.class);
		
		for(Item item : order.getItems())
		{
			item.setOrder(order);
		}
		
		orderRepository.save(order);
		
		orderDTO.setId(order.getId());
	}
	
	@Override
	public OrderDTO getOrderById(long id) 
	{
		Order order = orderRepository.findById(id).orElse(null);
		
		return (order == null) ? null : modelMapper.map(order, OrderDTO.class);
	}
	
	@Override
	public List<OrderDTO> getAllOrders() 
	{
		List<Order> list = orderRepository.findAll();
		
		List<OrderDTO> allOrders = new ArrayList<>(); 
		for(Order o : list)
		{
			OrderDTO orderDTO = modelMapper.map(o, OrderDTO.class);
			allOrders.add(orderDTO);
		}
		
		return allOrders;
	}
	
	@Override
	public void updateOrder(OrderDTO orderDTO) 
	{
		Order order = modelMapper.map(orderDTO, Order.class);
		orderRepository.save(order);
	}
	
	@Override
	public List<OrderDTO> getAllOrdersOfUser(long userId) 
	{
		List<Order> orderDTOList = orderRepository.findAllByUserId(userId);
		
		List<OrderDTO> list = new ArrayList<>();
		for(Order o : orderDTOList)
		{
			OrderDTO orderDTO = modelMapper.map(o, OrderDTO.class);
			
			list.add(orderDTO);
			
		}
		
		
		return list;
	}
}
