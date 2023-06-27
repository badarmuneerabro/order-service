package com.shop.orderservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>
{

}
