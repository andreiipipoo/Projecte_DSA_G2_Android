package com.example.aaaa.models;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;
import com.example.aaaa.Perfil;


public class Insignia {

    private String username;
    private String uriImagen;

    public Insignia(String username, String uriImagen){
        this.username=username;
        this.uriImagen=uriImagen;
    }
    public String getNombre() {
        return username;
    }

    public void setNombre(String username) {
        this.username = username;
    }

    public String getUri(){return uriImagen;}

    public void setUri(String uriImagen){this.uriImagen=uriImagen;}

    public static List<Insignia> getData(String[] vector){

        List<Insignia> insignias = new ArrayList<>();

        for (int i=0;i<vector.length;i++){
            insignias.add(new Insignia(vector[i],vector[i+1]));
            i++;
        }
        return insignias;
    }


}
