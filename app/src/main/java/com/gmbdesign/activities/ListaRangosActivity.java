package com.gmbdesign.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.gmbdesign.adapters.ObtenerRangos;
import com.gmbdesign.main.R;

public class ListaRangosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_rangos);

        String[] arrayDatos = new String[10];

        for(int i = 0; i < 10; i++){
            arrayDatos[i] = "Elemento " + i;
        }

        ListAdapter adaptadorLista = new ArrayAdapter<String>(this, R.layout.fila, arrayDatos);

        ListView lv = (ListView) findViewById(R.id.listacadenas);

        //lv.setAdapter(adaptadorLista);

        lv.setAdapter(new ObtenerRangos(this));
    }
}
