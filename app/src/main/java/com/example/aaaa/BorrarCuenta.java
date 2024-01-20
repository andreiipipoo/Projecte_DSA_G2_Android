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
    String user;
    String pass;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        user = getIntent().getExtras().getString("user");
        pass = getIntent().getExtras().getString("pass");
        TextView notificacion = findViewById(R.id.notifBorrar);

        contraseña = (EditText) findViewById(R.id.password3);

        contraseña2 = contraseña.getText().toString();


        enviar = (Button) findViewById(R.id.buttonEnviar1);
        progressBar = findViewById(R.id.progressBar7);

        if(!contraseña2.isEmpty() && !user.isEmpty()){
            progressBar.setVisibility(View.VISIBLE);
            if(contraseña2.equals(pass)){
                enviar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        progressBar.setVisibility(View.VISIBLE);
                        apiTrappy.delete(user).enqueue(new Callback<Void>(){
                            String code;
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                code = String.valueOf(response.code());
                                Log.d("Code", ""+response.code());
                                if(code.equals("201")){
                                    notificacion.setVisibility(View.VISIBLE);
                                    notificacion.setText("La cuenta se ha borrado correctamente");
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
            else {
                notificacion.setVisibility(View.VISIBLE);
                notificacion.setText("La contraseña no es correcta");
            }
        }

    }
}
