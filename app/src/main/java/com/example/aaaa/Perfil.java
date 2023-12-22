package com.example.aaaa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aaaa.api.APITrappy;
import com.example.aaaa.api.Client;
import com.example.aaaa.models.Insignia;
import com.example.aaaa.models.Item;
import com.google.android.material.textfield.TextInputEditText;

import java.util.TimerTask;
import java.util.Timer;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Perfil extends AppCompatActivity {
    Button enviar;
    String id;
    private ProgressBar progressBar;
    APITrappy apiTrappy;
    static String[] miVector = new String[100];

    public static String[] getVector(){
        return miVector;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        progressBar = findViewById(R.id.progressBar4);
        progressBar.setVisibility(View.GONE);

        apiTrappy = Client.getInstance().getApiTrappy();

        Timer timer = new Timer();



        enviar = (Button) findViewById(R.id.enviarBtn);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);

                TextInputEditText idUser = (TextInputEditText) findViewById(R.id.idUsuario);
                id = idUser.getText().toString();
                Log.d("Valor id introducido: ", String.valueOf(id));

                //MINIMO 2
                apiTrappy.recibirInsignia(id).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.d("Código de Register: ", String.valueOf(response.code()));

                        if (response.code()==201) {

                            //Aquí estamos recibiendo la respuesta (la cual es un vector), y lo metemos en respuesta
                            String[] respuesta = new String[]{response.body().toString()};
                            //Aquí cojemos la respuesta y la introducimos en miVector
                            if (respuesta != null && respuesta.length >= 2) {
                                for(int i=0; i<respuesta.length;i++) {
                                    // Asignar los elementos de la respuesta al vector miVector
                                    miVector[i] = respuesta[i];
                                }
                                RecyclerView recyclerView = findViewById(R.id.recyclerView2);

                                LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
                                recyclerView.setLayoutManager(layoutManager2);

                                MyAdapter2 adapter = new MyAdapter2();
                                recyclerView.setAdapter(adapter);
                                adapter.setInsignias(Insignia.getData());

                                /*
                                TextView username = (TextView) findViewById(R.id.nombreUsuario);
                                username.setText(miVector[0]);

                                ImageView userImage = (ImageView) findViewById(R.id.imagenUsuario);
                                userImage.setImageURI(Uri.parse(miVector[1]));

                                 */
                            }

                            //Si falta algo es que ha habido algún error
                            else {
                                // Manejar el caso en el que la respuesta no es válida
                                Log.e("Error", "La respuesta no es un array de Strings de tamaño 2");
                            }

                        } else if (response.code() == 404) {

                            timer.schedule(new TimerTask() {
                                public void run() {
                                    Intent o = new Intent(Perfil.this, Perfil.class);
                                    startActivity(o);
                                    progressBar.setVisibility(View.GONE);
                                }
                            }, 2000);
                        }
                        else if (response.code() == 401) {

                            timer.schedule(new TimerTask() {
                                public void run() {
                                    /*password1.setText("");
                                    password2.setText("");*/
                                    Intent o = new Intent(Perfil.this, Perfil.class);
                                    startActivity(o);
                                    progressBar.setVisibility(View.GONE);
                                }
                            }, 2000);
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        String msg = "Error in retrofit: " + t.toString();
                        Log.d("Mensaje error Register", msg);
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "msg", Toast.LENGTH_SHORT).show();
                        Intent o = new Intent(Perfil.this, Perfil.class);
                        startActivity(o);
                    }
                });

            }
        });
        //GET /user/xxxxxx/badges/
        //==>
        //[
        //{
        //‘name’: ‘master del universo’
        //‘avatar’:'https://cdn.pixabay.com/photo/2017/07/11/15/51/kermit-2493979_1280.png'
        //},
        //{
        //‘name’: ‘rey/reina de xxx’
        //‘avatar’: ‘..’
        //},
        //]



    }

}
