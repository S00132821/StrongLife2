package com.example.maart.stronglife2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PurchaseNewMembership extends AppCompatActivity {

    static final int WEEKLYMEMBERSHIP = 1;
    static final int MONTHLYMEMBERSHIP = 2;
    static final int YEARLYMEMBERSHIP = 3;


    private Button mWeekly;
    private Button mMonthly;
    private Button mYearly;
    private TextView notice_text;
    public static final String MEMBERSHIP = "MEMBERSHIP";
    // 0 = default error
    // 1 = WEEKLY
    // 2 = MONTHLY
    // 3 = YEARLY

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_new_membership);

        notice_text = (TextView) findViewById(R.id.notice_text);

        mWeekly = (Button) findViewById(R.id.weekly);
        mWeekly.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PurchaseNewMembership.this, PaymentGateway.class);
                intent.putExtra(MEMBERSHIP, 1);
                startActivityForResult(intent, WEEKLYMEMBERSHIP);
            }
        });

        mMonthly = (Button) findViewById(R.id.monthly);
        mMonthly.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PurchaseNewMembership.this, PaymentGateway.class);
                intent.putExtra(MEMBERSHIP, 2);
                startActivityForResult(intent, MONTHLYMEMBERSHIP);
            }
        });

        mYearly = (Button) findViewById(R.id.yearly);
        mYearly.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PurchaseNewMembership.this, PaymentGateway.class);
                intent.putExtra(MEMBERSHIP, 3);
                startActivityForResult(intent, YEARLYMEMBERSHIP);
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == WEEKLYMEMBERSHIP)
        {
            if (resultCode == RESULT_OK)
            {
                notice_text.setText("Weekly membership successfully purchased!");
            }
            else
            {
                notice_text.setText("Error when purchasing membership. Try again.");
            }
        }
        else if (requestCode == MONTHLYMEMBERSHIP)
        {
            if (resultCode == RESULT_OK)
            {
                notice_text.setText("Monthly membership successfully purchased!");
            }
            else
            {
                notice_text.setText("Error when purchasing membership. Try again.");
            }
        }
        else if (requestCode == YEARLYMEMBERSHIP)
        {
            if (resultCode == RESULT_OK)
            {
                notice_text.setText("Yearly membership successfully purchased!");
            }
            else
            {
                notice_text.setText("Error when purchasing membership. Try again.");
            }
        }

    }

}
