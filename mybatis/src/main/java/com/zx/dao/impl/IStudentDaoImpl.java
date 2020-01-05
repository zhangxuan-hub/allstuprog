package com.zx.dao.impl;

import com.zx.beans.Student;
import com.zx.dao.IStudentDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class IStudentDaoImpl implements IStudentDao {
    @Override
    public void insertStudent(Student student) {
        try {
            //1.加载主配置文件
            InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
            //2.创建sqlSessionFactory对象
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //3.创建sessionFactory对象
            SqlSession sqlSession = sqlSessionFactory.openSession();
            //4.进行相关操作
            sqlSession.insert("insertStudent", student);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
