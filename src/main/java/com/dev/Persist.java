package com.dev;

import com.dev.objects.*;
import com.dev.utils.Utils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

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
                    "jdbc:mysql://localhost:3306/appointmentsDB?useSSL=false", "root", "1234");


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // client sign up

    public boolean doseClientPhoneAvailable(String phoneNumber) {
        Session session = sessionFactory.openSession();
        Client client = (Client) session.createQuery("FROM Client c WHERE c.phoneNumber =:phoneNumber")
                .setParameter("phoneNumber", phoneNumber)
                .uniqueResult();
        session.close();

        if (client != null) {
            return false;
        }
        return  true;
    }


    public boolean createClient (String clientName, String password ,String phoneNumber ) {
        if (doseClientPhoneAvailable(phoneNumber)) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Client client = new Client(clientName, password, Utils.createHash(clientName, password), phoneNumber);
            session.save(client);
            transaction.commit();
            session.close();
            if (client.id != 0) {
                return true;
            }
        }
        return false;
    }

    // client log in

    public String logIn( String password , String phoneNumber ) {
        Session session = sessionFactory.openSession();
        Client client = (Client) session.createQuery( "FROM Client c WHERE  c.password=:password AND c.phoneNumber=:phoneNumber ")
                .setParameter("password",password).setParameter("phoneNumber",phoneNumber)
                .uniqueResult();
        session.close();
        if (client != null) {
            return client.getToken();
        }
        return  null ;

    }

    // get client

    public  Client getClientByToken (String token){
        Session session = sessionFactory.openSession();
        Client client = (Client) session.createQuery("FROM Client c WHERE c.token=:token").setParameter("token",token)
                .uniqueResult();
        session.close();
        return client;
    }

    // get appointments for client

    public List<Appointment> getAppointmentsForClient (String token){
        Session session = sessionFactory.openSession();
        List<Appointment> appointments = session.createQuery("FROM Appointment a WHERE a.client.token=:token").setParameter("token",token)
                .list();
        session.close();
        return appointments;


    }

// get  appointment
    public Appointment getAppointmentById (int id){
        Session session= sessionFactory.openSession();
        Appointment appointment = (Appointment) session.createQuery("FROM Appointment a WHERE a.id =:id").
                setParameter("id",id).uniqueResult();
        session.close();
        return appointment;

    }
    ///// get all appointments

    public List<Appointment> getAppointments (){
        return sessionFactory.openSession().createQuery("FROM Appointment").list();
    }

// get  employee
    public Employee getEmployeeById ( int id){
        Session session = sessionFactory.openSession();
        Employee employee = (Employee) session.createQuery("FROM Employee e WHERE  e.id =:id").setParameter("id",id)
                .uniqueResult();
        session.close();
        return employee ;


    }
// get all employees

    public List<Employee> getEmployees (){
        return sessionFactory.openSession().createQuery("FROM Employee ").list();
    }

  // get employees by role
    public List<Employee> getEmployeesByRole ( String role){
        Session session = sessionFactory.openSession();
        List<Employee> employees = session.createQuery("FROM Employee e WHERE e.role =:role").setParameter("role",role)
                .list();
        session.close();
        return employees;

    }

// get appointments list for employee
    public List<Appointment> gatAppointmentForEmployee (int employeeId ){
         Session session =sessionFactory.openSession();
        List <Appointment> appointments = session.createQuery(" FROM Appointment a WHERE a.employee.id=:id ")
                .setParameter("id",employeeId)
                .list();
        session.close();
        return appointments;
    }
    // add appointment

    public void addAppointment ( String token , int employeeId , String date , String startTime , String endTime  ){

        Session session= sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Appointment appointment = new Appointment(getClientByToken(token),getEmployeeById(employeeId),date ,startTime ,endTime);
        session.save(appointment);
        transaction.commit();
        session.close();


    }

    // delete appointment
    public  void deleteAppointmentForClient ( String token , int appointmentId ){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Appointment appointment = (Appointment) session.load(Appointment.class,appointmentId);
        session.delete(appointment);
        transaction.commit();
        session.close();


    }

}


















