package com.gmbdesign.activities;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.gmbdesign.listeners.EscuchaBoton;
import com.gmbdesign.main.R;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Se carga la vista de RegistroActivity
        setContentView(R.layout.activity_registro);
        //cargamos la mejora estetica en el HintText del password
        solucionFuenteHint();

        //preparamos el listener del boton registro
        Button botonRegistrar = (Button) findViewById(R.id.botonRegistro);
        botonRegistrar.setOnClickListener(new EscuchaBoton(this));

    }

    /**
     * La fuente por defecto del editText tipo password es courrier, con este metodo
     * solucionamos esteticamente el problema.
     */
    private void solucionFuenteHint(){
        EditText pass = (EditText) findViewById(R.id.passRegistro);
        pass.setTypeface(Typeface.DEFAULT);
    }
}
