
package edu.cs751homework4.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "addOrder", namespace = "http://service.cs751homework4.edu/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addOrder", namespace = "http://service.cs751homework4.edu/")
public class AddOrder {

    @XmlElement(name = "arg0", namespace = "")
    private edu.cs751homework4.model.Order arg0;

    /**
     * 
     * @return
     *     returns Order
     */
    public edu.cs751homework4.model.Order getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(edu.cs751homework4.model.Order arg0) {
        this.arg0 = arg0;
    }

}
