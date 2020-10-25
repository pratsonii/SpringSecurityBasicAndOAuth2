package com.pratik.dto;

import com.pratik.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequestDto {

    private String email;

    private String firstName;

    private String lastName;

    private String password;

    public UserRequestDto(User user){
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }
}
