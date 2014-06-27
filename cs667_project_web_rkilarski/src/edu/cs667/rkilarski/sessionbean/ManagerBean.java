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

	@EJB
	Request bean;

	/**
	 * Navigate to the employee edit page and load the current employee.
	 */
	public String doEmployeeEdit(String employeeId) {
		currentEmployee = bean.getEmployee(employeeId);
		return "employeeedit.jsf";
	}

	/**
	 * Navigate to the employee list page.
	 */
	public String doEmployeeNavigation() {
		return "employeelist.jsf";
	}

	/**
	 * Navigate to the employee view page and load the current employee.
	 */
	public String doEmployeeView(String employeeId) {
		currentEmployee = bean.getEmployee(employeeId);
		return "employeeview.jsf";
	}

	/**
	 * Navigate to the main company website.
	 */
	public String doInitialNavigation() {
		return "company.jsf";
	}

	/**
	 * Navigate to the project list page.
	 */
	public String doProjectNavigation() {
		return "projectlist.jsf";
	}

	/**
	 * Submit the employee information to the database and navigate to the employee list.
	 * @return
	 */
	public String doSubmitEmployee() {
		bean.updateEmployee(currentEmployee.getEmployeeId(), currentEmployee);
		return "employeelist.jsf";
	}

	/**
	 * Helper method to populate the company select list.
	 * @return
	 */
	public List<SelectItem> getCompanies() {
		List<SelectItem> companies = new ArrayList<SelectItem>();
		companies.add(new SelectItem("--- select company ---"));

		List<Company> company = bean.getAllCompanies();
		for (Company detail : company) {
			companies.add(new SelectItem(detail.getCompanyId(), detail.getCompanyName()));
		}

		return companies;
	}

	/**
	 * Method to get the current company id.
	 */
	public String getCompanyId() {
		if (currentCompany == null) {
			return "";
		}
		return getCompany().getCompanyId();
	}

	/**
	 * Method to get the current company name.
	 */
	public String getCompanyName() {
		if (currentCompany == null) {
			return "";
		}
		return getCompany().getCompanyName();
	}

	/**
	 * Method to return the current company.
	 */
	public Company getCurrentCompany() {
		return currentCompany;
	}

	/**
	 * Method to return the current employee.
	 */
	public Employee getCurrentEmployee() {
		return currentEmployee;
	}

	/**
	 * Method to return the list of employees in the current company.
	 */
	public List<Employee> getEmployeesInCompany() {
		return bean.getEmployees(companyId);
	}

	/**
	 * Method to return the list of employees given a project.
	 */
	public List<Employee> getEmployeesInProject(String projectCode) {
		return bean.getEmployeesInProject(projectCode);
	}

	/**
	 * Method to get the list of all projects for the current company id.
	 */
	public List<Project> getProjects() {
		return bean.getAllProjects(companyId);
	}

	/**
	 * Method to get the list of all projects for the current employee.
	 * @return
	 */
	public List<Project> getProjectsForEmployee() {
		return bean.getProjectsForEmployee(currentEmployee.getEmployeeId());
	}

	/**
	 * Return if the company is null.
	 */
	public boolean isCompanyNull() {
		return companyNull;
	}

	/**
	 * Method to set the company id from the company dropdown.
	 * @param companyId
	 */
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

	/**
	 * @param companyNull the companyNull to set
	 */
	public void setCompanyNull(boolean companyNull) {
		this.companyNull = companyNull;
	}

	/**
	 * Method to get the current company. 
	 */
	private Company getCompany() {
		return currentCompany;
	}
}
