package com.example.yeison.contacafe;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.parse.Parse;



public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView list;
    TextView txt;
    String data[];
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Parse.initialize(this, "rbgw6OKnrWM8z75CU637SakoLyXdSSZSO2ZRLHEk", "l7u7uiWjmVwe11OD2Wh30dOrsvPhGxor75WsSfPo");

        list = (ListView) findViewById(R.id.list_menu_principal);
        txt = (TextView) findViewById(R.id.txt_select_item);

        data = getResources().getStringArray(R.array.arrayMenuopciones);

        list.setOnItemClickListener(this);

        //adapter basico
        // adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);

        //adapter con template
        adapter = new ArrayAdapter<String>(this, R.layout.template_item_menu_prin, R.id.name_option, data);
        list.setAdapter(adapter);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        txt.setText(data[i]);

        switch (i){
            case 0:
                Intent intent = new Intent(this, ViewWeekActivity.class);
                startActivity(intent);
                finish();
            break;
            case 1:
                Intent intent1 = new Intent(this, ViewInsumosActivity.class);
                startActivity(intent1);
                finish();
                break;

        }
    }
}
