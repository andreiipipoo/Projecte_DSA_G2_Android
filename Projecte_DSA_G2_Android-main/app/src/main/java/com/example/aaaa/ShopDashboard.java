package com.example.aaaa;

import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.aaaa.api.APITrappy;
import com.example.aaaa.api.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Timer;
import java.util.TimerTask;


public class ShopDashboard extends AppCompatActivity {

    CardView ranaCard;
    CardView desiertoCard;
    CardView maskCard;
    CardView pinkCard;
    CardView virtualCard;
    CardView scientistCard;
    ImageButton volver3;
    APITrappy apiTrappy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_dashboard);
        apiTrappy = Client.getInstance().getApiTrappy();

        volver3 = findViewById(R.id.volver4);

        Timer timer = new Timer();
        volver3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (ShopDashboard.this, NewHome.class);
                startActivity(i);
            }
        });

        ranaCard = findViewById(R.id.ranaCard);

        ranaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idplayer = "ejemplo";
                String iditem = "1";

                Log.d("Nombre item: botas","");
                Log.d("Id item: id ","");
                Log.d("Id player: id ","");


                apiTrappy.buy(iditem,idplayer).enqueue(new Callback<Void>() {
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
                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    startActivity(i);
                                }
                            }, 2000);
                        } else if (response.code() == 404) {
                            timer.schedule(new TimerTask() {
                                public void run() {
                                    TextView success = (TextView) findViewById(R.id.Notifi);
                                    success.setText("No se ha podido realizar la compra.");
                                    success.setVisibility(View.VISIBLE);
                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
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
                        Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                        startActivity(i);
                    }
                });


            }
        });

        desiertoCard = findViewById(R.id.desiertoCard);

        desiertoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idplayer = "ejemplo";
                String iditem = "2";

                Log.d("Nombre item: espada","");
                Log.d("Id item: id ","");
                Log.d("Id player: id ","");


                apiTrappy.buy(iditem,idplayer).enqueue(new Callback<Void>() {
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
                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    startActivity(i);
                                }
                            }, 2000);
                        } else if (response.code() == 404) {
                            timer.schedule(new TimerTask() {
                                public void run() {
                                    TextView success = (TextView) findViewById(R.id.Notifi);
                                    success.setText("No se ha podido realizar la compra.");
                                    success.setVisibility(View.VISIBLE);
                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
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
                        Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                        startActivity(i);
                    }
                });

            }
        });

        maskCard = findViewById(R.id.maskCard);

        maskCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idplayer = "ejemplo";
                String iditem = "3";

                Log.d("Nombre item: pocion","");
                Log.d("Id item: id ","");
                Log.d("Id player: id ","");


                apiTrappy.buy(iditem,idplayer).enqueue(new Callback<Void>() {
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
                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    startActivity(i);
                                }
                            }, 2000);
                        } else if (response.code() == 404) {
                            timer.schedule(new TimerTask() {
                                public void run() {
                                    TextView success = (TextView) findViewById(R.id.Notifi);
                                    success.setText("No se ha podido realizar la compra.");
                                    success.setVisibility(View.VISIBLE);
                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
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
                        Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                        startActivity(i);
                    }
                });

            }
        });

        pinkCard = findViewById(R.id.pinkCard);

        pinkCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idplayer = "ejemplo";
                String iditem = "4";

                Log.d("Nombre item: baya","");
                Log.d("Id item: id ","");
                Log.d("Id player: id ","");


                apiTrappy.buy(iditem,idplayer).enqueue(new Callback<Void>() {
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
                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    startActivity(i);
                                }
                            }, 2000);
                        } else if (response.code() == 404) {
                            timer.schedule(new TimerTask() {
                                public void run() {
                                    TextView success = (TextView) findViewById(R.id.Notifi);
                                    success.setText("No se ha podido realizar la compra.");
                                    success.setVisibility(View.VISIBLE);
                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
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
                        Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                        startActivity(i);
                    }
                });

            }
        });

        virtualCard = findViewById(R.id.virtualCard);

        virtualCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idplayer = "ejemplo";
                String iditem = "5";

                Log.d("Nombre item: magnesio","");
                Log.d("Id item: id ","");
                Log.d("Id player: id ","");


                apiTrappy.buy(iditem,idplayer).enqueue(new Callback<Void>() {
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
                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    startActivity(i);
                                }
                            }, 2000);
                        } else if (response.code() == 404) {
                            timer.schedule(new TimerTask() {
                                public void run() {
                                    TextView success = (TextView) findViewById(R.id.Notifi);
                                    success.setText("No se ha podido realizar la compra.");
                                    success.setVisibility(View.VISIBLE);
                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
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
                        Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                        startActivity(i);
                    }
                });

            }
        });

        scientistCard = findViewById(R.id.scientistCard);

        scientistCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idplayer = "ejemplo";
                String iditem = "6";

                Log.d("Nombre item: escudo","");
                Log.d("Id item: id ","");
                Log.d("Id player: id ","");


                apiTrappy.buy(iditem,idplayer).enqueue(new Callback<Void>() {
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
                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    startActivity(i);
                                }
                            }, 2000);
                        } else if (response.code() == 404) {
                            timer.schedule(new TimerTask() {
                                public void run() {
                                    TextView success = (TextView) findViewById(R.id.Notifi);
                                    success.setText("No se ha podido realizar la compra.");
                                    success.setVisibility(View.VISIBLE);
                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
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
                        Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                        startActivity(i);
                    }
                });

            }
        });


    }
}