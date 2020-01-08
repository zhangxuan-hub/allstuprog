package com.zx.mapperdynamicproxy.dao;

import com.zx.signtabcrud.beans.Student;

import java.util.List;

public interface IStudentDao {
    List<Student> selstuNoParam();
    Student selStuById(int id);
}
