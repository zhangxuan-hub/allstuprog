package com.zx.selectbymap.dao;

import com.zx.signtabcrud.beans.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IStudentDao {
    List<Student> selectStuByCondition(Map<String, Object> params);

    List<Student> selstuByParams(@Param("name") String name,
                                 @Param("age") Integer age);

    List<Student> selStuByForeach(int[] ids);
    List<Student> selStuByForeachList(List<Integer> ids);
    List<Student> selStuByForeachGenerics(List<Student> stu);
}
