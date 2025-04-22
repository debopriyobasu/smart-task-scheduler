package in.co.debo.smarttaskscheduler.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.co.debo.smarttaskscheduler.model.Task;
import in.co.debo.smarttaskscheduler.model.User;
import in.co.debo.smarttaskscheduler.repository.TaskRepository;
import in.co.debo.smarttaskscheduler.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public Task createTaskForUser(Long userId, Task task) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        task.setUser(user);
        return taskRepository.save(task);
    }

    public List<Task> getTasksForUser(Long userId) {
        return taskRepository.findByUserUserId(userId);
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));
    }

    public Task updateTask(Long taskId, Task updatedTask) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setPriority(updatedTask.getPriority());
        existingTask.setDeadline(updatedTask.getDeadline());
        existingTask.setEffortHours(updatedTask.getEffortHours());
        existingTask.setStatus(updatedTask.getStatus());
        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new EntityNotFoundException("Task not found with id: " + taskId);
        }
        taskRepository.deleteById(taskId);
    }
}
