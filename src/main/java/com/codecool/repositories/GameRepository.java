package com.codecool.repositories;

import com.codecool.domain.Game;
import org.springframework.data.repository.CrudRepository;


public interface GameRepository extends CrudRepository<Game, Long> {
}
