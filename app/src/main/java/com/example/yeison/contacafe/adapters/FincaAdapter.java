package com.example.yeison.contacafe.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.yeison.contacafe.R;
import com.example.yeison.contacafe.models.Finca;

import java.util.List;

/**
 * Created by YEISON on 17/09/2015.
 */
public class FincaAdapter extends BaseAdapter {
    Context context;
    List<Finca> data;

    public FincaAdapter(Context context, List<Finca> data) {
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
        View v = null;
        if(convertView == null)
            v =  View.inflate(context, R.layout.template_finca, null);
        else
            v = convertView;

        Finca f = (Finca) getItem(position);
        TextView txt = (TextView) v.findViewById(R.id.txt);
        txt.setText(f.getNombre());


        return v;

    }
}
