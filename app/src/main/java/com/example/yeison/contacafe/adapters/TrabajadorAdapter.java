package com.example.yeison.contacafe.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yeison.contacafe.R;
import com.example.yeison.contacafe.models.Trabajador;

import java.util.List;

/**
 * Created by YEISON on 03/10/2015.
 */
public class TrabajadorAdapter extends BaseAdapter {
    Context context;
    List<Trabajador> data;

    public TrabajadorAdapter(Context context, List<Trabajador> data) {

        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v ;
        if(convertView == null)
            v =  View.inflate(context, R.layout.template_trabajador, null);
        else
            v = convertView;

        Trabajador t = (Trabajador) getItem(position);
        TextView txt = (TextView) v.findViewById(R.id.temp_nombre_trab);
        txt.setText(t.getNombre());
        txt = (TextView) v.findViewById(R.id.temp_id_trab);
        txt.setText(t.getIdtra());
        txt = (TextView) v.findViewById(R.id.klu_trab);
        txt.setText(""+t.getL());
        txt = (TextView) v.findViewById(R.id.kma_trab);
        txt.setText(""+t.getMa());
        txt = (TextView) v.findViewById(R.id.kmi_trab);
        txt.setText(""+t.getMi());
        txt = (TextView) v.findViewById(R.id.kju_trab);
        txt.setText(""+t.getJ());
        txt = (TextView) v.findViewById(R.id.kvi_trab);
        txt.setText(""+t.getV());
        txt = (TextView) v.findViewById(R.id.ksa_trab);
        txt.setText(""+t.getS());
        txt = (TextView) v.findViewById(R.id.kdom_trab);
        txt.setText(""+t.getD());
        txt = (TextView) v.findViewById(R.id.ktotal);
        int totalk= t.getL()+t.getMa()+t.getMi()+t.getJ()+t.getV()+t.getS()+t.getD();
        txt.setText(""+totalk);

        return v;
    }
}
