package in.co.debo.smarttaskscheduler.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import in.co.debo.smarttaskscheduler.model.Task;
import in.co.debo.smarttaskscheduler.service.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/users/{userId}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@PathVariable Long userId, @Valid @RequestBody Task task) {
        return taskService.createTaskForUser(userId, task);
    }

    @GetMapping("/users/{userId}/tasks")
    public List<Task> getTasksForUser(@PathVariable Long userId) {
        return taskService.getTasksForUser(userId);
    }

    @GetMapping("/tasks/{taskId}")
    public Task getTaskById(@PathVariable Long taskId) {
        return taskService.getTaskById(taskId);
    }

    @PutMapping("/tasks/{taskId}")
    public Task updateTask(@PathVariable Long taskId, @Valid @RequestBody Task task) {
        return taskService.updateTask(taskId, task);
    }

    @DeleteMapping("/tasks/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }
}
