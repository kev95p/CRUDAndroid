package com.kev95p.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AgregarActivity extends AppCompatActivity {

    public final static int AGREGAR_REGISTRO = 1001;
    public final static int EDITAR_REGISTRO = 1002;
    public final static String NOMBRE_FRUTA = "NOMBREFRUTA";
    public final static String EDITAR = "EDITAR";
    public static final String POSICION = "POS" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);




        final EditText nombre = findViewById(R.id.txtNombreFruta);
        Button agregar = findViewById(R.id.btnGuardarRegistro);

        Intent i = getIntent();
        final boolean editar = i.getBooleanExtra(EDITAR,false);
        if(editar){
            agregar.setText("Editar Registro");
            String nombreFruta = i.getStringExtra(NOMBRE_FRUTA);
            nombre.setText(nombreFruta);
        }

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = getIntent();
                i.putExtra(NOMBRE_FRUTA,nombre.getText().toString());
                if(editar){
                    int pos = i.getIntExtra(POSICION,0);
                    i.putExtra(POSICION,pos);
                    setResult(EDITAR_REGISTRO,i);
                }
                else {
                    setResult(AGREGAR_REGISTRO, i);
                }
                finish();
            }
        });


    }
}