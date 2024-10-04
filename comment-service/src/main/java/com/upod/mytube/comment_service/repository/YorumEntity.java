package com.upod.mytube.comment_service.repository;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.upod.mytube.comment_service.domain.Yorum;
import com.upod.mytube.comment_service.domain.YorumDurumu;
import com.upod.mytube.comment_service.domain.YorumMetni;

@Table(name = "YORUMLAR")
public class YorumEntity {
    @Id
    private Long id;

    private Long videoId;
    private Long yazarKullaniciId;
    private String metin;
    private YorumDurumu durum;
    private Instant olusturulmaZamani;
    private Instant guncellemeZamani;

    // Getter ve Setter'lar

    public static YorumEntity fromDomain(Yorum yorum) {
        YorumEntity entity = new YorumEntity();
        entity.setId(yorum.getYorumId());
        entity.setVideoId(yorum.getVideoId());
        entity.setYazarKullaniciId(yorum.getYazarKullaniciId());
        entity.setMetin(yorum.getMetin().getDeger());
        entity.setDurum(yorum.getDurum());
        entity.setOlusturulmaZamani(yorum.getOlusturulmaZamani());
        entity.setGuncellemeZamani(yorum.getGuncellemeZamani());
        return entity;
    }

    public Yorum toDomain() {
        return new Yorum(
                id,
                videoId,
                yazarKullaniciId,
                new YorumMetni(metin)
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public Long getYazarKullaniciId() {
        return yazarKullaniciId;
    }

    public void setYazarKullaniciId(Long yazarKullaniciId) {
        this.yazarKullaniciId = yazarKullaniciId;
    }

    public String getMetin() {
        return metin;
    }

    public void setMetin(String metin) {
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

