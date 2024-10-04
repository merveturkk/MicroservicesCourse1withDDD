package com.upod.mytube.comment_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.upod.mytube.comment_service.domain.Yorum;
import com.upod.mytube.comment_service.domain.YorumDurumu;
import com.upod.mytube.comment_service.domain.YorumMetni;
import com.upod.mytube.comment_service.repository.YorumEntity;
import com.upod.mytube.comment_service.repository.YorumRepository;

@Service
public class YorumServisi {

    private final YorumRepository yorumRepository;

    public YorumServisi(YorumRepository yorumRepository) {
        this.yorumRepository = yorumRepository;
    }

    public Long yorumEkle(Long videoId, Long yazarKullaniciId, String metin) {
        YorumMetni yorumMetni = new YorumMetni(metin);

        Yorum yorum = new Yorum(null, videoId, yazarKullaniciId, yorumMetni);
        YorumEntity yorumEntity = yorumRepository.save(YorumEntity.fromDomain(yorum));

        return yorumEntity.getId();
    }

    public Optional<Yorum> yorumBul(Long yorumId) {
        return yorumRepository.findById(yorumId).map(YorumEntity::toDomain);
    }

    public List<Yorum> videoYorumlariniGetir(Long videoId) {
        return yorumRepository.findByVideoId(videoId).stream()
            .map(YorumEntity::toDomain)
            .toList();
    }

    public void yorumSil(Long yorumId, Long talepEdenKullaniciId) {
        Optional<YorumEntity> yorumOpt = yorumRepository.findById(yorumId);
        if (yorumOpt.isPresent()) {
            Yorum yorum = yorumOpt.get().toDomain();
            if (yorum.getYazarKullaniciId().equals(talepEdenKullaniciId)) {
                yorum.durumDegistir(YorumDurumu.SILINDI);
                yorumRepository.save(YorumEntity.fromDomain(yorum));
            } else {
                throw new RuntimeException("Bu yorumu silme yetkiniz yok");
            }
        } else {
            throw new RuntimeException("Yorum bulunamadı");
        }
    }

}

