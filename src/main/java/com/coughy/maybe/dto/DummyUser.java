package com.coughy.maybe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DummyUser {
    private String firstName;
    private String lastName;
    private String userName;

    public void setUserName(DummyUser dummyUser) {
        this.userName = dummyUser.getFirstName() + dummyUser.getLastName();
    }
}
