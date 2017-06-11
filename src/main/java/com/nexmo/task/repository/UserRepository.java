package com.nexmo.task.repository;

import com.nexmo.task.domain.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<AppUser, Long> {
}
