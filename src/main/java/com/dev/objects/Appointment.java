package com.dev.objects;


import javax.persistence.*;

@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id")
    public Integer id;

    @Column (name = "appointment_date")
    private String date;
    @Column (name = "start_time")
    private String startTime;
    @Column (name = "end_time")
    private String endTime;
    @ManyToOne
    @JoinColumn (name= " employee")
    private Employee employee ;

    @ManyToOne
    @JoinColumn (name= "client")
    private Client client ;



    public Appointment (Client client , Employee employee , String date , String startTime ){
        this.client = client;
        this.employee = employee ;
        this.date = date ;
        this.startTime =startTime;


    }

    public Appointment() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String appointmentDate) {
        this.date = appointmentDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
