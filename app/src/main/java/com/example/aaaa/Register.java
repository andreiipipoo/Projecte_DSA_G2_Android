package com.example.aaaa;

import android.os.Handler;
import android.os.Looper;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.aaaa.api.APITrappy;
import com.example.aaaa.api.Client;
import com.example.aaaa.models.Usuario;
import com.google.android.material.textfield.TextInputEditText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
public class Register extends AppCompatActivity {
    private static final String SHARED_PREFS = "Data";
    public static final String TEXT1 = "Username: ";
    public static final String TEXT2 = "Password: ";
    public static final String TEXT3 = "Correo: ";
    public static final String TEXT4 = "Tlf: ";
    ImageButton volver;
    String user1;
    String phone;
    String email;
    String UserPassword1;
    String UserPassword2;
    APITrappy apiTrappy;
    Button register;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        apiTrappy = Client.getInstance().getApiTrappy();
        Intent l = new Intent(Register.this, LogIn.class);
        Intent r = new Intent(Register.this, Register.class);
        Timer timer = new Timer();
        apiTrappy = Client.getInstance().getApiTrappy();

        progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.GONE);

        volver = findViewById(R.id.volverBtn);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(l);
            }
        });


        register = (Button) findViewById(R.id.RegisterBtn);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                TextInputEditText username = (TextInputEditText) findViewById(R.id.username);
                user1 = username.getText().toString();
                Log.d("Valor username: ", String.valueOf(user1));

                EditText phonenumber = (EditText) findViewById(R.id.phone);
                phone = phonenumber.getText().toString();
                Log.d("Valor teléfono: ", String.valueOf(phone));

                TextInputEditText mailaddress = (TextInputEditText) findViewById(R.id.mail);
                email = mailaddress.getText().toString();
                Log.d("Valor mail: ", String.valueOf(email));

                EditText password1 = (EditText) findViewById(R.id.password1);
                UserPassword1 = password1.getText().toString();
                Log.d("Valor password 1: ", String.valueOf(UserPassword1));

                EditText password2 = (EditText) findViewById(R.id.password2);
                UserPassword2 = password2.getText().toString();
                Log.d("Valor password 2: ", String.valueOf(UserPassword2));

                Usuario usuario = new Usuario(user1, UserPassword1, phone, email, "1", 200);



                apiTrappy.register(new com.example.aaaa.models.Usuario(user1,UserPassword1,phone,email,"1",200)).enqueue(new Callback<Void>() {
                //apiTrappy.register(new com.example.aaaa.models.RegisterModel(user1, UserPassword1, mail, tlf)).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.d("Código de Register: ", String.valueOf(response));

                        if (response.code()==201) {
                            TextView success = (TextView) findViewById(R.id.Notif);
                            success.setText("Te has registrado correctamente.");
                            success.setVisibility(View.VISIBLE);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí
                                    startActivity(l);
                                    progressBar.setVisibility(View.GONE);
                                }
                            }, 1000);  // El retraso en milisegundos antes de que se ejecute tu código

/*
                            timer.schedule(new TimerTask() {
                                public void run() {

                                    startActivity(l);
                                    progressBar.setVisibility(View.GONE);

                                }


                            }, 2000);

 */





                        } else if (response.code() == 405) {
                            TextView success = (TextView) findViewById(R.id.Notif);
                            success.setText("El nombre de usuario no está disponible");
                            success.setVisibility(View.VISIBLE);

                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí
                                    startActivity(r);
                                    progressBar.setVisibility(View.GONE);
                                }
                            }, 1000);  // El retraso en milisegundos antes de que se ejecute tu código
                        }
                        else if (response.code() == 406) {
                            TextView success = (TextView) findViewById(R.id.Notif);
                            success.setText("El correo no está disponible");
                            success.setVisibility(View.VISIBLE);

                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí
                                    startActivity(r);
                                    progressBar.setVisibility(View.GONE);
                                }
                            }, 1000);  // El retraso en milisegundos antes de que se ejecute tu código
                        }
                        else if (response.code() == 500) {
                            TextView success = (TextView) findViewById(R.id.Notif);
                            success.setText("VALIDATION ERROR");
                            success.setVisibility(View.VISIBLE);

                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí
                                    startActivity(r);
                                    progressBar.setVisibility(View.GONE);
                                }
                            }, 1000);  // El retraso en milisegundos antes de que se ejecute tu código
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        String msg = "Error in retrofit: " + t.toString();
                        Log.d("Mensaje error Register", msg);
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "msg", Toast.LENGTH_SHORT).show();
                        Intent o = new Intent(Register.this, Register.class);
                        startActivity(o);
                    }
                });
                Log.d("Code", "end login");
            }
        });
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT1, user1);
        editor.putString(TEXT2, UserPassword1);
        editor.putString(TEXT3, email);
        editor.putString(TEXT4, phone);
        editor.apply();
    }

}

