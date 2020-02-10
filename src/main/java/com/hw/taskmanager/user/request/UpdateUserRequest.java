package com.hw.taskmanager.user.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@NotNull
@AllArgsConstructor
public class UpdateUserRequest {

    @NotNull
    @Length(min = 1, max = 20)
    String firstName;

    @NotNull
    @Length(min = 1, max = 20)
    String lastName;
}
