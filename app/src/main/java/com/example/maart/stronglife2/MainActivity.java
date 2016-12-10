package com.example.maart.stronglife2;

import android.content.ContentValues;
import android.content.Intent;
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

        final SQLiteDatabase mDatabase = new StrongLifeDbHelper(getApplicationContext()).getWritableDatabase();

        int count = 0;
        while ( count < 10 ) {
            ContentValues values = new ContentValues();
            Date created = new Date();
            values.put(StrongLifeDbSchema.NotificationsTable.Cols.NOTIFICATIONTITLE, "Notification #" + Integer.toString(count));
            values.put(StrongLifeDbSchema.NotificationsTable.Cols.CREATED, created.getTime());
            values.put(StrongLifeDbSchema.NotificationsTable.Cols.CHECKED, 0); //1 = true, 0 = false.
            values.put(StrongLifeDbSchema.NotificationsTable.Cols.NOTIFICATIONID, count);
            mDatabase.insert(StrongLifeDbSchema.NotificationsTable.name, null, values );
            count++;
        }




    }
}
