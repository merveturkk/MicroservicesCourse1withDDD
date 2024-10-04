package com.upod.mytube.video_service.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;

@Service
public class VideoDepolamaServisi {
    
    private final Path videoDepolamaDizini = Paths.get("videolar");


    public void videoDosyasiniDepola(Long videoId, InputStream videoStream) {
        try {
            Files.createDirectories(videoDepolamaDizini);
            Path dosyaYolu = videoDepolamaDizini.resolve(videoId.toString() + ".mp4");
            Files.copy(videoStream, dosyaYolu, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Video dosyası depolanamadı", e);
        }
    }

    public InputStream videoDosyasiniGetir(Long videoId) {
        try {
            Path dosyaYolu = videoDepolamaDizini.resolve(videoId.toString() + ".mp4");
            return Files.newInputStream(dosyaYolu);
        } catch (IOException e) {
            throw new RuntimeException("Video dosyası bulunamadı", e);
        }
    }

    public void videoDosyasiniSil(Long videoId) {
        try {
            Path dosyaYolu = videoDepolamaDizini.resolve(videoId.toString() + ".mp4");
            Files.deleteIfExists(dosyaYolu);
        } catch (IOException e) {
            throw new RuntimeException("Video dosyası silinemedi", e);
        }
    }
}
