package com.dev.controllers;

import com.dev.Persist;
import com.dev.objects.*;
//import com.dev.utils.MessagesHandler;
import com.dev.objects.Class;
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


    @PostConstruct
    private void init() {


    }

    @RequestMapping(value = "create-user", method = RequestMethod.POST)
    public boolean addUser(@RequestParam String name, String phone, String email, String password, String type, Integer collegeType) {
        return persist.createUser(name, phone, email, password, type, collegeType);
    }

    @RequestMapping(value = "login")
    public String login(String email, String password) {

        return persist.logIn(email, password);

    }

    @RequestMapping(value = "check-first-log-in")
    public boolean checkFirstLogIn(String token) {

        return persist.checkFirstLogIn(token);

    }

    @RequestMapping(value = "inc-first-logIn", method = RequestMethod.POST)
    public void incFirstLogIn(@RequestParam String token) {
        persist.incFirstLogIn(token);
    }

    @RequestMapping(value = "get-specialization-by-id")
    public Specialization getSpecializationById(int specializationId) {
        return persist.getSpecializationById(specializationId);
    }

    @RequestMapping(value = "get-class-by-id")
    public Class getClassById(Integer classId) {
        return persist.getClassById(classId);
    }

    @RequestMapping(value = "get-all-specializations")
    public List<Specialization> getAllSpecializations() {
        return persist.getAllSpecializations();
    }

    @RequestMapping(value = "get-specializations-for-lecturer")
    public List<Specialization> getSpecializationsForLecturer(String token) {
        return persist.getSpecializationsForLecturer(token);
    }

    @RequestMapping(value = "get-Lecturers-for-specializations")
    public List<User> getLecturersForSpecializations(int specializationId) {
        return persist.getLecturersForSpecializations(specializationId);
    }

    @RequestMapping(value = "get-classes-for-lecturer")
    public List<Class> getClassesForLecturer(String token) {
        return persist.getFutureClasses(persist.getClassesForLecturer(token));
    }

    @RequestMapping(value = "get-classes-for-student")
    public List<Class> getClassesForStudent(String token) {
        return persist.getClassesForStudent(token);
    }

    @RequestMapping(value = "get-classes-by-specialization")
    public List<Class> getClassesBySpecialization(int specializationId) {
        return persist.getClassesBySpecialization(specializationId);
    }

    @RequestMapping(value = "check-user-type")
    public int checkUserType(String token) {
        return persist.checkUserType(token);
    }

    @RequestMapping(value = "get-user")
    public User getUserDetails(String token) {

        return persist.getUserDetails(token);
    }

    @RequestMapping(value = "change-specialization-for-lecturer")
    public boolean changeSpecializationForLecturer(String token, int specializationId) {
        return persist.changeSpecializationForLecturer(token, specializationId);
    }

    @RequestMapping(value = "create-class", method = RequestMethod.POST)
    public boolean createClass(@RequestParam String date, String startTime, String token, int specializationId) {
        return persist.createClass(date, startTime, token, specializationId);
    }

    @RequestMapping(value = "add-student-to-class", method = RequestMethod.POST)
    public boolean addStudentToClass(@RequestParam String token, int classId) {
        return persist.addStudentToClass(token, classId);
    }

    @RequestMapping(value = "remove-student-from-class", method = RequestMethod.POST)
    public void removeStudentFromClass(@RequestParam String token, int id) {
        persist.removeStudentFromClass(token, id);
    }

    @RequestMapping(value = "delete-class", method = RequestMethod.POST)
    public void deleteClass(@RequestParam String token, int classId) {
        persist.deleteClass(token, classId);
    }

    @RequestMapping(value = "get-all-lecturers", method = RequestMethod.GET)
    public List<FormatUser> getAllLecturers() {
        return persist.getAllLecturers();
    }

    @RequestMapping(value = "get-all-lecturers-college", method = RequestMethod.GET)
    public List<FormatUser> getAllLecturersCollege(int collegeType) {
        return persist.getAllLecturersByCollege(collegeType);
    }

    @RequestMapping(value = "get-report")
    public List<Class> getReports(int lecId, String month, String year) {
        return persist.getLecturerReport(lecId, month, year);
    }

    @RequestMapping(value = "get-students-email-for-class")
    public List<String> getStudentsEmailsForClass(int classId) {
        return persist.getStudentsEmailsForClass(classId);
    }


}
