package com.kev95p.crud;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAgregar;
    ArrayList<String> frutas;
    ListView lista;
    FrutaAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAgregar = findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this,AgregarActivity.class),1);
            }
        });

        frutas = new ArrayList<>();
        frutas.add("Mango");

        lista = findViewById(R.id.lista);
        adapter = new FrutaAdapter(this,frutas);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,AgregarActivity.class);
                intent.putExtra(AgregarActivity.NOMBRE_FRUTA,frutas.get(i));
                intent.putExtra(AgregarActivity.EDITAR,true);
                intent.putExtra(AgregarActivity.POSICION,i);
                startActivityForResult(intent,1);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Desea eliminar este registro?")
                        .setTitle("Alerta")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int in) {
                                frutas.remove(i);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int in) {

                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == AgregarActivity.AGREGAR_REGISTRO){
            String fruta = data.getStringExtra(AgregarActivity.NOMBRE_FRUTA);
            frutas.add(fruta);
            adapter.notifyDataSetChanged();
        }
        else if(requestCode == 1 && resultCode == AgregarActivity.EDITAR_REGISTRO){
            int pos = data.getIntExtra(AgregarActivity.POSICION,0);
            String fruta = data.getStringExtra(AgregarActivity.NOMBRE_FRUTA);
            frutas.set(pos,fruta);
        }

    }
}