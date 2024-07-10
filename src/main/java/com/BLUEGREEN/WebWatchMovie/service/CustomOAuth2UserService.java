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

import java.util.Optional;
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
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            // Người dùng đã tồn tại, cập nhật thông tin nếu cần
            User user = existingUser.get();
            user.setName(oAuth2User.getAttribute("name"));
            user.setNameLogin(oAuth2User.getAttribute("sub"));
            userRepository.save(user);
        } else {
            // Người dùng chưa tồn tại, tạo mới người dùng và lưu vào cơ sở dữ liệu
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName(oAuth2User.getAttribute("name"));
            newUser.setNameLogin(oAuth2User.getAttribute("sub"));
            newUser.setPassword(new BCryptPasswordEncoder().encode(this.generateRandomPassword())); // Set an empty password for OAuth users
            userRepository.save(newUser);
            // Thực hiện các hành động khác, ví dụ như gán quyền cho người dùng
            userService.editUserRole(newUser.getIdUser(), new int[]{1});
        }
        return oAuth2User;
    }

    private String generateRandomPassword() {
        // Logic để tạo mật khẩu ngẫu nhiên (ví dụ)
        return UUID.randomUUID().toString().substring(0, 8); // Tạo một chuỗi ngẫu nhiên
    }
}
