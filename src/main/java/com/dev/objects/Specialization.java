package com.dev.objects;

import javax.persistence.*;

@Entity
@Table(name = "specialization")
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public Integer id;

    @Column (name="specialization_name")
    private String specializationName;

    public Specialization(){

    }
    public Specialization(String specializationName){
        this.specializationName = specializationName;
    }

    public Specialization(Specialization specialization){
        this.id = specialization.getId();
        this.specializationName = specialization.getSpecializationName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }
}
