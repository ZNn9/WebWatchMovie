package com.BLUEGREEN.WebWatchMovie.service;

import com.BLUEGREEN.WebWatchMovie.model.*;
import com.BLUEGREEN.WebWatchMovie.repository.RoleRepository;
import com.BLUEGREEN.WebWatchMovie.repository.TokenRepository;
import com.BLUEGREEN.WebWatchMovie.repository.UserRepository;
import com.BLUEGREEN.WebWatchMovie.repository.UserRolesRepository;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import ognl.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public List<User> findAll() {
        return userRepository.findAll();
    }
    public void save(@NotNull User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }



//    public User registerUser(User user, int[] roles) {
//        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
//        user.setIsHidden(false);
//        return userRepository.save(user);
//    }

    public User registerUser(User user, int[] roleIds) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        User registeredUser = userRepository.save(user);

        for (int roleId : roleIds) {
            Optional<Role> role = roleRepository.findById(roleId);
            if (role.isPresent()) {
                UserRoles userRole = new UserRoles(new UserRolesId(registeredUser.getIdUser(), roleId), registeredUser, role.get());
                userRolesRepository.save(userRole);
            } else {
                throw new RuntimeException("Role not found: " + roleId);
            }
        }
        return registeredUser;
    }

//    public User loginUser(String nameLogin, String password) {
//        User user = userRepository.findByNameLogin(nameLogin);
//        if (user != null && new BCryptPasswordEncoder().matches(password, user.getPassword())) {
//            return user;
//        }
//        return null;
//    }

    public User loginUser(String nameLogin, String password) {
        Optional<User> optionalUser = userRepository.findByNameLogin(nameLogin);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (new BCryptPasswordEncoder().matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public User updateUser(int id, User userDetails) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setNameLogin(userDetails.getNameLogin());

            if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
                user.setPassword(new BCryptPasswordEncoder().encode(userDetails.getPassword()));
            }
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            return userRepository.save(user);
        }
        return null;
    }

    public boolean deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean hideUser(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setIsHidden(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByNameLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getNameLogin(), user.getPassword(), new ArrayList<>());
    }
    public String sendEmail(User user) {
        try {
            String resetLink = generateResetToken(user);

            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom("vtshinz2k3@gmail.com");// input the senders email ID
            msg.setTo(user.getEmail());

            msg.setSubject("Welcome To My Channel");
            msg.setText("Hello \n\n" + "Please click on this link to Reset your Password :" + resetLink + ". \n\n"
                    + "Regards \n" + "ABC");

            javaMailSender.send(msg);

            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }


    public String generateResetToken(User user) {
        UUID uuid = UUID.randomUUID();
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime expiryDateTime = currentDateTime.plusMinutes(30);
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setUser(user);
        resetToken.setToken(uuid.toString());
        resetToken.setExpiryDateTime(expiryDateTime);
        resetToken.setUser(user);
        PasswordResetToken token = tokenRepository.save(resetToken);
        if (token != null) {
            String endpointUrl = "http://localhost:8080/user/resetPassword";
            return endpointUrl + "/" + resetToken.getToken();
        }
        return "";
    }


    public boolean hasExipred(LocalDateTime expiryDateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return expiryDateTime.isAfter(currentDateTime);
    }
}
