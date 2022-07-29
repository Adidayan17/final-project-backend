package com.dev.objects;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id")
    public Integer id;

    @Column (name="client_name")
    private String clientName;

    @Column (name="password")
    private String password;

    @Column (name="token")
    private String token;

    @Column (name = "phone_number")
    private String phoneNumber ;





    public Client() {

    }
    public Client (String clientName , String password ,String token , String phoneNumber){
        this.clientName= clientName;
        this.password= password;
        this.token = token;
        this.phoneNumber = phoneNumber;

    }
    public Client ( Client client ){
        this.id= client.getId();
        this.clientName= client.getClientName();
        this.password=client.getPassword();
        this.token = client.token;
        this.phoneNumber = client.getPhoneNumber();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
