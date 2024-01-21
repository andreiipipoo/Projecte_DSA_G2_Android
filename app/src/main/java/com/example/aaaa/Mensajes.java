package com.example.aaaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aaaa.api.APITrappy;
import com.example.aaaa.api.Client;
import com.example.aaaa.models.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.example.aaaa.models.Message;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Mensajes extends AppCompatActivity {
    ImageButton volver2;
    APITrappy apiTrappy;
    List<Message> mensajes = new ArrayList<>();
    TextView mensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajes);
        apiTrappy = Client.getInstance().getApiTrappy();
        volver2 = findViewById(R.id.volver4);

        Timer timer = new Timer();
        volver2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (Mensajes.this, Home.class);
                startActivity(i);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        MyAdapter adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);
        mensaje = (TextView) findViewById(R.id.firstLine);
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

        if(!mensajes.isEmpty()){
            adapter.setItems(mensajes);
            Log.d("Mensajes:","Se muestran los mensajes");
        }
        else{
            Log.d("Mensajes:","Ha habido un error mostrando los mensajes.");
        }
        /*
        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Item item) {
                String nombre;
                String descripcion;
                String type;
                double price;
                String id;
                //Aquí recibe el item sobre el que hemos hecho click
                nombre = item.getNombre();
                Log.d("Nombre item: ","" + nombre);
                descripcion = item.getDescripcion();
                Log.d("Descripcion item: ","" + descripcion);
                type = item.getType();
                Log.d("Tipo item: ","" + type);
                price = item.getPrice();
                Log.d("Precio item: ","" + price);
                id = item.getId();
                Log.d("Id item: ","" + id);
                apiTrappy.comprar(new com.example.aaaa.models.Item(nombre,descripcion,type,price,id)).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response){
                        Log.d("Código de compra: ", String.valueOf(response));
                        //Aquí van los diferentes códigos que recibimos (compra completada con éxito o no)
                        if (response.code()==201) {

                            timer.schedule(new TimerTask() {
                                public void run() {
                                    TextView success = (TextView) findViewById(R.id.Notifi);
                                    success.setText("Compra realizada con éxito.");
                                    success.setVisibility(View.VISIBLE);
                                    Intent i = new Intent(Mensajes.this, Mensajes.class);
                                    startActivity(i);
                                }
                            }, 2000);
                        } else  {
                            timer.schedule(new TimerTask() {
                                public void run() {
                                    TextView success = (TextView) findViewById(R.id.Notifi);
                                    success.setText("No se ha podido realizar la compra.");
                                    success.setVisibility(View.VISIBLE);
                                    Intent i = new Intent(Mensajes.this, Mensajes.class);
                                    startActivity(i);
                                }
                            }, 2000);
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        String msg = "Error in retrofit: " + t.toString();
                        Log.d("Mensaje error Register", msg);
                        Toast.makeText(getApplicationContext(), "msg", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Mensajes.this, Mensajes.class);
                        startActivity(i);
                    }
                });
            }
        });

         */
    }
}