package com.pockocmoc.task_manager.service;

import com.pockocmoc.task_manager.annotation.TrackUserAction;
import com.pockocmoc.task_manager.model.Task;
import com.pockocmoc.task_manager.model.TaskStatus;
import com.pockocmoc.task_manager.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с задачами.
 */
@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    /**
     * Получить все задачи.
     *
     * @return список всех задач
     */
    @TrackUserAction
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    /**
     * Получить задачу по идентификатору.
     *
     * @param id идентификатор задачи
     * @return найденная задача или пустое значение, если задача не найдена
     */
    @TrackUserAction
    public Optional<Task> getTaskById(Long id) {
        return repository.findById(id);
    }

    /**
     * Создать новую задачу.
     *
     * @param task данные новой задачи
     * @return созданная задача
     */
    @TrackUserAction
    public Task createTask(Task task) {
        return repository.save(task);
    }

    /**
     * Получить список задач по статусу.
     *
     * @param status статус задачи
     * @return список задач с указанным статусом
     */
    @TrackUserAction
    public List<Task> getTasksByStatus(TaskStatus status) {
        return repository.findByStatus(status);
    }


    /**
     * Обновить статус задачи.
     *
     * @param id          идентификатор задачи
     * @param taskDetails новые данные задачи
     * @return обновленная задача
     * @throws IllegalArgumentException если задача с указанным идентификатором не найдена
     */
    @TrackUserAction
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

    /**
     * Удалить задачу по идентификатору.
     *
     * @param id идентификатор задачи
     */
    @TrackUserAction
    public void deleteTask(Long id) {
        repository.deleteById(id);
    }
}
