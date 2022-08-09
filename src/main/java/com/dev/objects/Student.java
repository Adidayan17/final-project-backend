package com.dev.objects;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
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

    @Column (name = "department ")
    private String department ;

    public Student(){

    }
    public Student(String name , String phone , String email , String department){
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.department = department;
    }

    public Student(Student student){
        this.id = student.getId();
        this.name = student.getName();
        this.phone = student.getPhone();
        this.email = student.getEmail();
        this.department = student.getDepartment();
    }

    public Integer getId() {
        return id;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
