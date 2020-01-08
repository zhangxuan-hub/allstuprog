package com.zx.signtabcrud;

import com.zx.signtabcrud.beans.Student;
import com.zx.signtabcrud.dao.IStudentDao;
import com.zx.signtabcrud.dao.impl.StudentDaoImpl;
import com.zx.utils.MyBatisutils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class AppTest {
    private IStudentDao studentDao;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Before
    public void before(){
        studentDao = new StudentDaoImpl();
    }

    @After
    public void close() {
        MyBatisutils.closeSqlSession();
    }

    @Test
    public void selStusNoParam() throws ParseException {
        List<Student> students = studentDao.selStu();
        for (Student student:students) {
            System.out.println(student);
        }
    }

    @Test
    public void selStuById() throws ParseException {
        System.out.println(studentDao.selStuById(1));
    }
}
