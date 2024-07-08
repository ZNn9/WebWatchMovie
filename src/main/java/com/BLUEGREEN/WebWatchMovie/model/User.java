package com.BLUEGREEN.WebWatchMovie.model;

import com.BLUEGREEN.WebWatchMovie.repository.UserRolesRepository;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Getter
@Setter
@Table(name = "user")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;

    @NotBlank(message = "Name login must not be blank")
    @Pattern(regexp = "^[a-z0-9]+$", message = "Name login must be in lowercase letters and numbers, without whitespace")
    @Column(nullable = false, unique = true, length = 50)
    private String nameLogin;

    @NotBlank
    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 50)
    private String name;

    @NotBlank(message = "email must not be blank")
    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false)
    private Boolean isHidden = false;

    @Transient
    private int[] roleIds;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIdentityReference(alwaysAsId = true)
    private List<UserRoles> roles;

    @Transactional
    public void clearUserRoles(UserRolesRepository userRolesRepository) {
        roles.forEach(userRole -> userRolesRepository.delete(userRole));
        roles.clear();
    }
}
