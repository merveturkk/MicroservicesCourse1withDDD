package com.upod.mytube.subscription_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface AbonelikRepository extends CrudRepository<AbonelikEntity,Long>{

    Optional<AbonelikEntity> findByKullaniciIdAndKanalId(Long kullaniciId, Long kanalId);

    List<AbonelikEntity> findByKullaniciId(Long kullaniciId);

}
