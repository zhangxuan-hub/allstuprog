package com.zx.oneself;

import com.zx.many2one.beans.Minister;
import com.zx.many2one.dao.IMinisterDao;
import com.zx.utils.MyBatisutils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @ClassName AppTest
 * @Author Administrator
 * @Description TODO
 * @Date 2020/1/13 8:35
 * @Version 1.0
 */
public class AppTest {
    private SqlSession sqlSession;

    @Before
    public void before(){
        sqlSession = MyBatisutils.getSqlSession();
    }

    @After
    public void close() {
        sqlSession.commit();
        MyBatisutils.closeSqlSession();
    }

    @Test
    public void test01() {
    }
}
