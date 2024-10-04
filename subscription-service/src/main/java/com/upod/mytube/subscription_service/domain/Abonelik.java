package com.upod.mytube.subscription_service.domain;

// Abonelik Varlığı
public class Abonelik {
    private final Long abonelikId;
    private final Long kullaniciId; // Abone olan kullanıcı
    private final Long kanalId;     // Abone olunan kanal
    private AbonelikTarihi abonelikTarihi;

    public Abonelik(Long abonelikId, Long kullaniciId, Long kanalId, AbonelikTarihi abonelikTarihi) {
        this.abonelikId = abonelikId;
        this.kullaniciId = kullaniciId;
        this.kanalId = kanalId;
        this.abonelikTarihi = abonelikTarihi;
    }

    public Long getAbonelikId() {
        return abonelikId;
    }

    public Long getKullaniciId() {
        return kullaniciId;
    }

    public Long getKanalId() {
        return kanalId;
    }

    public AbonelikTarihi getAbonelikTarihi() {
        return abonelikTarihi;
    }
}

