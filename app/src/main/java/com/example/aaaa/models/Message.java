package com.example.aaaa.models;

import java.util.ArrayList;
import java.util.List;

public class Message {
    public static List<Item> getData() {
        // Aquí deberías crear y devolver una lista de artículos
        List<Item> articles = new ArrayList<>();

        // Agregar artículos a la lista (esto es solo un ejemplo, ajusta según tus necesidades)
        articles.add(new Item("Magnesio", "Stat: +20 speed" ,"Type: Potion", 10, "1"));
        articles.add(new Item("Escudo de madera", "Stat: +5 armor","Type: Weapon",10,"2" ));
        articles.add(new Item("Media Baya", "Stat: +2 hearts","Type: Food",25,"3" ));
        articles.add(new Item("Baya", "Stat: +4 hearts","Type: Food", 40,"4" ));
        articles.add(new Item("Creatina", "Stat: +30 strength","Type: Potion", 10,"5"));
        articles.add(new Item("Escudo de metal", "Stat: +50 armor","Type: Weapon", 50 ,"6"));
        articles.add(new Item("Mariposa" ,"Stat: +30 attack","Type: Weapon", 100,"7"));
        articles.add(new Item("Baya dorada", "Stat: Full Health","Type: Food", 150,"8" ));

        return articles;
    }
    public Message(){

    }
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
}