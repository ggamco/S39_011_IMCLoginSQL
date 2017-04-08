package com.gmbdesign.controles;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.gmbdesign.activities.LogueoActivity;
import com.gmbdesign.activities.PrincipalActivity;
import com.gmbdesign.activities.ResultadoActivity;
import com.gmbdesign.databases.BaseDatosUsuarios;
import com.gmbdesign.main.MainActivity;
import com.gmbdesign.modelos.Usuario;

/**
 * Created by ggamboa on 19/1/17.
 */

public class Acciones {

    public static void mostrarResultado(Context contexto, double[] datos){

        //Generamos un intent explicito, pasandole el contexto y llamando a la clase
        //que queremos lanzar, en este caso otra actividad.
        Intent intent = new Intent(contexto, ResultadoActivity.class);

        //añadimos en el intent los datos suministrados
        intent.putExtra("datosIntroducidos", datos);

        //preparamos la actividad que lanzara el intent explicito
        Activity actividad = (Activity) contexto;

        //Lanzamos el intent
        Log.d("TAG-IMC", "Lanzando el intent explicito");
        actividad.startActivity(intent);

    }

    public static void mostrarToast(Context contexto, String mensaje){

        Toast.makeText(contexto, mensaje, Toast.LENGTH_SHORT).show();

    }

    public static void registrarUsuario(Context contexto, Usuario usuario){

        //aquí programamos el registro del usuario en la base de datos
        BaseDatosUsuarios baseDatosUsuarios = new BaseDatosUsuarios(contexto, "miDB", null, 1);
        boolean control = baseDatosUsuarios.insertarUsuario(usuario);

        if(control) {
            //Almacenamos en el SharedPreferences la booleana de esRegistrado
            SharedPreferences preferences = contexto.getSharedPreferences("preferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("esRegistrado", true);
            editor.commit();

            //lanzamos la siguiente actividad
            Activity actividad = (Activity) contexto;
            Intent intent = new Intent(contexto, LogueoActivity.class);
            actividad.startActivity(intent);
            actividad.finish();
        } else {
            Acciones.mostrarToast(contexto, "Se ha producido un error. Vuelva a intentarlo.");
        }

    }

    public static void iniciarSesionUsuario(Context contexto, Usuario usuario){

        BaseDatosUsuarios baseDatosUsuarios = new BaseDatosUsuarios(contexto, "miDB", null, 1);
        boolean control = baseDatosUsuarios.loginUsuario(usuario);

        if(control) {
            //Almacenamos en el SharedPreferences la booleana de esLogueado
            SharedPreferences preferences = contexto.getSharedPreferences("preferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("esLogueado", true);
            editor.commit();

            //lanzamos la siguiente actividad
            Activity actividad = (Activity) contexto;
            Intent intent = new Intent(contexto, PrincipalActivity.class);
            actividad.startActivity(intent);
            actividad.finish();
        } else {
            Acciones.mostrarToast(contexto, "Usuario o contraseña no validos. Intentelo de nuevo");
        }

    }

}
