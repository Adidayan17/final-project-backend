package com.dev.objects;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class User {
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

    @Column (name = "student")
    private int student =0;

    @Column(name = "lecturer")
    private int lecturer=0;

    @Column (name="password")
    private String password;

    @Column (name="token")
    private String token;

    public User(){

    }
    public User(String name , String phone , String email , String department , String password, String type){

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.department = department;
        this.password=password;
        switch (type){
            case "student":
                this.student = 1;
                break;
            case "lecturer":
                this.lecturer=1;
                break;
            case "both":
                this.lecturer =1;
                this.student =1;
                break;

        }


    }

    public User(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.department = user.getDepartment();
        this.password=user.getPassword();
        this.student= user.getStudent();
        this.lecturer=user.getLecturer();
        this.token = user.getToken();
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

    public int getStudent() {
        return student;
    }

    public void setStudent(int student) {
        this.student = student;
    }

    public int getLecturer() {
        return lecturer;
    }

    public void setLecturer(int lecturer) {
        this.lecturer = lecturer;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
