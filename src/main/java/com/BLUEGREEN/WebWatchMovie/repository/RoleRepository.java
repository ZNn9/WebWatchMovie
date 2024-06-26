package com.BLUEGREEN.WebWatchMovie.repository;

import com.BLUEGREEN.WebWatchMovie.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleById(Long id);
}
