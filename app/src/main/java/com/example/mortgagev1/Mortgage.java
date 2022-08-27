package com.example.mortgagev1;
import java.text.DecimalFormat;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class Mortgage {
    public final DecimalFormat MONEY
            = new DecimalFormat( "$#,##0.00" );

    //creating the key string names
    private static final String PREFERENCE_AMOUNT = "amount";
    private static final String PREFERENCE_YEARS = "years";
    private static final String PREFERENCE_RATE = "rate";

    private float amount;
    private int years;
    private float rate;

    public Mortgage( ) {
        setAmount( 100000.0f );
        setYears( 30 );
        setRate( 0.035f );
    }

    //making a second constructor for persistence
    public Mortgage (Context context)
    {
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(context);

       //this constructor reads in the saved data from pref object
       //or sets the default back to the default numbers int the second arg
        // if it cannot find the key value strings (doesn't exist yet)
      setAmount(pref.getFloat(PREFERENCE_AMOUNT, 100000.0f));
      setYears(pref.getInt(PREFERENCE_YEARS,30));
      setRate(pref.getFloat(PREFERENCE_RATE,0.035f));
    }

    //a method we defined to set our preferences to be saved
    public void setPreferences(Context context)
    {
        //get a shared preferences editor
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();

        //write the data with our key value pairs (string and the user input data)
        editor.putFloat(PREFERENCE_AMOUNT, amount);
        editor.putInt(PREFERENCE_YEARS,years);
        editor.putFloat(PREFERENCE_RATE,rate);
        //needs a commit
        editor.commit();
    }

    public void setAmount( float newAmount ) {
        if( newAmount >= 0 )
            amount = newAmount;
    }
    public void setYears( int newYears ) {
        if( newYears >= 0 )
            years = newYears;
    }

    public void setRate( float newRate ) {
        if( newRate >= 0 )
            rate = newRate;
    }

    public float getAmount( ) {
        return amount;
    }

    public String getFormattedAmount( ) {
        return MONEY.format( amount );
    }

    public int getYears( ) {
        return years;
    }

    public float getRate( ) {
        return rate;
    }

    public float monthlyPayment( ) {
        float mRate = rate / 12;  // monthly interest rate
        double temp = Math.pow( 1/( 1 + mRate ), years * 12 );
        return amount * mRate / ( float ) ( 1 - temp );
    }

    public String formattedMonthlyPayment( ) {
        return MONEY.format( monthlyPayment( ) );
    }

    public float totalPayment( ) {
        return monthlyPayment( ) * years * 12;
    }

    public String formattedTotalPayment( ) {
        return MONEY.format( totalPayment( ) );
    }
}


