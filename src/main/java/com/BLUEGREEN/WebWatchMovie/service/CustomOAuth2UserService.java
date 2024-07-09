package com.BLUEGREEN.WebWatchMovie.service;

import com.BLUEGREEN.WebWatchMovie.model.User;
import com.BLUEGREEN.WebWatchMovie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");

        // Lưu hoặc cập nhật thông tin người dùng vào cơ sở dữ liệu
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setName(oAuth2User.getAttribute("name"));
            user.setNameLogin(oAuth2User.getAttribute("sub"));

            user.setPassword(new BCryptPasswordEncoder().encode(this.generateRandomPassword())); // Set an empty password for OAuth users
            userRepository.save(user);
        }
        userService.editUserRole(user.getIdUser(), new int[]{1});
        return oAuth2User;
    }

    private String generateRandomPassword() {
        // Logic to generate a random password (example)
        return UUID.randomUUID().toString().substring(0, 8); // Generate a random string
    }
}
