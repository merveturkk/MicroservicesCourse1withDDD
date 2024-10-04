package com.upod.mytube.userservice.repository;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import com.upod.mytube.userservice.domain.Email;
import com.upod.mytube.userservice.domain.Kullanici;
import com.upod.mytube.userservice.domain.ProfilBilgileri;
import com.upod.mytube.userservice.domain.Rol;
import com.upod.mytube.userservice.domain.Sifre;

@Table(name = "KULLANICILAR")
public class KullaniciEntity {
    @Id
    private Long id;

    private String email;
    private String sifre;
    private String ad;
    private String soyad;
    private String profilResmiUrl;
    private Instant kayitTarihi;

    @MappedCollection(idColumn= "KULLANICI_ID")
    private Set<RolEntity> roller;

    public static KullaniciEntity fromDomain(Kullanici kullanici) {
        KullaniciEntity entity = new KullaniciEntity();
        entity.setId(kullanici.getKullaniciId());
        entity.setEmail(kullanici.getEmail().getDeger());
        entity.setSifre(kullanici.getSifre().getHashlenmisDeger());
        entity.setAd(kullanici.getProfilBilgileri().getAd());
        entity.setSoyad(kullanici.getProfilBilgileri().getSoyad());
        entity.setProfilResmiUrl(kullanici.getProfilBilgileri().getProfilResmiUrl());
        entity.setKayitTarihi(kullanici.getKayitTarihi());
        entity.setRoller(
            kullanici.getRoller().stream()
            .map(RolEntity::fromDomain)
            .collect(Collectors.toSet()));
        return entity;
    }

    public Kullanici toDomain() {
        ProfilBilgileri profilBilgileri = new ProfilBilgileri(ad, soyad, profilResmiUrl);
        Kullanici kullanici = new Kullanici(id, new Email(email), new Sifre(sifre), profilBilgileri);
        roller.forEach(
            rolEntity -> kullanici.rolEkle(
                new Rol(null, rolEntity.getIsim(), new HashSet<>())));
        return kullanici;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long kullaniciId) {
        this.id = kullaniciId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getProfilResmiUrl() {
        return profilResmiUrl;
    }

    public void setProfilResmiUrl(String profilResmiUrl) {
        this.profilResmiUrl = profilResmiUrl;
    }

    public Instant getKayitTarihi() {
        return kayitTarihi;
    }

    public void setKayitTarihi(Instant kayitTarihi) {
        this.kayitTarihi = kayitTarihi;
    }

    public Set<RolEntity> getRoller() {
        return roller;
    }

    public void setRoller(Set<RolEntity> roller) {
        this.roller = roller;
    }

}
