package com.hw.taskmanager.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hw.taskmanager.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Date dateTime;

    @JsonIgnore
    @ManyToOne
    private User user;

}
