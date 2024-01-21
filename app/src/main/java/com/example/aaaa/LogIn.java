package com.example.aaaa;

import android.os.Handler;
import android.os.Looper;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.aaaa.models.LoginModel;
import com.example.aaaa.models.Usuario;
import com.google.android.material.textfield.TextInputEditText;
import com.example.aaaa.api.APITrappy;
import com.example.aaaa.api.Client;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import java.util.Timer;
import java.util.TimerTask;

public class LogIn extends AppCompatActivity {
    Button register;
    Button login;
    APITrappy apiTrappy;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    private static final String KEY_NOMBRE = "";
    String user;
    String UserPassword;
    Integer coins;

    private ProgressBar progressBar;


    private void saveAuthenticationState(boolean isAuthenticated) {
        sharedPref = getSharedPreferences("AuthPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("is_authenticated", isAuthenticated);
        editor.putString("user",user);
        editor.putString("pass",UserPassword);
        editor.putInt("coins",coins);
        editor.apply();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent reg = new Intent (LogIn.this, Register.class);
        Intent home = new Intent (LogIn.this, NewHome.class);
        Intent l = new Intent (LogIn.this, LogIn.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        apiTrappy = Client.getInstance().getApiTrappy();
        Timer timer = new Timer();
        register = (Button) findViewById(R.id.RegisterBtn);
        login = (Button) findViewById(R.id.LoginBtn);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                startActivity(reg);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                TextInputEditText username;
                username = (TextInputEditText) findViewById(R.id.inputUsername2);
                user = username.getText().toString();
                Log.d("Valor username Login: ", String.valueOf(user));


                EditText password;
                password = (EditText) findViewById(R.id.password);
                UserPassword = password.getText().toString();
                Log.d("Valor username Password Login: ", String.valueOf(UserPassword));


                LoginModel loginModel = new LoginModel(user, UserPassword);
                apiTrappy.login(new com.example.aaaa.models.LoginModel(user,UserPassword)).enqueue(new Callback<Void>(){
                    String code;
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        code = String.valueOf(response.code());
                        Log.d("Code", ""+response.code());
                        if(code.equals("200")){
                            TextView success = (TextView) findViewById(R.id.notif);
                            success.setText("Te has logeado correctamente");
                            success.setVisibility(View.VISIBLE);
                            coins = 200;
                            saveAuthenticationState(true);

                            home.putExtra("user",user);
                            home.putExtra("pass",UserPassword);
                            home.putExtra("coins",coins);

                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí
                                    startActivity(home);
                                    progressBar.setVisibility(View.GONE);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                            /*
                            timer.schedule(new TimerTask() {
                                public void run() {

                                    startActivity(home);
                                }
                            }, 2000);

                             */


                        }
                        else if (code.equals("404")){
                            TextView success = (TextView) findViewById(R.id.notif);
                            success.setText("No se ha encontrado ese nombre de usuario");
                            success.setVisibility(View.VISIBLE);

                            timer.schedule(new TimerTask() {
                                public void run() {
                                    /*progressBar.setVisibility(View.GONE);*/
                                    startActivity(l);
                                }
                            }, 1500);
                        }
                        else if (code.equals("405")){
                            TextView success = (TextView) findViewById(R.id.notif);
                            success.setText("Contraseña incorrecta");
                            success.setVisibility(View.VISIBLE);

                            timer.schedule(new TimerTask() {
                                public void run() {
                                    /*progressBar.setVisibility(View.GONE);*/
                                    startActivity(l);
                                }
                            }, 1500);
                        }
                        else if (code.equals("500")){
                            TextView success = (TextView) findViewById(R.id.notif);
                            success.setText("VALIDATION ERROR");
                            success.setVisibility(View.VISIBLE);

                            timer.schedule(new TimerTask() {
                                public void run() {
                                    /*progressBar.setVisibility(View.GONE);*/
                                    startActivity(l);
                                }
                            }, 1500);
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        String msg = "Error in retrofit: " + t.toString();
                        Log.d("Code",msg);
                        Toast.makeText(getApplicationContext(),"msg", Toast.LENGTH_SHORT).show();
                        /*progressBar.setVisibility(View.GONE);*/
                        startActivity(l);
                    }
                });
                Log.d("Code", "end login");
            }
        });
    }
}
