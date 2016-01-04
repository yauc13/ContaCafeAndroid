package com.example.yeison.contacafe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.parse.Parse;


public class RootActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parse.initialize(this, "rbgw6OKnrWM8z75CU637SakoLyXdSSZSO2ZRLHEk", "l7u7uiWjmVwe11OD2Wh30dOrsvPhGxor75WsSfPo");


    }

    @Override
    protected void onResume() {
        super.onResume();



        SharedPreferences preferences = getSharedPreferences(LoginActivity.PREFERENCE, MODE_PRIVATE);
        boolean login = preferences.getBoolean(LoginActivity.KEY_LOGIN, false);
        Intent intent = null;

        if(login)
            intent = new Intent(this, MainActivity.class);
        else
            intent =  new Intent(this, LoginActivity.class);

        startActivity(intent);
        finish();
    }
}
