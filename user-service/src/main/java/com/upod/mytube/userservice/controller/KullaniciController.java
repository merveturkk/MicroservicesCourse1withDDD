package com.upod.mytube.userservice.controller;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upod.mytube.userservice.domain.Kullanici;
import com.upod.mytube.userservice.domain.Rol;
import com.upod.mytube.userservice.service.KullaniciServisi;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/kullanicilar")
public class KullaniciController {
    private final KullaniciServisi kullaniciServisi;

    public KullaniciController(KullaniciServisi kullaniciServisi) {
        this.kullaniciServisi = kullaniciServisi;
    }

    @PostMapping("/kayit")
    public ResponseEntity<?> kayitOl(@RequestBody KayitOlRequest request) {
        long kullaniciId = kullaniciServisi.kullaniciKaydet(
                request.getEmail(),
                request.getSifre(),
                request.getAd(),
                request.getSoyad()
        );
        return ResponseEntity.ok(Collections.singletonMap("kullaniciId", kullaniciId));
    }

    @PostMapping("/giris")
    public ResponseEntity<?> girisYap(@RequestBody GirisRequest request) {
        Optional<Kullanici> kullaniciOpt = kullaniciServisi.girisYap(request.getEmail(), request.getSifre());
        if (kullaniciOpt.isPresent()) {
            // JWT token olusturma ve dondurme
            String token = jwtTokenOlustur(kullaniciOpt.get());
            return ResponseEntity.ok(Collections.singletonMap("token", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Gecersiz e-posta veya sifre");
        }
    }

    private String jwtTokenOlustur(Kullanici kullanici) {
        // Kullanici bilgilerine gore JWT token olusturma
        // Ornegin, io.jsonwebtoken.Jwts kullanarak
        return Jwts.builder()
                .subject(String.valueOf(kullanici.getKullaniciId()))
                .claim("roller", kullanici.getRoller().stream().map(Rol::getIsim).collect(Collectors.toList()))
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                .signWith(SignatureAlgorithm.HS256, "gizliAnahtar")
                .compact();
    }
}

