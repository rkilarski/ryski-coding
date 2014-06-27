/**
ryszard kilarski
met cs 667
emrys@bu.edu
bu id: u81-39-8560
 */
package edu.cs667.rkilarski.request;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.cs667.rkilarski.entity.Company;
import edu.cs667.rkilarski.entity.Employee;
import edu.cs667.rkilarski.entity.Project;

@Stateful
public class RequestBean implements Request {

	@PersistenceContext(unitName = "companyDB")
	private EntityManager em;

	public RequestBean() {
	}

	/**
	 * Return list of all companies
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Company> getAllCompanies() {
		List<Company> list = null;
		try {
			list =
					(List<Company>) em.createNamedQuery("edu.cs667.rkilarski.entity.Company.findAllCompanies").getResultList();

			return list;
		} catch (Exception ex) {
			throw new EJBException(ex);
		}
	}

	/**
	 * Return list of all projects in a company.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Project> getAllProjects(String companyId) {
		List<Project> list = null;
		try {
			list =
					(List<Project>) em.createNamedQuery("edu.cs667.rkilarski.entity.Project.findAllProjects").setParameter("companyId", companyId).getResultList();

			return list;
		} catch (Exception ex) {
			throw new EJBException(ex);
		}
	}

	/**
	 * Get a company given its id.
	 */
	@Override
	public Company getCompany(String companyId) {
		Company company = null;

		try {
			company = (Company) em.find(Company.class, companyId);
		} catch (Exception ex) {
			throw new EJBException(ex);
		}
		return company;
	}

	/**
	 * Get an employee given its id.
	 */
	@Override
	public Employee getEmployee(String employeeId) {
		Employee item = null;
		try {
			item = em.find(Employee.class, employeeId);
		} catch (Exception ex) {
			throw new EJBException(ex);
		}
		return item;
	}

	/**
	 * Get the list of employees given a company id.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> getEmployees(String companyId) {
		List<Employee> list = null;
		try {
			list =
					(List<Employee>) em.createNamedQuery("edu.cs667.rkilarski.entity.Employee.findAllEmployees").setParameter("companyId", companyId).getResultList();

		} catch (Exception ex) {
			throw new EJBException(ex);
		}
		return list;
	}

	/**
	 * Get a list of employees given a project id.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> getEmployeesInProject(String projectCode) {
		List<Employee> list = null;
		try {
			list =
					(List<Employee>) em.createNamedQuery("edu.cs667.rkilarski.entity.Project.findAllEmployeesForProject").setParameter("projectCode", projectCode).getResultList();

		} catch (Exception ex) {
			throw new EJBException(ex);
		}
		return list;
	}

	/**
	 * Get a list of projects given an employee id.
	 * @param employeeId
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Project> getProjectsForEmployee(String employeeId) {
		List<Project> list = null;
		try {
			list =
					(List<Project>) em.createNamedQuery("edu.cs667.rkilarski.entity.Project.findAllProjectsForEmployee").setParameter("employeeId", employeeId).getResultList();

			return list;
		} catch (Exception ex) {
			throw new EJBException(ex);
		}
	}

	/**
	 * Create test data.
	 * Create the Company, two employees, and four projects. 
	 * Add the employees to the Company as each employee is being created. 
	 * For the projects, use setProjects to add all of them at one time. 
	 * Invoke the company's methods getEmployees and getProjects, 
	 * iterate over the collections, and print them to the console.	 
	 */
	@Override
	public void test1() {
		try {
			System.out.println("test1() run start -------------------");
			Company company = null;
			Employee employee = null;
			Project project = null;

			company = new Company("company1", "Microshot", null, null);

			// Create an employee, persist, and add to the database.
			employee = new Employee("employee1", "Fates, Will", null);
			company.getEmployees().add(employee);
			em.persist(employee);

			// Create an employee, persist, and add to the database.
			employee = new Employee("employee2", "Fallon, Gaul", null);
			company.getEmployees().add(employee);
			em.persist(employee);

			Set<Project> projects = new HashSet<Project>();
			project = new Project("projecta", "TRES", null);
			projects.add(project);
			em.persist(project);

			project = new Project("projectb", "Skylight", null);
			projects.add(project);
			em.persist(project);

			project = new Project("projectc", "YTriangle", null);
			projects.add(project);
			em.persist(project);

			project = new Project("projectd", "Factory", null);
			projects.add(project);
			em.persist(project);

			company.setProjects(projects);
			em.persist(company);

			// Output the company object.
			System.out.println("This is the company.");
			outputCompany(company);
			System.out.println("test1() run end -------------------");
		} catch (Exception ex) {
			throw new EJBException(ex);
		}
	}

	/**
	 * Link test data from 1 to more data.
	 * Using the first employee object, add the first three projects to the first employee. 
	 * Using the second employee object, add the last three projects to the second employee. 
	 * Invoke the getEmployees method on each of the four projects, 
	 * iterate over the resulting collection, and print them to the console.
	 */
	@Override
	public void test2() {
		try {
			System.out.println("\n\ntest2() run start -------------------");
			Employee employee = null;
			// Link employee 1 to projects A, B, and C.
			employee = (Employee) em.find(Employee.class, "employee1");
			employee.getProjects().add((Project) em.find(Project.class, "projecta"));
			employee.getProjects().add((Project) em.find(Project.class, "projectb"));
			employee.getProjects().add((Project) em.find(Project.class, "projectc"));
			em.persist(employee);

			// Link employee 2 to projects B, C, and D.
			employee = (Employee) em.find(Employee.class, "employee2");
			employee.getProjects().add((Project) em.find(Project.class, "projectb"));
			employee.getProjects().add((Project) em.find(Project.class, "projectc"));
			employee.getProjects().add((Project) em.find(Project.class, "projectd"));
			em.persist(employee);

			// Output all the project info from the database.
			outputProject((Project) em.find(Project.class, "projecta"));
			outputProject((Project) em.find(Project.class, "projectb"));
			outputProject((Project) em.find(Project.class, "projectc"));
			outputProject((Project) em.find(Project.class, "projectd"));

			System.out.println("test2() run end -------------------");
		} catch (Exception ex) {
			throw new EJBException(ex);
		}
	}

