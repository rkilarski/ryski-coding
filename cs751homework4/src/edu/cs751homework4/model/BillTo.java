/**
 * author: Ryszard Kilarski email: emrys@bu.edu bu id: U81-39-8560
 */
package edu.cs751homework4.model;

public class BillTo {

	private String city;
	private String name;
	private String state;
	private String street;
	private String zipCode;
	private String phone;

	public BillTo() {
	}

	public String getCity() {
		return city;
	}

	public String getPhone() {
		return phone;
	}

	public String getName() {
		return name;
	}

	public String getState() {
		return state;
	}

	public String getStreet() {
		return street;
	}

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
