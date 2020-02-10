package com.hw.taskmanager.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hw.taskmanager.task.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String firstName;

    private String lastName;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Task> tasks;
}
