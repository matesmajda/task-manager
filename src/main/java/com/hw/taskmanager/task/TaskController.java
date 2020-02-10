package com.hw.taskmanager.task;

import com.hw.taskmanager.task.request.CreateTaskRequest;
import com.hw.taskmanager.task.request.UpdateTaskRequest;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping(path = "/user/{userId}/task")
public class TaskController {

    private TaskService taskService;

    @GetMapping("/{taskId}")
    public Task getTaskById(@PathVariable Long userId, @PathVariable Long taskId) {
        return taskService.getTaskForUser(userId, taskId);
    }

    @GetMapping("/")
    public List<Task> getTaskForUser(@PathVariable Long userId) {
        return taskService.getAllTasksForUser(userId);
    }

    @PostMapping("/")
    public Task createTask(@PathVariable Long userId, @RequestBody @Valid CreateTaskRequest request) {
        return taskService.createTask(userId, request);
    }

    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable Long userId, @PathVariable Long taskId, @RequestBody @Valid UpdateTaskRequest request) {
        return taskService.updateTask(userId, taskId, request);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long userId, @PathVariable Long taskId) {
        taskService.deleteTaskById(userId, taskId);
    }

}
