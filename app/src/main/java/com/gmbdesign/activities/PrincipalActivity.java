package com.gmbdesign.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gmbdesign.listeners.EscuchaBoton;
import com.gmbdesign.main.R;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Se dibuja la actividad principal
        setContentView(R.layout.activity_principal);

        //creamos el objeto Listener
        View.OnClickListener escuchaBoton = new EscuchaBoton(this);

        //Preparamos botones y seteamos el listener
        Button botonPulsado = (Button) findViewById(R.id.botonCalcular);
        botonPulsado.setOnClickListener(escuchaBoton);

        Button listar = (Button) findViewById(R.id.botonListar);
        listar.setOnClickListener(escuchaBoton);
    }
}
