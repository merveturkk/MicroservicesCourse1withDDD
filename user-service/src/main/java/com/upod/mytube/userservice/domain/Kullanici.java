package com.upod.mytube.userservice.domain;

import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

// Kullanıcı Varligi
public class Kullanici {

    private final Long kullaniciId;
    private final Email email;
    private final Sifre sifre;
    private final ProfilBilgileri profilBilgileri;
    private final Set<Rol> roller;
    private final Instant kayitTarihi;

    public Kullanici(Long kullaniciId, Email email, Sifre sifre, ProfilBilgileri profilBilgileri) {
        this.kullaniciId = kullaniciId;
        this.email = email;
        this.sifre = sifre;
        this.profilBilgileri = profilBilgileri;
        this.roller = new HashSet<>();
        this.kayitTarihi = Instant.now();
    }

    // Is kurallari ve davranislar
    public void rolEkle(Rol rol) {
        this.roller.add(rol);
    }

    public boolean izinVarMi(Izin izin) {
        return roller.stream().anyMatch(rol -> rol.getIzinler().contains(izin));
    }

    public boolean sifreDogrula(String girilenSifre) {
        return sifre.dogrula(girilenSifre);
    }

    public Email getEmail() {
        return email;
    }

    public Sifre getSifre() {
        return sifre;
    }

    public Instant getKayitTarihi() {
        return kayitTarihi;
    }

    public Long getKullaniciId() {
        return kullaniciId;
    }

    public ProfilBilgileri getProfilBilgileri() {
        return profilBilgileri;
    }

    public Set<Rol> getRoller() {
        return Collections.unmodifiableSet(roller);
    }

}