package com.zx.many2one;

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
    private IMinisterDao ministerDao;

    @Before
    public void before(){
        sqlSession = MyBatisutils.getSqlSession();
        ministerDao = sqlSession.getMapper(IMinisterDao.class);
    }

    @After
    public void close() {
        sqlSession.commit();
        MyBatisutils.closeSqlSession();
    }

    @Test
    public void test01() {
        Minister minister = ministerDao.selectMinisterById(2);
        System.out.println(minister);
    }
}
