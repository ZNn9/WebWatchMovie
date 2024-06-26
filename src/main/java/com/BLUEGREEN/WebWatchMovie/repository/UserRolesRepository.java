package com.BLUEGREEN.WebWatchMovie.repository;

import com.BLUEGREEN.WebWatchMovie.model.Role;
import com.BLUEGREEN.WebWatchMovie.model.User;
import com.BLUEGREEN.WebWatchMovie.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {

    // Find all roles associated with a specific user
    List<UserRoles> findByUser(User user);

    // Find all users with a specific role
    List<UserRoles> findByRole(Role role);

    // You can add other custom methods here (optional)

}
