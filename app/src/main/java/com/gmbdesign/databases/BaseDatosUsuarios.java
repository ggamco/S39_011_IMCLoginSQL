package com.gmbdesign.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.gmbdesign.modelos.Usuario;

import java.util.List;

/**
 * Created by ggamboa on 15/2/17.
 */

public class BaseDatosUsuarios extends SQLiteOpenHelper {

    //variables locales de la clase
    private static final String CREAR_TABLA_USUARIOS = "CREATE TABLE IF NOT EXISTS Usuarios (idUsuario INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT UNIQUE, password TEXT)";


    public BaseDatosUsuarios(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_USUARIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Este método nos permite almacenar un usuario en la base de datos interna del dispositivo
     * La devolución del metodo es una primitiva de tipo booleano, siendo true siempre que la
     * sentencia de almacenado se haya completado sin errores.
     *
     * @param usuario -> Objeto del tipo usuario que se quiere insertar en la base de datos
     * @return -> boolean true: usuario insertado correctamente en la DB.
     */
    public boolean insertarUsuario(Usuario usuario){

        boolean respuesta = true;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String sql = "INSERT INTO Usuarios ('email', 'password') VALUES ('"+usuario.getEmail()+"', '"+usuario.getPassword()+"')";

        try{
            sqLiteDatabase.execSQL(sql);
            Log.d("TAG-IMC", "El usuario se ha introducido correctamente en la DB");
        }catch (Throwable t){
            Log.e("TAG-IMC", "Se ha producido un error al insertar un usuario en DB. Error: " + t.getMessage());
            respuesta = false;
        }finally {
            sqLiteDatabase.close();
        }

        return respuesta;
    }

    /**
     * Este método nos permite comprobar si las credenciales de un usuario son validas para iniciar sesion de usuario.
     * La devolución del método es un primitiva de tipo booleana que indica si el usuario ha introducido unas
     * unas credenciales correctas, siendo true el resultado es este caso.
     *
     * @param usuario -> Objeto del tipo usuario que contienes las credenciales del usuario quiere acceder a la aplicación.
     * @return -> boolean true: acceso correcto.
     */
    public boolean loginUsuario(Usuario usuario){

        boolean esLogueado = false;

        //Instanciamos la DB preparandola para lectura
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        //Sentencia SQL cotejamiento de usuario
        String sql = "SELECT idUsuario FROM Usuarios WHERE email = '"+usuario.getEmail()+"' AND password = '"+usuario.getPassword()+"'";
        try {
            //Recuperación de los datos solicitados
            Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

            //Procesamos el cursor para comprobar que existen coincidencias;
            if (cursor != null && cursor.getCount() == 1) {
                cursor.moveToFirst();
                int idUsuarioLogueado = cursor.getInt(0);
                esLogueado = true;
                Log.d("TAG-IMC", "Los datos introducidos coinciden con el usuario_ID: " + idUsuarioLogueado);
            }
        }catch(Throwable t){
            Log.e("TAG-IMC", "Se produjo un error al intentar loguear a un usuario. Error: " + t.getMessage());
        }finally {
            sqLiteDatabase.close();
        }

        return esLogueado;
    }

}
