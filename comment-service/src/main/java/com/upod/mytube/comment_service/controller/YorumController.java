package com.upod.mytube.comment_service.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.upod.mytube.comment_service.domain.Yorum;
import com.upod.mytube.comment_service.service.YorumServisi;

@RestController
@RequestMapping("/yorumlar")
public class YorumController {
    private final YorumServisi yorumServisi;

    public YorumController(YorumServisi yorumServisi) {
        this.yorumServisi = yorumServisi;
    }

    @PostMapping("/ekle")
    public ResponseEntity<?> yorumEkle(
            @RequestParam("videoId") Long videoId,
            @RequestParam("yazarKullaniciId") Long yazarKullaniciId,
            @RequestParam("metin") String metin) {
        Long yorumId = yorumServisi.yorumEkle(videoId, yazarKullaniciId, metin);
        return ResponseEntity.ok(Collections.singletonMap("yorumId", yorumId));
    }

    @GetMapping("/video/{videoId}")
    public ResponseEntity<?> videoYorumlariniGetir(@PathVariable Long videoId) {
        List<Yorum> yorumlar = yorumServisi.videoYorumlariniGetir(videoId);
        return ResponseEntity.ok(yorumlar);
    }

}

