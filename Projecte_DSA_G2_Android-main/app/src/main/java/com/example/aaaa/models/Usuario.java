package com.example.aaaa.models;

public class Usuario {
    private String username;
    private String password;
    private String tlf;
    private String mail;

    public Usuario(){
    }
    public Usuario (String username, String password, String tlf, String mail){
        this.username = username;
        this.password = password;
        this.tlf = tlf;
        this.mail = mail;
    }
    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}
    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}
    public String getTlf(){return tlf;}
    public void setTlf(String tlf){this.tlf = tlf;}
    public String getMail(){return mail;}
    public void setMail(String mail){this.mail = mail;}
    public String toString(){
        return username;
    }
}
