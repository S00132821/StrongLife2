package com.example.maart.stronglife2.Courses;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maart.stronglife2.R;
import java.util.List;

/**
 * Created by maart on 19/11/2016.
 */

public class ViewMyCoursesAdapter extends RecyclerView.Adapter<ViewMyCoursesHolder> {

        private List<String> mList;

        public ViewMyCoursesAdapter(List<String> list) {
            mList = list;
        }

        @Override
        public ViewMyCoursesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.list_item_myclasses, parent, false);

            return new ViewMyCoursesHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewMyCoursesHolder holder, int position) {
            String className = mList.get(position);

            holder.title.setText(className);
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
}


