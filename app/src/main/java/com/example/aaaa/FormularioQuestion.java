package com.example.aaaa;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aaaa.api.APITrappy;
import com.example.aaaa.api.Client;
import com.example.aaaa.models.Question;
import com.example.aaaa.models.Usuario;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FormularioQuestion extends AppCompatActivity {

    private String date;
    private APITrappy APIservice;
    private TextInputEditText titleInput;
    private TextInputEditText messageInput;
    private TextInputEditText senderInput;
    private TextView notif;
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
        setContentView(R.layout.activity_formulario_question);
        this.date=obtainDate("yyyy-MM-dd");
        getCredenciales();
    }

    public void returnFunction(View view) {
        Intent intentRegister = new Intent(FormularioQuestion.this, NewHome.class);
        setCredenciales(intentRegister);
    }

    public void questionReport(View view){
        titleInput = findViewById(R.id.titleNametext);
        messageInput = findViewById(R.id.messageNametext);
        senderInput = findViewById(R.id.senderNametext);
        Question a = new Question(this.date, this.titleInput.getText().toString(),this.messageInput.getText().toString(),this.senderInput.getText().toString());
        APIservice = Client.getInstance().getApiTrappy();
        Call<Void> call = APIservice.postQuestion(a);
        notif = findViewById(R.id.textViewNotif);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {
                    case 200:
                        notif.setText("Tu pregunta se ha envíado correctamente");
                        notif.setVisibility(View.VISIBLE);
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Tu código de actualización de la interfaz de usuario va aquí

                                Intent i = new Intent(FormularioQuestion.this, FormularioQuestion.class);
                                setCredenciales(i);
                            }
                        }, 2000);  // El retraso en milisegundos antes de que se ejecute tu código
                        break;
                    case 500:
                        notif.setText("VALIDATION ERROR");
                        notif.setVisibility(View.VISIBLE);
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Tu código de actualización de la interfaz de usuario va aquí

                                Intent i = new Intent(FormularioQuestion.this, FormularioQuestion.class);
                                setCredenciales(i);
                            }
                        }, 2000);  // El retraso en milisegundos antes de que se ejecute tu código
                        break;
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showSnackbar(view, "There is a network failure");
            }
        });
    }

    private void setLabel() {
        titleInput.setText("");
        messageInput.setText("");
        senderInput.setText("");
    }

    private void showSnackbar(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @SuppressLint("SimpleDateFormat")
    private String obtainDate(String s) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(s);
        return simpleDateFormat.format(date);
    }
}