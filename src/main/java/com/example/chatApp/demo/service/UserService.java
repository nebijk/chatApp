package com.example.chatApp.demo.service;

import com.example.chatApp.demo.Entity.User;
import com.example.chatApp.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUserByOauthId(String oauthId) {
        return userRepository.findByOauthId(oauthId);
    }

    public User getOrCreateUser(OAuth2User oAuthUser) {
        String oauthId = oAuthUser.getName();
        String username = oAuthUser.getAttribute("login"); // Adjust attribute name as needed

        return findUserByOauthId(oauthId).orElseGet(() -> {
            User newUser = new User();
            newUser.setOauthId(oauthId);
            newUser.setUsername(username);
            return saveUser(newUser);
        });
    }
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}