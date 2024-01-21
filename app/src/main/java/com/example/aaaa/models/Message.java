package com.example.aaaa.models;

import java.util.ArrayList;
import java.util.List;

public class Message {
    private String cuerpoMensaje;

    public Message(String cuerpoMensaje) {
        this.cuerpoMensaje=cuerpoMensaje;
    }

    public String getCuerpoMensaje() {
        return cuerpoMensaje;
    }

    public void setCuerpoMensaje(String cuerpoMensaje) {
        this.cuerpoMensaje = cuerpoMensaje;
    }
    public static List<Message> getData() {
        // Aquí deberías crear y devolver una lista de artículos
        List<Message> articles = new ArrayList<>();

        // Agregar artículos a la lista (esto es solo un ejemplo, ajusta según tus necesidades)
        articles.add(new Message("Nuevas skins han sido añadidas en la tienda de Croacky!"));
        articles.add(new Message("Proximamente se añadiran nuevos niveles!"));
        articles.add(new Message("Se ha implementado un nuevo sistema de reportes!"));
        articles.add(new Message("Charly ha sido baneado por 1 semana!"));

        return articles;
    }

}