package com.upod.mytube.subscription_service.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.upod.mytube.subscription_service.domain.Abonelik;
import com.upod.mytube.subscription_service.domain.AbonelikTarihi;
import com.upod.mytube.subscription_service.repository.AbonelikEntity;
import com.upod.mytube.subscription_service.repository.AbonelikRepository;

@Service
public class AbonelikServisi {

    private final AbonelikRepository abonelikRepository;

    public AbonelikServisi(AbonelikRepository abonelikRepository) {
        this.abonelikRepository = abonelikRepository;
    }

    public Long aboneOl(Long kullaniciId, Long kanalId) {
        // Daha önce abonelik var mı kontrol et
        Optional<AbonelikEntity> mevcutAbonelik = abonelikRepository.findByKullaniciIdAndKanalId(kullaniciId, kanalId);
        if (mevcutAbonelik.isPresent()) {
            throw new RuntimeException("Kullanıcı zaten bu kanala abone");
        }
        AbonelikTarihi abonelikTarihi = new AbonelikTarihi(Instant.now());

        Abonelik abonelik = new Abonelik(null, kullaniciId, kanalId, abonelikTarihi);
        AbonelikEntity abonelikEntity = abonelikRepository.save(AbonelikEntity.fromDomain(abonelik));

        return abonelikEntity.getId();
    }

    public void abonelikIptalEt(Long kullaniciId, Long kanalId) {
        Optional<AbonelikEntity> abonelikOpt = abonelikRepository.findByKullaniciIdAndKanalId(kullaniciId, kanalId);
        if (abonelikOpt.isPresent()) {
            AbonelikEntity abonelikEntity = abonelikOpt.get();
            abonelikRepository.delete(abonelikEntity);
        } else {
            throw new RuntimeException("Abonelik bulunamadı");
        }
    }

    public List<Abonelik> kullanicininAbonelikleri(Long kullaniciId) {
        return abonelikRepository.findByKullaniciId(kullaniciId).stream()
            .map(AbonelikEntity::toDomain)
            .toList();
    }

}
