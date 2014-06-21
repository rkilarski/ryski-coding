package edu.cs667.rkilarski.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

//@Entity
//@Table(name = "RKILARSKI_PROJECT")
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;

	private Set<Employee> employees;
	private String projectCode;
	private String projectName;

	public Project() {
	}

	public String toString() {
		return "id: " + this.projectCode + " name: " + this.projectName;
	}

	public Project(String projectCode, String projectName, Set<Employee> employees) {
		this.projectCode = projectCode;
		this.projectName = projectName;
		this.employees = employees;
	}

	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, mappedBy = "projects")
	public Set<Employee> getEmployees() {
		if (employees == null) {
			return new HashSet<Employee>();
		}
		return employees;
	}

	@Id
	public String getProjectCode() {
		return projectCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}
