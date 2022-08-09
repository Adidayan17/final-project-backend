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
      //  return persist.createClient(clientName,password,phoneNumber);
        return true;
    }
    @RequestMapping(value ="login")
    public String login ( String password , String phoneNumber){

        //return persist.logIn(password,phoneNumber);
        return "tt";
    }

}
