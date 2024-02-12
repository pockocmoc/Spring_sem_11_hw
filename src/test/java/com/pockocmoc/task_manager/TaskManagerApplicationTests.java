package com.pockocmoc.task_manager;

import com.pockocmoc.task_manager.model.Task;
import com.pockocmoc.task_manager.model.TaskStatus;
import com.pockocmoc.task_manager.repository.TaskRepository;
import com.pockocmoc.task_manager.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TaskManagerApplicationTests {

    @Mock
    private TaskRepository repository;

    @InjectMocks
    private TaskService service;

    private Task task1;
    private Task task2;
    private List<Task> tasks;

    @BeforeEach
    public void setUp() {
        task1 = new Task();
        task1.setId(1L);
        task1.setDescription("Task 1");
        task1.setStatus(TaskStatus.NOT_STARTED);

        task2 = new Task();
        task2.setId(2L);
        task2.setDescription("Task 2");
        task2.setStatus(TaskStatus.IN_PROGRESS);

        tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
    }

    @Test
    public void testGetAllTasks() {
        when(repository.findAll()).thenReturn(tasks);

        List<Task> result = service.getAllTasks();

        assertEquals(2, result.size());
        assertEquals(task1, result.get(0));
        assertEquals(task2, result.get(1));

        verify(repository, times(1)).findAll();
    }

    @Test
    public void testGetTaskById() {
        when(repository.findById(1L)).thenReturn(Optional.of(task1));

        Optional<Task> result = service.getTaskById(1L);

        assertTrue(result.isPresent());
        assertEquals(task1, result.get());

        verify(repository, times(1)).findById(1L);
    }

    @Test
    public void testGetTaskByIdNotFound() {
        when(repository.findById(3L)).thenReturn(Optional.empty());

        Optional<Task> result = service.getTaskById(3L);

        assertFalse(result.isPresent());

        verify(repository, times(1)).findById(3L);
    }

    @Test
    public void testCreateTask() {
        when(repository.save(task1)).thenReturn(task1);

        Task result = service.createTask(task1);

        assertEquals(task1, result);

        verify(repository, times(1)).save(task1);
    }

    @Test
    public void testGetTasksByStatus() {
        when(repository.findByStatus(TaskStatus.IN_PROGRESS)).thenReturn(List.of(task2));

        List<Task> result = service.getTasksByStatus(TaskStatus.IN_PROGRESS);
        assertEquals(1, result.size());
        assertEquals(task2, result.get(0));

        verify(repository, times(1)).findByStatus(TaskStatus.IN_PROGRESS);
    }

    @Test
    public void testUpdateTaskStatus() {
        Task updatedTask = new Task();
        updatedTask.setId(1L);
        updatedTask.setDescription("Task 1");
        updatedTask.setStatus(TaskStatus.COMPLETED);

        when(repository.findById(1L)).thenReturn(Optional.of(task1));
        when(repository.save(task1)).thenReturn(updatedTask);

        Task result = service.updateTaskStatus(1L, updatedTask);

        assertEquals(updatedTask, result);

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(task1);
    }

    @Test
    public void testUpdateTaskStatusNotFound() {
        Task updatedTask = new Task();
        updatedTask.setId(3L);
        updatedTask.setDescription("Task 3");
        updatedTask.setStatus(TaskStatus.COMPLETED);

        when(repository.findById(3L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            service.updateTaskStatus(3L, updatedTask);
        });

        verify(repository, times(1)).findById(3L);
        verify(repository, times(0)).save(updatedTask);
    }

    @Test
    public void testDeleteTask() {
        doNothing().when(repository).deleteById(1L);

        service.deleteTask(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}
