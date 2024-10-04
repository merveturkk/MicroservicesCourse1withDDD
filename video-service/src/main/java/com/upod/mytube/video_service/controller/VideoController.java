package com.upod.mytube.video_service.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.upod.mytube.video_service.domain.Video;
import com.upod.mytube.video_service.service.VideoServisi;

@RestController
@RequestMapping("/videolar")
public class VideoController {
    private final VideoServisi videoServisi;

    public VideoController(VideoServisi videoServisi) {
        this.videoServisi = videoServisi;
    }

    @PostMapping(value = "/yukle", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> videoYukle(
            @RequestParam("yukleyiciId") Long yukleyiciId,
            @RequestParam("kanalId") Long kanalId,
            @RequestParam("baslik") String baslik,
            @RequestParam("aciklama") String aciklama,
            @RequestParam("etiketler") Set<String> etiketler,
            @RequestParam("dosya") MultipartFile dosya) {
        try {
            Long videoId = videoServisi.videoYukle(yukleyiciId, kanalId, baslik, aciklama, etiketler, dosya.getInputStream());
            return ResponseEntity.ok(Collections.singletonMap("videoId", videoId));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Dosya yükleme başarısız");
        }
    }

    @GetMapping("/{videoId}")
    public ResponseEntity<?> videoGetir(@PathVariable Long videoId) {
        Optional<Video> videoOpt = videoServisi.videoBul(videoId);
        if (videoOpt.isPresent()) {
            Video video = videoOpt.get();
            return ResponseEntity.ok(video);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Video bulunamadı");
        }
    }

}

