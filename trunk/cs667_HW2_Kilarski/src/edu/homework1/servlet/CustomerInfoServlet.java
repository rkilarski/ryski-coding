/**
ryszard kilarski
met cs 667
emrys@bu.edu
bu id: u81-39-8560
 */
package edu.homework1.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.homework1.bean.CustomerInfo;

public class CustomerInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        // Get the customer bean from the session object, if any.
        CustomerInfo customerInfo = (CustomerInfo) session.getAttribute("customerInfo");
        boolean allFieldsNull = false;
        if (customerInfo == null) {
            customerInfo = new CustomerInfo();
            session.setAttribute("customerInfo", customerInfo);
            allFieldsNull = true;
        }

        // Process the parameters to this page and add to the session.
        String customerId = request.getParameter("customerId");
        if ((customerId != null) && (!customerId.trim().equals(""))) {
            customerInfo.setCustomerId(customerId);
        }
        String firstName = request.getParameter("firstName");
        if ((firstName != null) && (!firstName.trim().equals(""))) {
            customerInfo.setFirstName(firstName);
        }
        String lastName = request.getParameter("lastName");
        if ((lastName != null) && (!lastName.trim().equals(""))) {
            customerInfo.setLastName(lastName);
        }
        String emailAddress = request.getParameter("emailAddress");
        if ((emailAddress != null) && (!emailAddress.trim().equals(""))) {
            customerInfo.setEmailAddress(emailAddress);
        }

        // Check if we pass. If so, actually register the customer.
        String address;
        // Entering synchronized block to be sure we do not allow duplicates.
        synchronized (customerInfo) {
            // Check for duplicate.
            boolean isDuplicate = (CustomerInfo.getCustomer(customerId) != null);
            if (!isDuplicate && passes(customerInfo)) {
                address = "/jsp/Registered.jsp";
                CustomerInfo.addCustomer(customerInfo);
                session.setAttribute("registeredCustomerInfo", customerInfo);
                session.setAttribute("customerInfo", null);
            } else {
                // If we don't pass the checks, re-prompt the user and show a message.
                String message = "";
                if (isDuplicate) {
                    message =
                            "the customer id is a duplicate in the database.  please choose a different id. ";
                    customerInfo.setCustomerId("");
                }
                if (!allFieldsNull) {
                    message += "fields marked with * are required.";
                }
                session.setAttribute("message", message);
                address = "/jsp/InputForm.jsp";
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
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
}
