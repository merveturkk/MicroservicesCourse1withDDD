package com.upod.mytube.userservice.domain;

public class ProfilBilgileri {

    private final String ad;
    private final String soyad;
    private final String profilResmiUrl;

    public ProfilBilgileri(String ad, String soyad, String profilResmiUrl) {
        this.ad = ad;
        this.soyad = soyad;
        this.profilResmiUrl = profilResmiUrl;
    }

    public String getAd() {
        return ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public String getProfilResmiUrl() {
        return profilResmiUrl;
    }

}
