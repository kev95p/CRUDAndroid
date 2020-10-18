package com.kev95p.crud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;


public class FrutaAdapter extends BaseAdapter{

    ArrayList<String> frutas;
    Context context;

    public FrutaAdapter(@NonNull Context context, @NonNull ArrayList<String> objects) {
        this.context = context;
        frutas = objects;
    }

    @Override
    public int getCount() {
        return frutas.size();
    }

    @Override
    public String getItem(int i) {
        return frutas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.fruta_item,viewGroup,false);
        }

        String fruta = getItem(i);

        TextView nombreFruta = view.findViewById(R.id.labelNombreFruta);
        nombreFruta.setText(fruta);
        return view;
    }
}