	/**
	 * Print data that was filed in test1() and test2()
	 */
	@Override
	public void test3() {
		System.out.println("\n\ntest3() run start -------------------");
		System.out.println("--Load from the database and print out all the information for the company and projects--");

		System.out.println("-----Company-----");
		outputCompany((Company) em.find(Company.class, "company1"));

		System.out.println("\n\n-----Project A-----");
		outputProject((Project) em.find(Project.class, "projecta"));

		System.out.println("\n-----Project B-----");
		outputProject((Project) em.find(Project.class, "projectb"));

		System.out.println("\n-----Project C-----");
		outputProject((Project) em.find(Project.class, "projectc"));

		System.out.println("\n-----Project D-----");
		outputProject((Project) em.find(Project.class, "projectd"));
		System.out.println("test3() run end -------------------");
	}

	/**
	 * Create additional test data.
	 */
	@Override
	public void test4() {
		try {
			System.out.println("test4() run start -------------------");
			System.out.println("test4() adds more data to the database, but will not print out the data.");
			Company company = null;
			Employee employee = null;
			Project project = null;

			company = new Company("company2", "Strawberry", null, null);

			// Create an employee, persist, and add to the database.
			employee = new Employee("employee3", "Funs, Frank", null);
			company.getEmployees().add(employee);
			em.persist(employee);

			// Create an employee, persist, and add to the database.
			employee = new Employee("employee4", "Wiznoyak, Frank", null);
			company.getEmployees().add(employee);
			em.persist(employee);

			Set<Project> projects = new HashSet<Project>();
			project = new Project("projecte", "uFresh", null);
			projects.add(project);
			em.persist(project);

			project = new Project("projectf", "uFreshAir", null);
			projects.add(project);
			em.persist(project);

			project = new Project("projectg", "uTelegraph", null);
			projects.add(project);
			em.persist(project);

			project = new Project("projecth", "uRadio", null);
			projects.add(project);
			em.persist(project);

			company.setProjects(projects);
			em.persist(company);
			System.out.println("test4() run end -------------------");
		} catch (Exception ex) {
			throw new EJBException(ex);
		}
	}

	/**
	 * Link test data from 4 to more data.
	 */
	@Override
	public void test5() {
		try {
			System.out.println("\n\ntest5() run start -------------------");
			System.out.println("test5() adds more data to the database, but will not print out the data.");

			Employee employee = null;
			// Link employee 1 to projects A, B, and C.
			employee = (Employee) em.find(Employee.class, "employee3");
			employee.getProjects().add((Project) em.find(Project.class, "projecte"));
			employee.getProjects().add((Project) em.find(Project.class, "projectf"));
			employee.getProjects().add((Project) em.find(Project.class, "projectg"));
			em.persist(employee);

			// Link employee 2 to projects B, C, and D.
			employee = (Employee) em.find(Employee.class, "employee4");
			employee.getProjects().add((Project) em.find(Project.class, "projectf"));
			employee.getProjects().add((Project) em.find(Project.class, "projectg"));
			employee.getProjects().add((Project) em.find(Project.class, "projecth"));
			em.persist(employee);
			System.out.println("test5() run end -------------------");
		} catch (Exception ex) {
			throw new EJBException(ex);
		}
	}

	/**
	 * Given an employee id and employee data, update that employee in the database.
	 */
	@Override
	public void updateEmployee(String employeeId, Employee employee) {
		Employee item = null;
		try {
			item = em.find(Employee.class, employeeId);
			item.setFullName(employee.getFullName());
			em.persist(item);
		} catch (Exception ex) {
			throw new EJBException(ex);
		}
	}

	/**
	 * Output a whole company and all its information.
	 */
	private void outputCompany(Company company) {
		if (company == null) {
			System.out.println("Company is null!");
			return;
		}
		System.out.println("Company " + company.toString());

		System.out.println("\n\n Company Projects:");
		System.out.println(" # of projects: " + company.getProjects().size());
		for (Project project : company.getProjects()) {
			System.out.println("  Project " + project.toString());
		}

		System.out.println("\n\n Employees and their projects:");
		System.out.println("  # of employees: " + company.getEmployees().size());
		for (Employee employee : company.getEmployees()) {
			outputEmployee(employee);
		}
	}

	/**
	 * Output the employee and related projects.
	 */
	private void outputEmployee(Employee employee) {
		if (employee == null) {
			System.out.println("Employee is null!");
			return;
		}
		System.out.println("   Employee " + employee.toString());
		System.out.println("   # of projects: " + employee.getProjects().size());
		for (Project project : employee.getProjects()) {
			System.out.println("    Project " + project.toString());
		}
	}

	/**
	 * Output the project and related employees.
	 */
	private void outputProject(Project project) {
		if (project == null) {
			System.out.println("Project is null!");
			return;
		}
		System.out.println(" Project " + project.toString());
		System.out.println(" # of employees: " + project.getEmployees().size());
		for (Employee employee : project.getEmployees()) {
			System.out.println("  Employee " + employee.toString());
		}
	}
}
