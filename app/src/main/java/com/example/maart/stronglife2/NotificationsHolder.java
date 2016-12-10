package com.example.maart.stronglife2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.maart.stronglife2.R;

/**
 * Created by maart on 24/11/2016.
 */

public class NotificationsHolder extends RecyclerView.ViewHolder{

    public TextView title;


    public NotificationsHolder(View itemView) {
            super(itemView);

        title = (TextView) itemView.findViewById(R.id.title);

    }
}
