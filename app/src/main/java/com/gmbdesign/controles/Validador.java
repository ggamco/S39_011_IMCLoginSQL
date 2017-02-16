package com.gmbdesign.controles;

import android.content.Context;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ggamboa on 14/2/17.
 */

public class Validador {

    //patron de validación de email
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private Context contexto;

    public Validador(Context contexto) {
        this.contexto = contexto;
    }

    public boolean validarRegistroUsuario(String email, String pass){

        boolean esValido = false;

        Pattern patron = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = patron.matcher(email);

        boolean esValidoMail = matcher.matches();

        if(email.length() != 0 && pass.length() != 0) {
            if (esValidoMail) {
                if (pass.length() >= 6) {
                    esValido = true;
                } else {
                    Acciones.mostrarToast(contexto, "Introduce almenos 6 caracteres");
                }
            } else {
                Acciones.mostrarToast(contexto, "El email no es válido");
            }
        } else {
            Acciones.mostrarToast(contexto, "Por favor, introduce los datos solicitados");
        }

        return esValido;
    }

}
