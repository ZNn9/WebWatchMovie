package com.BLUEGREEN.WebWatchMovie.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Getter
@Setter
@Table(name = "UserRoles")
@IdClass(UserRolesId.class)
public class UserRoles implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idUser")
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idRole")
    private Role role;
}
