package com.gmbdesign.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gmbdesign.controles.Acciones;
import com.gmbdesign.listeners.EscuchaBoton;
import com.gmbdesign.main.R;
import com.gmbdesign.modelos.Usuario;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.gmbdesign.controles.RecuperaDatosVistas.recuperaValoresUsuario;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    private AdView mAdView;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private final String TAG = RegistroActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Se carga la vista de RegistroActivity
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        //cargamos el Banner
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //cargamos la mejora estetica en el HintText del password
        solucionFuenteHint();

        //preparamos el listener del boton registro
        Button botonRegistrar = (Button) findViewById(R.id.botonRegistro);
        botonRegistrar.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }

    @Override
    public void onClick(View v) {
        Usuario usuario = recuperaValoresUsuario(this);
        boolean esValidado = Acciones.validarFormularioUsuario(usuario, v);
        if (esValidado){
            Log.d(TAG, "El usuario ha sido validado, pasamos a crear su cuenta: " + usuario.getEmail());
            crearCuentaUsuario(usuario);
        } else {
            Log.d("TAG-IMC", "El usuario está vacio");
        }
    }

    private void crearCuentaUsuario(Usuario usuario) {
        Log.d(TAG, "crearCuentaUsuario:" + usuario.getEmail());

        mAuth.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "crearCuentaUsuarioConEmail:CORRECTO");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegistroActivity.this, "La autenticación ha fallado",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * La fuente por defecto del editText tipo password es courrier, con este metodo
     * solucionamos esteticamente el problema.
     */
    private void solucionFuenteHint() {
        EditText pass = (EditText) findViewById(R.id.passRegistro);
        pass.setTypeface(Typeface.DEFAULT);
    }


}
