package com.example.maart.stronglife2;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.maart.stronglife2.StrongLifeDbSchema.MembershipTable;

import java.util.Date;

public class PaymentGateway extends AppCompatActivity {

    private Button purchaseBtn;

    public static final String TAG = "PAYMENTGATEWAY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_gateway);

        purchaseBtn = (Button) findViewById(R.id.purchasebtn);

        final int membership = getIntent().getIntExtra(PurchaseNewMembership.MEMBERSHIP, 0);
        final SQLiteDatabase mDatabase = new StrongLifeDbHelper(getApplicationContext()).getWritableDatabase();

        Log.d(TAG, "Membership is: " + membership);

        purchaseBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                ContentValues data = new ContentValues();
                data.put(MembershipTable.Cols.USERID, 1);
                data.put(MembershipTable.Cols.ACTIVE, true);
                Date expire = new Date();
                data.put(MembershipTable.Cols.EXPIRYDATE, expire.toString());
                data.put(MembershipTable.Cols.MEMBERSHIPTYPE, membership);
                data.put(MembershipTable.Cols.PURCHASEDATE, expire.toString());
                data.put(MembershipTable.Cols.MEMBERSHIPID, 1);

                try{
                    int rowsEffected = mDatabase.update(MembershipTable.name, data, "_id=" + 1, null);
                    if (rowsEffected == 0) {
                        mDatabase.insert(MembershipTable.name, null, data);
                    }
                }
                catch (Exception e){
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_CANCELED, returnIntent);
                    finish();
                    String error =  e.getMessage().toString();
                }

                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
//                Intent intent = new Intent(PaymentGateway.this, MyMembership.class);
//                intent.putExtra(PurchaseNewMembership.MEMBERSHIP, membership);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
            }
        });

    }
}
