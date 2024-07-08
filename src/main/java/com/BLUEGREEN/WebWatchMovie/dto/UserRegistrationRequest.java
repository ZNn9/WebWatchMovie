package com.BLUEGREEN.WebWatchMovie.dto;

import com.BLUEGREEN.WebWatchMovie.model.User;
import lombok.Data;

@Data
public class UserRegistrationRequest {
    private User user;
    private int[] roleIds;
}
