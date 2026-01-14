package com.example.prac.app;

public class User {

    private  int id;
    private  String name ;
    private  String email;
    private String course ;
    private  int age ;


    public User(int id , String name , String email) {
        this.id = id;
        this.name = name ;
        this.email = email;
        this.course = course ;
        this.age =age ;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCourse() {
        return course;
    }

    public int getAge() {
        return age;
    }
}
