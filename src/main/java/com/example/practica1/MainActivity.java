package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creo el boton y lo busco por id
        Button btn_formulario = (Button) findViewById(R.id.btn1);
        btn_formulario.setOnClickListener(this);

        Button btn_email = (Button) findViewById(R.id.btn2);
        btn_email.setOnClickListener(this);
    }

    public void onClick(View v) {

    //primer boton buscarndo por su id
    if(v.getId()==R.id.btn1){

        //creo un nuevo intent
        Intent intento = new Intent(v.getContext(),MainActivity2_Form.class);
        //me lleva a la otra activity
        startActivity(intento);
    }else{

        Intent intento = new Intent(v.getContext(),MainActivity2_Form.class);
        startActivity(intento);
    }

    //segundo boton
    if(v.getId()==R.id.btn2){

        //creo un nuevo intent con el tipo sendto
        Intent email = new Intent(Intent.ACTION_SENDTO)
                .setType("plain/text")

                //meto el destinatario para que me llegen los emails
                .setData(Uri.parse("ignacio.hergueta.guerra@alumnos.fesac.es"))
                .setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail")

                //inicializo el asunto del email
                .putExtra(Intent.EXTRA_SUBJECT,"Sugerencias");

                //lanzamos el intnt ya creado
                startActivity(email);

    }


    }


}