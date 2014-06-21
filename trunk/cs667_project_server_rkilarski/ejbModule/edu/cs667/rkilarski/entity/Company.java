package edu.cs667.rkilarski.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "RKILARSKI_COMPANY")
public class Company implements Serializable {

	private static final long serialVersionUID = 1L;

	private String companyId;
	private String companyName;
	private Set<Employee> employees;
	private Set<Project> projects;

	public String toString() {
		return "id: " + this.companyId + " name: " + this.companyName;
	}

	public Company() {
	}

	public Company(String companyId, String companyName, Set<Employee> employees, Set<Project> projects) {
		this.companyId = companyId;
		this.companyName = companyName;
		this.employees = employees;
		this.projects = projects;
	}

	@Id
	public String getCompanyId() {
		return companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "RKILARSKI_JOIN_COMPANY_EMPLOYEE")
	public Set<Employee> getEmployees() {
		if (employees == null) {
			employees = new HashSet<Employee>();
		}
		return employees;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "RKILARSKI_JOIN_COMPANY_PROJECT")
	public Set<Project> getProjects() {
		if (projects == null) {
			projects = new HashSet<Project>();
		}
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
}
