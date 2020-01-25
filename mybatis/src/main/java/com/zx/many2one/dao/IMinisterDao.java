package com.zx.many2one.dao;

import com.zx.many2one.beans.Minister;

/**
 * @ClassName IMinisterDao
 * @Author Administrator
 * @Description TODO
 * @Date 2020/1/13 9:43
 * @Version 1.0
 */
public interface IMinisterDao {
    Minister selectMinisterById(int mId);
}
