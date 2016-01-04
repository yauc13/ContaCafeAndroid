package com.example.yeison.contacafe.parse;



import android.util.Log;

import com.example.yeison.contacafe.models.Semana;
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
 * Created by YEISON on 08/10/2015.
 */
public class WeekParse implements SaveCallback, DeleteCallback, FindCallback<ParseObject> {



    public interface SemanaParseI{
        void done(boolean success);
        void resultSemana(boolean success, Semana semana);
       void resultListSemana(boolean success, List<Semana> semana);
    }

    public static final String CLASS = "Semana";
    public static final String C_ID_SEM = "objectId";
    public static final String C_NAME_SEM = "nom_semana";
    public static final String C_CREATEDAT = "createdAt";

    SemanaParseI semanaParseI;

    public WeekParse(SemanaParseI semanaParseI) {
        this.semanaParseI = semanaParseI;
    }

    public void insertSemana(Semana semana){
        ParseObject parseObject= new ParseObject(CLASS);
        parseSemana(parseObject, semana);
        parseObject.saveInBackground(this);
    }

    public void updateSemana(final Semana semana){

        ParseQuery<ParseObject> query = ParseQuery.getQuery(CLASS);
        query.getInBackground(semana.getIdSemana(), new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                object.put(C_NAME_SEM, semana.getNombreSemana());
                object.saveInBackground();
            }
        });


    }

    private void parseSemana(ParseObject parseObject, Semana semana){
        parseObject.put(C_NAME_SEM, semana.getNombreSemana());
    }

    public void deleteSemana(Semana semana){
        ParseObject parseObject= new ParseObject(CLASS);
        parseObject.put(C_ID_SEM, semana.getIdSemana());
        //parseSemana(parseObject, semana);
        parseObject.deleteInBackground(this);
        Log.i("DELETE", "idsem: " + semana.getIdSemana());
        //parseObject.remove(semana.getIdSemana());
        //parseObject.saveInBackground();
    }

    public void getAllSemana(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery(CLASS);
        query.findInBackground(this);
    }

    public void getSemanaById(String idSem){
        ParseQuery<ParseObject> query =  ParseQuery.getQuery(CLASS);
        query.getInBackground(idSem, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    semanaParseI.resultSemana(true, processParseObject(object));
                } else {
                    semanaParseI.resultSemana(false, null);
                }
            }
        });
    }


    //escuchar el ParseObject getTrabajadorById y done de listTrabajador
    private Semana processParseObject(ParseObject object) {

        Semana semana= new Semana();
        semana.setIdSemana(object.getObjectId());
        semana.setNombreSemana(object.getString(C_NAME_SEM));
        semana.setFechaSemana(object.getDate(C_CREATEDAT));
        return semana;
    }



    @Override  //para los metodos delete, update y create Semana
    public void done(ParseException e) {
        if(e==null){
            semanaParseI.done(true);
        }else {
            semanaParseI.done(false);
        }
    }

    @Override//para getAllSemana
    public void done(List<ParseObject> objects, ParseException e) {
        if (e==null){
            List<Semana> data_sem = new ArrayList<>();
            for(int i=0; i<objects.size();i++){
                data_sem.add(processParseObject(objects.get(i)));
            }semanaParseI.resultListSemana(true, data_sem);
        }else {
            List<Semana> data_sem = new ArrayList<>();
            Semana tran= new Semana("DATOS LOCALES Semana");
            data_sem.add(tran);

            semanaParseI.resultListSemana(false, data_sem);
        }
    }



}
