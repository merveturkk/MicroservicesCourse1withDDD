package com.upod.mytube.subscription_service.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.upod.mytube.subscription_service.domain.Abonelik;
import com.upod.mytube.subscription_service.service.AbonelikServisi;

@RestController
@RequestMapping("/abonelikler")
public class AbonelikController {
    private final AbonelikServisi abonelikServisi;

    public AbonelikController(AbonelikServisi abonelikServisi) {
        this.abonelikServisi = abonelikServisi;
    }

    @PostMapping("/abone-ol")
    public ResponseEntity<?> aboneOl(
            @RequestParam("kanalId") Long kanalId,
            @RequestParam("kullaniciId") Long kullaniciId) {
        Long abonelikId = abonelikServisi.aboneOl(kullaniciId, kanalId);
        return ResponseEntity.ok(Collections.singletonMap("abonelikId", abonelikId));
    }

    @PostMapping("/abonelik-iptal")
    public ResponseEntity<?> abonelikIptalEt(
            @RequestParam("kanalId") Long kanalId,
            @RequestParam("kullaniciId") Long kullaniciId) {
        abonelikServisi.abonelikIptalEt(kullaniciId, kanalId);
        return ResponseEntity.ok("Abonelik iptal edildi");
    }

    @GetMapping("/kullanici/aboneliklerim")
    public ResponseEntity<?> aboneliklerim(@RequestParam("kullaniciId") Long kullaniciId) {
        List<Abonelik> abonelikler = abonelikServisi.kullanicininAbonelikleri(kullaniciId);
        return ResponseEntity.ok(abonelikler);
    }

}
