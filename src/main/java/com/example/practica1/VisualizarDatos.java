package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class VisualizarDatos extends AppCompatActivity {

    private TextView tv_nombre,tv_dni,tv_email,tv_spin,tv_noticias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_datos);

        //guardo en los texview ya creados el textview correspodiente con su respectiva id
        tv_nombre = (TextView)findViewById(R.id.nombre_dato);
        tv_dni = (TextView)findViewById(R.id.dni_dato);
        tv_spin = (TextView)findViewById(R.id.spinner_dato);
        tv_email = (TextView)findViewById(R.id.correo_dato);
        tv_noticias = (TextView)findViewById(R.id.noticias_dato);

        //recepciono los datos que me han mandado desde la otra actividad
        String dato_nombre = getIntent().getStringExtra("dato1");
        String dato_dni = getIntent().getStringExtra("dato2");
        String dato_spin = getIntent().getStringExtra("dato3");
        String dato_correo = getIntent().getStringExtra("dato4");
        String dato_noticias =getIntent().getStringExtra("dato5");

        //hago que en los textview se pongo los datos captados
        tv_nombre.setText ("\n\t -" + dato_nombre);
        tv_dni.setText("\n\t -" + dato_dni);
        tv_spin.setText("\n\t -" + dato_spin);
        tv_email.setText("\n\t -" + dato_correo);
        tv_noticias.setText("\n\t -" + dato_noticias);
    }




}