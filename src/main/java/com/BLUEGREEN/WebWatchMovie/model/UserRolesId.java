package com.BLUEGREEN.WebWatchMovie.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter

public class UserRolesId implements Serializable {
    private int user;
    private int role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRolesId that = (UserRolesId) o;
        return user == that.user && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, role);
    }
}
