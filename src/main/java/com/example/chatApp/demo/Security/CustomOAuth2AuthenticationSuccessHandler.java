package com.example.chatApp.demo.Security;

import com.example.chatApp.demo.Entity.User;
import com.example.chatApp.demo.repository.UserRepository;
import com.example.chatApp.demo.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomOAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserService userService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuthUser = (OAuth2User) authentication.getPrincipal();
        System.out.println("something");
        User user = userService.getOrCreateUser(oAuthUser);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
