package edu.homework1.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.homework1.bean.CustomerInfo;

/** Reads firstName and lastName request parameters and forwards
 *  to JSP page to display them. Uses session-based bean sharing
 *  to remember previous values.
 */

public class CustomerInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		CustomerInfo customerInfo = (CustomerInfo) session.getAttribute("customerInfo");
		if (customerInfo == null) {
			customerInfo = new CustomerInfo();
			session.setAttribute("customerInfo", customerInfo);
		}
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

		String address;
		if (passes(customerInfo)) {
			address = "/jsp/Registered.jsp";
		} else {
			address = "/jsp/InputForm.jsp";
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
