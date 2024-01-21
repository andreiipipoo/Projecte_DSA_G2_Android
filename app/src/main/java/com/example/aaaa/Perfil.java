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

import java.util.ArrayList;
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
    List<Insignia> ejemploInsignias = new ArrayList<>();
    Button recibirInsignias;
    RecyclerView recyclerView ;
    TextView usernameView ;
    TextView idView ;
    TextView tlfView;
    TextView croCoinsView;
    TextView emailView;
    public static String[] getVector(){
        return miVector;
    }
    String usercreds;
    String passcreds;
    Integer coins;
    private void getCredenciales(){
        usercreds = getIntent().getExtras().getString("user");
        passcreds = getIntent().getExtras().getString("pass");
        coins = getIntent().getExtras().getInt("coins");
    }
    private void setCredenciales(Intent i){
        i.putExtra("user",usercreds);
        i.putExtra("pass",passcreds);
        i.putExtra("coins",coins);
        startActivity(i);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        getCredenciales();
        recyclerView = findViewById(R.id.recyclerView2);
        usernameView = findViewById(R.id.textView3);
        idView = findViewById(R.id.textView2);
        tlfView = findViewById(R.id.textView4);
        croCoinsView = findViewById(R.id.textView6);
        emailView = findViewById(R.id.textView5);
        usernameView.setVisibility(View.GONE);
        idView.setVisibility(View.GONE);
        tlfView.setVisibility(View.GONE);
        croCoinsView.setVisibility(View.GONE);
        emailView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        progressBar = findViewById(R.id.progressBar4);
        progressBar.setVisibility(View.GONE);


        apiTrappy = Client.getInstance().getApiTrappy();

        Timer timer = new Timer();


        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager2);

        MyAdapter2 adapter = new MyAdapter2();
        recyclerView.setAdapter(adapter);


        volver = (ImageButton) findViewById(R.id.volverBtn2);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volver = new Intent(Perfil.this, NewHome.class);
                setCredenciales(volver);
            }
        });


        recibirInsignias = (Button) findViewById(R.id.buttonInsignia);
        recibirInsignias.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                usernameView.setVisibility(View.GONE);
                idView.setVisibility(View.GONE);
                tlfView.setVisibility(View.GONE);
                croCoinsView.setVisibility(View.GONE);
                emailView.setVisibility(View.GONE);

                recyclerView.setVisibility(View.VISIBLE);
                adapter.setInsignias(Insignia.getData());


                /*
                TextInputEditText username2 = (TextInputEditText) findViewById(R.id.idUsuario2);
                username1 = username2.getText().toString();
                Log.d("Valor id introducido: ", String.valueOf(username1));
                apiTrappy.recibirInsignia(username1).enqueue(new Callback<Insignia>() {
                    @Override
                    public void onResponse(Call<Insignia> call, Response<Insignia> response) {
                        Log.d("Code: ",String.valueOf(response.code()));
                        if (response.code()==200) {
                            Log.d("CODIGO","HE ENTRADO EN 200");
                            recyclerView.setVisibility(View.VISIBLE);
                            //Aquí estamos recibiendo la respuesta (la cual es un vector), y lo metemos en respuesta
                            String[] respuesta = new String[]{response.body().toString()};
                            //Aquí cojemos la respuesta y la introducimos en miVector
                            if (respuesta != null && respuesta.length >= 2) {
                                for (int i = 0; i < respuesta.length; i++) {
                                    // Asignar los elementos de la respuesta al vector miVector
                                    miVector[i] = respuesta[i];
                                }
                                ejemploInsignias.clear();
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
                                    progressBar.setVisibility(View.GONE);
                                }
                            }, 2000);
                        }
                    }
                    @Override
                    public void onFailure(Call<Insignia> call, Throwable t) {
                        Log.d("Mensaje error Register","Ha habido un error, y ni siquiera he entrado en onResponse");
                    }
                });
                */

            }


        });


        enviar = (Button) findViewById(R.id.enviarBtn);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recyclerView.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);

                ejemploInsignias.clear();

                TextInputEditText username2 = (TextInputEditText) findViewById(R.id.idUsuario2);
                username1 = username2.getText().toString();
                Log.d("Valor id introducido: ", String.valueOf(username1));


                //MINIMO 2
                apiTrappy.recibirPErfil(username1).enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        Log.d("Código de Register: ", String.valueOf(response.code()));

                        if (response.code() == 201) {
                            usernameView.setVisibility(View.VISIBLE);
                            idView.setVisibility(View.VISIBLE);
                            tlfView.setVisibility(View.VISIBLE);
                            croCoinsView.setVisibility(View.VISIBLE);
                            emailView.setVisibility(View.VISIBLE);
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

                            idView.setText("ID: " + id);
                            usernameView.setText("Username: " + username);
                            tlfView.setText("Telephone: " + telephone);
                            emailView.setText("Email: " + email);
                            croCoinsView.setText("CroCoins: " + String.valueOf(croCoins));
                            progressBar.setVisibility(View.GONE);

                        } else if (response.code() == 404) {

                            timer.schedule(new TimerTask() {
                                public void run() {
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
