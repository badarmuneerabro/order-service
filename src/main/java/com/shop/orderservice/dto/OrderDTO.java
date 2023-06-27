package com.shop.orderservice.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.shop.orderservice.model.Item;
import com.shop.orderservice.model.OrderStatus;

public class OrderDTO 
{

	private long id;
	
	@NotNull(message = "*UserID is required.")
	private Long userId;
	
	private long totalPrice;
	
	private String billingAddress;
	private OrderStatus status = OrderStatus.PENDING;

	@Size(min = 1, message = "At least there should be one item")
	private Set<Item> items = new HashSet<>();


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public long getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}


	public String getBillingAddress() {
		return billingAddress;
	}


	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}


	public OrderStatus getStatus() {
		return status;
	}


	public void setStatus(OrderStatus status) {
		this.status = status;
	}


	public Set<Item> getItems() {
		return items;
	}


	public void setItems(Set<Item> items) {
		this.items = items;
	}
	
	
}
