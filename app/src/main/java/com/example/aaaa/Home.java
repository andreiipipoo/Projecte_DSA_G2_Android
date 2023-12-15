package com.example.aaaa;

import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {
    Button shop;
    Button shopDash1;
    Button shopDash2;

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