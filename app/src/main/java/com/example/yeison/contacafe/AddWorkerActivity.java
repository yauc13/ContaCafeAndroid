package com.example.yeison.contacafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yeison.contacafe.adapters.TrabajadorAdapter;

import com.example.yeison.contacafe.models.Trabajador;
import com.example.yeison.contacafe.parse.WorkerParse;
import com.example.yeison.contacafe.util.AppUtil;

import java.util.List;

public class AddWorkerActivity extends AppCompatActivity implements View.OnClickListener, WorkerParse.TrabajadorParseI {

    public static final String KEY_POS="pos";
    public static final String KEY_IDSEMANA="idsemana";
    Button btn_add;
    EditText nombre_add, lu_add, ma_add, mi_add, ju_add, vi_add, sa_add, do_add;
    int pos = -1;
    Trabajador model;
    WorkerParse parse;
    String idposem;

    TrabajadorAdapter adater;



    int idtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_worker);
        idposem = getIntent().getStringExtra(KEY_IDSEMANA); //llave foranea de idsemana
        Log.i("Id Semana", "que llega al AddWorkerActivity:" + idposem);

        btn_add = (Button) findViewById(R.id.btn_add_worked);
        nombre_add = (EditText) findViewById(R.id.add_name_worker);
        lu_add = (EditText) findViewById(R.id.add_klu);
        ma_add = (EditText) findViewById(R.id.add_kma);
        mi_add = (EditText) findViewById(R.id.add_kmi);
        ju_add = (EditText) findViewById(R.id.add_kju);
        vi_add = (EditText) findViewById(R.id.add_kvi);
        sa_add = (EditText) findViewById(R.id.add_ksa);
        do_add = (EditText) findViewById(R.id.add_kdom);

        parse = new WorkerParse(this);
        model = new Trabajador();



        idtra = getIntent().getIntExtra(KEY_POS,pos);
       // Trabajador pla = AppUtil.data_trabajador.get(pos);
       //int idpla= AppUtil.data_trabajador.get(pos).getIdtra();

        if(idtra > -1){
            //idt para capturar el id del trabajador
            pos= idtra;
            model= AppUtil.data_trabajador.get(idtra);
            //pos= idtra;
             //para saber si se edita o crea nuevo
            nombre_add.setText(model.getNombre());
            lu_add.setText(""+model.getL());
            ma_add.setText(""+model.getMa());
            mi_add.setText(""+model.getMi());
            ju_add.setText(""+model.getJ());
            vi_add.setText(""+model.getV());
            sa_add.setText(""+model.getS());
            do_add.setText(""+model.getD());
        }





        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn_add.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_worker, menu);
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
        String name =nombre_add.getText().toString();
        int klu = Integer.parseInt(lu_add.getText().toString());
        int kma = Integer.parseInt(ma_add.getText().toString());
        int kmi = Integer.parseInt(mi_add.getText().toString());
        int kju = Integer.parseInt(ju_add.getText().toString());
        int kvi = Integer.parseInt(vi_add.getText().toString());
        int ksa = Integer.parseInt(sa_add.getText().toString());
        int kdo = Integer.parseInt(do_add.getText().toString());
        String fkidsem= idposem;  //para la llave foranea idsemana



        if(pos==-1) {
            Trabajador p = new Trabajador(name, klu, kma, kmi, kju, kvi, ksa, kdo, fkidsem);
            parse.insertTrabajador(p);
            Toast.makeText(this, "Insertar Trabajador : "+pos, Toast.LENGTH_SHORT).show();
        }else{
            model.setIdtra(model.getIdtra());
            model.setNombre(name);
            model.setL(klu);
            model.setMa(kma);
            model.setMi(kmi);
            model.setJ(kju);
            model.setV(kvi);
            model.setS(ksa);
            model.setD(kdo);
            model.setIdsemana(model.getIdsemana());

           parse.updateTrabajador(model);
            Toast.makeText(this, "Modificar Trabajador: "+pos, Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    @Override
    public void done(boolean success) {

    }

    @Override
    public void resultTrabajador(boolean success, Trabajador trabajador) {

    }

    @Override
    public void resultListTrabajador(boolean success, List<Trabajador> trabajador) {

    }
}
