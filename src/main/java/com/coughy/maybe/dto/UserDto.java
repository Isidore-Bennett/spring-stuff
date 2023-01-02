package com.coughy.maybe.dto;

import com.coughy.maybe.entity.Product;
import com.coughy.maybe.entity.User;
import com.coughy.maybe.entity.UserProfile;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserDto implements Serializable {

    private String id;
    private List<String> productNames;
    private String userProfileName;

    public UserDto(String id, Product product, UserProfile userProfile) {
        this.id = id;
        this.userProfileName = userProfile.getTitle();
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.userProfileName = user.getUserProfile().getTitle();
        this.productNames = user
                .getUserOrders()
                .stream()
                .map(userOrder -> userOrder.getProduct().getTitle())
                .collect(Collectors.toList());
    }
}
