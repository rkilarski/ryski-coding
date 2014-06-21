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
			Company company = new Company("Company1", "Company 1", null, null);
			Employee employee = new Employee("employee1", "One, Employee", null);
			// company.getEmployees().add(employee);

			employee = new Employee("employee2", "Two, Employee", null);
			// company.getEmployees().add(employee);

			Set<Project> projects = new HashSet<Project>();
			projects.add(new Project("projectA", "Project A", null));
			projects.add(new Project("projectB", "Project B", null));
			projects.add(new Project("projectC", "Project C", null));
			projects.add(new Project("projectD", "Project D", null));
			// company.setProjects(projects);

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

	private void outputCompany(Company company) {
		System.out.println("Company: " + company.toString());
		/*
		for (Employee employee : company.getEmployees()) {
			System.out.println(" Employee: " + employee.toString());
			for (Project project : employee.getProjects()) {
				System.out.println("  Projects: " + project.toString());
			}
		}
		*/
	}
}
