package com.dev.controllers;

import com.dev.Persist;
import com.dev.objects.*;
//import com.dev.utils.MessagesHandler;
import org.springframework.beans.NotWritablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;


import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
public class TestController {
    @Autowired
    private Persist persist;
//    private MessagesHandler messagesHandler;
//
//
//    public TestController(MessagesHandler messagesHandler) {
//        this.messagesHandler = messagesHandler;
//    }

    @PostConstruct
    private void init () {



    }
    @RequestMapping(value = "create-client" ,method = RequestMethod.POST)
    public boolean addClient (@RequestParam String clientName , String password ,String phoneNumber){
        return persist.createClient(clientName,password,phoneNumber);
    }
    @RequestMapping(value ="login")
    public String login ( String password , String phoneNumber){
        return persist.logIn(password,phoneNumber);
    }
    @RequestMapping ( value = "get-client")
    public Client getClient (String token){
        return persist.getClientByToken(token);
    }
    @RequestMapping (value = "get-clients-appointments")
    public List <Appointment> getClientsAppointments (String token){
        return persist.getAppointmentsForClient(token );
    }
    @RequestMapping (value = "get-clients-appointments-by-role")
    public List <Appointment> getClientsAppointmentsByRole (String token, String role){

        return persist.getAppointmentsForClientByRole(token , role);
    }
    @RequestMapping (value = "get-appointment-by-id")
    public Appointment getAppointmentByID (int id){
        return persist.getAppointmentById(id);
    }
    @RequestMapping(value = "get-all-appointments")
    public List<Appointment> getAllAppointments(){
        return persist.getAppointments();
    }
    @RequestMapping(value = "get-employee-by-id")
    public Employee getEmployeeById (int id){
        return persist.getEmployeeById(id);
    }
    @RequestMapping(value = "gey-all-employees")
    public List<Employee> getAllEmployees (){
        return persist.getEmployees();
    }
    @RequestMapping(value = "get-employees-by-role")
    public List<Employee> getEmployeesByRole (String role){
        return persist.getEmployeesByRole(role);
    }
    @RequestMapping(value = "get-employees-appointments")
    public List<Appointment> getEmployeesAppointment (int employeeId ,String date  ){
        return persist.gatAppointmentForEmployee(employeeId, date );
    }
    @RequestMapping (value = "add-appointment",method = RequestMethod.POST)
    public void addAppointment (@RequestParam String token , int employeeId , String date , String startTime){
         persist.addAppointment(token,employeeId,date,startTime);
    }
    @RequestMapping (value ="delete-appointment",method = RequestMethod.POST)
    public void deleteAppointment (@RequestParam  String token , int appointmentId ){
        persist.deleteAppointmentForClient(token,appointmentId);
    }
}
