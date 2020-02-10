package com.hw.taskmanager.task;

import com.hw.taskmanager.exception.NotFoundException;
import com.hw.taskmanager.task.request.CreateTaskRequest;
import com.hw.taskmanager.task.request.UpdateTaskRequest;
import com.hw.taskmanager.user.User;
import com.hw.taskmanager.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    private TaskService taskService;
    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserService userService;

    @BeforeEach
    void setup() {
        taskService = new TaskService(taskRepository, userService);
    }

    @Test
    void getTaskForUserReturnsTask() {
        long id = 1L;
        long userId = 2L;
        Task task = Task.builder().id(id).build();
        when(taskRepository.findByUserIdAndId(userId, id)).thenReturn(Optional.of(task));

        Task result = taskService.getTaskForUser(userId, id);
        assertEquals(task, result);
    }

    @Test
    void getTaskForUserThrowsIfNotFound() {
        when(taskRepository.findByUserIdAndId(1L, 1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> taskService.getTaskForUser(1L, 1L));
    }

    @Test
    void getAllForUserReturnsTasks() {
        long userId = 2L;
        List<Task> tasks = singletonList(Task.builder().build());
        User user = User.builder().id(userId).tasks(tasks).build();
        when(userService.getUserById(userId)).thenReturn(user);

        List<Task> result = taskService.getAllTasksForUser(userId);
        assertEquals(tasks, result);
    }

    @Test
    void createTaskSavesTask() {
        long userId = 2L;
        User user = User.builder().id(userId).build();
        Date date= new Date();
        Task task = Task.builder().name("n").description("d").dateTime(date).user(user).build();
        CreateTaskRequest request = new CreateTaskRequest("n", "d", date);

        when(userService.getUserById(userId)).thenReturn(user);

        taskService.createTask(userId, request);
        verify(taskRepository).save(task);
    }

    @Test
    void createTaskThrowsIfUserNotFound() {
        when(userService.getUserById(1L)).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> taskService.createTask(1L, new CreateTaskRequest()));
    }

    @Test
    void updateTaskChangesTask() {
        long userId = 2L;
        long taskId = 1L;
        Task task = Task.builder().name("n").description("d").build();

        Task updatedTask = Task.builder().name("n2").description("d2").build();
        UpdateTaskRequest request = new UpdateTaskRequest("n2", "d2");

        when(taskRepository.findByUserIdAndId(userId, taskId)).thenReturn(Optional.of(task));

        taskService.updateTask(userId, taskId, request);
        verify(taskRepository).save(updatedTask);
    }

    @Test
    void updateTaskThrowsIfNotFound() {
        when(taskRepository.findByUserIdAndId(1L, 1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> taskService.updateTask(1L, 1L, new UpdateTaskRequest()));
    }

    @Test
    void deleteTaskForUserCallsDelete() {
        long id = 1L;
        long userId = 2L;
        Task task = Task.builder().id(id).build();
        when(taskRepository.findByUserIdAndId(userId, id)).thenReturn(Optional.of(task));

        taskService.deleteTaskById(userId, id);
        verify(taskRepository).delete(task);
    }

    @Test
    void deleteTaskForUserThrowsIfNotFound() {
        when(taskRepository.findByUserIdAndId(1L, 1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> taskService.deleteTaskById(1L, 1L));
    }

}