package com.esmabeydili.repository;

import com.esmabeydili.model.Il;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IlRepository extends MongoRepository<Il,String> {//Database e bağlanma
   Optional<Il> findByName(String name);
    List<Il> findAllByName(String name);
}
