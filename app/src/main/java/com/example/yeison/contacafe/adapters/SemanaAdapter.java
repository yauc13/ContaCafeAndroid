package com.example.yeison.contacafe.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yeison.contacafe.R;
import com.example.yeison.contacafe.models.Semana;


import java.util.List;

/**
 * Created by YEISON on 08/10/2015.
 */
public class SemanaAdapter extends BaseAdapter {

    Context context;
    List<Semana> data;

    public SemanaAdapter(Context context, List<Semana> data) {
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
            v =  View.inflate(context, R.layout.template_semana, null);
        else
            v = convertView;

        Semana s = (Semana) getItem(position);
        TextView txt = (TextView) v.findViewById(R.id.temp_nombre_week);
        txt.setText(s.getNombreSemana());
        txt = (TextView) v.findViewById(R.id.tem_fecha_week);
        txt.setText(""+s.getIdSemana());


        return v;
    }
}
