package com.example.maart.stronglife2.Courses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.maart.stronglife2.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UpcomingCourses extends AppCompatActivity {


    private Button mBack;
    private RecyclerView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_classes);

        mBack = (Button) findViewById(R.id.backbtn);
        mList = (RecyclerView) findViewById(R.id.listView);

        mList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        List<SingleCourse> classList = new ArrayList<SingleCourse>();

        for (int i = 0; i < 20; i++) {
            SingleCourse singleCourse = new SingleCourse(i, "Class #" + i, new Date(), new Date(), 1);
            classList.add(singleCourse);
        }


        CoursesAdapter mAdapter = new CoursesAdapter(classList, getApplicationContext()); // List of info from database
        mList.setAdapter(mAdapter);

        mBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
