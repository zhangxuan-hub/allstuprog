package com.zx.one2many.beans;

import java.util.Set;

/**
 * @ClassName Counter
 * @Author Administrator
 * @Description TODO
 * @Date 2020/1/13 8:23
 * @Version 1.0
 */
public class Country {
    private Integer cId;
    private String cName;
    //关联属性
    private Set<Minister> ministers;

    public Country() {
    }

    public Country(Integer cId, String cName, Set<Minister> ministers) {
        this.cId = cId;
        this.cName = cName;
        this.ministers = ministers;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public Set<Minister> getMinisters() {
        return ministers;
    }

    public void setMinisters(Set<Minister> ministers) {
        this.ministers = ministers;
    }

    @Override
    public String toString() {
        return "Counter{" +
                "cId=" + cId +
                ", cName='" + cName + '\'' +
                ", ministers=" + ministers +
                '}';
    }
}
