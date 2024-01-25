package com.pockocmoc.task_manager.controller;

import com.pockocmoc.task_manager.model.Task;
import com.pockocmoc.task_manager.model.TaskStatus;
import com.pockocmoc.task_manager.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping()
    public List<Task> getAllBook() {
        return taskService.getAllTasks();
    }

    @PostMapping("/add")
    public Task addTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status) {
        return taskService.getTasksByStatus(status);
    }

    @PutMapping("/update/{id}")
    public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTaskStatus(id, task);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }


}
