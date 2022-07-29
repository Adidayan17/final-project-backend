package com.dev.objects;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employees")

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id")
    private Integer id ;

    @Column (name="employee_name ")
    private String employeeName ;

    @Column (name= "role")
    private String role ;

    public  Employee (){

    }
    public Employee (Employee employee ){
        this.id= employee.getId();
        this.employeeName = employee.getEmployeeName();
        this.role = employee.getRole();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
