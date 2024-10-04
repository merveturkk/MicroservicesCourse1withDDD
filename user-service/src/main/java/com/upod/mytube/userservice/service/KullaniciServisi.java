package com.upod.mytube.userservice.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.upod.mytube.userservice.domain.Email;
import com.upod.mytube.userservice.domain.Izin;
import com.upod.mytube.userservice.domain.Kullanici;
import com.upod.mytube.userservice.domain.ProfilBilgileri;
import com.upod.mytube.userservice.domain.Rol;
import com.upod.mytube.userservice.domain.Sifre;
import com.upod.mytube.userservice.repository.KullaniciEntity;
import com.upod.mytube.userservice.repository.KullaniciRepository;

@Service
public class KullaniciServisi {
    
    private final KullaniciRepository kullaniciRepository;

    public KullaniciServisi(KullaniciRepository kullaniciRepository) {
        this.kullaniciRepository = kullaniciRepository;
    }

    public long kullaniciKaydet(String email, String sifre, String ad, String soyad) {
        Email emailObj = new Email(email);
        Sifre sifreObj = new Sifre(sifre);
        ProfilBilgileri profilBilgileri = new ProfilBilgileri(ad, soyad, null);

        Kullanici kullanici = new Kullanici(null, emailObj, sifreObj, profilBilgileri);
        kullanici.rolEkle(new Rol(null,"KULLANICI", Set.of(Izin.VIDEO_YUKLE, Izin.YORUM_YAP)));

        KullaniciEntity yeniKulllanici = kullaniciRepository.save(KullaniciEntity.fromDomain(kullanici));

        return yeniKulllanici.getId();
    }

    public Optional<Kullanici> girisYap(String email, String sifre) {
        Optional<KullaniciEntity> kullaniciOpt = kullaniciRepository.findByEmail(new Email(email));
        if (kullaniciOpt.isPresent()) {
            Kullanici kullanici = kullaniciOpt.get().toDomain();
            if (kullanici.sifreDogrula(sifre)) {
                return Optional.of(kullanici);
            } 
        }
        return Optional.empty();
    }

    // Diğer iş mantığı metodları
}
