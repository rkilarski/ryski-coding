/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package edu.cs751hw1.model;

public class BillTo {
    
    private String city;
    private String company;
    private String country;
    private String name;
    private String state;
    private String street;
    private String zipCode;
    
    public String getCity() {
        return city;
    }
    public String getCompany() {
        return company;
    }
    public String getCountry() {
        return country;
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
    public void setCompany(String company) {
        this.company = company;
    }
    public void setCountry(String country) {
        this.country = country;
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
