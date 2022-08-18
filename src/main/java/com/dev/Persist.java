package com.dev;

import com.dev.objects.*;
import com.dev.objects.Class;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Component
public class Persist {
    private final SessionFactory sessionFactory;
    private Connection connection;


    @Autowired
    public Persist(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @PostConstruct
    public void createConnectionToDatabase() {

        try {
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/final-project?useSSL=false", "root", "1234");


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean doseEmailAvailable(String email) {
        Session session = sessionFactory.openSession();
        User user = (User) session.createQuery("FROM User u WHERE u.email =:email")
                .setParameter("email", email)
                .uniqueResult();
        session.close();

        if (user != null) {
            return false;
        }
        return true;
    }

    // create user

    public boolean createUser(String name , String phone , String email  , String password, String type )
    {
        if (doseEmailAvailable(email)){
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            User user = new User( name ,  phone ,  email , password, type);
            session.save(user);
            transaction.commit();
            session.close();
            if (user.id!=0 ){
                return true;
            }
        }
        return false;

    }

    //login
    public String logIn (String email , String password){

        Session session = sessionFactory.openSession();
        User user = (User) session.createQuery("FROM User u WHERE u.email=:email AND u.password=:password")
                .setParameter("email", email).setParameter("password",password)
                .uniqueResult();
        session.close();
        return user.getToken();

    }
    //get user by token
    public User getUserByToken (String token){
        Session session = sessionFactory.openSession();
        User user = (User) session.createQuery("FROM User u WHERE u.token = :token")
                .setParameter("token",token)
                .uniqueResult();
        session.close();
        return user;
    }
    // check first log in
    public boolean checkFirstLogIn (String token)  {
        User user = getUserByToken(token);
        if (user.isFirstLogIn() == 1 ) {;
            return true;
        } else {
            return false;
        }
    }

    //inc first log in
    public void incFirstLogIn (String token){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User userObject = getUserByToken(token);
        int firstLogin = userObject.isFirstLogIn()+1;
        userObject.setFirstLogIn(firstLogin);
        session.saveOrUpdate(userObject);
        transaction.commit();
        session.close();
    }

    //get specialization by id
    public Specialization getSpecializationById (int specializationId){
        Session session = sessionFactory.openSession();
        Specialization specialization = (Specialization) session.createQuery("FROM Specialization s WHERE s.id =:specializationId")
                .setParameter("specializationId",specializationId)
                .uniqueResult();
        session.close();
        return specialization;
    }

    //get class by id

    public Class getClassById (int classId){
        Session session = sessionFactory.openSession();
        Class aClass = (Class) session.createQuery("FROM Class c WHERE c.id =:classId")
                .setParameter("classId",classId)
                .uniqueResult();
        session.close();
        return aClass;
    }



    // get all specializations
    public List<Specialization> getAllSpecializations (){
        return sessionFactory.openSession().createQuery("FROM Specialization ").list();

    }

    //get specializations for lecturer
    public List<Specialization> getSpecializationsForLecturer (String token){

        Session session = sessionFactory.openSession();
        List<Specialization> specializations = session.createQuery(" SELECT specialization FROM SpecializationForLecturer s WHERE s.lecturer.token =:token")
                .setParameter("token", token)
                .list();
        session.close();
        return specializations;
    }

    // get lecturers for specialization
    public List<User> getLecturersForSpecializations(int specializationId){

        Session session = sessionFactory.openSession();
        List<User> lecturers = session.createQuery(" SELECT lecturer FROM SpecializationForLecturer s WHERE s.specialization.id =:specializationId")
                .setParameter("specializationId", specializationId)
                .list();
        session.close();
        return lecturers;
    }

    //get classes for lecturer
    public List<Class> getClassesForLecturer (String token){
        Session session = sessionFactory.openSession();
        List<Class> classes = session.createQuery(" SELECT lecturer FROM Class c WHERE c.lecturer.token =:token")
                .setParameter("token", token)
                .list();
        session.close();
        return classes;
    }

    // get classes for student
    public List<Class> getClassesForStudent (String token){
        Session session = sessionFactory.openSession();
        List<Class> classes = session.createQuery(" SELECT student FROM StudentToClass s WHERE s.student.token =:token")
                .setParameter("token", token)
                .list();
        session.close();
        return classes;
    }




    // check if lecturer
    public boolean checkIfLecturer (String token) {
        Session session = sessionFactory.openSession();
        User user = (User) session.createQuery("FROM User u WHERE u.token = :token")
                .setParameter("token", token)
                .uniqueResult();
        session.close();
        if (user.getLecturer() == 1) {
            return true ;
        } else {
            return false;

        }
    }

    // dose lecturer have specialization
    public boolean doseLecturerHaveSpecialization(String token , int specializationId){
        Session session = sessionFactory.openSession();
        SpecializationForLecturer specializationLecturer=(SpecializationForLecturer) session
                .createQuery("SELECT  specialization FROM SpecializationForLecturer s WHERE s.lecturer.token =:token AND s.specialization.id =:specializationId")
                .setParameter("token",token)
                .setParameter("specializationId",specializationId)
                .uniqueResult();
        session.close();
        if (specializationLecturer!=null)
        {
            return true;
        }else {
            return false;
        }

    }

    // delete specialization for lecturer

    public void deleteSpecializationForLecturer(String token, int specializationId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        SpecializationForLecturer specializationLecturer =(SpecializationForLecturer)  session.load(SpecializationForLecturer.class,specializationId);
        session.delete(specializationLecturer);
        transaction.commit();
        session.close();
    }

    // add specialization for lecturer

    public void addSpecializationForLecturer(String token,int specializationId) {

    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
    SpecializationForLecturer specialization = new SpecializationForLecturer(getSpecializationById(specializationId),getUserByToken(token));
        session.save(specialization);
        transaction.commit();
        session.close();
    }


    //change specialization to lecturer
    public boolean changeSpecializationForLecturer(String token , int specializationId){
        if (getUserByToken(token) != null) {
            if (doseLecturerHaveSpecialization(token, specializationId)) {
                deleteSpecializationForLecturer(token, specializationId);
                return false;

            } else {
                addSpecializationForLecturer(token, specializationId);
                return true;
            }
        }

        return false;
    }

    // create class
    public boolean createClass (String date , String startTime  , String subject , String token, int specializationId){
       if (checkIfLecturer(token)){
           Session session = sessionFactory.openSession();
           Transaction transaction = session.beginTransaction();
           Class c = new Class( date ,  startTime  , subject, getUserByToken(token),getSpecializationById(specializationId));
           session.save(c);
           transaction.commit();
           session.close();
           return true;
       }else {
           return false;
       }

    }

    // add student to class

    public boolean addStudentToClass (String token ,int classId )
    {
        int student = getUserByToken(token).getId();
        int lecturer = getClassById(classId).getLecturer().getId();
        if (student!=lecturer) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            StudentToClass studentToClass = new StudentToClass(getClassById(classId),getUserByToken(token));
            session.save(studentToClass);
            transaction.commit();
            session.close();
            return true;
        }else {
            return true;
        }

    }





}




