package com.ncho.controller;

import com.ncho.database.DatabaseService;
import com.ncho.model.ArrivalDepartureTime;
import com.ncho.model.Employee;
import com.ncho.model.Report;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Dominic E. Ncho
 */
@ManagedBean
@SessionScoped
public class EmployeeController implements Serializable{
private Employee employee = new Employee();
private Employee selectedEmployee;
private List<Employee> employees;
private ArrivalDepartureTime arrivalDepartureTime;
private List<Report> reportItems;



private int deleteId;

private DatabaseService databaseService;
    
    public EmployeeController() {
    }
    
    @PostConstruct()
    public void init(){
        employee = new Employee();
        databaseService = new DatabaseService();
        selectedEmployee = new Employee();
        arrivalDepartureTime = new ArrivalDepartureTime();
        employees = databaseService.findAllEmployees();
    }
    
    
    //Messages
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
    //Create employee
    public void doCreateEmployee() throws SQLException{
        databaseService.createEmployee(employee);
         addMessage(FacesMessage.SEVERITY_INFO, "Success", "Employee added");
        employees = databaseService.findAllEmployees();
    }
    
    //Delete a single employee
    public void doDeleteEmployee(){
        databaseService.deleteById(selectedEmployee);
        employees = databaseService.findAllEmployees();
    }

    //Edit a single employee
    public void doEditEmployee(){
        databaseService.editEmployee(selectedEmployee);
        employees = databaseService.findAllEmployees();
    }
    
    
    //Record arrival time    
    public void recordArrivalTime(){
        databaseService.recordTime(arrivalDepartureTime,"Arrival");
    }
    //Record departure time    
    public void recordDepartureTime(){
        databaseService.recordTime(arrivalDepartureTime,"Departure");
    }
    
    
    //Get arrival records
    public void dofindAllArrivals(){
        reportItems = databaseService.findAllArrivals();
    }
    
     //Get departure records
    public void dofindAllDepartures(){
        reportItems = databaseService.findAllDepartures();
    }
     //Get departure records
    public void dofindAllArrivalsAndDepartures(){
        reportItems = databaseService.findAllArivalsAndDepartures();
    }
    
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;  
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public int getDeleteId() {
        return deleteId;
    }

    public void setDeleteId(int deleteId) {
        this.deleteId = deleteId;
    }

    public ArrivalDepartureTime getArrivalDepartureTime() {
        return arrivalDepartureTime;
    }

    public void setArrivalDepartureTime(ArrivalDepartureTime arrivalDepartureTime) {
        this.arrivalDepartureTime = arrivalDepartureTime;
    }

    public List<Report> getReportItems() {
        return reportItems;
    }

    public void setReportItems(List<Report> reportItems) {
        this.reportItems = reportItems;
    }

  
}
