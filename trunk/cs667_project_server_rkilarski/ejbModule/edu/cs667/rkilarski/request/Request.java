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

	/**
	 * Return list of all companies
	 */
	List<Company> getAllCompanies();

	/**
	 * Return list of all projects in a company.
	 */
	List<Project> getAllProjects(String companyId);

	/**
	 * Get a company given its id.
	 */
	Company getCompany(String companyId);

	/**
	 * Get an employee given its id.
	 */
	Employee getEmployee(String employeeId);

	/**
	 * Get the list of employees given a company id.
	 */
	List<Employee> getEmployees(String companyId);

	/**
	 * Get a list of employees given a project id.
	 */
	List<Employee> getEmployeesInProject(String projectCode);

	/**
	 * Get a project given its project code
	 */
	Project getProject(String projectCode);

	/**
	 * Get a list of projects given an employee id.
	 * @param employeeId
	 * @return
	 */
	List<Project> getProjectsForEmployee(String employeeId);

	/**
	 * Create test data.
	 */
	void test1();

	/**
	 * Link test data from 1 to more data.
	 */
	void test2();

	/**
	 * Print data that was filed in test1() and test2()
	 */
	void test3();

	/**
	 * Create additional test data.
	 */
	void test4();

	/**
	 * Link test data from 4 to more data.
	 */
	void test5();

	/**
	 * Given an employee id and employee data, update that employee in the database.
	 */
	void updateEmployee(String employeeId, Employee employee);
}
