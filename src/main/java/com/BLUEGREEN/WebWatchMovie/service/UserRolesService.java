package com.BLUEGREEN.WebWatchMovie.service;

import com.BLUEGREEN.WebWatchMovie.model.UserRoles;
import com.BLUEGREEN.WebWatchMovie.model.UserRolesId;
import com.BLUEGREEN.WebWatchMovie.repository.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRolesService {

    @Autowired
    private UserRolesRepository userRolesRepository;

    public List<UserRoles> findAll() {
        return userRolesRepository.findAll();
    }

    public Optional<UserRoles> findById(UserRolesId id) {
        return userRolesRepository.findById(id);
    }

    public UserRoles save(UserRoles userRoles) {
        return userRolesRepository.save(userRoles);
    }

    public void deleteById(UserRolesId id) {
        userRolesRepository.deleteById(id);
    }
}
