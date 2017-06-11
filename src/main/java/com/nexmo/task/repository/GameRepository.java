package com.nexmo.task.repository;

import com.nexmo.task.domain.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game,Long> {
}
