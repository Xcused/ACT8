package com.example.EXC2.database;

public class Friends {
    String id;
    String name;
    String contact;
    public Friends() {
    }
    public Friends(String id, String nama, String telpon) {
        this.id = id;
        this.name = nama;
        this.contact = telpon;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNama() {
        return name;
    }
    public void setNama(String nama) {
        this.name = nama;
    }
    public String getTelpon() {
        return contact;
    }
    public void setTelpon(String telpon) {
        this.contact = telpon;
    }
}