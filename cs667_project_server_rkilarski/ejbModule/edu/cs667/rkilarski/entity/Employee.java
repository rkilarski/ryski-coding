package edu.cs667.rkilarski.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "RKILARSKI_EMPLOYEE")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private String employeeId;
    private String fullName;
    private Set<Project> projects;

    public Employee() {
    }

    public String toString() {
        return "id: " + this.employeeId + " name: " + this.fullName;
    }

    public Employee(String employeeId, String fullName, Set<Project> projects) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.projects = projects;
    }

    @Id
    public String getEmployeeId() {
        return employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(name = "RKILARSKI_EMPLOYEE_PROJECT")
    public Set<Project> getProjects() {
        if (projects == null) {
            return new HashSet<Project>();
        }
        return projects;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
