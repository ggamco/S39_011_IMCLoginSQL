package com.gmbdesign.listeners;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.gmbdesign.activities.ListaRangosActivity;
import com.gmbdesign.controles.Acciones;
import com.gmbdesign.main.R;
import com.gmbdesign.modelos.Usuario;

import static com.gmbdesign.controles.RecuperaDatosVistas.*;


/**
 * Created by ggamboa on 19/1/17.
 */

public class EscuchaBoton implements View.OnClickListener{

    Context contexto;

    public EscuchaBoton(Context contexto){
        this.contexto = contexto;
    }

    /**
     * Este método es invocado como callback cuando el usuario pulse sobre un
     * objeto de la vista.
     *
     * @param view vista que ha sido soleccionada.
     */
    @Override
    public void onClick(View view) {

        //detectamos el id del boton que llama al evento
        int idVistaPulsada = view.getId();

        switch (idVistaPulsada){

            case R.id.botonCalcular:

                Log.d("TAG-IMC", "El usuario a pulsado el botonCalcular");

                double[] datosIntroducidos = recuperaValoresIMC(contexto);

                if(datosIntroducidos != null){
                    Acciones.mostrarResultado(contexto, datosIntroducidos);
                } else {
                    Acciones.mostrarToast(contexto, "Introduce los datos solicitados");
                }

                break; //finalizamos el case

            case R.id.botonListar:

                Log.d("TAG-IMC", "El usuario a pulsado el botonListar");

                Intent intent = new Intent(contexto, ListaRangosActivity.class);
                Activity actividad = (Activity) contexto;
                actividad.startActivity(intent);

                break; //finalizamos el case

            case R.id.botonRegistro:

                Log.d("TAG-IMC", "El usuario a pulsado el botonRegistrar");

                Usuario usuario = recuperaValoresUsuario(contexto);

                if(usuario != null) {
                    Log.d("TAG-IMC", "Datos de registro introducidos correctamente en los campos");
                    Acciones.registrarUsuario(contexto, usuario);

                } else {
                    Log.d("TAG-IMC", "El usuario está vacio");
                }

                break; //finalizamos el case

            case R.id.botonLogin:

                Log.d("TAG-IMC", "El usuario a pulsado el botonLogin");

                Usuario usuarioLog = recuperaValoresUsuario(contexto);

                if(usuarioLog != null) {
                    Log.d("TAG-IMC", "Datos de acceso introducidos correctamente en los campos");
                    Acciones.iniciarSesionUsuario(contexto, usuarioLog);

                } else {
                    Log.d("TAG-IMC", "El usuario está vacio");
                }

                break; //finalizamos el case
        }

    }
}
