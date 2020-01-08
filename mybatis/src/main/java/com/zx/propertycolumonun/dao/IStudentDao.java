package com.zx.propertycolumonun.dao;

import com.zx.signtabcrud.beans.Student;

import java.util.List;
import java.util.Map;

public interface IStudentDao {
    List<Student> selStu();
    Student selStuById(int id);
}
