package com.zx.many2one.beans;

/**
 * @ClassName Minister
 * @Author Administrator
 * @Description TODO
 * @Date 2020/1/13 8:21
 * @Version 1.0
 */
public class Minister {
    private Integer mId;
    private String mName;
    //关联属性
    private Country country;

    public Minister() {
    }

    public Minister(Integer mId, String mName, Country country) {
        this.mId = mId;
        this.mName = mName;
        this.country = country;
    }

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Minister{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", country=" + country +
                '}';
    }
}
