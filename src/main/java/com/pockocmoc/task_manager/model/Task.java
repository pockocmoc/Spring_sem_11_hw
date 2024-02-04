package com.pockocmoc.task_manager.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Модель задачи.
 */
@Data
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @Column(name = "creation_time", nullable = true)
    private LocalDateTime creationTime;

    /**
     * Метод для установки времени создания задачи перед сохранением в базу данных.
     */
    @PrePersist
    public void setCreationTime() {
        this.creationTime = LocalDateTime.now();
    }

}
