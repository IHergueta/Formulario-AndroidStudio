package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity2_Form extends AppCompatActivity implements View.OnClickListener{

    //incializo los edit text donde se guardaran los dato introducidos
    EditText campo1,campo2,campo3;
    //aqui se guardara la eleccion de lspinner
    String eleccion;
    CheckBox noticias;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2__form);

        //instacio el boton enviar
        Button btn_enviar = (Button) findViewById(R.id.btn3);
        btn_enviar.setOnClickListener(this);

        //creo variables para meter los texto buscados por sus respectivas id
         campo1 = (EditText) findViewById(R.id.dato1);
         campo2 = (EditText) findViewById(R.id.dato2);
         campo3 = (EditText) findViewById(R.id.dato3);
         Spinner spinner = (Spinner) findViewById(R.id.spin);
         noticias= (CheckBox) findViewById(R.id.check);

        // creo un array adapter con la lista de nacionalidades
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.nacionalidades, android.R.layout.simple_spinner_item);
        //inicializo el spinner con el adapter
        spinner.setAdapter(adapter);

        //metodo para ver si el boton de noticias esta selecionado o no
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //si esta selecionado que lo guardo en la variable
                eleccion = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

    }

    public boolean validar(){

        boolean retorno = true;

        //guardo las variables de nates que eram edittext y las paso a string para poder operar con ellas
        String dato1 = campo1.getText().toString();
        String dato2 = campo2.getText().toString();
        String dato3 = campo3.getText().toString();

        //compruebo que no lo dejen en blanco
        if(dato1.isEmpty()){
            campo1.setError("Parámetro fallido");
            retorno= false;
        }
        if(dato2.isEmpty()){
            campo2.setError("Parámetro fallido");
            retorno= false;
        }
        if(dato3.isEmpty()){
            campo3.setError("Parámetro fallido");
            retorno= false;
        }

        return retorno;
    }


    //validar el nombre
    private boolean validarNombre(String nombre){

        boolean esValido = false;
        int i = 0;
        int palabras = 0;

        //pongo la condicion de que tenga al menos algun espacio
        if(nombre.trim().contains(" ")) {

            do {

                //el split cuenta las palabras
                String[] contar = nombre.trim().split(" ");

                //meto el tamaño de la array en una variable
                palabras = palabras + contar.length;

                //si hay tres palabras palante
                if(palabras == 3){

                    esValido=true;
                }
                        i++;
            }


            while (i < nombre.length() && esValido);


        }

        return esValido;

    }

    //validar correo
    private boolean validarEmail(String email) {

        boolean esValido = false;
        int j = 0;
        int codigoasci = 0;
        int posarroba = 0;
        int pospunto = 0;

        //si elemail contiene @ o .
        if( email.contains("@") && email.contains(".")) {

            esValido=true;
            /*
            //esto no se por que no me va
            //me dice que todos los correos estan mal

            do {

                //va el codigo asci cada uno de los digitos en esa variable
                codigoasci = email.charAt(j);

                //si el codigo ascci es igual a 64 ,que es la @, entonces que me guarde la posicion de la arroba en una variable
                if (codigoasci == 64) {

                    posarroba = j;

                }

                //si el codigo ascci es igual a 46,que es el ., entonces que me guarde la posicion en una varieble
                if (codigoasci == 46) {

                    pospunto = j;

                }



                //si la posicion del punto es mas grnade que la de la arroba
                if (pospunto > posarroba) {

                    esValido = true;

                }



                j++;


            }

            while (j < email.length() && esValido);



             */
        }


        return esValido;

    }



    //metodo para validar el dni
    public static boolean validaDni(String dni){

        boolean esValido = false;
        int i = 0;
        int codigoasci = 0;
        char letra = ' ';
        int numeros = 0;

        //si el dni tiene 9 digitos y si el ultimo digito es una letra
        if(dni.length() == 9 && Character.isLetter(dni.charAt(8))) {

            do {

                //va el codigo asci cada uno de los digitos en esa variable
                codigoasci = dni.charAt(i);

                //comprueba q el numero este entre 0 a 9
                if(codigoasci > 47 && codigoasci < 58 ){

                    esValido=true;

                }

                i++;
            }

            //hasta que recorra el tamaño del dni menos 1 por que no queremos que coja la letra
            while(i < dni.length() -1 && esValido);
        }

        return esValido;

    }


    @Override
    public void onClick(View v) {
        //guardo en otras variables el dato del edittext
        String nombre = campo1.getText().toString();
        String dni = campo2.getText().toString();
        String correo = campo3.getText().toString();

        if(validarEmail(correo) && validaDni(dni) && validar() && validarNombre(nombre)){

            //creo un nuevo intent
            Intent intento = new Intent(this,VisualizarDatos.class);
            //me lleva a la otra activity
            //meto los extras para mandar datos a la otra activity
            intento.putExtra("dato1",nombre);
            intento.putExtra("dato2",dni);
            intento.putExtra("dato3",eleccion);
            intento.putExtra("dato4",correo);

            //siel boton esta selecionado que lo lleve a la otra activity
            if(noticias.isChecked()){
                intento.putExtra("dato5","Si");
            }else{
                intento.putExtra("dato5","No");
            }

           startActivity(intento);
        }

        // si minimamente fallan dos a la vez que me diga que hay varios parametrosno correctos
        if(!validaDni(dni) && !validarEmail(correo) || !validaDni(dni) && !validarNombre(nombre) || !validarNombre(nombre) && !validarEmail(correo)) {

            //creo un nuevo toast
            Toast toast = Toast.makeText(this, "Varios parámetros incorrectos", Toast.LENGTH_SHORT);
            toast.show();

        }else {

            // si solo es el corre
            if (!validarEmail(correo)) {
                campo3.setError("Email no válido");

                //creo un toast
                Toast toast = Toast.makeText(this, "Parámetro correo no permitido", Toast.LENGTH_SHORT);
                toast.show();

            }

            //sisolo esta mal el dni
            if (!validaDni(dni)) {
                campo2.setError("Dni no válido");

                //creo un toast
                Toast toast = Toast.makeText(this, "Parámetro dni no permitido", Toast.LENGTH_SHORT);
                toast.show();
            }

            //si solo esta mal el nombre
            if(!validarNombre(nombre)){
                campo1.setError("Nombre y apellidos no válido");

                //creo un toast
                Toast toast = Toast.makeText(this, "Parámetro nombre y apellidos no permitido", Toast.LENGTH_SHORT);
                toast.show();
            }


        }

        //si algun campo esta vacio
        if(!validar()){
            //creo un toast
            Toast toast = Toast.makeText(this, "Parámetro no permitido", Toast.LENGTH_SHORT);
            toast.show();

        }



    }



}