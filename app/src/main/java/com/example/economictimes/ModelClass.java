package com.example.economictimes;

public class ModelClass {
    public int id;
    public String text_url;

    public ModelClass(String text_url) {
        this.id = id;
        this.text_url = text_url;
    }
    public ModelClass(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getText_url() {
        return text_url;
    }

    public void setText_url(String text_url) {
        this.text_url = text_url;
    }
}
