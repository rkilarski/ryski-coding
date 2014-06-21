package edu.cs667.rkilarski.request;

import java.util.HashSet;
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

	/*
	Create the Company, two employees, and four projects. 
	Add the employees to the Company as each employee is being created. 
	For the projects, use setProjects to add all of them at one time. 
	Invoke the company's methods getEmployees and getProjects, 
	iterate over the collections, and print them to the console.	 
	*/
	public void test1() {
		try {
			Company company = null;
			Employee employee = null;
			Project project = null;

			company = new Company("Company1", "Company 1", null, null);

			// Create an employee, persist, and add to the database.
			employee = new Employee("employee1", "One, Employee", null);
			company.getEmployees().add(employee);
			em.persist(employee);

			// Create an employee, persist, and add to the database.
			employee = new Employee("employee2", "Two, Employee", null);
			company.getEmployees().add(employee);
			em.persist(employee);

			Set<Project> projects = new HashSet<Project>();
			project = new Project("projectA", "Project A", null);
			projects.add(project);
			em.persist(project);

			project = new Project("projectB", "Project B", null);
			projects.add(project);
			em.persist(project);

			project = new Project("projectC", "Project C", null);
			projects.add(project);
			em.persist(project);

			project = new Project("projectD", "Project D", null);
			projects.add(project);
			em.persist(project);
			company.setProjects(projects);

			// Look up the company in the database and output it.
			outputCompany((Company) em.find(Company.class, "Company1"));
			em.persist(company);
		} catch (Exception ex) {
			throw new EJBException(ex);
		}
	}

	/*
	Using the first employee object, add the first three projects to the first employee. 
	Using the second employee object, add the last three projects to the second employee. 
	Invoke the getEmployees method on each of the four projects, 
	iterate over the resulting collection, and print them to the console.
	*/
	public void test2() {
		try {
			Employee employee = null;
			// Link employee 1 to projects A, B, and C.
			employee = (Employee) em.find(Employee.class, "employee1");
			employee.getProjects().add((Project) em.find(Project.class, "projectA"));
			employee.getProjects().add((Project) em.find(Project.class, "projectB"));
			employee.getProjects().add((Project) em.find(Project.class, "projectC"));
			em.persist(employee);

			// Link employee 2 to projects B, C, and D.
			employee = (Employee) em.find(Employee.class, "employee2");
			employee.getProjects().add((Project) em.find(Project.class, "projectB"));
			employee.getProjects().add((Project) em.find(Project.class, "projectC"));
			employee.getProjects().add((Project) em.find(Project.class, "projectD"));
			em.persist(employee);

			// Output all the project info from the database.
			outputProject((Project) em.find(Project.class, "projectA"));
			outputProject((Project) em.find(Project.class, "projectB"));
			outputProject((Project) em.find(Project.class, "projectC"));
			outputProject((Project) em.find(Project.class, "projectD"));
		} catch (Exception ex) {
			throw new EJBException(ex);
		}
	}

	// Output a whole company and all its information.
	private void outputCompany(Company company) {
		System.out.println("Company: " + company.toString());

		System.out.println("\n Company Projects:");
		for (Project project : company.getProjects()) {
			System.out.println("  Project: " + project.toString());
		}

		System.out.println("\n Employees and their projects:");
		for (Employee employee : company.getEmployees()) {
			outputEmployee(employee);
		}
	}

	private void outputEmployee(Employee employee) {
		System.out.println("  Employee: " + employee.toString());
		for (Project project : employee.getProjects()) {
			System.out.println("   Projects: " + project.toString());
		}
	}

	private void outputProject(Project project) {
		System.out.println("\n Project:" + project.toString());
		for (Employee employee : project.getEmployees()) {
			System.out.println("  Employee: " + employee.toString());
		}
	}
}
