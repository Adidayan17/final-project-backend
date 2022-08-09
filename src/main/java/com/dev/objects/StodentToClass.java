package com.dev.objects;

import javax.persistence.*;

@Entity
@Table(name = "class")
public class StodentToClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id")
    public Integer id;

    @ManyToOne
    @JoinColumn(name = "class")
    private Class aClass ;

    @ManyToOne
    @JoinColumn(name="student")
    private User student;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }
}
