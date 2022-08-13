package com.dev.objects;

import javax.persistence.*;

@Entity
@Table(name = "Specialization_for_lecturer")
public class SpecializationForLecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public Integer id;

    @ManyToOne
    @JoinColumn(name="specialization")
    private Specialization specialization;

    @ManyToOne
    @JoinColumn(name="lecturer")
    private  User lecturer;

    public SpecializationForLecturer(){

    }
    public SpecializationForLecturer( Specialization specialization ,User lecturer){
        this.specialization = specialization;
        this.lecturer = lecturer;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public User getLecturer() {
        return lecturer;
    }

    public void setLecturer(User lecturer) {
        this.lecturer = lecturer;
    }
}
