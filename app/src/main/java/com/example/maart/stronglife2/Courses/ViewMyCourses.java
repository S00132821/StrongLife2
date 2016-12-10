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

    final static String VIEWMYCOURSESTAG = "COURSEADAPTER";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_courses);

        mBack = (Button) findViewById(R.id.backbtn);

        mList = (RecyclerView) findViewById(R.id.listView);

        mList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        final SQLiteDatabase mDatabase = new StrongLifeDbHelper(getApplicationContext()).getWritableDatabase();
//
//        Cursor cursor = mDatabase.query(
//                StrongLifeDbSchema.MyCoursesTable.name,
//                null,
//                null,
//                null,
//                null,
//                null,
//                null
//        );

        Cursor cursor = mDatabase.rawQuery("select * from " + StrongLifeDbSchema.CoursesTable.name +
                " where " + StrongLifeDbSchema.CoursesTable.Cols.COURSEID + " in " +
                "(select " + StrongLifeDbSchema.MyCoursesTable.Cols.COURSEID +
                " from " + StrongLifeDbSchema.MyCoursesTable.name + ")", new String[]{});


        List<String> classList = new ArrayList<String>();

        try {
            if (cursor.getCount() == 0) {
                Log.d(VIEWMYCOURSESTAG, "Found no values");
            } else {
                cursor.moveToFirst();
                while ( !cursor.isAfterLast()) {
                    String className = cursor.getString(
                            cursor.getColumnIndex(StrongLifeDbSchema.CoursesTable.Cols.COURSENAME)
                    );
                    Log.d(VIEWMYCOURSESTAG, className);
                    classList.add(className);
                    cursor.moveToNext();
                }
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
