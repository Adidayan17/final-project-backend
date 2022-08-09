package com.dev;

import com.dev.objects.*;
import com.dev.utils.Utils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;



import java.text.DateFormat;
import java.text.ParseException;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    public boolean creatUser (String name , String phone , String email , String department  , String password, String type )
    {
        if (doseEmailAvailable(email)){
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            User user = new User( name ,  phone ,  email ,  department , password, type);
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
    public User logIn (String email , String password){

        Session session = sessionFactory.openSession();
        User user = (User) session.createQuery("FROM User u WHERE u.email=:email AND u.password=:password")
                .setParameter("email", email).setParameter("password",password)
                .uniqueResult();
        session.close();
        return user;

    }


}



