package com.example.yeison.contacafe.parse;

import android.util.Log;

import com.example.yeison.contacafe.models.Insumos;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YEISON on 13/10/2015.
 */
public class InsumosParse implements FindCallback<ParseObject>, SaveCallback, DeleteCallback {




    public interface InsumosParseI{
        void done(boolean success);
        void resultSemana(boolean success, Insumos semana);
        void resultListSemana(boolean success, List<Insumos> semana);
    }

    public static final String CLASS = "Insumos";
    public static final String C_ID_INSUMO = "objectId";
    public static final String C_NAME_INSUMO = "nom_insumo";
    public static final String C_COSTOINSUMO = "costo_insumo";
    public static final String C_CREATEDAT = "createdAt";

    InsumosParseI insumosParseI;

    public InsumosParse(InsumosParseI insumosParseI) {
        this.insumosParseI = insumosParseI;
    }

    public void insertInsumo(Insumos insumos){
        ParseObject parseObject= new ParseObject(CLASS);
        parseInsumo(parseObject, insumos);
        parseObject.saveInBackground(this);
    }

    public void updateInsumo(final Insumos insumos){

        ParseQuery<ParseObject> query = ParseQuery.getQuery(CLASS);
        query.getInBackground(insumos.getIdInsumo(), new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                object.put(C_NAME_INSUMO, insumos.getNombreInsumo());
                object.put(C_COSTOINSUMO, insumos.getCostoInsumo());
                object.saveInBackground();
            }
        });


    }

    private void parseInsumo(ParseObject parseObject, Insumos insumos){
        parseObject.put(C_NAME_INSUMO, insumos.getNombreInsumo());
        parseObject.put(C_COSTOINSUMO, insumos.getCostoInsumo());
    }

    public void deleteInsumo(Insumos insumos){
        ParseObject parseObject= new ParseObject(CLASS);
        parseObject.put(C_ID_INSUMO,insumos.getIdInsumo());
        //parseSemana(parseObject, semana);
        parseObject.deleteInBackground(this);
        Log.i("DELETE", "idsem: " + insumos.getIdInsumo());
        //parseObject.remove(semana.getIdSemana());
        //parseObject.saveInBackground();
    }

    public void getAllInsumos(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery(CLASS);
        query.findInBackground(this);
    }

    //escuchar el ParseObject getTrabajadorById y done de listTrabajador
    private Insumos processParseObject(ParseObject object) {

        Insumos insumos= new Insumos();
        insumos.setIdInsumo(object.getObjectId());
        insumos.setNombreInsumo(object.getString(C_NAME_INSUMO));
        insumos.setCostoInsumo(object.getDouble(C_COSTOINSUMO));
        return insumos;
    }

    @Override
    public void done(ParseException e) {
        if(e==null){
            insumosParseI.done(true);
        }else {
            insumosParseI.done(false);
        }
    }

    @Override
    public void done(List<ParseObject> objects, ParseException e) {
        if (e==null){
            List<Insumos> data_sem = new ArrayList<>();
            for(int i=0; i<objects.size();i++){
                data_sem.add(processParseObject(objects.get(i)));
            }insumosParseI.resultListSemana(true, data_sem);
        }else {
            List<Insumos> data_ins = new ArrayList<>();
            Insumos tran= new Insumos("DATOS LOCALES Insumos",0);
            data_ins.add(tran);

            insumosParseI.resultListSemana(false, data_ins);
        }
    }


}
