package com.example.maart.stronglife2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by maart on 24/11/2016.
 */

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsHolder> {

    private List<String> mList;

    public NotificationsAdapter(List<String> list) {
        mList = list;
    }

    @Override
    public NotificationsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_notifications, parent, false);

        return new NotificationsHolder(view);
    }

    @Override
    public void onBindViewHolder(NotificationsHolder holder, int position) {
        String courseName = mList.get(position);

        holder.title.setText(courseName);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
