package com.dev;

import com.dev.objects.*;
import com.dev.objects.Class;
import com.dev.utils.Utils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public boolean createUser(String name, String phone, String email, String password, String type) {
        if (doseEmailAvailable(email)) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            User user = new User(name, phone, email, password, Utils.createHash(name, password), type);
            session.save(user);
            transaction.commit();
            session.close();
            if (user.id != 0) {
                return true;
            }
        }
        return false;

    }

    //login
    public String logIn(String email, String password) {

        Session session = sessionFactory.openSession();
        User user = (User) session.createQuery("FROM User u WHERE u.email=:email AND u.password=:password")
                .setParameter("email", email).setParameter("password", password)
                .uniqueResult();
        session.close();
        if (user != null) {
            return user.getToken();
        }
        return null;
    }

    //get user by token
    public User getUserByToken(String token) {
        Session session = sessionFactory.openSession();
        User user = (User) session.createQuery("FROM User u WHERE u.token = :token")
                .setParameter("token", token)
                .uniqueResult();
        session.close();
        return user;
    }

    public User getUserById(int userId) {
        Session session = sessionFactory.openSession();
        User user = (User) session.createQuery("FROM User u WHERE u.id =:userId")
                .setParameter("userId", userId)
                .uniqueResult();
        session.close();
        return user;

    }

    // check first log in
    public boolean checkFirstLogIn(String token) {
        User user = getUserByToken(token);
        if (user.isFirstLogIn() == 1) {
            ;
            incFirstLogIn(token);
            return true;
        } else {
            return false;
        }
    }

    //inc first log in
    public void incFirstLogIn(String token) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User userObject = getUserByToken(token);
        int firstLogin = userObject.isFirstLogIn() + 1;
        userObject.setFirstLogIn(firstLogin);
        session.saveOrUpdate(userObject);
        transaction.commit();
        session.close();
    }

    //get specialization by id
    public Specialization getSpecializationById(int specializationId) {
        Session session = sessionFactory.openSession();
        Specialization specialization = (Specialization) session.createQuery("FROM Specialization s WHERE s.id =:specializationId")
                .setParameter("specializationId", specializationId)
                .uniqueResult();
        session.close();
        return specialization;
    }

    //get class by id
    public Class getClassById(Integer classId) {
        Session session = sessionFactory.openSession();
        Class aClass = (Class) session.createQuery("FROM Class c WHERE c.id =:classId")
                .setParameter("classId", classId)
                .uniqueResult();
        session.close();
        return aClass;
    }


    // get all specializations
    public List<Specialization> getAllSpecializations() {
        return sessionFactory.openSession().createQuery("FROM Specialization ").list();

    }

    //get specializations for lecturer
    public List<Specialization> getSpecializationsForLecturer(String token) {

        Session session = sessionFactory.openSession();
        List<Specialization> specializations = session.createQuery(" SELECT specialization FROM SpecializationForLecturer s WHERE s.lecturer.token =:token")
                .setParameter("token", token)
                .list();
        session.close();
        return specializations;
    }

    // get lecturers for specialization
    public List<User> getLecturersForSpecializations(int specializationId) {

        Session session = sessionFactory.openSession();
        List<User> lecturers = session.createQuery(" SELECT lecturer FROM SpecializationForLecturer s WHERE s.specialization.id =:specializationId")
                .setParameter("specializationId", specializationId)
                .list();
        session.close();
        return lecturers;
    }

    //get classes for lecturer
    public List<Class> getClassesForLecturer(String token) {
        Session session = sessionFactory.openSession();
        List<Class> classes = session.createQuery(" FROM Class c WHERE c.lecturer.token =:token")
                .setParameter("token", token)
                .list();
        session.close();
        return classes;

    }

    // get classes for student
    public List<Class> getClassesForStudent(String token) {
        Session session = sessionFactory.openSession();
        List<Class> classes = session.createQuery("SELECT aClass  FROM StudentToClass s WHERE s.student.token =:token")
                .setParameter("token", token)
                .list();
        session.close();
        return getFutureClasses(classes);
    }

    // get students for class

    public List<String> getStudentsEmailsForClass (int classId){
        Session session = sessionFactory.openSession();
        List<User> students = session.createQuery("SELECT student FROM StudentToClass s WHERE s.aClass.id=:classId")
                .setParameter("classId",classId)
                .list();
        session.close();


        ArrayList<String> emails = new ArrayList<>();
        for (User student:students) {
            emails.add(student.getEmail());
        }
        return emails;

    }
    // get classes by specialization
    public List<Class> getClassesBySpecialization(int specializationId) {
        Session session = sessionFactory.openSession();
        List<Class> classes = session.createQuery("  FROM Class s WHERE s.specialization.id =:specializationId")
                .setParameter("specializationId", specializationId)
                .list();
        session.close();

        return getFutureClasses(classes);
    }


    // check if lecturer 2 means both ,10 means admin user
    public int checkUserType(String token) {
        Session session = sessionFactory.openSession();
        User user = (User) session.createQuery("FROM User u WHERE u.token = :token")
                .setParameter("token", token)
                .uniqueResult();
        session.close();
        if (user.getStudent() == 10) {
            return 10;
        }
        if (user.getStudent() == 1 && user.getLecturer() == 1) {
            return 2;
        } else if (user.getLecturer() == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    // get user details for settings page
    public User getUserDetails(String token) {
        Session session = sessionFactory.openSession();
        User user = (User) session.createQuery("FROM User u WHERE u.token = :token")
                .setParameter("token", token)
                .uniqueResult();
        session.close();
        user.setPassword("********");
        return user;
    }

    // dose lecturer have specialization
    public boolean doseLecturerHaveSpecialization(String token, int specializationId) {
        Session session = sessionFactory.openSession();
        SpecializationForLecturer specializationLecturer = (SpecializationForLecturer) session
                .createQuery("SELECT  specialization FROM SpecializationForLecturer s WHERE s.lecturer.token =:token AND s.specialization.id =:specializationId")
                .setParameter("token", token)
                .setParameter("specializationId", specializationId)
                .uniqueResult();
        session.close();
        if (specializationLecturer != null) {
            return true;
        } else {
            return false;
        }

    }

    // delete specialization for lecturer

    public void deleteSpecializationForLecturer(String token, int specializationId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        SpecializationForLecturer specializationLecturer = (SpecializationForLecturer) session.load(SpecializationForLecturer.class, specializationId);
        session.delete(specializationLecturer);
        transaction.commit();
        session.close();
    }

    // add specialization for lecturer

    public void addSpecializationForLecturer(String token, int specializationId) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        SpecializationForLecturer specialization = new SpecializationForLecturer(getSpecializationById(specializationId), getUserByToken(token));
        session.save(specialization);
        transaction.commit();
        session.close();
    }


    //change specialization to lecturer
    public boolean changeSpecializationForLecturer(String token, int specializationId) {
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
    public boolean createClass(String date, String startTime, String token, int specializationId) {
        if (checkUserType(token) != 0) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Class c = new Class(date, startTime, getUserByToken(token), getSpecializationById(specializationId));
            session.save(c);
            transaction.commit();
            session.close();
            return true;
        } else {
            return false;
        }

    }

    //dose student in class
    public boolean doseStudentInClass(String token, int classId) {
        Session session = sessionFactory.openSession();
        User user = (User) session.createQuery(" FROM StudentToClass  s WHERE s.aClass.id =:classId AND s.student.token=:token")
                .setParameter("classId", classId).setParameter("token", token)
                .uniqueResult();
        session.close();
        if (user == null) {
            return false;
        } else {
            return true;
        }
    }


    // add student to class

    public boolean addStudentToClass(String token, int classId) {
        int student = getUserByToken(token).getId();
        int lecturer = getClassById(classId).getLecturer().getId();
        if (student != lecturer && !doseStudentInClass(token, classId)) {
            Session session = sessionFactory.openSession();

            Transaction transaction = session.beginTransaction();
            StudentToClass studentToClass = new StudentToClass(getClassById(classId), getUserByToken(token));
            session.save(studentToClass);
            transaction.commit();
            session.close();
            return true;
        } else {
            return false;
        }

    }

    // remove student from class
    public void removeStudentFromClass(String token, int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        StudentToClass studentToClass = (StudentToClass) session.createQuery("FROM StudentToClass s WHERE s.student.token=:token AND s.aClass.id=:id")
                .setParameter("token", token).setParameter("id", id)
                .uniqueResult();
        session.delete(studentToClass);
        transaction.commit();
        session.close();
    }

    // delete class
    public void deleteClass(String token, int classId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Class aClass = (Class) session.load(Class.class, classId);
        session.delete(aClass);
        List<StudentToClass> classes = session.createQuery("  FROM StudentToClass s WHERE s.aClass.id =:classId")
                .setParameter("classId", classId)
                .list();
        for (StudentToClass classToDelete : classes) {
            session.delete(classToDelete);
        }
        transaction.commit();
        session.close();

    }

//     public List<String> getEmailForClassStudents (int classId){
//        Session session = sessionFactory.openSession();
//        List<String>emails = session.createQuery(" email  FROM StudentToCkass s WHERE s.class.id =:classId")
//                        .setParameter("classId", classId)
//                        .list();
//         session.close();
//         return emails;
//     }


    public List<Class> getFutureClasses(List<Class> classes) {
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String date = (formatter.format(new Date())).toString();
        List<Class> futureClasses = new ArrayList<>();
        String frontDay = date.substring(0, 2);
        String frontMonth = date.substring(3, 5);
        String frontYear = date.substring(6, 10);
        if (classes != null) {
            for (Class aClass : classes) {
                String formatDay = aClass.getDate().substring(0, 2);
                String formatMonth = aClass.getDate().substring(3, 5);
                String formatYear = aClass.getDate().substring(6, 10);
                if (Integer.parseInt(formatYear) > Integer.parseInt(frontYear)) {
                    futureClasses.add(aClass);
                } else if (Integer.parseInt(formatYear) == Integer.parseInt(frontYear)) {
                    if (Integer.parseInt(formatMonth) > Integer.parseInt(frontMonth)) {
                        futureClasses.add(aClass);
                    } else if (Integer.parseInt(formatMonth) == Integer.parseInt(frontMonth)) {
                        if (Integer.parseInt(formatDay) > Integer.parseInt(frontDay)) {
                            futureClasses.add(aClass);

                        }
                    }
                }
            }
        }

        return futureClasses;
    }


    public List<Class> getLecturerReport(int lecId, String month, String year) {
        List<Class> classes = getClassesForLecturer(getUserById(lecId).getToken());
        List<Class> thisMonthClasses = new ArrayList<>();
        if (classes != null) {
            for (Class aClass : classes) {
                String formatMonth = aClass.getDate().substring(3, 5);
                String formatYear = aClass.getDate().substring(6, 10);
                if (Integer.parseInt(formatYear) == Integer.parseInt(year)) {
                    if (Integer.parseInt(formatMonth) == Integer.parseInt(month)) {
                        thisMonthClasses.add(aClass);
                    }
                }
            }
        }
        return thisMonthClasses;
    }

    // get all Lecturers for reports
    public List<FormatUser> getAllLecturers() {
        Session session = sessionFactory.openSession();
        List<User> lecturers = session.createQuery("  FROM User u WHERE u.lecturer =:type")
                .setParameter("type", 1)
                .list();
        session.close();
        ArrayList<FormatUser> temp = new ArrayList<>();
        for (User lecturer:lecturers) {
            FormatUser user=new FormatUser(lecturer.getId(),lecturer.getName(),lecturer.getToken());
            temp.add(user);
        }
        return temp;
    }


}



