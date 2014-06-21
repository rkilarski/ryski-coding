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

			outputCompany(company);
			em.persist(company);
		} catch (Exception ex) {
			throw new EJBException(ex);
		}
	}

	public void test2() {
		try {

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
			System.out.println("  Employee: " + employee.toString());
			for (Project project : employee.getProjects()) {
				System.out.println("   Projects: " + project.toString());
			}
		}
	}
}
