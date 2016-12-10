package com.example.maart.stronglife2.Courses;


import android.content.ContentValues;
import android.content.Context;
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


public class CoursesAdapter extends RecyclerView.Adapter<ViewMyCoursesHolder> {

    private List<SingleCourse> mList;
    Context mContext;

    public CoursesAdapter(List<SingleCourse> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public ViewMyCoursesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_courses, parent, false);

        return new ViewMyCoursesHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewMyCoursesHolder holder, int position) {
        SingleCourse singleCourse = mList.get(position);

        holder.title.setText(singleCourse.getmClassName());
        holder.date.setText(singleCourse.getmClassDate().toString());

        holder.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // replace with code that creates the row in the database for that user
                // to signup them up to the list of classes
                final SQLiteDatabase mDatabase = new StrongLifeDbHelper(mContext).getWritableDatabase();

                Toast.makeText(view.getContext(), "You've Signed up", Toast.LENGTH_LONG).show();

                ContentValues data = new ContentValues();
                Log.d("COURSEADAPTER", "creating course row");

                data.put(StrongLifeDbSchema.CoursesTable.Cols.COURSEID, 1);
                data.put(StrongLifeDbSchema.CoursesTable.Cols.USERID, 1);
                data.put(StrongLifeDbSchema.CoursesTable.Cols.COURSENAME, "Course Example" );
                Log.d("COURSEADAPTER", "creating date for row");
                Date today = new Date();
                data.put(StrongLifeDbSchema.CoursesTable.Cols.TIME, today.toString());
                Log.d("COURSEADAPTER", "creating coursedate for row");
                data.put(StrongLifeDbSchema.CoursesTable.Cols.COURSEDATE, today.toString());

                try{
                    Log.d("COURSEADAPTER", "inserting course row");
                    mDatabase.insert(StrongLifeDbSchema.CoursesTable.name, null, data);

                }
                catch (Exception e){
                    String error =  e.getMessage().toString();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
