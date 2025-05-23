package com.buoi1.models;

public class Member {
    private int id;
    private String fullName;
    private String className;
    private int age;

    public Member(int id, String fullName, String className, int age) {
        this.id = id;
        this.fullName = fullName;
        this.className = className;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getClassName() {
        return className;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
}