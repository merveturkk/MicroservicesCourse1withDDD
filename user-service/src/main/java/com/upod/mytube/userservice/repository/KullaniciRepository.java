package com.upod.mytube.userservice.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.upod.mytube.userservice.domain.Email;

@Repository
public interface KullaniciRepository extends CrudRepository<KullaniciEntity, Long>{

    public Optional<KullaniciEntity> findByEmail(Email email);

}
