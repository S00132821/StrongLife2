package com.example.maart.stronglife2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Notifications extends AppCompatActivity {

    private Button backbtn;
    private RecyclerView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        backbtn = (Button) findViewById(R.id.backbtn);

        mList = (RecyclerView) findViewById(R.id.listView);

        mList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.notifications_list, listitems);


        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(itemsAdapter);




        final SQLiteDatabase mDatabase = new StrongLifeDbHelper(getApplicationContext()).getWritableDatabase();

        Cursor cursor = mDatabase.query(
                StrongLifeDbSchema.NotificationsTable.name,
                null,
                null,
                null,
                null,
                null,
                null
        );


        List<String> notificationsList = new ArrayList<String>();

        try {
            if (cursor.getCount() == 0) {
                Log.d("NOTIFICATIONSADAPTER", "Found no values");
            } else {
                cursor.moveToFirst();
                while ( !cursor.isAfterLast()) {
                    String notificationsTitle = cursor.getString(
                            cursor.getColumnIndex(StrongLifeDbSchema.NotificationsTable.Cols.NOTIFICATIONTITLE)
                    );


                    Log.d("NOTIFICATIONSADAPTER", notificationsTitle);
                    notificationsList.add(notificationsTitle);
                    cursor.moveToNext();
                }
            }

        } finally {
            cursor.close();
        }

        NotificationsAdapter mAdapter = new NotificationsAdapter(notificationsList); // List of info from database
        mList.setAdapter(mAdapter);


        backbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
