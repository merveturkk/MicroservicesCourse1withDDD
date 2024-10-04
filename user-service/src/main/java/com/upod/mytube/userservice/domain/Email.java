package com.upod.mytube.userservice.domain;

// Email Değer Nesnesi
public class Email {

    private final String deger;

    public Email(String deger) {
        if (deger == null || !deger.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Geçersiz e-posta adresi");
        }
        this.deger = deger;
    }

    public String getDeger() {
        return deger;
    }

}
