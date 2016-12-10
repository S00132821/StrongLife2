package com.example.maart.stronglife2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyMembership extends AppCompatActivity {

    private Button mBack;

    private TextView membershiptype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_membership);

        membershiptype = (TextView) findViewById(R.id.membershiptype);

        mBack = (Button) findViewById(R.id.back);

        // retrieve information from the database instead of intent
        int membership = getIntent().getIntExtra(PurchaseNewMembership.MEMBERSHIP, 0);

        setMembership(membership);

        SQLiteDatabase mDatabase = new StrongLifeDbHelper(getApplicationContext()).getWritableDatabase();

        Cursor cursor = mDatabase.query(
                StrongLifeDbSchema.MembershipTable.name,
                null,
                "_id = 1",
                null,
                null,
                null,
                null
        );

        try {
            if (cursor.getCount() == 0) {
                membershiptype.setText("No Membership Purchased");
            } else {
                cursor.moveToFirst();
                int membershipDatabase = cursor.getInt(
                        cursor.getColumnIndex(StrongLifeDbSchema.MembershipTable.Cols.MEMBERSHIPTYPE)
                );
                setMembership(membershipDatabase);
        }

        } finally {
            cursor.close();
        }

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyMembership.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(MyMembership.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void setMembership(int membership) {
        switch (membership) {
            case 0:
                membershiptype.setText("No Membership Purchased");
                break;
            case 1:
                membershiptype.setText("Weekly Membership");
                break;
            case 2:
                membershiptype.setText("Monthly Membership");
                break;
            case 3:
                membershiptype.setText("Yearly Membership");
                break;
            default:
                break;
        }
    }
}
