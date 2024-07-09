package com.BLUEGREEN.WebWatchMovie.repository;

import com.BLUEGREEN.WebWatchMovie.model.UserRoles;
import com.BLUEGREEN.WebWatchMovie.model.UserRolesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, UserRolesId> {
    void deleteById_User(int userId);
}
