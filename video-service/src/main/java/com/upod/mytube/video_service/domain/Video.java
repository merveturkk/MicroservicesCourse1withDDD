package com.upod.mytube.video_service.domain;

import java.time.Instant;

// Video Varlığı
public class Video {
    private final Long videoId;
    private VideoBasligi baslik;
    private String aciklama;
    private final Long kanalId; // Kanal varlığına referans
    private final Long yukleyiciId; // Kullanıcı varlığına referans
    private final Etiketler etiketler;
    private VideoDurumu durum;
    private final Instant yuklemeZamani;
    private Instant guncellemeZamani;

    public Video(Long videoId, VideoBasligi baslik, String aciklama, Long kanalId, Long yukleyiciId, Etiketler etiketler) {
        this.videoId = videoId;
        this.baslik = baslik;
        this.aciklama = aciklama;
        this.kanalId = kanalId;
        this.yukleyiciId = yukleyiciId;
        this.etiketler = etiketler;
        this.durum = VideoDurumu.INCELEMEDE;
        this.yuklemeZamani = Instant.now();
        this.guncellemeZamani = Instant.now();
    }

    // İş kuralları ve davranışlar
    public void baslikGuncelle(VideoBasligi yeniBaslik) {
        this.baslik = yeniBaslik;
        this.guncellemeZamani = Instant.now();
    }

    public void aciklamaGuncelle(String yeniAciklama) {
        this.aciklama = yeniAciklama;
        this.guncellemeZamani = Instant.now();
    }

    public void durumDegistir(VideoDurumu yeniDurum) {
        this.durum = yeniDurum;
        this.guncellemeZamani = Instant.now();
    }

    public VideoBasligi getBaslik() {
        return baslik;
    }

    public String getAciklama() {
        return aciklama;
    }

    public Etiketler getEtiketler() {
        return etiketler;
    }

    public VideoDurumu getDurum() {
        return durum;
    }

    public Long getVideoId() {
        return videoId;
    }

    public Long getKanalId() {
        return kanalId;
    }

    public Long getYukleyiciId() {
        return yukleyiciId;
    }

    public Instant getYuklemeZamani() {
        return yuklemeZamani;
    }

    public Instant getGuncellemeZamani() {
        return guncellemeZamani;
    }  
    
}

