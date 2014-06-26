/**
ryszard kilarski
met cs 667
emrys@bu.edu
bu id: u81-39-8560
 */
package edu.cs667.rkilarski.request;

import java.util.List;

import javax.ejb.Remote;

import edu.cs667.rkilarski.entity.Company;
import edu.cs667.rkilarski.entity.Employee;
import edu.cs667.rkilarski.entity.Project;

@Remote
public interface Request {
	List<Company> getAllCompanies();

	List<Project> getAllProjects(String companyId);

	Company getCompany(String companyId);

	Employee getEmployee(String employeeId);

	List<Employee> getEmployees(String companyId);

	List<Employee> getEmployeesInProject(String projectCode);

	List<Project> getProjectsForEmployee(String employeeId);

	void test1();

	void test2();

	void test3();

	void updateEmployee(String employeeId, Employee employee);
}
