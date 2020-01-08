package com.zx.mapperdynamicproxy;

import com.zx.selectbymap.dao.IStudentDao;
import com.zx.signtabcrud.beans.Student;
import com.zx.utils.MyBatisutils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppTest {
    private SqlSession sqlSession;
    private IStudentDao studentDao;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Before
    public void before(){
        sqlSession = MyBatisutils.getSqlSession();
        studentDao = sqlSession.getMapper(IStudentDao.class);
    }

    @After
    public void close() {
        sqlSession.commit();
        MyBatisutils.closeSqlSession();
    }

    @Test
    public void test01() throws ParseException {
        Map<String, Object> params = new HashMap<String, Object>();
        Student student = new Student("田七", 27, 95);
        params.put("name", "猪");
        params.put("age", "23");
        params.put("stu", student);
        List<Student> students = studentDao.selectStuByCondition(params);
        System.out.println(students);
    }
}
