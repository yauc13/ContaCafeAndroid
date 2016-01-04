package com.example.yeison.contacafe;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yeison.contacafe.adapters.TrabajadorAdapter;
import com.example.yeison.contacafe.models.Trabajador;
import com.example.yeison.contacafe.parse.WorkerParse;
import com.example.yeison.contacafe.util.AppUtil;
import com.parse.Parse;
import java.util.ArrayList;
import java.util.List;

public class ViewWorkerActivity extends AppCompatActivity implements  WorkerParse.TrabajadorParseI, DialogInterface.OnClickListener {
    public static final String KEY_IDSEMANA="";
   // static final int PAGE=3;

    WorkerParse parse;
    ListView listworker;
    //List<Trabajador> data;
    TrabajadorAdapter adapter;
    ProgressDialog dialogTrabajador;



    int pos;
    String idpo="";
    AlertDialog delete;
    String idposem;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_worker);
        dialogTrabajador = new ProgressDialog(this);
        dialogTrabajador.setMessage(getString(R.string.cargando));
        idposem = getIntent().getStringExtra(KEY_IDSEMANA); //llave foranea de idsemana
        Log.i("Id Semana","que llega:"+idposem);

       // Parse.initialize(this, "rbgw6OKnrWM8z75CU637SakoLyXdSSZSO2ZRLHEk", "l7u7uiWjmVwe11OD2Wh30dOrsvPhGxor75WsSfPo");
        dialogTrabajador = new ProgressDialog(this);
        dialogTrabajador.setMessage("Cargando...");



        listworker = (ListView) findViewById(R.id.list_view_worker);
        //.setOnScrollListener(this);
        //listworker.setOnItemClickListener(this);

        //adapter = new TrabajadorAdapter(this, AppUtil.data_trabajador);

        AppUtil.data_trabajador = new ArrayList<>();
        adapter = new TrabajadorAdapter(this,AppUtil.data_trabajador);


        listworker.setAdapter(adapter);
        dialogTrabajador.show();
        parse = new WorkerParse(this);

        parse.getAllTrabajador(idposem);
        //parse.getTrabajadorByPages(PAGE, null);


        //listworker.setOnScrollListener(this);



        registerForContextMenu(listworker);

        delete = new AlertDialog.Builder(this)
                .setTitle(R.string.titulo_eliminar)
                .setMessage(R.string.deseaeliminar)
                        //.setView()
                .setPositiveButton(getString(R.string.aceptar),this)
                .setNegativeButton(getString(R.string.cancelar),this)
                .create();


        Log.i("notify adapterr","entro");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       adapter.notifyDataSetChanged();



    }

    @Override
    protected void onRestart() {
        super.onRestart();
        AppUtil.data_trabajador = new ArrayList<>();
        adapter = new TrabajadorAdapter(this, AppUtil.data_trabajador);
        listworker.setAdapter(adapter);
        dialogTrabajador.show();
        parse.getAllTrabajador(idposem);
        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_worker, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();

        //aqui se captura la entrada de ingresar nuevo trabajador +
        switch(item.getItemId()) {

            case R.id.action_add_worker:
                Intent intent = new Intent(this, AddWorkerActivity.class);
                intent.putExtra(AddWorkerActivity.KEY_IDSEMANA, idposem);
                startActivity(intent);
                break;
            case R.id.action_logout_view:
                getSharedPreferences(LoginActivity.PREFERENCE,MODE_PRIVATE).edit();
                editor.putBoolean(LoginActivity.KEY_LOGIN, false);
                editor.commit();

                Intent intentlog = new Intent(this, RootActivity.class);
                startActivity(intentlog);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        getMenuInflater().inflate(R.menu.menu_ctx_list, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        pos = info.position;

        switch(item.getItemId()){
            case R.id.action_edit:
                Intent intent =  new Intent(this, AddWorkerActivity.class);
                String idsem= AppUtil.data_trabajador.get(pos).getIdtra();

                intent.putExtra(AddWorkerActivity.KEY_POS, pos);
                startActivity(intent);
                break;
            case R.id.action_delete:
                delete.show();
                break;
        }

        return super.onContextItemSelected(item);
    }






    @Override
    public void done(boolean success) {

    }

    @Override
    public void resultTrabajador(boolean success, Trabajador trabajador) {

    }

    @Override
    public void resultListTrabajador(boolean success, List<Trabajador> trabajador) {
        Log.i("resultlistTrabajador","entro");
        dialogTrabajador.hide();
        Log.i("Tamanio", "data size ListTrabajador:" + trabajador.size());
        if(success==true) {
            for (int i = 0; i < trabajador.size(); i++) {
                AppUtil.data_trabajador.add(trabajador.get(i));
                // adapter.notifyDataSetChanged();
            }
            adapter.notifyDataSetChanged();
        }

    }


    @Override
    public void onClick(DialogInterface dialog, int which) {

        String id= AppUtil.data_semana.get(pos).getIdSemana();
        Trabajador sem= AppUtil.data_trabajador.get(pos);
        Toast.makeText(this, "Antes de presionar eliminar : ", Toast.LENGTH_SHORT).show();
        if(which == DialogInterface.BUTTON_POSITIVE){
            Toast.makeText(this, "Pulsado eliminar : ", Toast.LENGTH_SHORT).show();
            AppUtil.data_semana.remove(pos);
            parse.deleteTrabajador(sem);
            listworker.invalidateViews();
            adapter.notifyDataSetChanged();
        }
    }


}
