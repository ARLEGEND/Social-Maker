package com.api.socialMaker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileUpdateRequest {
    private String fullName;
    private String bio;
    private String profilePictureUrl;
}
