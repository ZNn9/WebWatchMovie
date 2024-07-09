package com.BLUEGREEN.WebWatchMovie.service;

import com.BLUEGREEN.WebWatchMovie.model.Role;
import com.BLUEGREEN.WebWatchMovie.model.User;
import com.BLUEGREEN.WebWatchMovie.model.UserRoles;
import com.BLUEGREEN.WebWatchMovie.model.UserRolesId;
import com.BLUEGREEN.WebWatchMovie.repository.RoleRepository;
import com.BLUEGREEN.WebWatchMovie.repository.UserRepository;
import com.BLUEGREEN.WebWatchMovie.repository.UserRolesRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User registerUser(User user, int[] newRoleIds) {
        // Kiểm tra tính duy nhất của nameLogin
        Optional<User> existingUser = userRepository.findByNameLogin(user.getNameLogin());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Name login already exists: " + user.getNameLogin());
        }

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setIsHidden(false);
        userRepository.save(user);

        // Thêm các vai trò mới từ newRoleIds
        for (int roleId : newRoleIds) {
            // Kiểm tra xem role có tồn tại không
            Optional<Role> optionalRole = roleRepository.findById(roleId);
            if (optionalRole.isPresent()) {
                Role role = optionalRole.get();

                // Tạo mới UserRoles và lưu vào database
                UserRoles userRole = new UserRoles(user, role);
                userRolesRepository.save(userRole);
            } else {
                throw new RuntimeException("Role not found: " + roleId);
            }
        }
        // Đảm bảo rằng các thay đổi trong session được xóa bỏ
        entityManager.flush();
        entityManager.clear();

        return userRepository.save(user);
    }

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
            editUserRole(id, userDetails.getRoleIds());

            user.setIsHidden(userDetails.getIsHidden());

            if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
                user.setPassword(new BCryptPasswordEncoder().encode(userDetails.getPassword()));
            }
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            return userRepository.save(user);
        }
        return null;
    }

    @Transactional
    public User editUserRole(int userId, int[] newRoleIds) {
        // Tìm người dùng cần chỉnh sửa
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        // Xóa toàn bộ vai trò hiện tại của người dùng
        deleteUserRoles(user.getIdUser());

        // Thêm các vai trò mới từ newRoleIds
        for (int roleId : newRoleIds) {
            // Kiểm tra xem role có tồn tại không
            Optional<Role> optionalRole = roleRepository.findById(roleId);
            if (optionalRole.isPresent()) {
                Role role = optionalRole.get();

                // Tạo mới UserRoles và lưu vào database
                UserRoles userRole = new UserRoles(user, role);
                userRolesRepository.save(userRole);
            } else {
                throw new RuntimeException("Role not found: " + roleId);
            }
        }
        // Đảm bảo rằng các thay đổi trong session được xóa bỏ
        entityManager.flush();
        entityManager.clear();

        // Lưu lại thông tin người dùng sau khi chỉnh sửa
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUserRoles(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        user.clearUserRoles(userRolesRepository);
    }

    public boolean deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean hideUser(int id, boolean check) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setIsHidden(check);
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

    // Tìm kiếm người dùng dựa trên tên đăng nhập.
//    public Optional<User> findByUsername(String username) throws
//            UsernameNotFoundException {
//        return userRepository.findByUsername(username);
//    }

}
