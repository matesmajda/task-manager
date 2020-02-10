package com.hw.taskmanager.user;

import com.hw.taskmanager.exception.BadRequestException;
import com.hw.taskmanager.exception.NotFoundException;
import com.hw.taskmanager.user.request.CreateUserRequest;
import com.hw.taskmanager.user.request.UpdateUserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setup() {
        userService = new UserService(userRepository);
    }

    @Test
    void getUserByIdReturnsUser() {
        long id = 1L;
        User user = User.builder().id(id).build();
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        User result = userService.getUserById(id);
        assertEquals(user, result);
    }

    @Test
    void getAllUsersByIdRetursUsers() {
        User user1 = User.builder().build();
        User user2 = User.builder().build();
        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();
        assertEquals(users, result);
    }

    @Test
    void getUserByIdThrowsIfNotExist() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.getUserById(1L));
    }

    @Test
    void createUserSavesUser() {
        User user = User.builder().username("u").firstName("f").lastName("l").build();
        CreateUserRequest request = new CreateUserRequest("u", "f", "l");

        userService.createUser(request);
        verify(userRepository).save(user);
    }

    @Test
    void createUserThrowsBadRequestIfUsernameExist() {
        CreateUserRequest request = new CreateUserRequest("u", "f", "l");
        when(userRepository.existsByUsername("u")).thenReturn(true);

        assertThrows(BadRequestException.class, () -> userService.createUser(request));
        verify(userRepository, never()).save(any());
    }

    @Test
    void updateUserChangesFields() {
        long id = 1L;
        User user = User.builder().id(id).build();
        User updatedUser = User.builder().id(id).firstName("f").lastName("l").build();

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        UpdateUserRequest request = new UpdateUserRequest("f", "l");

        userService.updateUser(id, request);

        verify(userRepository).save(updatedUser);
    }

    @Test
    void updateUserThrowsNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.updateUser(1L, new UpdateUserRequest("f", "l")));
        verify(userRepository, never()).save(any());
    }
}