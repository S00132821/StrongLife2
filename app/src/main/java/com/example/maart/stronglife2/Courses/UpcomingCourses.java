package com.example.maart.stronglife2.Courses;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.maart.stronglife2.R;
import com.example.maart.stronglife2.StrongLifeDbHelper;
import com.example.maart.stronglife2.StrongLifeDbSchema;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UpcomingCourses extends AppCompatActivity {


    private Button mBack;
    private RecyclerView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_courses);

        mBack = (Button) findViewById(R.id.backbtn);
        mList = (RecyclerView) findViewById(R.id.listView);

        mList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        List<SingleCourse> courseList = new ArrayList<SingleCourse>();

        final SQLiteDatabase mDatabase = new StrongLifeDbHelper(getApplicationContext()).getWritableDatabase();
        Cursor cursor = mDatabase.query(
                StrongLifeDbSchema.CoursesTable.name,
                null,
                null,
                null,
                null,
                null,
                null
        );

        try {
            if (cursor.getCount() == 0) {
            } else {
                cursor.moveToFirst();
                while ( !cursor.isAfterLast()) {
                    String courseTitle = cursor.getString(
                            cursor.getColumnIndex(StrongLifeDbSchema.CoursesTable.Cols.COURSENAME)
                    );


                    Integer courseId = cursor.getInt
                            (cursor.getColumnIndex(StrongLifeDbSchema.CoursesTable.Cols.COURSEID));

                    SingleCourse singleCourse = new SingleCourse(0, courseTitle, new Date(), new Date(), 1);

                    courseList.add(singleCourse);
                    cursor.moveToNext();
                }
            }

        } finally {
            cursor.close();
        }

        CoursesAdapter mAdapter = new CoursesAdapter(courseList, getApplicationContext()); // List of info from database
        mList.setAdapter(mAdapter);

        mBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
