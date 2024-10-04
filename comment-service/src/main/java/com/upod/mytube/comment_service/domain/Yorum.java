package com.upod.mytube.comment_service.domain;

// Yorum Varlığı

import java.time.Instant;

public class Yorum {
    
    private final Long yorumId;
    private final Long videoId;
    private final Long yazarKullaniciId;
    private YorumMetni metin;
    private YorumDurumu durum;
    private Instant olusturulmaZamani;
    private Instant guncellemeZamani;

    public Yorum(Long yorumId, Long videoId, Long yazarKullaniciId, YorumMetni metin) {
        this.yorumId = yorumId;
        this.videoId = videoId;
        this.yazarKullaniciId = yazarKullaniciId;
        this.metin = metin;
        this.durum = YorumDurumu.INCELEMEDE;
        this.olusturulmaZamani = Instant.now();
        this.guncellemeZamani = Instant.now();
    }

    // İş kuralları ve davranışlar
    public void metinGuncelle(YorumMetni yeniMetin) {
        this.metin = yeniMetin;
        this.guncellemeZamani = Instant.now();
    }

    public void durumDegistir(YorumDurumu yeniDurum) {
        this.durum = yeniDurum;
        this.guncellemeZamani = Instant.now();
    }

    public Long getYorumId() {
        return yorumId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public Long getYazarKullaniciId() {
        return yazarKullaniciId;
    }

    public YorumMetni getMetin() {
        return metin;
    }

    public void setMetin(YorumMetni metin) {
        this.metin = metin;
    }

    public YorumDurumu getDurum() {
        return durum;
    }

    public void setDurum(YorumDurumu durum) {
        this.durum = durum;
    }

    public Instant getOlusturulmaZamani() {
        return olusturulmaZamani;
    }

    public void setOlusturulmaZamani(Instant olusturulmaZamani) {
        this.olusturulmaZamani = olusturulmaZamani;
    }

    public Instant getGuncellemeZamani() {
        return guncellemeZamani;
    }

    public void setGuncellemeZamani(Instant guncellemeZamani) {
        this.guncellemeZamani = guncellemeZamani;
    }

}

