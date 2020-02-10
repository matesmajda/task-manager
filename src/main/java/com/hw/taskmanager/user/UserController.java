package com.hw.taskmanager.user;

import com.hw.taskmanager.user.request.CreateUserRequest;
import com.hw.taskmanager.user.request.UpdateUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @GetMapping
    List<User> getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    User createUser(@RequestBody @Valid CreateUserRequest request) {
        return userService.createUser(request);
    }

    @PutMapping(path = "/{id}")
    User updateUser(@PathVariable("id") Long id, @RequestBody @Valid UpdateUserRequest request) {
        return userService.updateUser(id, request);
    }

}
