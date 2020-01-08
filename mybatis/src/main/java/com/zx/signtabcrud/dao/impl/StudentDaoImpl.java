package com.zx.signtabcrud.dao.impl;

import com.zx.signtabcrud.beans.Student;
import com.zx.signtabcrud.dao.IStudentDao;
import com.zx.utils.MyBatisutils;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDaoImpl implements IStudentDao {
    private static SqlSession sqlSession;

    static {
        sqlSession = MyBatisutils.getSqlSession();
    }

    @Override
    public void insertStu(Student student) {
        sqlSession.insert("insertStudent", student);
        sqlSession.commit();
        MyBatisutils.closeSqlSession();
    }

    @Override
    public int insertStuCatchId(Student student) {
        int id = sqlSession.insert("insertStuCatchId", student);
        sqlSession.commit();
        MyBatisutils.closeSqlSession();
        return id;
    }

    @Override
    public void delStudById(int id) {
        sqlSession.delete("delStudById", id);
        sqlSession.commit();
        MyBatisutils.closeSqlSession();
    }

    @Override
    public void updateStuById(Student student) {
        sqlSession.update("updateStuById", student);
        sqlSession.commit();
        MyBatisutils.closeSqlSession();
    }

    @Override
    public List<Student> selStu() {
        List<Student> students = new ArrayList<Student>();
        students = sqlSession.selectList("selstuNoParam");
        MyBatisutils.closeSqlSession();
        return students;
    }

    @Override
    public Map<String, Object> selAllStusMap() {
        Map<String,Object> map = new HashMap<String, Object>();
        map = sqlSession.selectMap("selstuNoParam", "name");
        MyBatisutils.closeSqlSession();
        return map;
    }

    @Override
    public Student selStuById(int id) {
        return sqlSession.selectOne("selStuById", id);
    }

    @Override
    public List<Student> selStudentByName(String name) {
        return sqlSession.selectList("selStudentByName", name);
    }
}
