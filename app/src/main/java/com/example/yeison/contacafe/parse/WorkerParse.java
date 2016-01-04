package com.example.yeison.contacafe.parse;

import android.util.Log;

import com.example.yeison.contacafe.models.Semana;
import com.example.yeison.contacafe.models.Trabajador;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by YEISON on 06/10/2015.
 */
public class WorkerParse implements SaveCallback, DeleteCallback,  FindCallback<ParseObject> {




    public interface TrabajadorParseI{
        void done(boolean success);
        void resultTrabajador(boolean success, Trabajador trabajador);
        void resultListTrabajador(boolean success, List<Trabajador> trabajador);
    }

    public static final String CLASS = "Trabajador";
    public static final String C_ID_TRA = "objectId";
    public static final String C_NAME_TRA = "nombre";
    public static final String C_KLU = "klu";
    public static final String C_KMA = "kma";
    public static final String C_KMI = "kmi";
    public static final String C_KJU = "kju";
    public static final String C_KVI = "kvi";
    public static final String C_KSA = "ksa";
    public static final String C_KDOM = "kdom";
    public static final String C_CREATEDAT = "createdAt";
    public static final String C_IDSEMANA = "idsemana";

    TrabajadorParseI trabajadorParseI;

    public WorkerParse(TrabajadorParseI trabajadorParseI) {
        this.trabajadorParseI = trabajadorParseI;
    }

    public void insertTrabajador(Trabajador trabajador){
        ParseObject parseObject= new ParseObject(CLASS);
        parseTrabajador(parseObject, trabajador);
        parseObject.saveInBackground(this);
    }

    public void updateTrabajador(final Trabajador trabajador){
        ParseQuery<ParseObject> query = ParseQuery.getQuery(CLASS);
        query.getInBackground(trabajador.getIdtra(), new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                object.put(C_NAME_TRA, trabajador.getNombre());
                object.put(C_KLU, trabajador.getL());
                object.put(C_KMA, trabajador.getMa());
                object.put(C_KMI, trabajador.getMi());
                object.put(C_KJU, trabajador.getJ());
                object.put(C_KVI, trabajador.getV());
                object.put(C_KSA, trabajador.getS());
                object.put(C_KDOM, trabajador.getD());
                object.saveInBackground();
            }
        });

    }

    private void parseTrabajador(ParseObject parseObject, Trabajador trabajador){
        parseObject.put(C_NAME_TRA, trabajador.getNombre());
        parseObject.put(C_KLU, trabajador.getL());
        parseObject.put(C_KMA, trabajador.getMa());
        parseObject.put(C_KMI, trabajador.getMi());
        parseObject.put(C_KJU, trabajador.getJ());
        parseObject.put(C_KVI, trabajador.getV());
        parseObject.put(C_KSA, trabajador.getS());
        parseObject.put(C_KDOM, trabajador.getD());
        parseObject.put(C_IDSEMANA, trabajador.getIdsemana());
    }

    public void deleteTrabajador(Trabajador trabajador){
        ParseObject parseObject= new ParseObject(CLASS);
        parseObject.put(C_ID_TRA, trabajador.getIdtra());
        parseTrabajador(parseObject, trabajador);
        parseObject.deleteInBackground(this);
    }

    public void getTrabajadorById(String idTra){
        ParseQuery<ParseObject> query =  ParseQuery.getQuery(CLASS);
        query.getInBackground(idTra, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e==null){
                    trabajadorParseI.resultTrabajador(true,processParseObject(object));
                }else {
                    trabajadorParseI.resultTrabajador(false, null);
                }
            }
        });
    }

    public void getAllTrabajador(String idsem){
        ParseQuery<ParseObject> query = ParseQuery.getQuery(CLASS);
        //query.findInBackground(this);
        query.whereEqualTo(C_IDSEMANA, idsem);  //consulta los trabajadores con la id de semana
        query.findInBackground(this);
    }

    public void getRecentGames(Date last){
        ParseQuery<ParseObject> query = ParseQuery.getQuery(CLASS);
        query.orderByDescending(C_CREATEDAT);
        query.whereGreaterThan(C_CREATEDAT, last);
        query.findInBackground(this);
    }

    public void getTrabajadorByPages(int limit, Date last){
        ParseQuery<ParseObject> query = ParseQuery.getQuery(CLASS);
        if (last != null)
             query.whereLessThan(C_CREATEDAT, last);

        query.orderByDescending(C_CREATEDAT);
        query.setLimit(limit);
        query.findInBackground(this);
    }

    @Override // escuchar ParseObject de deleteTrabajador saveInBackground
    public void done(ParseException e) {
        if(e==null){
            trabajadorParseI.done(true);
        }else {
            trabajadorParseI.done(false);
        }
    }

      //escuchar el ParseObject getTrabajadorById y done de listTrabajador
    private Trabajador processParseObject(ParseObject object) {

        Trabajador trabajador= new Trabajador();
        trabajador.setIdtra(object.getObjectId());
        trabajador.setNombre(object.getString(C_NAME_TRA));
        trabajador.setL(object.getInt(C_KLU));
        trabajador.setMa(object.getInt(C_KMA));
        trabajador.setMi(object.getInt(C_KMI));
        trabajador.setJ(object.getInt(C_KJU));
        trabajador.setV(object.getInt(C_KVI));
        trabajador.setS(object.getInt(C_KSA));
        trabajador.setD(object.getInt(C_KDOM));
       // trabajador.setCreateAt(object.getCreatedAt());

        return trabajador;
    }

    @Override
    public void done(List<ParseObject> objects, ParseException e) {
        if (e==null){
            List<Trabajador> data_tra = new ArrayList<>();
            Log.i("Tamanio","data size objetcParse:"+objects.size());
            for(int i=0; i<objects.size();i++){
                data_tra.add(processParseObject(objects.get(i)));
            }
            trabajadorParseI.resultListTrabajador(true, data_tra);

        }else {
            List<Trabajador> data_tra = new ArrayList<>();
            Trabajador tran= new Trabajador("DATOS LOCALES",5,6,7,9,3,4,5,"hello");
            data_tra.add(tran);

            trabajadorParseI.resultListTrabajador(false,data_tra);
        }
    }
}


