package com.example.aaaa.api;


import android.os.StrictMode;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    public static final String BASE_URL = "http://10.0.2.2:8080/dsaApp/";
    private static Client instance = null;
    private APITrappy apiTrappy;
    public static synchronized Client getInstance(){
        if(instance==null){
            instance = new Client();
        }
        return instance;
    }
    private Client() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        //IA me propone esto, que es incluir Logging (Log class) for logging instead of printing

        //HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> Log.d(TAG, message));
        //interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        // OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiTrappy = retrofit.create(APITrappy.class);
    }
    public APITrappy getApiTrappy(){ return apiTrappy; }

}
