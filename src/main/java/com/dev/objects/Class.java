package com.dev.objects;

import javax.persistence.*;
@Entity
@Table(name = "class")

public class Class {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column (name="id")
        public Integer id;

        @Column (name = "date")
        private String date;

        @Column (name = "start_time")
        private String startTime ;

        @ManyToOne
        @JoinColumn(name="lecturer")
        private User lecturer;

         @ManyToOne
         @JoinColumn(name="Specialization")
         private Specialization specialization;


    public Class (){

        }
    public Class (String date , String startTime   , User lecturer , Specialization specialization){
        this.date = date;
        this.startTime = startTime;
        this.lecturer= lecturer;
        this.specialization = specialization ;

    }
    public Class (Class c){
        this.date = c.getDate();
        this.startTime = c.getStartTime();
        this.lecturer= c.getLecturer();
        this.specialization = c.getSpecialization() ;

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public User getLecturer() {
        return lecturer;
    }

    public void setLecturer(User lecturer) {
        this.lecturer = lecturer;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }
}
