/**
ryszard kilarski
met cs 667
emrys@bu.edu
bu id: u81-39-8560
 */
package edu.cs667.rkilarski.sessionbean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import edu.cs667.rkilarski.entity.Company;
import edu.cs667.rkilarski.request.Request;

@ManagedBean
@SessionScoped
public class ManagerBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	Request bean;

	private static Company company = null;

	public ManagerBean() {
		System.out.println("Constructor for ManagerBean");
	}

	public ManagerBean(ManagerBean bean) {
		System.out.println("Constructor for ManagerBean(bean)");
	}

	public String getCompanyName() {
		System.out.println("getCompanyName()");
		return ((Company) bean.getCompany("Company1")).getCompanyName();
		// return company.getCompanyName();
	}

	public String getCompanyId() {
		System.out.println("getCompanyId()");
		return company.getCompanyId();
	}

	public String doInitialNavigation() {
		String address = "company.jsf";
		return address;
	}

	public String doProjectNavigation() {
		String address = "projectlist.jsf";
		return address;
	}

	public String doEmployeeNavigation() {
		String address = "employeelist.jsf";
		return address;
	}

	public String doUpdateEmployee() {
		return "employeelist.jsf";
	}

}
