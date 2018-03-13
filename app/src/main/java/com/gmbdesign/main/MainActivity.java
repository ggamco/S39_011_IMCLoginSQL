package com.gmbdesign.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gmbdesign.activities.LogueoActivity;
import com.gmbdesign.activities.PrincipalActivity;
import com.gmbdesign.activities.RegistroActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Versión 2.0 de la app IMC. En esta versión incluimos el registro y login de usuarios.
 *
 * Para esto, lo primero que vamos a controlar es si el usuario ya está registrado o no.
 * Tomaremos una instancia de SharedPreferences para comprobarlo
 */
public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //Recuperamos el objeto que contiene las preferencias.
        SharedPreferences preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);

        //Buscamos la variable esRegistrado (false es el valor por defecto en caso de no encontrarla)
        boolean esRegistrado = preferences.getBoolean("esRegistrado", false);
        //boolean esLogueado = preferences.getBoolean("esLogueado", false);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        Intent intent;

        if(user != null) {
            intent = new Intent(this, PrincipalActivity.class);
        } else {
            if(!esRegistrado) {
                intent = new Intent(this, RegistroActivity.class);
            } else {
                intent = new Intent(this, LogueoActivity.class);
            }
        }

        this.startActivity(intent);

        this.finish();

    }




}
