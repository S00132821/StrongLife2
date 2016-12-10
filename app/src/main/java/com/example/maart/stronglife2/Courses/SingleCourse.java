package com.example.maart.stronglife2.Courses;

import java.util.Date;

/**
 * Created by maart on 30/08/2016.
 */
public class SingleCourse {

    private int mClassId;
    private String mClassName;
    private Date mTime;
    private Date mClassDate;
    private int mUserId;

    public SingleCourse(int classId, String className, Date time, Date classDate, int userId) {
        mClassId = classId;
        mClassName = className;
        mTime = time;
        mClassDate = classDate;
        mUserId = userId;
    }

    public int getmClassId() {
        return mClassId;
    }

    public String getmClassName() {
        return mClassName;
    }

    public void setmClassName(String mClassName) {
        this.mClassName = mClassName;
    }

    public Date getmTime() {
        return mTime;
    }

    public void setmTime(Date mTime) {
        this.mTime = mTime;
    }

    public Date getmClassDate() {
        return mClassDate;
    }

    public void setmClassDate(Date mClassDate) {
        this.mClassDate = mClassDate;
    }

    public int getmUserId() {
        return mUserId;
    }
}
