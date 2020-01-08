package com.zx.signtabcrud.dao;

import com.zx.signtabcrud.beans.Student;

import java.util.List;
import java.util.Map;

public interface IStudentDao {
    void insertStu(Student student);
    int insertStuCatchId(Student student);

    void delStudById(int id);
    void updateStuById(Student student);


    List<Student> selStu();
    Map<String, Object> selAllStusMap();
    Student selStuById(int id);
    List<Student> selStudentByName(String name);
}
