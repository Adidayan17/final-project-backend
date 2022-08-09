package com.dev.objects;

import javax.persistence.*;

@Entity
@Table(name = "lecturer")
public class SpecializationForLecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public Integer id;

    @Column (name="name")
    private String name ;

    @ManyToOne
    @JoinColumn(name="lecturer")
    private  User lecturer;

    public SpecializationForLecturer(){

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

    public User getLecturer() {
        return lecturer;
    }

    public void setLecturer(User lecturer) {
        this.lecturer = lecturer;
    }
}
