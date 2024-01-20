package com.example.aaaa.api;

import java.util.List;

import com.example.aaaa.models.*;
import com.example.aaaa.models.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APITrappy {

        @POST("player/{username}")
        Call<Usuario> recibirInsignia(@Path ("username") String username);


        @POST("player/login")
        Call<Void> login(@Body LoginModel login);
        /*
        @POST("player/register")
        Call<Usuario> register(@Body RegisterModel registerModel);
        @POST("/dsaApp/jugador/Login")
        Call<Void> logIn(@Body LoginModel logIn);
         */
        @POST("items/shop")
        Call<Void> comprar(@Body Item item);
        @POST("player/signup")
        Call<Void> register(@Body Usuario register);


        @POST("trappy/buyItem/{idItem}/{usernamePlayer}")
        Call<Void> buy(@Path ("idItem") String idItem, @Path ("usernamePlayer") String usernamePlayer);

        @GET("items/shop")
        Call<Item> getItems(@Body Item item);

        @POST("minims/addIssue")
        Call<Void> denunciar(@Body DenunciaModel denuncia);

        @POST("minims/addQuestionFromPlayer")
        Call<Void> postQuestion(@Body Question question);
        @GET("minims/getMessages")
        Call<List<Message>> getMessages();
        @POST("player/delete/{username}")
        Call<Void> delete(@Path ("username") String username);
}

