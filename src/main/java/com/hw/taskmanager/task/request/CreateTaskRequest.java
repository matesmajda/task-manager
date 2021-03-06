package com.hw.taskmanager.task.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NotNull
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskRequest {

    @NotNull
    @Length(min = 1, max = 100)
    private String name;
    @NotNull
    @Length(min = 1, max = 100)
    private String description;
    @NotNull
    private Date dateTime;
}
