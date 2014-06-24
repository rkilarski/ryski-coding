package edu.cs667.rkilarski.request;

import javax.ejb.Remote;

import edu.cs667.rkilarski.entity.Company;

@Remote
public interface Request {
    void test1();

    void test2();

    void test3();

    Company getCompany(String companyId);

    /*
    void createCompany(Company company);

    void createEmployee(String id, String name);

    void createProject(Project project);

    void addEmployeeToProject(String employeeId, String projectId);

    void addEmployeeToCompany(String employeeId, String projectId);

    void createProjectInCompany(Project project, String companyId);

    void dropEmployee(String employeeId, String projectId);

    List<Employee> getAllEmployees();

    Company getCompany(String companyId);

    Employee getEmployee(String employeeId);

    Project getProject(String projectId);

    List<Project> getProjectsOfCompany(String companyId);

    void removeCompany(String companyId);

    void removeEmployee(String employeeId);

    void removeProject(String projectId);
    */
}
