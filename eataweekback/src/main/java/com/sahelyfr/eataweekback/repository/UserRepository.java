package com.sahelyfr.eataweekback.repository;

import com.sahelyfr.eataweekback.model.User;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
  Optional<User> findByEmail(String email);

}
