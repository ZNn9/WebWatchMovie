package com.BLUEGREEN.WebWatchMovie.model;

import jakarta.persistence.*;
import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Getter
@Setter
@Table(name = "UserRoles")

public class UserRoles {

    @EmbeddedId
    private UserRolesId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("user")
    @JoinColumn(name = "idUser", nullable = false)
    private User user;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("role")
    @JoinColumn(name = "idRole", nullable = false)
    private Role role;
}
