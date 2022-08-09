package com.dev.objects;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lecturer")

public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id")
    public Integer id;

    @Column (name="name")
    private String name ;

    @Column (name = "phone")
    private String phone ;

    @Column (name = "email")
    private String email ;

    public Lecturer(){

    }
    public Lecturer(String name , String phone , String email){
        this.name=name;
        this.phone = phone;
        this.email= email;
    }

    public Integer getId() {return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}

//
//@Entity
//@Table(name = "appointment")
//public class Appointment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column (name="id")
//    public Integer id;
//
//    @Column (name = "appointment_date")
//    private String date;
//    @Column (name = "start_time")
//    private String startTime;
//    @Column (name = "end_time")
//    private String endTime;
//    @ManyToOne
//    @JoinColumn (name= " employee")
//    private Employee employee ;
//
//    @ManyToOne
//    @JoinColumn (name= "client")
//    private Client client ;
//
//
//
//    public Appointment (Client client , Employee employee , String date , String startTime ){
//        this.client = client;
//        this.employee = employee ;
//        this.date = date ;
//        this.startTime =startTime;
//
//
//    }
//
//    public Appointment() {
//
//    }
//
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String appointmentDate) {
//        this.date = appointmentDate;
//    }
//
//    public String getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(String startTime) {
//        this.startTime = startTime;
//    }
//
//    public String getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(String endTime) {
//        this.endTime = endTime;
//    }
//
//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }
//
//    public Client getClient() {
//        return client;
//    }
//
//    public void setClient(Client client) {
//        this.client = client;
//    }
//}
