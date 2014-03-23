/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package com.restfully.order.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "billTo")
public class BillTo {

	private String city;
	private String name;
	private String state;
	private String street;
	private String zipCode;
	private String phone;

	public BillTo() {
	}

	public BillTo(String name, String street, String city, String state, String zipCode, String phone) {
		this.name = name;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.phone = phone;
	}

	@XmlElement
	public String getCity() {
		return city;
	}

	@XmlElement
	public String getPhone() {
		return phone;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	@XmlElement
	public String getState() {
		return state;
	}

	@XmlElement
	public String getStreet() {
		return street;
	}

	@XmlElement
	public String getZipCode() {
		return zipCode;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}
