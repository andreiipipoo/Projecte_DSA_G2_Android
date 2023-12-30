package com.example.aaaa;

import android.content.ComponentName;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.BreakIterator;

public class Home extends AppCompatActivity {
    Button shop;
    Button shopDash1;
    Button shopDash2;
    Button perfilUsuario;
    Button play;
    static int REQUEST_CODE_1 = 1;

    Button logout;
    private ProgressBar progressBar;
    private void clearAuthenticationInfo() {
        SharedPreferences sharedPref = getSharedPreferences("AuthPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove("is_authenticated");
        editor.apply();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        progressBar = findViewById(R.id.progressBar3);
        progressBar.setVisibility(View.GONE);

        perfilUsuario = (Button) findViewById(R.id.perfilBtn);
        perfilUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Intent i = new Intent (Home.this, Perfil.class);
                startActivity(i);
            }
        });

        play = (Button) findViewById(R.id.PlayBtn);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Intent play = new Intent (); //En vez del segundo Home.class hay que poner
                //la actividad en la que esté el juego.
                play.setComponent(new ComponentName("dsa.app2", "dsa.app2.Activity3"));

                play.putExtra("input","Aquí iría la info que queramos");
                startActivityForResult(play, Home.REQUEST_CODE_1);
                startActivity(play);
            }
        });

        shop = (Button) findViewById(R.id.ShopBtn);
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Intent i = new Intent (Home.this, Shop.class);
                startActivity(i);
            }
        });

        shopDash1 = (Button) findViewById(R.id.shopDashBtn);
        shopDash1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Intent i = new Intent (Home.this, ShopDashboard.class);
                startActivity(i);
            }
        });

        shopDash2 = (Button) findViewById(R.id.shopDashBtn2);
        shopDash2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Intent i = new Intent (Home.this, ShopDashboard2.class);
                startActivity(i);
            }
        });


        logout = (Button) findViewById(R.id.Logout);
        logout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick (View v) {
               progressBar.setVisibility(View.VISIBLE);
               clearAuthenticationInfo();
               Intent intent = new Intent(Home.this, LogIn.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
               startActivity(intent);
           }
        });
    }
}