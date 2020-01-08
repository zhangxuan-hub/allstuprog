package com.zx.selectbymap.dao;

import com.zx.signtabcrud.beans.Student;

import java.util.List;
import java.util.Map;

public interface IStudentDao {
    List<Student> selectStuByCondition(Map<String, Object> params);

    List<Student> selectStuByConditions(Map<String, Object> params);
}
