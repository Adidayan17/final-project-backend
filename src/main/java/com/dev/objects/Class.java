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

        @Column(name = "subject")
        private String subject;

        @ManyToOne
        @JoinColumn(name="lecturer")
        private User lecturer;


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

    public User getLecturer() {
        return lecturer;
    }

    public void setLecturer(User lecturer) {
        this.lecturer = lecturer;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
