package com.pockocmoc.task_manager.repository;

import com.pockocmoc.task_manager.model.Task;
import com.pockocmoc.task_manager.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status);
}
