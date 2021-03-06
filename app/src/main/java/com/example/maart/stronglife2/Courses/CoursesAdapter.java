package com.example.maart.stronglife2.Courses;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.maart.stronglife2.R;
import com.example.maart.stronglife2.StrongLifeDbHelper;
import com.example.maart.stronglife2.StrongLifeDbSchema;

import java.util.Date;
import java.util.List;


public class CoursesAdapter extends RecyclerView.Adapter<CoursesHolder> {

    private List<SingleCourse> mList;
    Context mContext;

    public CoursesAdapter(List<SingleCourse> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public CoursesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_courses, parent, false);

        return new CoursesHolder(view);
    }

    @Override
    public void onBindViewHolder(CoursesHolder holder, int position) {
        final SingleCourse singleCourse = mList.get(position);

        holder.title.setText(singleCourse.getmCourseName());
        holder.date.setText(singleCourse.getmCourseDate().toString());

        holder.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // replace with code that creates the row in the database for that user
                // to signup them up to the list of classes
                final SQLiteDatabase mDatabase = new StrongLifeDbHelper(mContext).getWritableDatabase();


                ContentValues data = new ContentValues();
                Log.d("COURSEADAPTER", "creating mycourse row");

                String statement = "select 1 from " +
                        StrongLifeDbSchema.MyCoursesTable.name +
                    " where " + StrongLifeDbSchema.MyCoursesTable.Cols.COURSEID + "=?";
                Cursor cursorMyCourses = mDatabase.rawQuery(statement,
                        new String[] { Integer.toString(singleCourse.getmCourseId()) });


                boolean exists = cursorMyCourses.getCount() > 0;
                if ( !exists ) {
                    Date created = new Date();
                    data.put(StrongLifeDbSchema.MyCoursesTable.Cols.MYCOURSEID, 0);
                    data.put(StrongLifeDbSchema.MyCoursesTable.Cols.COURSEID, singleCourse.getmCourseId());
                    data.put(StrongLifeDbSchema.MyCoursesTable.Cols.CREATED, created.getTime());

                    try {
                        Log.d("COURSEADAPTER", "inserting course row");
                        mDatabase.insert(StrongLifeDbSchema.MyCoursesTable.name, null, data);
                        Toast.makeText(view.getContext(), "You've Signed up", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        String error = e.getMessage().toString();
                    }
                } else {
                    Toast.makeText(view.getContext(), "Your already signed up", Toast.LENGTH_LONG).show();
                }

                cursorMyCourses.close();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
