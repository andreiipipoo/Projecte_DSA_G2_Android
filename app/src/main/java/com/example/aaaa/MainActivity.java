package com.example.aaaa;

import android.content.Intent;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Timer timer = new Timer();
    private void checkAuthenticationStatus() {

        SharedPreferences sharedPreferences = getSharedPreferences("AuthPrefs", MODE_PRIVATE);
        boolean isAuthenticated = sharedPreferences.getBoolean("is_authenticated", false);
        String pass = sharedPreferences.getString("pass","");
        String user = sharedPreferences.getString("user","");


        if (isAuthenticated) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    Intent home = new Intent (MainActivity.this, NewHome.class);
                    Log.d("pass1",pass);
                    Log.d("user",user);
                    home.putExtra("user",user);
                    home.putExtra("pass",pass);
                    startActivity(home);
                }
            }, 2000);
            // Si el usuario está autenticado, redirigir a la actividad principal

        } else {
            // Si el usuario no está autenticado, continuar con la lógica actual
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    Intent i = new Intent(MainActivity.this, LogIn.class);
                    startActivity(i);
                }
            }, 2000);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer.schedule(new TimerTask() {
            public void run() {
                Intent i = new Intent(MainActivity.this, LogIn.class);
                startActivity(i);

            }
        }, 3000);

         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Verificar el estado de autenticación
        checkAuthenticationStatus();
    }
}