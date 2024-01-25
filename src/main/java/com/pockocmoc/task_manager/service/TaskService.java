package com.pockocmoc.task_manager.service;

import com.pockocmoc.task_manager.model.Task;
import com.pockocmoc.task_manager.model.TaskStatus;
import com.pockocmoc.task_manager.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return repository.findById(id);
    }

    public Task createTask(Task task) {
        return repository.save(task);
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
        return repository.findByStatus(status);
    }


    public Task updateTaskStatus(Long id, Task taskDetails) {
        Optional<Task> optionalTask = repository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(taskDetails.getStatus());
            return repository.save(task);
        } else {
            throw new IllegalArgumentException("Task not found with id: " + id);
        }
    }

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }
}
