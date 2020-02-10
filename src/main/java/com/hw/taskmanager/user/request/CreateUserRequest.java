package com.hw.taskmanager.user.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@NotNull
@AllArgsConstructor
public class CreateUserRequest {

    @NotNull
    @Length(min = 1, max = 20)
    String username;

    @NotNull
    @Length(min = 1, max = 100)
    String firstName;

    @NotNull
    @Length(min = 1, max = 100)
    String lastName;
}
