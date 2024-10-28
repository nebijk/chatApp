package com.example.chatApp.demo.repository;


import com.example.chatApp.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
        Optional<User> findByOauthId(String oauthId);

    }



