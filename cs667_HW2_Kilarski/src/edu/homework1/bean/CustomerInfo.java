/**
ryszard kilarski
met cs 667
emrys@bu.edu
bu id: u81-39-8560
 */
package edu.homework1.bean;

import java.util.HashMap;
import java.util.Map;

public class CustomerInfo {
    private String customerId;
    private String firstName;
    private String lastName;
    private String emailAddress;

    private static Map<String, CustomerInfo> customers;

    public CustomerInfo() {
    }

    /**
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId
     *            the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     *            the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress
     *            the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Add a customer to the internal customer database.
     * 
     * @param info
     */
    public static synchronized void addCustomer(CustomerInfo info) {
        if (customers == null) {
            customers = new HashMap<String, CustomerInfo>();
        }

        customers.put(info.getCustomerId(), info);
    }

    /**
     * Get a customer from the internal customer database.
     * 
     * @param id
     * @return
     */
    public static CustomerInfo getCustomer(String id) {
        if (customers == null) {
            return null;
        }
        return ((CustomerInfo) customers.get(id));
    }
}
