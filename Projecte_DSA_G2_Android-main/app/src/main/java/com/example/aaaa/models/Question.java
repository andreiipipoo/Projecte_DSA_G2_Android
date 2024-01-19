package com.example.aaaa.models;

public class Question {
    private String date;
    private String title;
    private String message;
    private String sender;

    public Question(){}

    // Constructor
    public Question(String date, String title, String message, String sender){
        this.date=date;
        this.title=title;
        this.message=message;
        this.sender=sender;
    }

    // Métodos de acceso (getters y setters)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}