package com.dev.objects;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "student")
    private int student;

    @Column(name = "lecturer")
    private int lecturer;

    @Column(name = "password")
    private String password;

    @Column(name = "token")
    private String token;

    @Column(name = "first_log_in")
    private int firstLogIn;

    //collegeType 0 means "ashkelon" 1 means "gan yavne"
    @Column(name = "college_type")
    private Integer collegeType;


    public User() {

    }

    public User(String name, String phone, String email, String password, String token, String type, Integer collegeType) {

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.token = token;
        this.collegeType = collegeType;
        switch (type) {
            case "student":
                this.student = 1;
                break;
            case "lecturer":
                this.lecturer = 1;
                break;
            case "both":
                this.lecturer = 1;
                this.student = 1;
                break;

        }
        this.firstLogIn = 1;


    }

    public User(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.student = user.getStudent();
        this.lecturer = user.getLecturer();
        this.token = user.getToken();
        this.firstLogIn = user.isFirstLogIn();
        this.collegeType = user.getCollegeType();

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

    public int isFirstLogIn() {
        return firstLogIn;
    }

    public void setFirstLogIn(int firstLogIn) {
        this.firstLogIn = firstLogIn;
    }

    public Integer getCollegeType() {
        return collegeType;
    }

    public void setCollegeType(Integer ganYavne) {
        this.collegeType = ganYavne;
    }
}
