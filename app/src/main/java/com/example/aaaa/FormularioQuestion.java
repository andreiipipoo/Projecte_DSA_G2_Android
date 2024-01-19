package com.example.aaaa;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_question);
        this.date=obtainDate("yyyy-MM-dd");
    }

    public void returnFunction(View view) {
        Intent intentRegister = new Intent(FormularioQuestion.this, LogIn.class);
        startActivity(intentRegister);
    }

    public void questionReport(View view){
        titleInput = findViewById(R.id.titleNametext);
        messageInput = findViewById(R.id.messageNametext);
        senderInput = findViewById(R.id.senderNametext);
        Question a = new Question(this.date, this.titleInput.getText().toString(),this.messageInput.getText().toString(),this.senderInput.getText().toString());
        APIservice = Client.getInstance().getApiTrappy();
        Call<Void> call = APIservice.postQuestion(a);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {
                    case 201:
                        Snackbar snaky201 = Snackbar.make(view, "Question has been send it!", 3000);
                        snaky201.show();
                        setLabel();
                        break;
                    case 403:
                        Snackbar snaky403 = Snackbar.make(view, "Error reporting the question", 3000);
                        snaky403.show();
                        break;
                    case 500:
                        Snackbar snaky500 = Snackbar.make(view, "Please fill in the sender text!", 3000);
                        snaky500.show();
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