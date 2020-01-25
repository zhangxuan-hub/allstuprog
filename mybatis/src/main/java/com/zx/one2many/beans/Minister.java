package com.zx.one2many.beans;

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

    public Minister() {
    }

    public Minister(Integer mId, String mName) {
        this.mId = mId;
        this.mName = mName;
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

    @Override
    public String toString() {
        return "Minister{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                '}';
    }
}
