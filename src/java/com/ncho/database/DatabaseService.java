package com.ncho.database;

import com.ncho.model.ArrivalDepartureTime;
import com.ncho.model.Employee;
import com.ncho.model.Report;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dominic E. Ncho
 */
public class DatabaseService {

    //Insert a new employee into the database
    public void createEmployee(Employee employee){
        Connection conn = DatabaseUtil.getDatabaseConnection();        
        String command = "INSERT INTO employees (first_name,last_name,email,phone,gender) VALUES(?,?,?,?,?)";
            PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(command);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getEmail());
            ps.setString(4, employee.getPhone());
            ps.setString(5, employee.getGender());
            ps.executeUpdate();
        }
       catch (SQLException ex) {
            Logger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            DatabaseUtil.closeConnection(conn);
        }
    }

    //Find a single employee by employeeId
    public Employee findById(Employee employeeToFind) {
        
        ResultSet rs = null;
        String command = "SELECT * FROM employees WHERE employeeId=?";
        
        Employee employee = new Employee();
        Connection conn = null;
        try {
           conn = DatabaseUtil.getDatabaseConnection(); 
           PreparedStatement ps = conn.prepareStatement(command);
           ps.setInt(1, employeeToFind.getEmployeeId());
            ps.execute();
            rs = ps.getResultSet();
            if (rs.next()){
               
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhone(rs.getString("phone"));
                employee.setGender(rs.getString("gender"));
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            DatabaseUtil.closeConnection(conn);
        }
        return employee;
    }
    
    //Delete a single employee
    public void deleteById(Employee employeeToDelete) {
        Connection conn = DatabaseUtil.getDatabaseConnection();
        String command = "DELETE FROM employees WHERE employee_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(command);
            ps.setInt(1, employeeToDelete.getEmployeeId());
            ps.executeUpdate();
            ps.close();
        }
       catch (SQLException ex) {
            Logger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            DatabaseUtil.closeConnection(conn);
        }
        
    }
    
    
    
    //Edit a single employee
    public void editEmployee(Employee employeeToEdit) {
        Connection conn = DatabaseUtil.getDatabaseConnection();
        String command = "UPDATE employees "
                + "SET first_name=?,"
                + "last_name=?,"
                + "email=?,"
                + "phone=?,"
                + "gender=?"
                + "WHERE employee_id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(command);
            
            ps.setString(1, employeeToEdit.getFirstName());
            ps.setString(2, employeeToEdit.getLastName());
            ps.setString(3, employeeToEdit.getEmail());
            ps.setString(4, employeeToEdit.getPhone());
            ps.setString(5, employeeToEdit.getGender());
            ps.setInt(6, employeeToEdit.getEmployeeId());
            ps.executeUpdate();
            ps.close();
        }
       catch (SQLException ex) {
            Logger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            DatabaseUtil.closeConnection(conn);
        }
        
    }
    

    //Find all employees
    public List<Employee> findAllEmployees() {
        ResultSet rs = null;
        String command = "SELECT * FROM employees WHERE first_name IS NOT NULL "
                + "ORDER BY employee_id ASC";
        List<Employee> employees = new ArrayList<>();
        Connection conn = null;
        try {
           conn = DatabaseUtil.getDatabaseConnection(); 
           PreparedStatement ps = conn.prepareStatement(command);
            ps.executeQuery();
            rs = ps.getResultSet();
            employees.clear();
            while (rs.next()){
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhone(rs.getString("phone"));
                employee.setGender(rs.getString("gender"));
                employees.add(employee);
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            DatabaseUtil.closeConnection(conn);
        }
        return employees;
    }
    
    
    //Recording arrival/departure time
    
     public void recordTime(ArrivalDepartureTime arrivalDepartureTime, String designation){
        Connection conn = DatabaseUtil.getDatabaseConnection();
        String arrivalSQL = "INSERT INTO arrivals (employee_id, record_date, arrival_time) VALUES(?,?,?)";
        String departureSQL = "INSERT INTO departures (employee_id, record_date, departure_time) VALUES(?,?,?)";
            PreparedStatement ps = null;
        try {
            if("Arrival".equals(designation)){
                ps = conn.prepareStatement(arrivalSQL);
            }
            else{
               ps = conn.prepareStatement(departureSQL);  
            }
            ps.setInt(1, arrivalDepartureTime.getEmployeeId());
            ps.setDate(2, java.sql.Date.valueOf(arrivalDepartureTime.getDate()));
            
            if("Arrival".equals(designation)){
                ps.setTime(3, java.sql.Time.valueOf(arrivalDepartureTime.getArrivalTime()));
            }
            else{
                ps.setTime(3, java.sql.Time.valueOf(arrivalDepartureTime.getDepartureTime())); 
            }
            ps.executeUpdate();
        }
       catch (SQLException ex) {
            Logger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            DatabaseUtil.closeConnection(conn);
        }
    }
     
     
     //Get employee checkin records
    public List<Report> findAllArrivals() {
        ResultSet rs = null;
        String command = "SELECT "
                + "first_name,"
                + "last_name,"
                + "record_date,"
                + "arrival_time "
                + "FROM employees, arrivals "
                + "WHERE employees.employee_id = arrivals.employee_id";
               
        List<Report> checkinList = new ArrayList<>();
        Connection conn = null;
        try {
           conn = DatabaseUtil.getDatabaseConnection(); 
           PreparedStatement ps = conn.prepareStatement(command);
            ps.executeQuery();
            rs = ps.getResultSet();
            checkinList.clear();
            while (rs.next()){
                Report report = new Report();
                report.getEmployee().setFirstName(rs.getString("first_name"));
                report.getEmployee().setLastName(rs.getString("last_name"));
                report.getTime().setDate(rs.getDate("record_date").toLocalDate());
                report.getTime().setArrivalTime(rs.getTime("arrival_time").toLocalTime());
                
                checkinList.add(report);
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            DatabaseUtil.closeConnection(conn);
        }
        return checkinList;
    }
    
    //Get employee checkout records
    public List<Report> findAllDepartures() {
        ResultSet rs = null;
        String command = "SELECT "
                + "first_name,"
                + "last_name,"
                + "record_date,"
                + "departure_time "
                + "FROM employees, departures "
                + "WHERE employees.employee_id = departures.employee_id";
               
        List<Report> checkinList = new ArrayList<>();
        Connection conn = null;
        try {
           conn = DatabaseUtil.getDatabaseConnection(); 
           PreparedStatement ps = conn.prepareStatement(command);
            ps.executeQuery();
            rs = ps.getResultSet();
            checkinList.clear();
            while (rs.next()){
                Report report = new Report();
                report.getEmployee().setFirstName(rs.getString("first_name"));
                report.getEmployee().setLastName(rs.getString("last_name"));
                report.getTime().setDate(rs.getDate("record_date").toLocalDate());
                report.getTime().setDepartureTime(rs.getTime("departure_time").toLocalTime());
                
                checkinList.add(report);
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            DatabaseUtil.closeConnection(conn);
        }
        return checkinList;
    }
    
    //Get employee checkin/checkout records
    public List<Report> findAllArivalsAndDepartures() {
        ResultSet rs = null;
        String command = "SELECT "
                + "employees.first_name,"
                + "employees.last_name,"
                + "arrivals.record_date,"
                + "arrivals.arrival_time,"
                + "departures.departure_time "
                + "FROM employees, arrivals, departures "
                + "WHERE employees.employee_id = departures.employee_id AND arrivals.employee_id = departures.employee_id";
               
        List<Report> checkinList = new ArrayList<>();
        Connection conn = null;
        try {
           conn = DatabaseUtil.getDatabaseConnection(); 
           PreparedStatement ps = conn.prepareStatement(command);
            ps.executeQuery();
            rs = ps.getResultSet();
            checkinList.clear();
            while (rs.next()){
                Report report = new Report();
                report.getEmployee().setFirstName(rs.getString("first_name"));
                report.getEmployee().setLastName(rs.getString("last_name"));
                report.getTime().setDate(rs.getDate("record_date").toLocalDate());
                report.getTime().setArrivalTime(rs.getTime("arrival_time").toLocalTime());
                report.getTime().setDepartureTime(rs.getTime("departure_time").toLocalTime());
                
                checkinList.add(report);
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            DatabaseUtil.closeConnection(conn);
        }
        return checkinList;
    }
}
