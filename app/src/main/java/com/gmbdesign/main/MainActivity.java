package com.gmbdesign.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gmbdesign.activities.LogueoActivity;
import com.gmbdesign.activities.PrincipalActivity;
import com.gmbdesign.activities.RegistroActivity;

/**
 * Versión 2.0 de la app IMC. En esta versión incluimos el registro y login de usuarios.
 *
 * Para esto, lo primero que vamos a controlar es si el usuario ya está registrado o no.
 * Tomaremos una instancia de SharedPreferences para comprobarlo
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //Recuperamos el objeto que contiene las preferencias.
        SharedPreferences preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);

        //Buscamos la variable esRegistrado (false es el valor por defecto en caso de no encontrarla)
        boolean esRegistrado = preferences.getBoolean("esRegistrado", false);
        boolean esLogueado = preferences.getBoolean("esLogueado", false);

        Intent intent;

        if(!esLogueado) {

            if(!esRegistrado) {
                intent = new Intent(this, RegistroActivity.class);
            } else {
                intent = new Intent(this, LogueoActivity.class);
            }

        } else {

            intent = new Intent(this, PrincipalActivity.class);

        }

        this.startActivity(intent);

        this.finish();

    }




}
