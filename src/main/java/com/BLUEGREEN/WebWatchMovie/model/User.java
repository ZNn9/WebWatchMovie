package com.BLUEGREEN.WebWatchMovie.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.apache.el.parser.BooleanNode;
import org.hibernate.Hibernate;

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

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;

    @Column(nullable = false, length = 50)
    private String nameLogin;

    @NotBlank
    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false)
    private Boolean isHidden;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserRoles> roles = new HashSet<>();

}
