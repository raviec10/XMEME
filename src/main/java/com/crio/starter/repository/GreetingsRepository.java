package com.crio.starter.repository;

import com.crio.starter.data.GreetingsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GreetingsRepository extends MongoRepository<GreetingsEntity, String> {

    List<GreetingsEntity> findTop100ByOrderByIdDesc();
    boolean existsByNameAndUrlAndCaption(String name, String url, String caption);
  // GreetingsEntity findByExtId(String extId);
}
