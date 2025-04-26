package dev.batist.ReadHub.user.mapper;

import dev.batist.ReadHub.user.model.User;
import dev.batist.ReadHub.user.request.UserRequest;
import dev.batist.ReadHub.user.response.UserResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User toUser(UserRequest request){
        return User.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .build();
    }

    public static UserResponse toUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

}
