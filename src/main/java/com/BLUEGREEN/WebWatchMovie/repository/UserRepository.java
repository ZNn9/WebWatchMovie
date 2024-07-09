package com.BLUEGREEN.WebWatchMovie.repository;

import com.BLUEGREEN.WebWatchMovie.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
//    User findByNameLogin(String nameLogin);
    Optional<User> findByNameLogin(String nameLogin);
    User findByNameLoginAndPassword(String nameLogin, String password); // Đăng nhập bằng google
}
