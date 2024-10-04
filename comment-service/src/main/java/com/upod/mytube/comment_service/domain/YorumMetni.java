package com.upod.mytube.comment_service.domain;

// Yorum Metni Değer Nesnesi
public class YorumMetni {
    private final String deger;

    public YorumMetni(String deger) {
        if (deger == null || deger.isEmpty()) {
            throw new IllegalArgumentException("Yorum metni boş olamaz");
        }
        if (deger.length() > 500) {
            throw new IllegalArgumentException("Yorum metni 500 karakterden fazla olamaz");
        }
        this.deger = deger;
    }

    public String getDeger() {
        return deger;
    }
}
