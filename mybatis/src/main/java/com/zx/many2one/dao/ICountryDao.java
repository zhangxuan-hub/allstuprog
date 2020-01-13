package com.zx.many2one.dao;

import com.zx.many2one.beans.Country;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName ICountryDao
 * @Author Administrator
 * @Description TODO
 * @Date 2020/1/13 8:32
 * @Version 1.0
 */
public interface ICountryDao {
    Country selectContryById(@Param("cid") int cid);
}
