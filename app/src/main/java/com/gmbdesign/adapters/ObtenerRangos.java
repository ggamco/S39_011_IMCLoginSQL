package com.gmbdesign.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.gmbdesign.main.R;

/**
 * Created by ggamboa on 21/1/17.
 */

public class ObtenerRangos extends BaseAdapter {

    //Variables locales de la clase ObtenerRangos
    private Context contexto;
    private Integer[] arrayImagenes = {
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7};

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

        ImageView image = null;

        if(view == null){
            //si esta vista no se ha creado todavia, debemos setear los datos

            Log.d(getClass().getCanonicalName(), "Se crea la vista" + i);

            image = new ImageView(contexto);
            image.setLayoutParams(new ListView.LayoutParams(150,150));
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image.setPadding(8, 8, 8, 8);
        } else {
            //la vista ya se habia creado, por lo que se va a reciclar el contenido.
            //esto quiere decir que la vista ya estaba inflada, no hay que volver a inflar.

            Log.d(getClass().getCanonicalName(), "Se recicla la vista" + i);

            image = (ImageView) view;
        }

        image.setImageResource(arrayImagenes[i]);

        return image;
    }
}
