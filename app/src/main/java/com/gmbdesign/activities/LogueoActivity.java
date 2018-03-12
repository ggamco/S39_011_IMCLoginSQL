package com.gmbdesign.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gmbdesign.listeners.EscuchaBoton;
import com.gmbdesign.main.R;
import com.google.android.gms.ads.AdView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogueoActivity extends AppCompatActivity implements View.OnClickListener{

    private AdView mAdView;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private final String TAG = LogueoActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logueo);

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

        //cargamos la mejora estetica en el HintText del password
        solucionFuenteHint();

        Button botonAcceso = (Button) findViewById(R.id.botonLogin);
        botonAcceso.setOnClickListener(this);
    }

    /**
     * La fuente por defecto del editText tipo password es courrier, con este metodo
     * solucionamos esteticamente el problema.
     */
    private void solucionFuenteHint(){
        EditText pass = (EditText) findViewById(R.id.passRegistro);
        pass.setTypeface(Typeface.DEFAULT);
    }

    @Override
    public void onClick(View v) {

    }
}
