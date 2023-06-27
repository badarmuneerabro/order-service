package com.shop.orderservice.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.shop.orderservice.dto.OrderDTO;

public interface OrderService 
{
	void saveOrder(OrderDTO orderDTO) throws SQLIntegrityConstraintViolationException;
	OrderDTO getOrderById(long id);
	List<OrderDTO> getAllOrders();
	void updateOrder(OrderDTO orderDTO);

}
