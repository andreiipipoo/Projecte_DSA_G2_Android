package com.example.aaaa;

import android.content.ComponentName;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.aaaa.api.APITrappy;
import com.example.aaaa.api.Client;
import com.example.aaaa.models.Message;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class NewHome extends AppCompatActivity {
    CardView playCard;
    CardView perfilCard;
    CardView tiendaCard;
    CardView pinkCard;
    CardView virtualCard;
    CardView scientistCard;
    Button logout;
    TextView mensaje;
    APITrappy apiTrappy;
    ProgressBar progressBar;
    static int REQUEST_CODE_1 = 1;
    int i;
    String mensajeMostrado = "";
    List<Message> mensajes = new ArrayList<>();
    String usercreds;
    String passcreds;
    Button verMensajes;
    private void getCredenciales(){
        usercreds = getIntent().getExtras().getString("user");
        passcreds = getIntent().getExtras().getString("pass");
    }
    private void setCredenciales(Intent i){
        i.putExtra("user",usercreds);
        i.putExtra("pass",passcreds);
        startActivity(i);
    }
    private void clearAuthenticationInfo() {
        SharedPreferences sharedPref = getSharedPreferences("AuthPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove("is_authenticated");
        editor.remove("user");
        editor.remove("pass");
        editor.apply();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NewHome.REQUEST_CODE_1 && resultCode == RESULT_OK && data != null) {
            String input2 = data.getStringExtra("input2");
            //result.setText("Result from Activity is: "+input2); Aquí habría que poner algo para obtener el resultado (un TextView).
        } else {
            //result.setText("Activity result received but not correct!");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_home);
        progressBar = findViewById(R.id.progressBar5);
        progressBar.setVisibility(View.GONE);
        apiTrappy = Client.getInstance().getApiTrappy();
       getCredenciales();

        verMensajes = findViewById(R.id.buttonMsg);

        verMensajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Intent w = new Intent (NewHome.this, Mensajes.class);
                setCredenciales(w);

            }
        });

        /*
        mensaje = (TextView) findViewById(R.id.message);

        if(mensajes.isEmpty()) { //Si la lista está vacía pedimos al BackEnd que nos mande toda la batería de mensajes
            i=0;
            Log.d("MENSAJES: ", "NO TENEMOS MENSAJES ASÍ QUE LOS PEDIMOS AL BACKEND.");
            apiTrappy = com.example.aaaa.api.Client.getInstance().getApiTrappy();
            //Recogemos los datos que nos mandan desde la función getMessage() del backend (una lista de mensajes)
            apiTrappy.getMessages().enqueue(new Callback<List<Message>>() {
                @Override
                public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                    if (response.isSuccessful()) {
                        mensajes = response.body();
                    } else {
                        Log.d("ERROR MENSAJE", "HA HABIDO UN ERROR RECIBIENDO LOS MENSAJES");
                    }
                }

                @Override
                public void onFailure(Call<List<Message>> call, Throwable t) {
                    Log.d("ERROR MENSAJE", "HA HABIDO UN ERROR RECIBIENDO LOS MENSAJES");
                }
            });
        }

        if (!mensajes.isEmpty()) {
            mensajeMostrado = mensajes.get(i).getCuerpoMensaje();
            Log.d("MENSAJES QUE SE MUESTRA: ", mensajeMostrado);
            mensaje.setText(mensajeMostrado);
        } else {
            Log.d("MENSAJES: ", "La lista de mensajes está vacía.");
            // Aquí puedes manejar la situación en la que no hay mensajes disponibles.
        }

         */

        //HECHO
        logout = findViewById(R.id.logoutbtn);

        Timer timer = new Timer();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                progressBar.setVisibility(View.VISIBLE);
                clearAuthenticationInfo();
                Intent intent = new Intent(NewHome.this, LogIn.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
/*
        playCard = findViewById(R.id.playCard);

        playCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Intent play = new Intent (); //En vez del segundo Home.class hay que poner
                //la actividad en la que esté el juego.
                play.setComponent(new ComponentName("com.DefaultCompany.CROACKY", "com.unity3d.player.UnityPlayerActivity"));

                play.putExtra("input","Aquí iría la info que queramos");
                startActivityForResult(play, NewHome.REQUEST_CODE_1);
            }
        });

 */
        playCard = findViewById(R.id.playCard);

        playCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("CLICK","CLICK SOBRE JUGAR");
                progressBar.setVisibility(View.VISIBLE);
                Intent launchIntent = new Intent();
                launchIntent.setComponent(new ComponentName("com.DefaultCompany.CROACKY", "com.DefaultCompany.CROACKY.Level1"));
                if (launchIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(launchIntent);
                } else {
                    Log.e("ERROR", "No se pudo resolver la actividad de UnityPlayerActivity");
                    // Aquí puedes manejar la situación de que la actividad no se resolvió correctamente.
                }
            }
        });

        /*

        ESTE ES EL EJEMPLO DE GITHUB https://github.com/jlopezr/activities/blob/main/app/src/main/java/dsa/app1/Activity1.java

          public void onClick4(View view) {
        Intent i = new Intent();
        i.setComponent(new ComponentName("dsa.app3", "com.unity3d.player.UnityPlayerActivity"));

        String data = input.getText().toString();
        i.putExtra("input",data);
        //startActivity(i);
        startActivityForResult(i, Activity2.REQUEST_CODE_1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Activity2.REQUEST_CODE_1 && resultCode == RESULT_OK && data != null) {
            String input2 = data.getStringExtra("input2");
            result.setText("Result from Activity is: "+input2);
        } else {
            result.setText("Activity result received but not correct!");
        }
    }
         */

        //HECHO
        perfilCard = findViewById(R.id.perfilCard);

        perfilCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Intent perfil = new Intent (NewHome.this, Perfil.class);
                startActivity(perfil);
            }
        });

        //HECHO
        tiendaCard = findViewById(R.id.tiendaCard);

        tiendaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Intent i = new Intent (NewHome.this, ShopDashboard.class);
                setCredenciales(i);
            }
        });

        //HECHO
        pinkCard = findViewById(R.id.pinkCard);

        pinkCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Intent i = new Intent (NewHome.this, Denuncia.class);
                setCredenciales(i);

            }
        });

        //HECHO
        virtualCard = findViewById(R.id.virtualCard);

        virtualCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Intent w = new Intent (NewHome.this, FormularioQuestion.class);
                setCredenciales(w);

            }
        });

        //HECHO
        scientistCard = findViewById(R.id.scientistCard);

        scientistCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Intent borrar = new Intent (NewHome.this, BorrarCuenta.class);
                setCredenciales(borrar);
            }
        });
    }
}