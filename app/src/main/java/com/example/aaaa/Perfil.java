package com.example.aaaa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
import com.example.aaaa.models.Usuario;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.util.List;
import java.util.TimerTask;
import java.util.Timer;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Perfil extends AppCompatActivity {
    Button enviar;
    ImageButton volver;
    String username1;
    private ProgressBar progressBar;
    APITrappy apiTrappy;
    static String[] miVector = new String[100];
    List<Insignia> ejemploInsignias;
    TextView usernameView;
    TextView idView;
    TextView tlfView;
    TextView croCoinsView;
    TextView emailView;

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

        /*
        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager2);

        MyAdapter2 adapter = new MyAdapter2();
        recyclerView.setAdapter(adapter);

         */

        volver = (ImageButton) findViewById(R.id.volverBtn2);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent volver = new Intent(Perfil.this, NewHome.class);
            startActivity(volver);
            }
        });

        enviar = (Button) findViewById(R.id.enviarBtn);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);

                ejemploInsignias.clear();

                TextInputEditText username2 = (TextInputEditText) findViewById(R.id.usernameUsuario1);
                username1 = username2.getText().toString();
                Log.d("Valor id introducido: ", String.valueOf(username1));

                TextView username = (TextView) findViewById(R.id.nombreUsuario2);
                username.setText(miVector[0]);

                ImageView userImage = (ImageView) findViewById(R.id.imagenUsuario2);
                userImage.setImageURI(Uri.parse(miVector[1]));

                usernameView = (TextView) findViewById(R.id.textView3);
                idView = (TextView) findViewById(R.id.textView2);
                tlfView= (TextView) findViewById(R.id.textView4);
                croCoinsView= (TextView) findViewById(R.id.textView6);
                emailView= (TextView) findViewById(R.id.textView5);

                //MINIMO 2
                apiTrappy.recibirInsignia(username1).enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        Log.d("Código de Register: ", String.valueOf(response.code()));

                        if(response.code()==201){
                            String id;
                            String username;
                            String telephone;
                            String email;
                            int croCoins;

                            Usuario usuario = new Usuario();

                            usuario = response.body();

                            id = usuario.getId();
                            username = usuario.getUsername();
                            telephone = usuario.getTlf();
                            email = usuario.getMail();
                            croCoins = usuario.getCroCoins();

                            idView.setText(id);
                            usernameView.setText(username);
                            tlfView.setText(telephone);
                            emailView.setText(email);
                            croCoinsView.setText(String.valueOf(croCoins));

                        }
                        else if (response.code() == 404) {

                            timer.schedule(new TimerTask() {
                                public void run() {
                                    Intent o = new Intent(Perfil.this, Perfil.class);
                                    startActivity(o);
                                    progressBar.setVisibility(View.GONE);
                                }
                            }, 2000);
                        }

                        /*
                        if (response.code()==201) {
                            //Aquí estamos recibiendo la respuesta (la cual es un vector), y lo metemos en respuesta
                            String[] respuesta = new String[]{response.body().toString()};
                            //Aquí cojemos la respuesta y la introducimos en miVector
                            if (respuesta != null && respuesta.length >= 2) {
                                for (int i = 0; i < respuesta.length; i++) {
                                    // Asignar los elementos de la respuesta al vector miVector
                                    miVector[i] = respuesta[i];
                                }


                                ejemploInsignias = Insignia.getData(miVector);
                                adapter.setInsignias(ejemploInsignias);

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

                                    Intent o = new Intent(Perfil.this, Perfil.class);
                                    startActivity(o);
                                    progressBar.setVisibility(View.GONE);
                                }
                            }, 2000);
                        }

                         */


                    }
                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
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
