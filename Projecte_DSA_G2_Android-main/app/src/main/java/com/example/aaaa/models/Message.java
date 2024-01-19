package com.example.aaaa.models;

import java.util.ArrayList;
import java.util.List;

public class Message {
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