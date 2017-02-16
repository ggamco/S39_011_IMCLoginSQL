package com.gmbdesign.controles;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.EditText;

import com.gmbdesign.activities.RegistroActivity;
import com.gmbdesign.main.R;
import com.gmbdesign.modelos.Usuario;

/**
 * Clase que contendrá metodos para recuperar información de las vistas
 *
 * Created by ggamboa on 19/1/17.
 */

public class RecuperaDatosVistas {

    /**
     *
     * @param contexto de la actividad que llama al método.
     * @return cadenas introducidas en los EditText.
     */
    public static double[] recuperaValoresIMC(Context contexto){

        double[] cadenasIntroducidas = new double[2];

        Activity actividad = (Activity) contexto;
        EditText etPeso = (EditText) actividad.findViewById(R.id.myPesoTF);
        EditText etAltura = (EditText) actividad.findViewById(R.id.myAlturaTF);

        try {
            cadenasIntroducidas[0] = Double.parseDouble(etAltura.getText().toString());
            cadenasIntroducidas[1] = Double.parseDouble(etPeso.getText().toString());
        }catch(NumberFormatException e){
            Log.e("TAG-IMC", "No se han introducido datos en los campos");
            cadenasIntroducidas = null;
        }
        return cadenasIntroducidas;

    }

    public static Usuario recuperaValoresUsuario(Context contexto){

        Usuario usuario = null;

        EditText emailTF, passwordTF;

        Activity actividad = (Activity) contexto;

        if(contexto instanceof RegistroActivity){

            emailTF = (EditText) actividad.findViewById(R.id.emailRegistro);
            passwordTF = (EditText) actividad.findViewById(R.id.passRegistro);

        } else {

            emailTF = (EditText) actividad.findViewById(R.id.emailLogin);
            passwordTF = (EditText) actividad.findViewById(R.id.passLogin);

        }

        String mail = emailTF.getText().toString();
        String pass = passwordTF.getText().toString();

        Validador validador = new Validador(contexto);

        if (validador.validarRegistroUsuario(mail, pass)) {
            usuario = new Usuario(mail, pass);
        }

        return usuario;
    }

}
