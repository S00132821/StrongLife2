package com.example.maart.stronglife2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

import static com.example.maart.stronglife2.StrongLifeDbSchema.*;

public class StrongLifeDbHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "strongLifeDataBase.db";


    public StrongLifeDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create Tables
        db.execSQL("create table " + UsersTable.name + "(" +
                " _id integer primary key autoincrement, " +
                UsersTable.Cols.USERID + " INTEGER, " +
                UsersTable.Cols.FIRSTNAME + " VARCHAR(255), " +
                UsersTable.Cols.LASTNAME + " VARCHAR(255), " +
                UsersTable.Cols.ADDRESSLINE1 + " VARCHAR(255), " +
                UsersTable.Cols.CITY + " VARCHAR(100), " +
                UsersTable.Cols.COUNTY + " VARCHAR(100), " +
                UsersTable.Cols.ZIPCODE + " INTEGER, " +
                UsersTable.Cols.PHONENUMBER + " INTEGER, " +
                UsersTable.Cols.EMAIL + " VARCHAR(100), " +
                UsersTable.Cols.PASSWORD + " CHAR(100), " +
                UsersTable.Cols.ISSTUDENT + " BOOLEAN)");

        db.execSQL("create table " + MembershipTable.name + "(" +
                " _id integer primary key autoincrement, " +
                MembershipTable.Cols.MEMBERSHIPID + " integer, " +
                MembershipTable.Cols.ACTIVE + " boolean, " +
                MembershipTable.Cols.PURCHASEDATE + " date, " +
                MembershipTable.Cols.EXPIRYDATE + " date, " +
                MembershipTable.Cols.MEMBERSHIPTYPE + " integer, " +
                MembershipTable.Cols.USERID + " integer," +
                "FOREIGN KEY(" + MembershipTable.Cols.USERID + ") REFERENCES " + UsersTable.name + "(id) )");


        db.execSQL("create table " + CoursesTable.name + "(" +
                " _id integer primary key autoincrement, " +
                CoursesTable.Cols.COURSEID + " INTEGER, " +
                CoursesTable.Cols.COURSENAME + " VARCHAR(255), " +
                CoursesTable.Cols.COURSEDATE + " DATE, " +
                CoursesTable.Cols.TIME + " DATETIME, " +
                CoursesTable.Cols.USERID + " INTEGER, " +
                "FOREIGN KEY(" + CoursesTable.Cols.USERID + ") REFERENCES " + UsersTable.name + "(id) )");


        db.execSQL("create table " + CoursesDetailsTable.name + "(" +
                " _id integer primary key autoincrement, " +
                CoursesDetailsTable.Cols.COURSEID + " INTEGER, " +
                CoursesDetailsTable.Cols.NAMEOFCOURSE + " VARCHAR(255), " +
                CoursesDetailsTable.Cols.DATEOFCOURSE + " DATE, " +
                CoursesDetailsTable.Cols.TIMEOFCOURSE + " DATETIME, " +
                CoursesDetailsTable.Cols.COURSESDETAILSID + " INTEGER )");

        db.execSQL("create table " + NotificationsTable.name + "(" +
                " _id integer primary key autoincrement, " +
                NotificationsTable.Cols.NOTIFICATIONID + " INTEGER, " +
                NotificationsTable.Cols.NOTIFICATIONTITLE + " VARCHAR(255), " +
                NotificationsTable.Cols.CREATED + " DATE, " +
                NotificationsTable.Cols.CHECKED + " BOOLEAN )");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
