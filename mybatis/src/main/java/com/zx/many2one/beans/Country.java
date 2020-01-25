package com.zx.many2one.beans;

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

    public Country() {
    }

    public Country(Integer cId, String cName) {
        this.cId = cId;
        this.cName = cName;
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

    @Override
    public String toString() {
        return "Country{" +
                "cId=" + cId +
                ", cName='" + cName + '\'' +
                '}';
    }
}
