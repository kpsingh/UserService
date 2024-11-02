package com.lld4.userservice.repositories;

import com.lld4.userservice.models.Long;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Long, java.lang.Long> {

   public Optional<Long> findByEmail(String email);
}
