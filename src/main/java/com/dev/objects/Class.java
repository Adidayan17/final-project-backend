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

        @Column (name = "end_time")
        private String endTime ;

        @ManyToOne
        @JoinColumn(name="lecturer")
        private Lecturer lecturer;


    public Class (){

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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }
}
