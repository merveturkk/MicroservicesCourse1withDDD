package com.upod.mytube.comment_service.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface YorumRepository extends CrudRepository<YorumEntity, Long>{

    List<YorumEntity> findByVideoId(Long videoId);

}
