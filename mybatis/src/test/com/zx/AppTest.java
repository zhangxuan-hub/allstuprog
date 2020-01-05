package com.zx;

import com.zx.beans.Student;
import com.zx.dao.IStudentDao;
import com.zx.dao.impl.IStudentDaoImpl;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AppTest {
    private IStudentDao studentDao;

    @Before
    public void before(){
        studentDao = new IStudentDaoImpl();
    }

    @Test
    public void testInsert() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse("2020-09-08 19:37:23");
        studentDao.insertStudent(new Student("孙悟空", 23, 56.7d, date));
    }
}
