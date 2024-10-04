package com.upod.mytube.video_service.service;

import java.io.InputStream;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.upod.mytube.video_service.domain.Etiketler;
import com.upod.mytube.video_service.domain.Video;
import com.upod.mytube.video_service.domain.VideoBasligi;
import com.upod.mytube.video_service.repository.VideoEntity;
import com.upod.mytube.video_service.repository.VideoRepository;

@Service
public class VideoServisi {
    private final VideoRepository videoRepository;
    private final VideoDepolamaServisi videoDepolamaServisi;

    public VideoServisi(VideoRepository videoRepository, VideoDepolamaServisi videoDepolamaServisi) {
        this.videoRepository = videoRepository;
        this.videoDepolamaServisi = videoDepolamaServisi;
    }

    public Long videoYukle(Long yukleyiciId, Long kanalId, String baslik, String aciklama, Set<String> etiketler, InputStream videoStream) {
        // Girdi doğrulama
        VideoBasligi videoBasligi = new VideoBasligi(baslik);
        Etiketler etiketlerObj = new Etiketler(etiketler);

        // Video varlığını oluşturma
        Video video = new Video(null, videoBasligi, aciklama, kanalId, yukleyiciId, etiketlerObj);

        // Repository'e kaydetme
        VideoEntity videoEntity = videoRepository.save(VideoEntity.fromDomain(video));

 
        Long videoId = videoEntity.getId();
        // Video dosyasını depolama
        videoDepolamaServisi.videoDosyasiniDepola(videoEntity.getId(), videoStream);

        return videoId;
    }

    public Optional<Video> videoBul(Long videoId) {
        return videoRepository.findById(videoId).map(VideoEntity::toDomain);
    }

    public void videoBasligiGuncelle(Long videoId, VideoBasligi yeniBaslik) {
        Optional<VideoEntity> videoOpt = videoRepository.findById(videoId);
        if (videoOpt.isPresent()) {
            Video video = videoOpt.get().toDomain();
            video.baslikGuncelle(yeniBaslik);
            videoRepository.save(VideoEntity.fromDomain(video));
        } else {
            throw new RuntimeException("Video bulunamadı");
        }
    }

}

