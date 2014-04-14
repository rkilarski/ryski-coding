/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package edu.cs751homework4.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import edu.cs751homework4.model.Item;
import edu.cs751homework4.model.Order;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface OrderService {

	@WebMethod
	public abstract Order[] getOrders();

	@WebMethod
	public abstract Order getOrder(String id);

	@WebMethod
	public abstract boolean addOrder(Order order);

	@WebMethod
	public abstract boolean updateOrder(String id, Item item);

	@WebMethod
	public abstract boolean deleteOrder(String id);

}