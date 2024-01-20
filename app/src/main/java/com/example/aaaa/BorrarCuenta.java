package com.example.aaaa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aaaa.api.APITrappy;
import com.example.aaaa.models.LoginModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BorrarCuenta extends AppCompatActivity {

    EditText contraseña;
    String contraseña2;
    Button enviar;
    private ProgressBar progressBar;
    APITrappy apiTrappy;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        contraseña = (EditText) findViewById(R.id.password3);

        contraseña2 = contraseña.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("AuthPrefs", MODE_PRIVATE);
        String password = sharedPreferences.getString("contraseña", "");
        String username = sharedPreferences.getString("username", "");

        enviar = (Button) findViewById(R.id.buttonEnviar1);
        progressBar = findViewById(R.id.progressBar7);

        if(!password.isEmpty() && !username.isEmpty()){
            progressBar.setVisibility(View.VISIBLE);
            if(password.equals(contraseña2)){
                enviar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        progressBar.setVisibility(View.VISIBLE);
                        apiTrappy.delete(username).enqueue(new Callback<Void>(){
                            String code;
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                code = String.valueOf(response.code());
                                Log.d("Code", ""+response.code());
                                if(code.equals("201")){
                                    TextView notificacion = findViewById(R.id.notifBorrar);
                                    notificacion.setVisibility(View.VISIBLE);
                                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            // Tu código de actualización de la interfaz de usuario va aquí
                                            Intent home = new Intent (BorrarCuenta.this, LogIn.class);
                                            startActivity(home);
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    }, 1000);  // El retraso en milisegundos antes de que se ejecute tu código

                                }
                                else if (code.equals("404")){
                                    TextView success = (TextView) findViewById(R.id.notif);
                                    success.setText("No se ha encontrado ese nombre de usuario");
                                    success.setVisibility(View.VISIBLE);

                                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            // Tu código de actualización de la interfaz de usuario va aquí
                                            Intent home1 = new Intent (BorrarCuenta.this, BorrarCuenta.class);
                                            startActivity(home1);
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    }, 1000);  // El retraso en milisegundos antes de que se ejecute tu código

                                }

                            }
                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                String msg = "Error in retrofit: " + t.toString();
                                Log.d("Code",msg);
                                Toast.makeText(getApplicationContext(),"msg", Toast.LENGTH_SHORT).show();
                                /*progressBar.setVisibility(View.GONE);*/
                                Intent home1 = new Intent (BorrarCuenta.this, BorrarCuenta.class);
                                startActivity(home1);
                            }
                        });
                        Log.d("Code", "end login");
                    }
                });
            }
        }

    }
}
