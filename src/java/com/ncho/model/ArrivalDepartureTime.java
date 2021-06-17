
package com.ncho.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 *
 * @author Dominic E. Ncho
 */
public class ArrivalDepartureTime implements Serializable{
    private int id;
    private int employeeId;
    private LocalDate date;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private String designation;

    public ArrivalDepartureTime() {
    }

    public ArrivalDepartureTime(int employeeId, LocalDate date, LocalTime arrivalTime, LocalTime departureTime, String designation) {
        this.employeeId = employeeId;
        this.date = LocalDate.now();
        this.arrivalTime = LocalTime.now();
        this.departureTime = LocalTime.now();
        this.designation = designation;
    }

  
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

 

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        hash = 23 * hash + this.employeeId;
        hash = 23 * hash + Objects.hashCode(this.date);
        hash = 23 * hash + Objects.hashCode(this.arrivalTime);
        hash = 23 * hash + Objects.hashCode(this.departureTime);
        hash = 23 * hash + Objects.hashCode(this.designation);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ArrivalDepartureTime other = (ArrivalDepartureTime) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.employeeId != other.employeeId) {
            return false;
        }
        if (!Objects.equals(this.designation, other.designation)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.arrivalTime, other.arrivalTime)) {
            return false;
        }
        return Objects.equals(this.departureTime, other.departureTime);
    }

    @Override
    public String toString() {
        return "ArrivalDepartureTime{" + "id=" + id + ", employeeId=" + employeeId + ", date=" + date + ", arrivalTime=" + arrivalTime + ", departureTime=" + departureTime + ", designation=" + designation + '}';
    }

   
   

   
}
