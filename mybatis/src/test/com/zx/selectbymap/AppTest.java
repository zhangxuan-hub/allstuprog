package com.zx.selectbymap;

import com.zx.selectbymap.dao.IStudentDao;
import com.zx.signtabcrud.beans.Student;
import com.zx.utils.MyBatisutils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName AppTest
 * @Author zhangxuan
 * @Description TODO
 * @Date 2020/1/9 9:04
 * @Version 1.0
 */
public class AppTest {

    private SqlSession sqlSession;
    private IStudentDao studentDao;

    @Before
    public void before(){
        sqlSession = MyBatisutils.getSqlSession();
        studentDao = sqlSession.getMapper(IStudentDao.class);
    }

    @After
    public void close() {
        MyBatisutils.closeSqlSession();
    }

    @Test
    public void test01() {
        List<Student> students = studentDao.selstuByParams("", null);
        System.out.println(students);
    }

    @Test
    public void test02() {
        Student stu1 = new Student();
        Student stu2 = new Student();
        stu1.setId(1);
        stu2.setId(5);

        List<Student> ids = new ArrayList<Student>();
        ids.add(stu1);
        ids.add(stu2);

        List<Student> students = studentDao.selStuByForeachGenerics(ids);
        System.out.println(students);
    }
}
