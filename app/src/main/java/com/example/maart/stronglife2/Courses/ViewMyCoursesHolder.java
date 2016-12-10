package com.example.maart.stronglife2.Courses;


//ASK ABOUT VIEWHOLDERS AGAIN!! If needed for every single table or can be used once!


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.maart.stronglife2.R;

public class ViewMyCoursesHolder extends RecyclerView.ViewHolder{

    public TextView title;
    public TextView date;
    public Button signup;

    public ViewMyCoursesHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.title);
        date = (TextView) itemView.findViewById(R.id.date);
        signup = (Button) itemView.findViewById(R.id.signup);
    }
}
