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

    public static List<Insignia> getData() {
        // Aquí deberías crear y devolver una lista de artículos
        List<Insignia> insignias = new ArrayList<>();

        // Agregar artículos a la lista (esto es solo un ejemplo, ajusta según tus necesidades)
        insignias.add(new Insignia("aaaa","https://cdn.pixabay.com/photo/2017/07/11/15/51/kermit-2493979_1280.png"));
        insignias.add(new Insignia("mario","https://cdn.discordapp.com/attachments/1171798174101413964/1198753837142515834/tienda1.png?ex=65c00d82&is=65ad9882&hm=b73df1768100738e9f10bbb2914a31196730a48932a78b4c52fd0a2030e00d8a&"));
        insignias.add(new Insignia("jajahaja","https://cdn.discordapp.com/attachments/1171798174101413964/1198754130001408140/wp5014469-1.png?ex=65c00dc8&is=65ad98c8&hm=50b7b80c545831b64b50fa69481f163defc56c8c3c0941a835c864495ba0601a&"));
        insignias.add(new Insignia("1234567890","https://cdn.pixabay.com/photo/2017/07/11/15/51/kermit-2493979_1280.png"));


        return insignias;
    }


}
