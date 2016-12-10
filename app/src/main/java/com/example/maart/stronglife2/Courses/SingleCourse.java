package com.example.maart.stronglife2.Courses;

import java.util.Date;

/**
 * Created by maart on 30/08/2016.
 */
public class SingleCourse {

    private int mCourseId;
    private String mCourseName;
    private Date mTime;
    private Date mCourseDate;
    private int mUserId;

    public SingleCourse(int courseId, String className, Date time, Date classDate, int userId) {
        mCourseId = courseId;
        mCourseName = className;
        mTime = time;
        mCourseDate = classDate;
        mUserId = userId;
    }

    public int getmCourseId() {
        return mCourseId;
    }

    public String getmCourseName() {
        return mCourseName;
    }

    public void setmCourseName(String mClassName) {
        this.mCourseName = mClassName;
    }

    public Date getmTime() {
        return mTime;
    }

    public void setmTime(Date mTime) {
        this.mTime = mTime;
    }

    public Date getmCourseDate() {
        return mCourseDate;
    }

    public void setmCourseDate(Date mClassDate) {
        this.mCourseDate = mClassDate;
    }

    public int getmUserId() {
        return mUserId;
    }
}
