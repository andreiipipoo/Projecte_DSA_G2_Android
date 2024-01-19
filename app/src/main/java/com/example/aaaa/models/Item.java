package com.example.aaaa.models;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private String nombre;
    private String descripcion;
    private String type;
    private double price;
    private String id;

    // Constructor
    public Item(String nombre, String descripcion, String type, double price,String id) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.type=type;
        this.price=price;
        this.id=id;
    }

    // Métodos de acceso (getters y setters)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getType(){return type;}

    public void setType(String type){this.type=type;}

    public double getPrice(){return price;}

    public void setPrice(double price){this.price=price;}
    public String getId(){return id;}
    public void setId(){this.id=id;}

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
}
