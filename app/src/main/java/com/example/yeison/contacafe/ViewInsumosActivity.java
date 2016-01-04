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

import com.example.yeison.contacafe.adapters.InsumosAdapter;
import com.example.yeison.contacafe.models.Insumos;
import com.example.yeison.contacafe.parse.InsumosParse;
import com.example.yeison.contacafe.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class ViewInsumosActivity extends AppCompatActivity implements InsumosParse.InsumosParseI, DialogInterface.OnClickListener {

    InsumosParse parse;
    ListView listinsumos;
    InsumosAdapter adapter;

    int pos;
    AlertDialog delete;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ProgressDialog dialogInsumos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_insumos);
        dialogInsumos = new ProgressDialog(this);
        dialogInsumos.setMessage(getString(R.string.cargando));

        //Parse.initialize(this, "rbgw6OKnrWM8z75CU637SakoLyXdSSZSO2ZRLHEk", "l7u7uiWjmVwe11OD2Wh30dOrsvPhGxor75WsSfPo");
        listinsumos = (ListView) findViewById(R.id.list_view_insumos);

        AppUtil.data_insumos = new ArrayList<>();
        adapter = new InsumosAdapter(this, AppUtil.data_insumos);
        listinsumos.setAdapter(adapter);
        dialogInsumos.show();
        parse = new InsumosParse(this);
        parse.getAllInsumos();

        registerForContextMenu(listinsumos);
        delete = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.titulo_eliminar))
                .setMessage(getString(R.string.deseaeliminar))
                        //.setView()
                .setPositiveButton(getString(R.string.aceptar), this)
                .setNegativeButton(getString(R.string.cancelar), this)
                .create();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        AppUtil.data_insumos = new ArrayList<>();
        adapter = new InsumosAdapter(this, AppUtil.data_insumos);
        listinsumos.setAdapter(adapter);
        dialogInsumos.show();
        parse.getAllInsumos();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_insumos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        //aqui se captura la entrada de ingresar nuevo trabajador +
        switch (item.getItemId()) {

            case R.id.action_add_insumo:
                Intent intent = new Intent(this, AddInsumosActivity.class);
                startActivity(intent);
                break;
            case R.id.action_logout_view:
                editor.putBoolean(LoginActivity.KEY_LOGIN, false);
                editor.commit();

                Intent intentlog = new Intent(this, LoginActivity.class);
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

        switch (item.getItemId()) {
            case R.id.action_edit:
                Intent intent = new Intent(this, AddInsumosActivity.class);
                String idsem = AppUtil.data_insumos.get(pos).getIdInsumo();
                intent.putExtra(AddInsumosActivity.KEY_POS, pos);
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
    public void resultSemana(boolean success, Insumos semana) {

    }

    @Override
    public void resultListSemana(boolean success, List<Insumos> insumos) {
        Log.i("resultlistInsumos", "entro");
        dialogInsumos.hide();
        Log.i("Tamanio", "data size ListInsumos:" + insumos.size());
        if (success == true) {
            for (int i = 0; i < insumos.size(); i++) {
                AppUtil.data_insumos.add(insumos.get(i));

            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        String id= AppUtil.data_semana.get(pos).getIdSemana();
        Insumos sem= AppUtil.data_insumos.get(pos);
        Toast.makeText(this, "Antes de presionar eliminar : ", Toast.LENGTH_SHORT).show();
        if(which == DialogInterface.BUTTON_POSITIVE){
            Toast.makeText(this, "Pulsado eliminar : ", Toast.LENGTH_SHORT).show();
            AppUtil.data_semana.remove(pos);
            parse.deleteInsumo(sem);
            listinsumos.invalidateViews();
            adapter.notifyDataSetChanged();
        }
    }
}
