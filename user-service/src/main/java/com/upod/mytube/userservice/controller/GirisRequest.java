package com.upod.mytube.userservice.controller;

public class GirisRequest {

    private final String email;
    private final String sifre;

    public GirisRequest(String email, String sifre) {
        this.email = email;
        this.sifre = sifre;
    }

    public String getEmail() {
        return email;
    }

    public String getSifre() {
        return sifre;
    }

    

}
