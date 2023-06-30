package com.shop.orderservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>
{
	List<Order> findAllByUserId(long userId);

}
