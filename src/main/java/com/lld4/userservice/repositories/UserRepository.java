package com.lld4.userservice.repositories;

import com.lld4.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, java.lang.Long> {

   public Optional<User> findByEmail(String email);
}
