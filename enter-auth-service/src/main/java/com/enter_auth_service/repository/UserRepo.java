package com.enter_auth_service.repository;

import com.enter_auth_service.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // SQL QUERY USE FOR FINDING USER BY EMAIL
}
