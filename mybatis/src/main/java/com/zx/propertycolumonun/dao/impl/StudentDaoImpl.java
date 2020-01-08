package com.zx.propertycolumonun.dao.impl;

import com.zx.propertycolumonun.dao.IStudentDao;
import com.zx.signtabcrud.beans.Student;
import com.zx.utils.MyBatisutils;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements IStudentDao {
    private static SqlSession sqlSession;

    static {
        sqlSession = MyBatisutils.getSqlSession();
    }

    @Override
    public List<Student> selStu() {
        List<Student> students = new ArrayList<Student>();
        students = sqlSession.selectList("selstuNoParam");
        MyBatisutils.closeSqlSession();
        return students;
    }

    @Override
    public Student selStuById(int id) {
        return sqlSession.selectOne("selStuById", id);
    }
}
