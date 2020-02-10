package com.hw.taskmanager.task;

import com.hw.taskmanager.exception.NotFoundException;
import com.hw.taskmanager.task.request.CreateTaskRequest;
import com.hw.taskmanager.task.request.UpdateTaskRequest;
import com.hw.taskmanager.user.User;
import com.hw.taskmanager.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private TaskRepository taskRepository;
    private UserService userService;

    public Task getTaskForUser(Long userId, Long taskId) {
        return taskRepository.findByUserIdAndId(userId, taskId).orElseThrow(NotFoundException::new);
    }

    public List<Task> getAllTasksForUser(Long userId) {
        return userService.getUserById(userId).getTasks();
    }

    public Task createTask(Long userId, CreateTaskRequest request) {
        User user = userService.getUserById(userId);

        Task task = Task.builder().name(request.getName())
                .description(request.getDescription())
                .dateTime(request.getDateTime())
                .user(user)
                .build();

        return taskRepository.save(task);
    }

    public Task updateTask(Long userId, Long taskId, UpdateTaskRequest request) {
        Task task = getTaskForUser(userId, taskId);
        task.setDescription(request.getDescription());
        task.setName(request.getName());
        return taskRepository.save(task);
    }

    public void deleteTaskById(Long userId, Long taskId) {
        Task task = getTaskForUser(userId, taskId);
        taskRepository.delete(task);
    }

}
