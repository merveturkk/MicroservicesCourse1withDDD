package com.upod.mytube.video_service.domain;

// Video Başlığı Değer Nesnesi
public class VideoBasligi {
    private final String deger;

    public VideoBasligi(String deger) {
        if (deger == null || deger.isEmpty()) {
            throw new IllegalArgumentException("Video başlığı boş olamaz");
        }
        if (deger.length() > 100) {
            throw new IllegalArgumentException("Video başlığı 100 karakterden fazla olamaz");
        }
        this.deger = deger;
    }

    public String getDeger() {
        return deger;
    }
}
