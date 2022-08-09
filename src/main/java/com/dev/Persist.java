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

//    public boolean doseEmailAvailable(String phoneNumber) {
//        Session session = sessionFactory.openSession();
//        Client client = (Client) session.createQuery("FROM Client c WHERE c.phoneNumber =:phoneNumber")
//                .setParameter("phoneNumber", phoneNumber)
//                .uniqueResult();
//        session.close();
//
//        if (client != null) {
//            return false;
//        }
//        return true;
//    }


}



