package com.example.mortgagev1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;



//import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    public static Mortgage mortgage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mortgage = new Mortgage();
        //it needs context passed in now to use the new constructor
        mortgage = new Mortgage(this);
        setContentView(R.layout.activity_main);
    }

    public void updateView()
    {
        TextView amountTV = ( TextView ) findViewById( R.id.amount );
        amountTV.setText( mortgage.getFormattedAmount( ) );
        TextView yearsTV = ( TextView ) findViewById( R.id.years );
        yearsTV.setText( "" + mortgage.getYears( ) );

        TextView rateTV = ( TextView ) findViewById( R.id.rate );
        rateTV.setText( "" + mortgage.getRate( )  );


        TextView monthlyPaymentTV = ( TextView ) findViewById( R.id.monthly_payment );
        monthlyPaymentTV.setText( "" + mortgage.formattedMonthlyPayment() );
        TextView totalPaymentTV = ( TextView ) findViewById( R.id.total_payment );
        totalPaymentTV.setText( "" + mortgage.formattedTotalPayment() );

    }

    public void onStart()
    {
        super.onStart();
        updateView();
    }

    public void modifyData( View v )
    {
        Intent myIntent = new Intent(this, DataActivity.class);
        this.startActivity(myIntent);
        overridePendingTransition(R.anim.slide_from_left,0);
    }
}
