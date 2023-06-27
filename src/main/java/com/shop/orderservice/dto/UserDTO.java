package com.shop.orderservice.dto;


public class UserDTO {
	private long id;
	private String firstName;

	private String lastName;
	private String email;
	private String password;
	
	private String shippingAddress;
	
	private long postalCode;
	
	private UserType userType = UserType.CUSTOMER;
	
	private PhoneDTO phoneNo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public long getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(long postalCode) {
		this.postalCode = postalCode;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public PhoneDTO getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(PhoneDTO phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	
	

}
