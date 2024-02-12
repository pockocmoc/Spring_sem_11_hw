package com.pockocmoc.task_manager.controller;

import com.pockocmoc.task_manager.model.Task;
import com.pockocmoc.task_manager.model.TaskStatus;
import com.pockocmoc.task_manager.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    /**
     * Получение списка всех задач.
     *
     * @return список всех задач
     */
    @GetMapping()
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    /**
     * Получение задачи по ее идентификатору.
     *
     * @param id идентификатор задачи
     * @return задача с указанным идентификатором
     */
    @GetMapping("/{id}")
    public Optional<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    /**
     * Создание новой задачи.
     *
     * @param task новая задача
     * @return созданная задача
     */
    @PostMapping("/add")
    public Task addTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    /**
     * Получение списка задач по их статусу.
     *
     * @param status статус задачи
     * @return список задач с указанным статусом
     */
    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status) {
        return taskService.getTasksByStatus(status);
    }

    /**
     * Обновление статуса задачи.
     *
     * @param id   идентификатор задачи
     * @param task обновленная задача
     * @return обновленная задача
     */
    @PutMapping("/update/{id}")
    public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTaskStatus(id, task);
    }

    /**
     * Удаление задачи по ее идентификатору.
     *
     * @param id идентификатор задачи
     */
    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    /**
     * Обработка исключения IllegalArgumentException.
     *
     * @param ex исключение IllegalArgumentException
     * @return ответ с ошибкой 400 и сообщением об ошибке
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }


}
