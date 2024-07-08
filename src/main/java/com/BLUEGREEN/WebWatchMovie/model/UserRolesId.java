package com.BLUEGREEN.WebWatchMovie.model;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

//@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@Data
@Embeddable
public class UserRolesId implements Serializable {

    @Column(name = "id_User")
    private int user;

    @Column(name = "id_Role")
    private int role;

    public UserRolesId() {
        // Constructor mặc định (có thể để trống)
    }

    public UserRolesId(int user, int role) {
        this.user = user;
        this.role = role;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserRolesId that = (UserRolesId) o;
//        return user == that.user && role == that.role;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(user, role);
//    }
}
