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
import com.example.aaaa.models.DenunciaModel;
import com.example.aaaa.models.Usuario;
import com.google.android.material.textfield.TextInputEditText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncia);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.GONE);
        apiTrappy = Client.getInstance().getApiTrappy();
        apiTrappy = Client.getInstance().getApiTrappy();
        volver = (ImageButton) findViewById(R.id.volverBtn);
        volver.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Denuncia.this,NewHome.class);
                startActivity(i);
            }
        });

        send = (Button) findViewById(R.id.buttonSend);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                TextInputEditText dateText = (TextInputEditText) findViewById(R.id.fechaText);
                date = dateText.getText().toString();
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

                DenunciaModel denuncia = new DenunciaModel(date, title, message, sender);

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
                                    startActivity(i);
                                    progressBar.setVisibility(View.GONE);
                                }
                            }, 1000);  // El retraso en milisegundos antes de que se ejecute tu código

                        } else if (response.code() == 404) {
                            TextView success = (TextView) findViewById(R.id.Notif);
                            success.setText("Ha habido un error al envíar tu denuncia");
                            success.setVisibility(View.VISIBLE);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí
                                    Intent i = new Intent(Denuncia.this, Denuncia.class);
                                    startActivity(i);
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
                                    startActivity(i);
                                    progressBar.setVisibility(View.GONE);
                                }
                            }, 1000);  // El retraso en milisegundos antes de que se ejecute tu código

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