package com.example.yeison.contacafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yeison.contacafe.models.Semana;
import com.example.yeison.contacafe.parse.WeekParse;
import com.example.yeison.contacafe.util.AppUtil;

import java.util.List;

public class AddWeekActivity extends AppCompatActivity implements View.OnClickListener, WeekParse.SemanaParseI {

    public static final String KEY_POS="pos";
    Button btn_add_semana;
    EditText semana_add;
    WeekParse parse;
    int pos = -1;
    Semana model;
    int idpos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_week);

        btn_add_semana = (Button) findViewById(R.id.btn_add_week);
        semana_add = (EditText) findViewById(R.id.add_week);

        parse = new WeekParse(this);
        model = new Semana();



        idpos = getIntent().getIntExtra(KEY_POS,pos);
        Toast.makeText(this, "valor de idpos : "+idpos, Toast.LENGTH_SHORT).show();
        if(idpos > -1){
            Toast.makeText(this, "Entra a editar y carga : "+idpos, Toast.LENGTH_SHORT).show();
            //idt para capturar el id del trabajador
            String ids= AppUtil.data_semana.get(idpos).getIdSemana();
            pos= idpos; //revisar esta vaina
            //parse.getSemanaById(ids);
            model =AppUtil.data_semana.get(idpos);
            semana_add.setText(model.getNombreSemana());

        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn_add_semana.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_week, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        String name =semana_add.getText().toString();
        if(pos==-1) {
            Semana p = new Semana(name);

            parse.insertSemana(p);

            Toast.makeText(this, "Insertar : " + pos, Toast.LENGTH_SHORT).show();


        }else{

            model.setIdSemana(model.getIdSemana());
            model.setNombreSemana(name);
            parse.updateSemana(model);


            //workerDao.updatePlaneta(model);
            Toast.makeText(this, "Modificar Trabajador: "+pos, Toast.LENGTH_SHORT).show();
        }
        finish();
    }



    @Override //recibe confirmacion de los insert, update, delete
    public void done(boolean success) {
        if(success==true){
            Toast.makeText(this, "USUARIO Insertado o modificado : ", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "ACCION NO REALIZADA : ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void resultSemana(boolean success, Semana semana) {

    }

    @Override
    public void resultListSemana(boolean success, List<Semana> semana) {

    }
}
