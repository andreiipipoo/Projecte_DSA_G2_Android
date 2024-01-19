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

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Shop extends AppCompatActivity {
    ImageButton volver2;
    APITrappy apiTrappy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        apiTrappy = Client.getInstance().getApiTrappy();
        volver2 = findViewById(R.id.volver4);

        Timer timer = new Timer();
        volver2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (Shop.this, Home.class);
                startActivity(i);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        MyAdapter adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setItems(Item.getData());
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
                                    Intent i = new Intent(Shop.this, Shop.class);
                                    startActivity(i);
                                }
                            }, 2000);
                        } else  {
                            timer.schedule(new TimerTask() {
                                public void run() {
                                    TextView success = (TextView) findViewById(R.id.Notifi);
                                    success.setText("No se ha podido realizar la compra.");
                                    success.setVisibility(View.VISIBLE);
                                    Intent i = new Intent(Shop.this, Shop.class);
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
                        Intent i = new Intent(Shop.this, Shop.class);
                        startActivity(i);
                    }
                });
            }
        });
    }
}