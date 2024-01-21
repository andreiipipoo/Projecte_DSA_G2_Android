package com.example.aaaa;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
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
    String usercreds;
    String passcreds;
    Integer coins;
    TextView saldo;
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
        setContentView(R.layout.activity_shop_dashboard);
        apiTrappy = Client.getInstance().getApiTrappy();
        getCredenciales();
        saldo = findViewById(R.id.saldoText);
        volver3 = findViewById(R.id.volver4);
        saldo.setText("Tus croCoins: "+coins);

        Timer timer = new Timer();
        volver3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (ShopDashboard.this, NewHome.class);
                setCredenciales(i);
            }
        });

        ranaCard = findViewById(R.id.ranaCard);

        ranaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String iditem = "1";

                Log.d("Nombre item: botas","");
                Log.d("Id item: id ",iditem);
                Log.d("Id player: id ",usercreds);



                apiTrappy.buy(iditem,usercreds).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response){
                        Log.d("Código de compra: ", String.valueOf(response));
                        //Aquí van los diferentes códigos que recibimos (compra completada con éxito o no)
                        if (response.code()==200) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("Ya tienes esta skin");
                            success.setVisibility(View.VISIBLE);
                            coins = coins - 0;
                            saldo.setText("Tus croCoins: "+coins);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        } else if (response.code() == 402) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("No dispones de crocoins suficientes");
                            success.setVisibility(View.VISIBLE);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        } else if (response.code() == 404) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("No se ha podido realizar la compra");
                            success.setVisibility(View.VISIBLE);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        } else if (response.code() == 405) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("No se ha podido realizar la compra");
                            success.setVisibility(View.VISIBLE);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        } else if (response.code() == 409) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("Ya tienes esta skin");
                            success.setVisibility(View.VISIBLE);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        String msg = "Error in retrofit: " + t.toString();
                        Log.d("Mensaje error Register", msg);
                        Toast.makeText(getApplicationContext(), "msg", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                        setCredenciales(i);
                    }
                });
            }
        });

        desiertoCard = findViewById(R.id.desiertoCard);

        desiertoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String iditem = "2";

                Log.d("Nombre item:","espada");
                Log.d("Id item: id ",iditem);
                Log.d("Id player: id ",usercreds);


                apiTrappy.buy(iditem,usercreds).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response){
                        Log.d("Código de compra: ", String.valueOf(response));
                        //Aquí van los diferentes códigos que recibimos (compra completada con éxito o no)
                        if (response.code()==200) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("Compra realizada con éxito.");
                            success.setVisibility(View.VISIBLE);
                            coins = coins - 40;
                            saldo.setText("Tus croCoins: "+coins);

                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        } else if (response.code() == 402) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("No dispones de crocoins suficientes");
                            success.setVisibility(View.VISIBLE);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        } else if (response.code() == 404) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("No se ha podido realizar la compra");
                            success.setVisibility(View.VISIBLE);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        } else if (response.code() == 405) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("No se ha podido realizar la compra");
                            success.setVisibility(View.VISIBLE);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        } else if (response.code() == 409) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("Ya tienes esta skin");
                            success.setVisibility(View.VISIBLE);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        String msg = "Error in retrofit: " + t.toString();
                        Log.d("Mensaje error Register", msg);
                        Toast.makeText(getApplicationContext(), "msg", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                        setCredenciales(i);
                    }
                });
            }
        });

        maskCard = findViewById(R.id.maskCard);

        maskCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String iditem = "3";

                Log.d("Nombre item: pocion","");
                Log.d("Id item: id ",iditem);
                Log.d("Id player: id ",usercreds);


                apiTrappy.buy(iditem,usercreds).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response){
                        Log.d("Código de compra: ", String.valueOf(response));
                        //Aquí van los diferentes códigos que recibimos (compra completada con éxito o no)
                        if (response.code()==200) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("Compra realizada con éxito.");
                            success.setVisibility(View.VISIBLE);
                            coins = coins - 30;
                            saldo.setText("Tus croCoins: "+coins);

                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        } else if (response.code() == 402) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("No dispones de crocoins suficientes");
                            success.setVisibility(View.VISIBLE);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        } else if (response.code() == 404) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("No se ha podido realizar la compra");
                            success.setVisibility(View.VISIBLE);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        } else if (response.code() == 405) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("No se ha podido realizar la compra");
                            success.setVisibility(View.VISIBLE);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        } else if (response.code() == 409) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("Ya tienes esta skin");
                            success.setVisibility(View.VISIBLE);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        String msg = "Error in retrofit: " + t.toString();
                        Log.d("Mensaje error Register", msg);
                        Toast.makeText(getApplicationContext(), "msg", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                        setCredenciales(i);
                    }
                });
            }
        });

        pinkCard = findViewById(R.id.pinkCard);

        pinkCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String iditem = "4";

                Log.d("Nombre item: baya","");
                Log.d("Id item: id ",iditem);
                Log.d("Id player: id ",usercreds);


                apiTrappy.buy(iditem,usercreds).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response){
                        Log.d("Código de compra: ", String.valueOf(response));
                        //Aquí van los diferentes códigos que recibimos (compra completada con éxito o no)
                        if (response.code()==200) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("Compra realizada con éxito.");
                            success.setVisibility(View.VISIBLE);
                            coins = coins - 60;
                            saldo.setText("Tus croCoins: "+coins);

                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        } else if (response.code() == 402) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("No dispones de crocoins suficientes");
                            success.setVisibility(View.VISIBLE);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        } else if (response.code() == 404) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("No se ha podido realizar la compra");
                            success.setVisibility(View.VISIBLE);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        } else if (response.code() == 405) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("No se ha podido realizar la compra");
                            success.setVisibility(View.VISIBLE);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        } else if (response.code() == 409) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("Ya tienes esta skin");
                            success.setVisibility(View.VISIBLE);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        String msg = "Error in retrofit: " + t.toString();
                        Log.d("Mensaje error Register", msg);
                        Toast.makeText(getApplicationContext(), "msg", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                        setCredenciales(i);
                    }
                });
            }
        });

        virtualCard = findViewById(R.id.virtualCard);

        virtualCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String iditem = "5";

                Log.d("Nombre item: magnesio","");
                Log.d("Id item: id ",iditem);
                Log.d("Id player: id ",usercreds);


                apiTrappy.buy(iditem,usercreds).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response){
                        Log.d("Código de compra: ", String.valueOf(response));
                        //Aquí van los diferentes códigos que recibimos (compra completada con éxito o no)
                        if (response.code()==200) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("Compra realizada con éxito.");
                            success.setVisibility(View.VISIBLE);
                            coins = coins - 50;
                            saldo.setText("Tus croCoins: "+coins);

                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        } else if (response.code() == 402) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("No dispones de crocoins suficientes");
                            success.setVisibility(View.VISIBLE);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        } else if (response.code() == 404) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("No se ha podido realizar la compra");
                            success.setVisibility(View.VISIBLE);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        } else if (response.code() == 405) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("No se ha podido realizar la compra");
                            success.setVisibility(View.VISIBLE);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        } else if (response.code() == 409) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("Ya tienes esta skin");
                            success.setVisibility(View.VISIBLE);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        String msg = "Error in retrofit: " + t.toString();
                        Log.d("Mensaje error Register", msg);
                        Toast.makeText(getApplicationContext(), "msg", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                        setCredenciales(i);
                    }
                });
            }
        });

        scientistCard = findViewById(R.id.scientistCard);

        scientistCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String iditem = "6";

                Log.d("Nombre item: escudo","");
                Log.d("Id item: id ",iditem);
                Log.d("Id player: id ",usercreds);


                apiTrappy.buy(iditem,usercreds).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response){
                        Log.d("Código de compra: ", String.valueOf(response));
                        //Aquí van los diferentes códigos que recibimos (compra completada con éxito o no)
                        if (response.code()==200) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("Compra realizada con éxito.");
                            success.setVisibility(View.VISIBLE);
                            coins = coins - 50;
                            saldo.setText("Tus croCoins: "+coins);

                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        } else if (response.code() == 402) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("No dispones de crocoins suficientes");
                            success.setVisibility(View.VISIBLE);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        } else if (response.code() == 404) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("No se ha podido realizar la compra");
                            success.setVisibility(View.VISIBLE);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        } else if (response.code() == 405) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("No se ha podido realizar la compra");
                            success.setVisibility(View.VISIBLE);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        } else if (response.code() == 409) {
                            TextView success = (TextView) findViewById(R.id.notif2);
                            success.setText("Ya tienes esta skin");
                            success.setVisibility(View.VISIBLE);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tu código de actualización de la interfaz de usuario va aquí

                                    Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                                    setCredenciales(i);
                                }
                            }, 1500);  // El retraso en milisegundos antes de que se ejecute tu código
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        String msg = "Error in retrofit: " + t.toString();
                        Log.d("Mensaje error Register", msg);
                        Toast.makeText(getApplicationContext(), "msg", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ShopDashboard.this, ShopDashboard.class);
                        setCredenciales(i);
                    }
                });
            }
        });
    }
}