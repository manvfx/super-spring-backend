package com.aryahamrah.app.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<AuthUser, String> {
    Optional<AuthUser> findByUsername(String username);
}