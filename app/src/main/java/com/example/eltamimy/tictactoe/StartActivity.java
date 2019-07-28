package com.example.eltamimy.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class StartActivity extends AppCompatActivity {

    LinearLayout mainLayout,soloLevel,multiplayerLevel;
    RelativeLayout solo,multi,exit;
    RelativeLayout easy,normal,hard;
    RelativeLayout sameMob,wifiDirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mainLayout=findViewById(R.id.mainLayout);
        soloLevel=findViewById(R.id.solo_Level);
        multiplayerLevel=findViewById(R.id.Multiplayer_Level);

        solo=findViewById(R.id.solo);
        multi=findViewById(R.id.multi);
        exit=findViewById(R.id.exit);

        easy=findViewById(R.id.easy);
        normal=findViewById(R.id.normal);
        hard=findViewById(R.id.hard);

        sameMob=findViewById(R.id.Same_Mobile);
        wifiDirect=findViewById(R.id.Wifi_Direct);


        solo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainLayout.setVisibility(View.GONE);
                soloLevel.setVisibility(View.VISIBLE);
            }
        });
        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainLayout.setVisibility(View.GONE);
                multiplayerLevel.setVisibility(View.VISIBLE);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SoloEasyActivity.class);
                startActivity(intent);
                //finish();
            }
        });
        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SoloNormalActivity.class);
                startActivity(intent);
                //finish();
            }
        });
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SoloHardActivity.class);
                startActivity(intent);
                //finish();
            }
        });


        sameMob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SameMob.class);
                startActivity(intent);
               // finish();
            }
        });
        wifiDirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                //finish();
            }
        });
    }

}
