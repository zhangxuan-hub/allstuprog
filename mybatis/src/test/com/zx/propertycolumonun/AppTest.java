package com.zx.propertycolumonun;


import com.zx.propertycolumonun.dao.IStudentDao;
import com.zx.propertycolumonun.dao.impl.StudentDaoImpl;
import com.zx.signtabcrud.beans.Student;
import com.zx.utils.MyBatisutils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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
