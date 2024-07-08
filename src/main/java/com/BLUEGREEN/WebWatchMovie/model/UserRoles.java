package com.BLUEGREEN.WebWatchMovie.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import java.util.Objects;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "UserRoles")

public class UserRoles {

    @EmbeddedId
    private UserRolesId id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("user")
    @JoinColumn(name = "id_User", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("role")
    @JoinColumn(name = "id_Role", nullable = false)
    private Role role;

    public UserRoles(User user, Role role) {
        this.user = user;
        this.role = role;
        this.id = new UserRolesId(user.getIdUser(), role.getIdRole());
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserRoles that = (UserRoles) o;
//        return user == that.user && role == that.role;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(user, role);
//    }
}
