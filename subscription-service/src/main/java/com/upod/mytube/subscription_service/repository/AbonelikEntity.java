package com.upod.mytube.subscription_service.repository;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.upod.mytube.subscription_service.domain.Abonelik;
import com.upod.mytube.subscription_service.domain.AbonelikTarihi;

@Table(name = "ABONELIKLER")
public class AbonelikEntity {
    @Id
    private Long id;

    private Long kullaniciId;
    private Long kanalId;
    private Instant abonelikTarihi;

    // Getter ve Setter'lar

    public static AbonelikEntity fromDomain(Abonelik abonelik) {
        AbonelikEntity entity = new AbonelikEntity();
        entity.setAbonelikId(abonelik.getAbonelikId());
        entity.setKullaniciId(abonelik.getKullaniciId());
        entity.setKanalId(abonelik.getKanalId());
        entity.setAbonelikTarihi(abonelik.getAbonelikTarihi().getDeger());
        return entity;
    }

    public Abonelik toDomain() {
        return new Abonelik(
            id,
            kullaniciId,
            kanalId,
            new AbonelikTarihi(abonelikTarihi)
        );
    }

    public Long getId() {
        return id;
    }

    public void setAbonelikId(Long id) {
        this.id = id;
    }

    public Long getKullaniciId() {
        return kullaniciId;
    }

    public void setKullaniciId(Long kullaniciId) {
        this.kullaniciId = kullaniciId;
    }

    public Long getKanalId() {
        return kanalId;
    }

    public void setKanalId(Long kanalId) {
        this.kanalId = kanalId;
    }

    public Instant getAbonelikTarihi() {
        return abonelikTarihi;
    }

    public void setAbonelikTarihi(Instant abonelikTarihi) {
        this.abonelikTarihi = abonelikTarihi;
    }


}

