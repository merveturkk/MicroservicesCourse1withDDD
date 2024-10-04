package com.upod.mytube.userservice.domain;

import org.springframework.util.DigestUtils;

// Şifre Değer Nesnesi
public class Sifre {

    private final String hashlenmisDeger;

    public Sifre(String hamSifre) {
        if (hamSifre == null || hamSifre.length() < 8) {
            throw new IllegalArgumentException("Şifre en az 8 karakter olmalıdır");
        }
        this.hashlenmisDeger = sifreyiHashle(hamSifre);
    }

    private String sifreyiHashle(String hamSifre) {
        // BCrypt veya benzeri bir algoritma kullanarak şifreyi hashleyin
        return DigestUtils.md5DigestAsHex(hamSifre.getBytes());
    }

    public boolean dogrula(String girilenSifre) {
        // BCrypt veya benzeri bir algoritma kullanarak şifreyi dogrulayin
        return DigestUtils.md5Digest(girilenSifre.getBytes()).equals(this.hashlenmisDeger);
    }

    public String getHashlenmisDeger() {
        return hashlenmisDeger;
    }

}
