package com.ncho.model;

import java.io.Serializable;

/**
 *
 * @author Dominic E. Ncho
 */
public class Report implements Serializable{
    private Employee employee = new Employee();
    private ArrivalDepartureTime time = new ArrivalDepartureTime();
    

    public Report() {
    }

    public Report(Employee employee, ArrivalDepartureTime time) {
        this.employee = employee;
        this.time = time;
    }
    
    

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public ArrivalDepartureTime getTime() {
        return time;
    }

    public void setTime(ArrivalDepartureTime time) {
        this.time = time;
    }
    
    
    
}
