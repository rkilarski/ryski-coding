/**
ryszard kilarski
met cs 667
emrys@bu.edu
bu id: u81-39-8560
 */
package edu.cs667.rkilarski.sessionbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import edu.cs667.rkilarski.entity.Company;
import edu.cs667.rkilarski.entity.Employee;
import edu.cs667.rkilarski.entity.Project;
import edu.cs667.rkilarski.request.Request;

@ManagedBean
@SessionScoped
public class ManagerBean implements Serializable {

	private static Company currentCompany = null;

	private static Employee currentEmployee = null;

	private static final long serialVersionUID = 1L;
	private String companyId = null;
	private boolean companyNull = true;

	/**
	 * @return the companyNull
	 */
	public boolean isCompanyNull() {
		return companyNull;
	}

	/**
	 * @param companyNull the companyNull to set
	 */
	public void setCompanyNull(boolean companyNull) {
		this.companyNull = companyNull;
	}

	@EJB
	Request bean;

	public String doEmployeeEdit(String employeeId) {
		currentEmployee = bean.getEmployee(employeeId);
		return "employeeedit.jsf";
	}

	public String doEmployeeNavigation() {
		return "employeelist.jsf";
	}

	public String doEmployeeView(String employeeId) {
		currentEmployee = bean.getEmployee(employeeId);
		return "employeeview.jsf";
	}

	public String doInitialNavigation() {
		String address = "company.jsf";
		return address;
	}

	public String doProjectNavigation() {
		return "projectlist.jsf";
	}

	public String doSubmitEmployee() {
		bean.updateEmployee(currentEmployee.getEmployeeId(), currentEmployee);
		return "employeelist.jsf";
	}

	public List<SelectItem> getCompanies() {
		List<SelectItem> companies = new ArrayList<SelectItem>();
		companies.add(new SelectItem("--- select company ---"));

		List<Company> company = bean.getAllCompanies();
		for (Company detail : company) {
			companies.add(new SelectItem(detail.getCompanyId()));
		}

		return (companies);
	}

	public String getCompanyId() {
		if (currentCompany == null) {
			return "";
		}
		return getCompany().getCompanyId();
	}

	public String getCompanyName() {
		if (currentCompany == null) {
			return "";
		}
		return getCompany().getCompanyName();
	}

	public Company getCurrentCompany() {
		return currentCompany;
	}

	public Employee getCurrentEmployee() {
		return currentEmployee;
	}

	public List<Employee> getEmployeesInCompany() {
		return bean.getEmployees(companyId);
	}

	public List<Employee> getEmployeesInProject(String projectCode) {
		return bean.getEmployeesInProject(projectCode);
	}

	public List<Project> getProjects() {
		return bean.getAllProjects(companyId);
	}

	public List<Project> getProjectsForEmployee() {
		return bean.getProjectsForEmployee(currentEmployee.getEmployeeId());
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
		if (companyId.equals("--- select company ---")) {
			currentCompany = null;
			companyNull = true;
			return;
		}
		currentCompany = bean.getCompany(companyId);
		companyNull = false;
	}

	private Company getCompany() {
		if (currentCompany == null) {
			currentCompany = bean.getCompany("Company1");
		}
		return currentCompany;
	}
}
