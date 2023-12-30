package com.example.aaaa.models;

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

    static String[] vector = Perfil.getVector();
    public static List<Insignia> getData(){

        List<Insignia> insignias = new ArrayList<>();

        insignias.add(new Insignia("master del universo", "https://cdn.pixabay.com/photo/2017/07/11/15/51/kermit-2493979_1280.png"));
        insignias.add(new Insignia("rey/reina de xxx",".."));
        insignias.add(new Insignia("becario enfurismado","https://cdn.pixabay.com/photo/2017/07/11/15/51/kermit-2493979_1280.png"));
        for (int i=0;i<vector.length;i++){
            insignias.add(new Insignia(vector[i],vector[i+1]));
            i++;
        }
        return insignias;
    }

}
