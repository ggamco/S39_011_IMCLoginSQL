package com.gmbdesign.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gmbdesign.listeners.EscuchaBoton;
import com.gmbdesign.main.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.firebase.auth.FirebaseAuth;

public class PrincipalActivity extends AppCompatActivity implements RewardedVideoAdListener {

    private FirebaseAuth mAuth;
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;
    private RewardedVideoAd mRewardedVideoAd;
    private boolean videoLoaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        //Se dibuja la actividad principal
        setContentView(R.layout.activity_principal);

        //app_id
        MobileAds.initialize(this, getResources().getString(R.string.app_id_ad_unit_id));
        //cargamos los videos de publicidad
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        //cargamos la publicidad Interstitial
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.inters_ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mInterstitialAd.show();
                loadVideoAd();
            }

            @Override
            public void onAdClosed() {
                //mInterstitialAd.loadAd(new AdRequest.Builder().build());
                if(videoLoaded){
                    mRewardedVideoAd.show();
                }
            }
        });

        //cargamos el Banner
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //creamos el objeto Listener
        View.OnClickListener escuchaBoton = new EscuchaBoton(this);

        //Preparamos botones y seteamos el listener
        Button botonPulsado = (Button) findViewById(R.id.botonCalcular);
        botonPulsado.setOnClickListener(escuchaBoton);

        Button listar = (Button) findViewById(R.id.botonListar);
        listar.setOnClickListener(escuchaBoton);
    }

    private void loadVideoAd(){
        mRewardedVideoAd.loadAd(getResources().getString(R.string.videos_ad_unit_id), new AdRequest.Builder().build());
    }

    @Override
    protected void onStop() {
        super.onStop();
        //mAuth.signOut();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        loadVideoAd();
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        videoLoaded = true;
    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }
}
