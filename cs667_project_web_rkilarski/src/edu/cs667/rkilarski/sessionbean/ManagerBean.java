/**
ryszard kilarski
met cs 667
emrys@bu.edu
bu id: u81-39-8560
 */
package edu.cs667.rkilarski.sessionbean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import edu.cs667.rkilarski.entity.Company;
import edu.cs667.rkilarski.request.Request;

@ManagedBean
@SessionScoped
public class ManagerBean {

    @EJB
    Request bean;

    private static Company company = null;

    public ManagerBean() {
    }

    public ManagerBean(Company info) {
        ManagerBean.company = bean.getCompany("Company1");
    }

    public String getCompanyName() {
        return company.getCompanyName();
    }

    public String getCompanyId() {
        return company.getCompanyId();
    }

    public String doProjectNavigation() {
        String address = "projectlist.jsf";
        return address;
    }

    public String doEmployeeNavigation() {
        String address = "employeelist.jsf";
        return address;
    }
    
    public String doUpdateEmployee(){
       return "employeelist.jsf";
    }
    
}
