package com.gmbdesign.activities;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.gmbdesign.listeners.EscuchaBoton;
import com.gmbdesign.main.R;

public class LogueoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logueo);

        //cargamos la mejora estetica en el HintText del password
        solucionFuenteHint();

        Button botonAcceso = (Button) findViewById(R.id.botonLogin);
        botonAcceso.setOnClickListener(new EscuchaBoton(this));
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
