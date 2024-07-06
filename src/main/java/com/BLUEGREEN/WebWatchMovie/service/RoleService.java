package com.BLUEGREEN.WebWatchMovie.service;

import com.BLUEGREEN.WebWatchMovie.model.Role;
import com.BLUEGREEN.WebWatchMovie.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Optional<Role> findById(int id) {
        return roleRepository.findById((long) id);
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public void deleteById(int id) {
        roleRepository.deleteById((long) id);
    }
}
