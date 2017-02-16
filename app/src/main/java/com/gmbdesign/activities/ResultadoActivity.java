package com.gmbdesign.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gmbdesign.main.R;

/**
 * Created by ggamboa on 19/1/17.
 */

public class ResultadoActivity extends AppCompatActivity {

    private double[] datosPasados;
    private double imc;
    private String mensaje;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        //Recuperamos los datos pasados por el intent
        Bundle bundle = getIntent().getExtras();
        this.datosPasados = bundle.getDoubleArray("datosIntroducidos");

        //mostramos el resultado obtenido
        presentaResultado();
    }

    /**
     * Método que calcula el indice de masa corporal
     */
    private void calcularIMC(){

        //La fórmula para calcular el IMC es peso(kg)/altura^2(m)
        double altura = datosPasados[0] / 100; //convertimos a metros
        double peso = datosPasados[1];

        //Realizamos el cálculo
        imc = peso / (altura*altura);

        if(imc < 16) {
            mensaje = getString(R.string.msg_desnutrido);
        } else if(imc < 18){
            mensaje = getString(R.string.msg_bajoPeso);
        } else if(imc < 25){
            mensaje = getString(R.string.msg_normal);
        } else if(imc < 31){
            mensaje = getString(R.string.msg_sobrepeso);
        } else {
            mensaje = getString(R.string.msg_obeso);
        }

    }

    /**
     * Método que presentará en las vistas los resultados obtenidos
     */
    private void presentaResultado(){

        TextView tvIMC = (TextView) findViewById(R.id.tvIMC);
        TextView tvTIPO = (TextView) findViewById(R.id.tvTIPO);

        //calculamos el IMC
        calcularIMC();

        tvIMC.setText(String.valueOf((int) imc));
        tvTIPO.setText(mensaje);
    }
}
