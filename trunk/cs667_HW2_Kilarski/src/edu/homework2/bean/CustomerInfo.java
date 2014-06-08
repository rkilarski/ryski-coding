/**
ryszard kilarski
met cs 667
emrys@bu.edu
bu id: u81-39-8560
 */
package edu.homework2.bean;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CustomerInfo {
	private static Map<String, CustomerInfo> customers = null;
	private String customerId = "";
	private String emailAddress = "";
	private String firstName = "";
	private String lastName = "";

	private String message = "";

	public String doNavigation() {
		// Get the customer bean from the session object, if any.
		boolean allFieldsNull = true;

		// Process the parameters to this page and add to the session.
		if ((customerId != null) && (!customerId.trim().equals(""))) {
			allFieldsNull = false;
		}
		if ((firstName != null) && (!firstName.trim().equals(""))) {
			allFieldsNull = false;
		}
		if ((lastName != null) && (!lastName.trim().equals(""))) {
			allFieldsNull = false;
		}
		if ((emailAddress != null) && (!emailAddress.trim().equals(""))) {
			allFieldsNull = false;
		}

		// Check if we pass. If so, actually register the customer.
		String address;
		// Entering synchronized block to be sure we do not allow duplicates.
		synchronized (this) {
			// Check for duplicate.
			boolean isDuplicate = (CustomerInfo.getCustomer(customerId) != null);
			if (!isDuplicate && passes(this)) {
				address = "Registered.jsf";
				CustomerInfo.addCustomer(this);
				// session.setAttribute("registeredCustomerInfo", customerInfo);
				// session.setAttribute("customerInfo", null);
			} else {
				// If we don't pass the checks, re-prompt the user and show a message.
				message = "";
				if (isDuplicate) {
					message =
							"the customer id is a duplicate in the database.  please choose a different id. ";
					customerId = "";
				}
				if (!allFieldsNull) {
					message += "fields marked with * are required.";
				}
				address = "InputForm.jsf";
			}
		}
		return address;
	}

	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	public String getMessage() {
		return message;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * @param emailAddress
	 *            the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private boolean passes(CustomerInfo info) {
		if ((info.getCustomerId() == null) || (info.getCustomerId().equals(""))) {
			return false;
		}
		if ((info.getFirstName() == null) || (info.getFirstName().equals(""))) {
			return false;
		}
		if ((info.getLastName() == null) || (info.getLastName().equals(""))) {
			return false;
		}
		if ((info.getEmailAddress() == null) || (info.getEmailAddress().equals(""))) {
			return false;
		}
		return true;
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
