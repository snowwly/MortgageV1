package com.example.mortgagev1;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;


public class DataActivity extends AppCompatActivity {

    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_data);
        updateView();


    }

    public void updateView()
    {
        Mortgage mortgage = MainActivity.mortgage;

        if( mortgage.getYears( ) == 10 ) {
            RadioButton rb10 = ( RadioButton ) findViewById( R.id.ten );
            rb10.setChecked( true );
        } else if( mortgage.getYears( ) == 15 ) {
        RadioButton rb15 =
                ( RadioButton ) findViewById( R.id.fifteen );
        rb15.setChecked( true );

        }

        EditText amountET = ( EditText ) findViewById( R.id.data_amount );
        amountET.setText( "" + mortgage.getAmount( ) );

        EditText interestRateET = ( EditText ) findViewById( R.id.data_rate );
        interestRateET.setText( "" + mortgage.getRate( ) );



    }

    //called in goBack() method so that user has finished inputting their data
    //so we can set it.
    public void updateMortgageObject( ) {
        Mortgage mortgage = MainActivity.mortgage;
        RadioButton rb10 = ( RadioButton ) findViewById( R.id.ten );
        RadioButton rb15 = ( RadioButton ) findViewById( R.id.fifteen );
        int years = 30;
        if( rb10.isChecked( ) )
            years = 10;
        else if ( rb15.isChecked( ) )
            years = 15;
        mortgage.setYears( years );
        EditText amountET = ( EditText ) findViewById( R.id.data_amount );
        String amountString = amountET.getText( ).toString( );

        EditText interestRateET = ( EditText ) findViewById( R.id.data_rate);
        String rateString = interestRateET.getText( ).toString( );

        try {

            //setting the mortgage object with the user input
            float amount = Float.parseFloat( amountString );
            mortgage.setAmount( amount );

            float rate = Float.parseFloat( rateString );
            mortgage.setRate( rate );

            //save the preferences now that user has inputted
            mortgage.setPreferences(this);


        } catch( NumberFormatException nfe ) {
            mortgage.setAmount( 100000.0f );
            mortgage.setRate( .035f );
        }
    }


    public void goBack (View v)
    {
        updateMortgageObject();
       this.finish();
       overridePendingTransition(R.anim.fade_in_and_scale,0);
    }



}

