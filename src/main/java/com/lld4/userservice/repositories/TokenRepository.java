package com.lld4.userservice.repositories;

import com.lld4.userservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByValueAndIsDeleted(String value, Boolean isDeleted);
}
