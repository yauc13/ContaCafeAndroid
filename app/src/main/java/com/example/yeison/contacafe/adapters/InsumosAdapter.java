package com.example.yeison.contacafe.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yeison.contacafe.R;
import com.example.yeison.contacafe.models.Insumos;


import java.util.List;

/**
 * Created by YEISON on 13/10/2015.
 */
public class InsumosAdapter extends BaseAdapter {
    Context context;
    List<Insumos> data;

    public InsumosAdapter(Context context, List<Insumos> data) {
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
            v =  View.inflate(context, R.layout.template_insumos, null);
        else
            v = convertView;

        Insumos s = (Insumos) getItem(position);
        TextView txt = (TextView) v.findViewById(R.id.temp_nombre_insumos);
        txt.setText(s.getNombreInsumo());
        txt = (TextView) v.findViewById(R.id.tem_costo_insumos);
        txt.setText(""+s.getCostoInsumo());


        return v;
    }
}
