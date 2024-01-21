package com.example.aaaa;

import android.annotation.SuppressLint;
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
import com.example.aaaa.models.DenunciaModel;
import com.example.aaaa.models.Usuario;
import com.google.android.material.textfield.TextInputEditText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Denuncia extends AppCompatActivity {
    String date;
    String title;
    String message;
    String sender;
    APITrappy apiTrappy;
    Button send;
    ImageButton volver;
    ProgressBar progressBar;
    String usercreds;
    String passcreds;
    private void getCredenciales(){
        usercreds = getIntent().getExtras().getString("user");
        passcreds = getIntent().getExtras().getString("pass");
    }
    private void setCredenciales(Intent i){
        i.putExtra("user",usercreds);
        i.putExtra("pass",passcreds);
        startActivity(i);
    }

    @SuppressLint("SimpleDateFormat")
    private String obtainDate(String s) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(s);
        return simpleDateFormat.format(date);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncia);
        this.date=obtainDate("yyyy-MM-dd");
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.GONE);
        apiTrappy = Client.getInstance().getApiTrappy();
        apiTrappy = Client.getInstance().getApiTrappy();
        volver = (ImageButton) findViewById(R.id.volverBtn);
        getCredenciales();

        volver.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Denuncia.this,NewHome.class);
                setCredenciales(i);
            }
        });

        send = (Button) findViewById(R.id.buttonSend);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                Log.d("Valor fecha: ", String.valueOf(date));

                TextInputEditText titleText = (TextInputEditText) findViewById(R.id.titleText);
                title = titleText.getText().toString();
                Log.d("Valor titulo: ", String.valueOf(title));

                TextInputEditText messageText = (TextInputEditText) findViewById(R.id.denunciaText);
                message = messageText.getText().toString();
                Log.d("Valor username: ", String.valueOf(message));

                TextInputEditText senderText = (TextInputEditText) findViewById(R.id.nameText);
                sender = senderText.getText().toString();
                Log.d("Valor username: ", String.valueOf(sender));

                DenunciaModel denuncia = new DenunciaModel(String.valueOf(date), title, message, sender);

                apiTrappy.denunciar(denuncia).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {
                            TextView success = (TextView) findViewById(R.id.Notif);
                            success.setText("Tu denuncia se ha enviado correctamente");
                            success.setVisibility(View.VISIBLE);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí
                                    Intent i = new Intent(Denuncia.this, Denuncia.class);
                                    setCredenciales(i);
                                    progressBar.setVisibility(View.GONE);
                                }
                            }, 2000);  // El retraso en milisegundos antes de que se ejecute tu código

                        } else if (response.code() == 404) {
                            TextView success = (TextView) findViewById(R.id.Notif);
                            success.setText("Ha habido un error al envíar tu denuncia");
                            success.setVisibility(View.VISIBLE);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí
                                    Intent i = new Intent(Denuncia.this, Denuncia.class);
                                    setCredenciales(i);
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
                                    Intent i = new Intent(Denuncia.this, Denuncia.class);
                                    setCredenciales(i);
                                    progressBar.setVisibility(View.GONE);
                                }
                            }, 2000);  // El retraso en milisegundos antes de que se ejecute tu código

                        }


                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        String msg = "Error in retrofit: " + t.toString();
                        Log.d("Mensaje error ", msg);
                        Toast.makeText(getApplicationContext(), "msg", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}