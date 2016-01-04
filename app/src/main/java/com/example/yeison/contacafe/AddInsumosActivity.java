package com.example.yeison.contacafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yeison.contacafe.models.Insumos;
import com.example.yeison.contacafe.parse.InsumosParse;
import com.example.yeison.contacafe.util.AppUtil;

import java.util.List;

public class AddInsumosActivity extends AppCompatActivity implements InsumosParse.InsumosParseI, View.OnClickListener {
    public static final String KEY_POS="pos";
    Button btn_add_insumo;
    EditText insumo_add, costo_insumo_add;
    InsumosParse parse;
    int pos = -1;
    Insumos model;
    int idpos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_insumos);

        btn_add_insumo = (Button) findViewById(R.id.btn_add_insumo);
        insumo_add = (EditText) findViewById(R.id.add_insumo);
        costo_insumo_add = (EditText) findViewById(R.id.add_costo_insumo);

        parse = new InsumosParse(this);
        model = new Insumos();

        idpos = getIntent().getIntExtra(KEY_POS,pos);
        Toast.makeText(this, "valor de idpos : " + idpos, Toast.LENGTH_SHORT).show();
        if(idpos > -1){
            Toast.makeText(this, "Entra a editar y carga : "+idpos, Toast.LENGTH_SHORT).show();
            //idt para capturar el id del trabajador
            String ids= AppUtil.data_insumos.get(idpos).getIdInsumo();
            pos= idpos; //para asociar la posicion con el insumo
            //parse.getSemanaById(ids);
            model =AppUtil.data_insumos.get(idpos);
            insumo_add.setText(model.getNombreInsumo());
            costo_insumo_add.setText(""+model.getCostoInsumo());

        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn_add_insumo.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_insumos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void done(boolean success) {
        if(success==true){
            Toast.makeText(this, "Insertado o modificado : ", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "ACCION NO REALIZADA : ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void resultSemana(boolean success, Insumos semana) {

    }

    @Override
    public void resultListSemana(boolean success, List<Insumos> semana) {

    }

    @Override
    public void onClick(View v) {
        String name =insumo_add.getText().toString();
        double costo = Double.parseDouble(costo_insumo_add.getText().toString());
        if(pos==-1) {
            Insumos p = new Insumos(name,costo);

            parse.insertInsumo(p);

            Toast.makeText(this, "Insertar : " + pos, Toast.LENGTH_SHORT).show();


        }else{

            model.setIdInsumo(model.getIdInsumo());
            model.setNombreInsumo(name);
            model.setCostoInsumo(costo);
            parse.updateInsumo(model);


            //workerDao.updatePlaneta(model);
            Toast.makeText(this, "Modificar Insumos: "+pos, Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}
