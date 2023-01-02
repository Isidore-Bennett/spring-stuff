package com.coughy.maybe.dto;

import com.coughy.maybe.entity.UserProfile;

import java.io.Serializable;

public class UserOrderDto implements Serializable {

    private String id;
    private String fullName;
    private String userProfileName;

    public UserOrderDto(String id, String firstName, String lastName, UserProfile userProfile) {
        this.id = id;
        this.fullName =  firstName + " " + lastName;
        this.userProfileName = userProfile.getTitle();
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUserProfileName() {
        return userProfileName;
    }
}
