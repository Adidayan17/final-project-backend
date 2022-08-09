package com.dev.objects;

import javax.persistence.*;

@Entity
@Table(name = "lecturer")
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public Integer id;

    @Column (name="name")
    private String name ;

    @ManyToOne
    @JoinColumn(name="lecturer")
    private Lecturer lecturer;

    public Specialization(){

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

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }
}
