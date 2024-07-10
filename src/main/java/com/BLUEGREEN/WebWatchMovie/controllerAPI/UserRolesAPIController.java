package com.BLUEGREEN.WebWatchMovie.controllerAPI;

import com.BLUEGREEN.WebWatchMovie.model.UserRoles;
import com.BLUEGREEN.WebWatchMovie.model.UserRolesId;
import com.BLUEGREEN.WebWatchMovie.service.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-roles")
public class UserRolesAPIController {

    @Autowired
    private UserRolesService userRolesService;

    @GetMapping
    public List<UserRoles> getAllUserRoles() {
        return userRolesService.findAll();
    }

    @GetMapping("/{userId}/{roleId}")
    public ResponseEntity<UserRoles> getUserRolesById(@PathVariable int userId, @PathVariable int roleId) {
        UserRolesId id = new UserRolesId(userId, roleId);
        Optional<UserRoles> userRoles = userRolesService.findById(id);
        return userRoles.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public UserRoles createUserRoles(@RequestBody UserRoles userRoles) {
        return userRolesService.save(userRoles);
    }

    @PutMapping("/{userId}/{roleId}")
    public ResponseEntity<UserRoles> updateUserRoles(@PathVariable int userId, @PathVariable int roleId, @RequestBody UserRoles updatedUserRoles) {
        UserRolesId id = new UserRolesId(userId, roleId);
        Optional<UserRoles> optionalUserRoles = userRolesService.findById(id);

        if (optionalUserRoles.isPresent()) {
            UserRoles existingUserRoles = optionalUserRoles.get();
            existingUserRoles.setUser(updatedUserRoles.getUser());
            existingUserRoles.setRole(updatedUserRoles.getRole());
            userRolesService.save(existingUserRoles);
            return ResponseEntity.ok(existingUserRoles);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}/{roleId}")
    public ResponseEntity<Void> deleteUserRoles(@PathVariable int userId, @PathVariable int roleId) {
        UserRolesId id = new UserRolesId(userId, roleId);
        if (userRolesService.findById(id).isPresent()) {
            userRolesService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
