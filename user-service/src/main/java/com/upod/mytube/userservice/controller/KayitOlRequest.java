package com.upod.mytube.userservice.controller;

public class KayitOlRequest {

    private final String email;
    private final String sifre;
    private final String ad;
    private final String soyad;

    public KayitOlRequest(String ad, String email, String sifre, String soyad) {
        this.ad = ad;
        this.email = email;
        this.sifre = sifre;
        this.soyad = soyad;
    }

    public String getEmail() {
        return email;
    }

    public String getSifre() {
        return sifre;
    }

    public String getAd() {
        return ad;
    }

    public String getSoyad() {
        return soyad;
    }

}
