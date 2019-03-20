package com.example.rememberingreminding;

public class User {

    int id;
    String name, username, password;

    public User(String name, String username, String password){
        this.name=name;
        this.username=username;
        this.password=password;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password=password;
    }
}
