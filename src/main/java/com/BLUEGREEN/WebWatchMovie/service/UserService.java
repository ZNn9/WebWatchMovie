package com.BLUEGREEN.WebWatchMovie.service;

import com.BLUEGREEN.WebWatchMovie.model.Role;
import com.BLUEGREEN.WebWatchMovie.model.User;
import com.BLUEGREEN.WebWatchMovie.model.UserRoles;
import com.BLUEGREEN.WebWatchMovie.repository.RoleRepository;
import com.BLUEGREEN.WebWatchMovie.repository.UserRepository;
import com.BLUEGREEN.WebWatchMovie.repository.UserRolesRepository;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<User> findAll() {
        return userRepository.findAll();
    }

//    public User registerUser(User user, int roleId) {
//        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
//        User registeredUser = userRepository.save(user);
//
//        Optional<Role> role = roleRepository.findById(roleId);
//        if (role.isPresent()) {
//            UserRoles userRole = new UserRoles(registeredUser, role.get());
//            userRolesRepository.save(userRole);
//        } else {
//            throw new RuntimeException("Role not found");
//        }
//        return registeredUser;
//    }

    public User registerUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
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
}
