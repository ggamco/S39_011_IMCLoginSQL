package com.gmbdesign.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmbdesign.main.R;

/**
 * Created by ggamboa on 21/1/17.
 */

public class ObtenerRangos extends BaseAdapter {

    //Variables locales de la clase ObtenerRangos
    private Context contexto;
    private Integer[] arrayImagenes = {
            R.drawable.desnutrido, R.drawable.bajopeso,
            R.drawable.normal, R.drawable.sobrepeso,
            R.drawable.obeso};
    private String[] arrayIndices = {
            "IMC < 16 - DESNUTRIDO", "IMC < 18.5 - BAJO PESO",
            "IMC < 25 - NORMAL", "IMC < 30 - SOBREPESO",
            "IMC > 30 - OBESIDAD"};

    //creamos un constructor para recibir el contexto
    public ObtenerRangos(Context contexto){
        this.contexto = contexto;
    }

    //===============================================================================
    //Estos son los metodos que debemos implementar para crear nuestro propio adapter
    //===============================================================================

    @Override
    public int getCount() {
        //devuelve la cantidad de vistas que tiene que pintar, depende de los datos a mostrar
        return arrayImagenes.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    //Creamos una nueva ImageView por cada item referenciado por el adaptador

    /**
     *
     * @param i posición del view a pintar
     * @param view objeto a mostrar
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View vista = null;

        if(view == null){
            //si esta vista no se ha creado todavia, debemos setear los datos

            Log.d(getClass().getCanonicalName(), "Se crea la vista" + i);

            //Inflamos la vista
            Activity actividad = (Activity) contexto;
            LayoutInflater li = actividad.getLayoutInflater();
            vista = li.inflate(R.layout.rango, viewGroup, false);


        } else {
            //la vista ya se habia creado, por lo que se va a reciclar el contenido.
            //esto quiere decir que la vista ya estaba inflada, no hay que volver a inflar.

            Log.d(getClass().getCanonicalName(), "Se recicla la vista" + i);
            vista = view;

        }

        //Aqui seteamos toda la información a la vista, ya sea reciclada o creada.
        TextView etiqueta = (TextView) vista.findViewById(R.id.etiqueta_rango);
        etiqueta.setText(arrayIndices[i]);

        ImageView imagen = (ImageView) vista.findViewById(R.id.icono_rango);
        imagen.setImageResource(arrayImagenes[i]);

        return vista;
    }
}
