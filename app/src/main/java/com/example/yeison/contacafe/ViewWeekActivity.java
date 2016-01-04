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

import com.example.yeison.contacafe.adapters.SemanaAdapter;
import com.example.yeison.contacafe.models.Semana;
import com.example.yeison.contacafe.parse.WeekParse;
import com.example.yeison.contacafe.util.AppUtil;
import com.parse.Parse;

import java.util.ArrayList;
import java.util.List;

public class ViewWeekActivity extends AppCompatActivity implements WeekParse.SemanaParseI, DialogInterface.OnClickListener, AdapterView.OnItemClickListener {
    WeekParse parse;
    ListView listweek;
    SemanaAdapter adapter;

    int pos;
    AlertDialog delete;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ProgressDialog dialogSemana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_week);
        dialogSemana = new ProgressDialog(this);
        dialogSemana.setMessage(getString(R.string.cargando));

       //Parse.initialize(this, "rbgw6OKnrWM8z75CU637SakoLyXdSSZSO2ZRLHEk", "l7u7uiWjmVwe11OD2Wh30dOrsvPhGxor75WsSfPo");
        listweek = (ListView) findViewById(R.id.list_view_week);

        AppUtil.data_semana = new ArrayList<>();
        adapter = new SemanaAdapter(this, AppUtil.data_semana);
        listweek.setAdapter(adapter);
        dialogSemana.show();
        parse = new WeekParse(this);
        parse.getAllSemana();

        listweek.setOnItemClickListener(this);

        registerForContextMenu(listweek);
        delete = new AlertDialog.Builder(this)
                .setTitle(R.string.titulo_eliminar)
                .setMessage(R.string.deseaeliminar)
                        //.setView()
                .setPositiveButton(getString(R.string.aceptar),this)
                .setNegativeButton(getString(R.string.cancelar),this)
                .create();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        AppUtil.data_semana = new ArrayList<>();
        adapter = new SemanaAdapter(this, AppUtil.data_semana);
        listweek.setAdapter(adapter);
        dialogSemana.show();
        parse.getAllSemana();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_week, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //aqui se captura la entrada de ingresar nuevo trabajador +
        switch(item.getItemId()) {

            case R.id.action_add_week:
                Intent intent = new Intent(this, AddWeekActivity.class);
                startActivity(intent);
                break;
            case R.id.action_logout_view_week:
                getSharedPreferences(LoginActivity.PREFERENCE,MODE_PRIVATE).edit();
                editor.putBoolean(LoginActivity.KEY_LOGIN, false);
                editor.commit();

                Intent intentlog = new Intent(this, RootActivity.class);
                startActivity(intentlog);
                finish();
                break;
        }

        if(item.getItemId() == android.R.id.home)
            finish();

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
                Intent intent =  new Intent(this, AddWeekActivity.class);
                   String idsem= AppUtil.data_semana.get(pos).getIdSemana();

                intent.putExtra(AddWeekActivity.KEY_POS, pos);
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


        if (success==true){
            Toast.makeText(this, "USUARIO ELIMINADO : ", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "USUARIO NO ELIMINADO : ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void resultSemana(boolean success, Semana semana) {

    }

    @Override
    public void resultListSemana(boolean success, List<Semana> semana) {
        dialogSemana.hide();
        for (int i=0; i<semana.size(); i++){
            AppUtil.data_semana.add(semana.get(i));
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        String id= AppUtil.data_semana.get(pos).getIdSemana();
        Semana sem= AppUtil.data_semana.get(pos);
        Toast.makeText(this, "Antes de presionar eliminar : ", Toast.LENGTH_SHORT).show();
        if(which == DialogInterface.BUTTON_POSITIVE){
            Toast.makeText(this, "Pulsado eliminar : ", Toast.LENGTH_SHORT).show();
            AppUtil.data_semana.remove(pos);
            parse.deleteSemana(sem);
            listweek.invalidateViews();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       // dialogSemana.hide();
        Log.i("Extra","pos:"+pos);
        Log.i("Extra","position:"+position);
        Intent intent = new Intent(this, ViewWorkerActivity.class);
        String idse =AppUtil.data_semana.get(position).getIdSemana();
        intent.putExtra(ViewWorkerActivity.KEY_IDSEMANA, idse);
        startActivity(intent);
        finish();
    }


}
