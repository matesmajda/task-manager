package com.hw.taskmanager.task.request;

import com.hw.taskmanager.task.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@NotNull
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskRequest {

    @Length(min = 1, max = 100)
    private String name;

    @Length(min = 1, max = 100)
    private String description;

    private TaskStatus status;
}
