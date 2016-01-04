package com.example.yeison.contacafe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String KEY_LOGIN="login";
    public static final String KEY_USER="user";

    public static final String PREFERENCE="preference";

    EditText usr, pass;
    Button in;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usr = (EditText) findViewById(R.id.login_usr);
        pass = (EditText) findViewById(R.id.login_pass);
        in = (Button) findViewById(R.id.login_btn);

        in.setOnClickListener(this);

        preferences = getSharedPreferences(PREFERENCE,MODE_PRIVATE);
        editor =preferences.edit();

    }


    @Override
    public void onClick(View v) {

        String user = usr.getText().toString();
        String password = pass.getText().toString();

        if (user.equals("yauc13") && password.equals("123")) {
            editor.putBoolean(KEY_LOGIN, true);
            editor.putString(KEY_USER, usr.getText().toString());
            editor.commit();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else{
            Toast.makeText(this, "ERROR EN LOS DATOS", Toast.LENGTH_SHORT).show();
        }
    }


}
