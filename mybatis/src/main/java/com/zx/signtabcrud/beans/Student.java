package com.zx.signtabcrud.beans;

import lombok.*;

import java.util.Date;

@ToString
public class Student {
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private int age;
    @NonNull
    private double score;

    public Student() {
    }

    public Student(@NonNull String name, @NonNull int age, @NonNull double score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public Student(Integer id, @NonNull String name, @NonNull int age, @NonNull double score, @NonNull Date createTime) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = score;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}