package com.zx.one2many;

import com.zx.one2many.beans.Country;
import com.zx.one2many.dao.ICountryDao;
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
    private ICountryDao countryDao;

    @Before
    public void before(){
        sqlSession = MyBatisutils.getSqlSession();
        countryDao = sqlSession.getMapper(ICountryDao.class);
    }

    @After
    public void close() {
        sqlSession.commit();
        MyBatisutils.closeSqlSession();
    }

    @Test
    public void test01() {
        Country country = countryDao.selectContryById(1);
        System.out.println(country);
    }
}
