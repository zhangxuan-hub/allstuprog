package com.zx.utils;

import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisutils {
    private static SqlSession sqlSession;

    public static SqlSession getSqlSession() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis.xml");
            if (sqlSession == null) {
                sqlSession = new SqlSessionFactoryBuilder().build(is).openSession();
                return sqlSession;
            } else {
                return sqlSession;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void closeSqlSession() {
        if (sqlSession != null) {
            sqlSession.close();
        }
        sqlSession = null;
    }
}
