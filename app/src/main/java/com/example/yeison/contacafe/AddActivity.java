package com.example.yeison.contacafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yeison.contacafe.models.Finca;
import com.example.yeison.contacafe.util.AppUtil;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String KEY_POS="pos";
    Button btn;
    EditText edit;
    int pos = -1;
    Finca finca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btn = (Button) findViewById(R.id.btn);
        edit = (EditText) findViewById(R.id.name);

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            pos = extras.getInt(KEY_POS);
            getSupportActionBar().setTitle("Editar Finca");
            finca = AppUtil.data_finca.get(pos);
            edit.setText(finca.getNombre());
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        //noinspection SimplifiableIfStatement
        if(item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        String name =edit.getText().toString();
        if(pos==-1) {
            Finca p = new Finca(name);
            AppUtil.data_finca.add(p);
        }else{
            finca.setNombre(name);
        }
        finish();

    }
}
