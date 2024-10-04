package com.upod.mytube.video_service.repository;

import java.time.Instant;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.upod.mytube.video_service.domain.Etiketler;
import com.upod.mytube.video_service.domain.Video;
import com.upod.mytube.video_service.domain.VideoBasligi;
import com.upod.mytube.video_service.domain.VideoDurumu;

@Table(name = "VIDEOLAR")
public class VideoEntity {
    @Id
    private Long id;
    private String baslik;
    private String aciklama;
    private Long kanalId; // Kanal varlığına referans
    private Long yukleyiciId; // Kullanıcı varlığına referans
    private Set<String> etiketler;
    private VideoDurumu durum;
    private Instant yuklemeZamani;
    private Instant guncellemeZamani;

    public static VideoEntity fromDomain(Video video) {
        VideoEntity entity = new VideoEntity();
        entity.setId(video.getVideoId());
        entity.setBaslik(video.getBaslik().getDeger());
        entity.setAciklama(video.getAciklama());
        entity.setKanalId(video.getKanalId());
        entity.setYukleyiciId(video.getYukleyiciId());
        entity.setEtiketler(video.getEtiketler().getEtiketler());
        entity.setDurum(video.getDurum());
        entity.setYuklemeZamani(video.getYuklemeZamani());
        entity.setGuncellemeZamani(video.getGuncellemeZamani());
        return entity;
    }

    public Video toDomain() {
        return new Video(
                id,
                new VideoBasligi(baslik),
                aciklama,
                kanalId,
                yukleyiciId,
                new Etiketler(etiketler)
        );
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBaslik() {
        return baslik;
    }
    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }
    public String getAciklama() {
        return aciklama;
    }
    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }
    public Long getKanalId() {
        return kanalId;
    }
    public void setKanalId(Long kanalId) {
        this.kanalId = kanalId;
    }
    public Long getYukleyiciId() {
        return yukleyiciId;
    }
    public void setYukleyiciId(Long yukleyiciId) {
        this.yukleyiciId = yukleyiciId;
    }
    public Set<String> getEtiketler() {
        return etiketler;
    }
    public void setEtiketler(Set<String> set) {
        this.etiketler = set;
    }
    public VideoDurumu getDurum() {
        return durum;
    }
    public void setDurum(VideoDurumu durum) {
        this.durum = durum;
    }
    public Instant getYuklemeZamani() {
        return yuklemeZamani;
    }
    public void setYuklemeZamani(Instant yuklemeZamani) {
        this.yuklemeZamani = yuklemeZamani;
    }
    public Instant getGuncellemeZamani() {
        return guncellemeZamani;
    }
    public void setGuncellemeZamani(Instant guncellemeZamani) {
        this.guncellemeZamani = guncellemeZamani;
    }

}
