package com.dev.objects;

public class FormatUser {
    public Integer id;
    private String name;
    private String token;

    public FormatUser() {
    }

    public FormatUser(Integer id, String name, String token) {
        this.id = id;
        this.name = name;
        this.token = token;
    }

    public FormatUser(FormatUser user) {
        this.id = user.getId();
        this.name = user.getName();
        this.token = user.getToken();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
