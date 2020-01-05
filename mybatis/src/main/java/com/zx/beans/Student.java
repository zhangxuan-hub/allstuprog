package com.zx.beans;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Student {
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private int age;
    @NonNull
    private double score;
    @NonNull
    private Date createTime;

    public Student(@NonNull String name, @NonNull int age, @NonNull double score, @NonNull Date createTime) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.createTime = createTime;
    }
}
