package com.example.aaaa.models;

public class Usuario {
    private String username;
    private String password;
    private String telephone;
    private String email;
    private String id;
    private int croCoins;

    public Usuario(){
    }
    public Usuario (String username, String password, String tlf, String email,String id, int croCoins){
        this.username = username;
        this.password = password;
        this.telephone = tlf;
        this.email = email;
        this.id=id;
        this.croCoins=croCoins;
    }

    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}
    public String getId(){return id;}
    public void setId(String id){this.id=id;}
    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}
    public String getTlf(){return telephone;}
    public void setTlf(String tlf){this.telephone = tlf;}
    public String getMail(){return email;}
    public void setMail(String email){this.email = email;}
    public String toString(){
        return username;
    }
    public int getCroCoins(){return croCoins;}
    public void setCroCoins(int croCoins){this.croCoins=croCoins;}
}
