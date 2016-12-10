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
import java.util.List;

public class ViewMyCourses extends AppCompatActivity {

    private Button mBack;
    private RecyclerView mList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_classes);

        mBack = (Button) findViewById(R.id.backbtn);

        mList = (RecyclerView) findViewById(R.id.listView);

        mList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


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

        List<String> classList = new ArrayList<String>();

        try {
            if (cursor.getCount() == 0) {
                Log.d("COURSEADAPTER", "Found no values");
            } else {
                cursor.moveToFirst();
                String className = cursor.getString(
                        cursor.getColumnIndex(StrongLifeDbSchema.CoursesTable.Cols.COURSENAME)
                );
                Log.d("COURSEADAPTER", className);
                classList.add(className);
            }

        } finally {
            cursor.close();
        }


        ViewMyCoursesAdapter mAdapter = new ViewMyCoursesAdapter(classList); // List of info from database
        mList.setAdapter(mAdapter);

        mBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}