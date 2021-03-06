package com.example.maart.stronglife2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maart.stronglife2.Courses.UpcomingCourses;
import com.example.maart.stronglife2.Courses.ViewMyCourses;

import java.util.Date;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {

    private ImageView userimage;
    private TextView username;
    private Button notifications;
    private Button membershipstatus;
    private Button purchasenewmembership;
    private Button viewmyclasses;
    private Button upcomingclasses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Strong Life");

        userimage = (ImageView) findViewById(R.id.imageView);
        username = (TextView) findViewById(R.id.textView);
        notifications = (Button) findViewById(R.id.button);
        membershipstatus = (Button) findViewById(R.id.button2);
        purchasenewmembership = (Button) findViewById(R.id.button3);
        viewmyclasses = (Button) findViewById(R.id.button4);
        upcomingclasses = (Button) findViewById(R.id.button5);

        username.setText("Maarten Tiemessen"); //Instead of name, would fetch variable which queries database

        upcomingclasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UpcomingCourses.class);
                startActivity(intent);
            }

        });
        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Notifications.class);
                startActivity(intent);
            }
        });

        membershipstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyMembership.class);
                startActivity(intent);
            }
        });
        purchasenewmembership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PurchaseNewMembership.class);
                startActivity(intent);
            }
        });
        viewmyclasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewMyCourses.class);
                startActivity(intent);
            }
        });


        // Pretend fetch and fill from the internet

        Date created = new Date();
        final SQLiteDatabase mDatabase = new StrongLifeDbHelper(getApplicationContext()).getWritableDatabase();

        String notificationStatement = "select 1 from " +
                StrongLifeDbSchema.NotificationsTable.name;
        Cursor cursorNotifications = mDatabase.rawQuery(notificationStatement,
                new String[] { });

        boolean notificationsExists = cursorNotifications.getCount() > 0;

        if ( !notificationsExists ) {
            ContentValues notificationOne = new ContentValues();
            notificationOne.put(StrongLifeDbSchema.NotificationsTable.Cols.NOTIFICATIONTITLE, "New Zumba Course Up and Coming");
            notificationOne.put(StrongLifeDbSchema.NotificationsTable.Cols.CREATED, created.getTime());
            notificationOne.put(StrongLifeDbSchema.NotificationsTable.Cols.CHECKED, 0); //1 = true, 0 = false.
            notificationOne.put(StrongLifeDbSchema.NotificationsTable.Cols.NOTIFICATIONID, 1);
            mDatabase.insert(StrongLifeDbSchema.NotificationsTable.name, null, notificationOne);

            ContentValues notificationTwo = new ContentValues();
            notificationTwo.put(StrongLifeDbSchema.NotificationsTable.Cols.NOTIFICATIONTITLE, "New Gym Class Course");
            notificationTwo.put(StrongLifeDbSchema.NotificationsTable.Cols.CREATED, created.getTime());
            notificationTwo.put(StrongLifeDbSchema.NotificationsTable.Cols.CHECKED, 0); //1 = true, 0 = false.
            notificationTwo.put(StrongLifeDbSchema.NotificationsTable.Cols.NOTIFICATIONID, 1);
            mDatabase.insert(StrongLifeDbSchema.NotificationsTable.name, null, notificationTwo);

            ContentValues notificationThree = new ContentValues();
            notificationThree.put(StrongLifeDbSchema.NotificationsTable.Cols.NOTIFICATIONTITLE, "50% offer on all new sign ups in January");
            notificationThree.put(StrongLifeDbSchema.NotificationsTable.Cols.CREATED, created.getTime());
            notificationThree.put(StrongLifeDbSchema.NotificationsTable.Cols.CHECKED, 0); //1 = true, 0 = false.
            notificationThree.put(StrongLifeDbSchema.NotificationsTable.Cols.NOTIFICATIONID, 1);
            mDatabase.insert(StrongLifeDbSchema.NotificationsTable.name, null, notificationThree);
        }

        String userStatement = "select 1 from " +
                StrongLifeDbSchema.UsersTable.name;
        Cursor cursorUsers = mDatabase.rawQuery(userStatement,
                new String[] { });

        boolean userExists = cursorUsers.getCount() > 0;

        if ( !userExists ) {
            //USER
            ContentValues user = new ContentValues();

            user.put(StrongLifeDbSchema.UsersTable.Cols.USERID, 0);
            user.put(StrongLifeDbSchema.UsersTable.Cols.FIRSTNAME, "John");
            user.put(StrongLifeDbSchema.UsersTable.Cols.LASTNAME, "Smith");
            user.put(StrongLifeDbSchema.UsersTable.Cols.ADDRESSLINE1, "Main Street");
            user.put(StrongLifeDbSchema.UsersTable.Cols.CITY, "Galway");
            user.put(StrongLifeDbSchema.UsersTable.Cols.COUNTY, "Galway");
            user.put(StrongLifeDbSchema.UsersTable.Cols.ZIPCODE, "90210");
            user.put(StrongLifeDbSchema.UsersTable.Cols.PHONENUMBER, "01 0123456");
            user.put(StrongLifeDbSchema.UsersTable.Cols.ISSTUDENT, true);
            user.put(StrongLifeDbSchema.UsersTable.Cols.EMAIL, "john@example.com");
            user.put(StrongLifeDbSchema.UsersTable.Cols.PASSWORD, "examplePass");

            mDatabase.insert(StrongLifeDbSchema.UsersTable.name, null, user);
        }


        String courseStatement = "select 1 from " +
                StrongLifeDbSchema.CoursesTable.name;
        Cursor cursorCourses = mDatabase.rawQuery(courseStatement,
                new String[] { });

        boolean coursesExists = cursorCourses.getCount() > 0;
        if ( !coursesExists ) {
            //COURSE ONE
            ContentValues courseOne = new ContentValues();
            ContentValues courseDetailOne = new ContentValues();

            courseOne.put(StrongLifeDbSchema.CoursesTable.Cols.COURSEID, 0);
            courseOne.put(StrongLifeDbSchema.CoursesTable.Cols.COURSENAME, "Zumba Class");
            courseOne.put(StrongLifeDbSchema.CoursesTable.Cols.USERID, 0);

            courseDetailOne.put(StrongLifeDbSchema.CoursesDetailsTable.Cols.COURSEID, 0);
            courseDetailOne.put(StrongLifeDbSchema.CoursesDetailsTable.Cols.COURSESDETAILSID, 0);
            courseDetailOne.put(StrongLifeDbSchema.CoursesDetailsTable.Cols.DATEOFCOURSE, created.getTime());
            courseDetailOne.put(StrongLifeDbSchema.CoursesDetailsTable.Cols.TIMEOFCOURSE, created.getTime());

            mDatabase.insert(StrongLifeDbSchema.CoursesTable.name, null, courseOne);
            mDatabase.insert(StrongLifeDbSchema.CoursesDetailsTable.name, null, courseDetailOne);


            //COURSE TWO
            ContentValues courseTwo = new ContentValues();
            ContentValues courseDetailTwo = new ContentValues();

            courseTwo.put(StrongLifeDbSchema.CoursesTable.Cols.COURSEID, 1);
            courseTwo.put(StrongLifeDbSchema.CoursesTable.Cols.COURSENAME, "Kettlebell Class");
            courseTwo.put(StrongLifeDbSchema.CoursesTable.Cols.USERID, 0);

            courseDetailTwo.put(StrongLifeDbSchema.CoursesDetailsTable.Cols.COURSEID, 1);
            courseDetailTwo.put(StrongLifeDbSchema.CoursesDetailsTable.Cols.COURSESDETAILSID, 1);
            courseDetailTwo.put(StrongLifeDbSchema.CoursesDetailsTable.Cols.DATEOFCOURSE, created.getTime());
            courseDetailTwo.put(StrongLifeDbSchema.CoursesDetailsTable.Cols.TIMEOFCOURSE, created.getTime());

            mDatabase.insert(StrongLifeDbSchema.CoursesTable.name, null, courseTwo);
            mDatabase.insert(StrongLifeDbSchema.CoursesDetailsTable.name, null, courseDetailTwo);


            //COURSE THREE
            ContentValues courseThree = new ContentValues();
            ContentValues courseDetailThree = new ContentValues();

            courseThree.put(StrongLifeDbSchema.CoursesTable.Cols.COURSEID, 2);
            courseThree.put(StrongLifeDbSchema.CoursesTable.Cols.COURSENAME, "Weightlifting Class");
            courseTwo.put(StrongLifeDbSchema.CoursesTable.Cols.USERID, 0);

            courseDetailThree.put(StrongLifeDbSchema.CoursesDetailsTable.Cols.COURSEID, 2);
            courseDetailThree.put(StrongLifeDbSchema.CoursesDetailsTable.Cols.COURSESDETAILSID, 2);
            courseDetailThree.put(StrongLifeDbSchema.CoursesDetailsTable.Cols.DATEOFCOURSE, created.getTime());
            courseDetailThree.put(StrongLifeDbSchema.CoursesDetailsTable.Cols.TIMEOFCOURSE, created.getTime());

            mDatabase.insert(StrongLifeDbSchema.CoursesTable.name, null, courseThree);
            mDatabase.insert(StrongLifeDbSchema.CoursesDetailsTable.name, null, courseDetailThree);
        }

    }
}
